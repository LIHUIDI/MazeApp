package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Player{
	GridMaze gridMaze;
	SquareCell previousCell;
	SquareCell cell;
	private BufferedImage image;
	ArrayList<PlayerMovementObserver> playerMovementObservers = new ArrayList<>();
	ArrayList<PlayerScoreObserver> playerScoreObservers = new ArrayList<>();

	private static final int INI_SCORE = 100;
	//move a step will minus 1 from player's score.
	private static final int MOVE_SCORE = -1;
	private int stepCost = MOVE_SCORE;
	private int score;
	private int numberOfStep = 0;
	private int numberOfGold = 0;
	private int numberOfTrap = 0;
	
	//indicate some movement done or cann't do.
	boolean done = false;
	private HashMap<String, String> playerImgs = new HashMap <>();
	private String playerImgPath = "resources/image/winterplayer.png"; //default cell background image.
	
	public Player(GridMaze gridMaze) {
		this.gridMaze = gridMaze;
		this.score = INI_SCORE;
		definePlayerImage();
	}
	
	public String getPlayerImgPath() {
		return playerImgPath;
	}

	public void setPlayerImgPath(String playerImgPath) {
		this.playerImgPath = playerImgPath;
	}

	private void definePlayerImage() {
		playerImgs.put("spring", "resources/image/springplayer.png");
		playerImgs.put("summer", "resources/image/summerplayer.png");
		playerImgs.put("autumn", "resources/image/autumnplayer.png");
		playerImgs.put("winter", "resources/image/winterplayer.png");
    }
	
	private void loadImage() {
	     try {                
	        image = ImageIO.read(new File(playerImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
	public void registerplayerMovementObservers(PlayerMovementObserver o) {
		playerMovementObservers.add(o);
	}
	
	public void removeplayerMovementObservers(PlayerMovementObserver o) {
		playerMovementObservers.remove(o);
	}
	
	public void registerplayerScoreObservers(PlayerScoreObserver o) {
		playerScoreObservers.add(o);
	}
	
	public void removeplayerScoreObservers(PlayerScoreObserver o) {
		playerScoreObservers.remove(o);
	}
	
	private void notifyAllPlayerMovementObervers(boolean done) {
		for (PlayerMovementObserver o : playerMovementObservers) {
			o.finishMovement(done);
		}
	}
	
	private void notifyAllPlayerScoreObervers(){
		for (PlayerScoreObserver o : playerScoreObservers) {
			o.updateScore();
		}
	}

	public void moveSelf(String direction) {
		if ((direction.equals(ActionData.MoveDirection.UP.toString()) && !this.getCurrentCell().hasTopWall())
			|| (direction.equals(ActionData.MoveDirection.DOWN.toString()) && !this.getCurrentCell().hasButtomWall())
			|| (direction.equals(ActionData.MoveDirection.LEFT.toString()) && (!this.getCurrentCell().hasLeftWall() && this.getCurrentCell().getX() != 0))
			|| (direction.equals(ActionData.MoveDirection.RIGHT.toString()) && (!this.getCurrentCell().hasRightWall() && this.getCurrentCell().getX()/this.getCurrentCell().getWidth() != gridMaze.getGrid().length - 1))) {
			//set previous cell to current cell
			this.previousCell = this.getCurrentCell();
			//collect item if old cell has item
			if ((previousCell instanceof SquareCellWithItem)) {
				if (((SquareCellWithItem) previousCell).hasItem()) {
					//update player's score based on the value of item of just visited cell
					changeScore(((SquareCellWithItem) previousCell).getItem().getValue());
					if (((SquareCellWithItem) previousCell).getItem() instanceof Skull) {
						numberOfTrap++;
					} else {
						numberOfGold++;
					}
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			//update current cell to next cell according to move direction.
			SquareCell newcell = null;
			if (direction.equals(ActionData.MoveDirection.UP.toString())) {
				newcell = gridMaze.getGrid()[previousCell.getX()/previousCell.getWidth()][(previousCell.getY()- previousCell.getWidth())/previousCell.getWidth()];
			} else if (direction.equals(ActionData.MoveDirection.DOWN.toString())) {
				newcell = gridMaze.getGrid()[(previousCell.getX())/previousCell.getWidth()][(previousCell.getY() + previousCell.getWidth())/previousCell.getWidth()];
			} else if (direction.equals(ActionData.MoveDirection.LEFT.toString())) {
				newcell = gridMaze.getGrid()[(previousCell.getX()- previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			} else if (direction.equals(ActionData.MoveDirection.RIGHT.toString())) {
				newcell = gridMaze.getGrid()[(previousCell.getX() + previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			} else {
				//shouldn't reach here
			}
			this.setCurrentCell(newcell);
			done = true;
			//since player moved a step, subtract score 1.
			changeScore(stepCost);
			numberOfStep++;
			notifyAllPlayerMovementObervers(done);
			done = false;
			
			//steps on chronorift, get teleported to new cell. while loop handles transfers from rift to rift
			while(this.cell instanceof SquareCellWithItem && ((SquareCellWithItem)(this.cell)).getItem() instanceof ChronoRift && !((ChronoRift)((SquareCellWithItem)(this.cell)).getItem()).getUsed()){
				
				ChronoRift rift=((ChronoRift)((SquareCellWithItem)(this.cell)).getItem());
				newcell = this.gridMaze.getGrid()[rift.getToPosX()][rift.getToPosY()];
				newcell.setX(rift.getToPosX()*cell.getWidth());
				newcell.setY(rift.getToPosY()*cell.getWidth());
				this.setCurrentCell(newcell);
				rift.setUsed(true);
				done = true;
				notifyAllPlayerMovementObervers(done);
				done = false;
			}
		} else {
			done = false;
			notifyAllPlayerMovementObervers(done);
		}
	}
	
	public void setCurrentCell(SquareCell cell) {
		this.cell = cell;
	}
	
	public SquareCell getCurrentCell() {
		return cell;
	}
	
	public SquareCell getPreviousCell() {
		return previousCell;
	}

	public void setPreviousCell(SquareCell previousCell) {
		this.previousCell = previousCell;
	}
	
	public void changePlayerImgPath(String themeName) {
		setPlayerImgPath(playerImgs.get(themeName));
	}
	
	public void paintPlayer(Graphics g) {
		loadImage();
		g.drawImage(image, cell.getX()+1, cell.getY()+1, cell.getWidth()-2, cell.getWidth()-2, null, null);
	}
	
	private void changeScore(int i) {
		this.score += i;
	}
	
	public void reSetScore() {
		score = INI_SCORE;
		numberOfStep = 0;
		numberOfGold = 0;
		numberOfTrap = 0;
		notifyAllPlayerScoreObervers();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getNumberOfStep() {
		return numberOfStep;
	}

	public void setNumberOfStep(int numberOfStep) {
		this.numberOfStep = numberOfStep;
	}

	public int getNumberOfGold() {
		return numberOfGold;
	}

	public void setNumberOfGold(int numberOfGold) {
		this.numberOfGold = numberOfGold;
	}

	public int getNumberOfTrap() {
		return numberOfTrap;
	}

	public void setNumberOfTrap(int numberOfTrap) {
		this.numberOfTrap = numberOfTrap;
	}
	
	public void setStepCost(int stepCost) {
		this.stepCost = stepCost;
	}
}
