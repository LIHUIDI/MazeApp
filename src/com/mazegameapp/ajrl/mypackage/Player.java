package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
/**
 * Player encapsulates the state and behavior of the agent of Maze game. The main components are the grid maze and cell it is sitting in. 
 * Several Observers that monitor it's movement and score state. 
 *
 */
public class Player{
	GridMaze gridMaze;
	SquareCell previousCell;
	SquareCell cell;
	private BufferedImage image;
	ArrayList<PlayerMovementObserver> playerMovementObservers = new ArrayList<>();
	ArrayList<PlayerScoreObserver> playerScoreObservers = new ArrayList<>();
	private boolean frozen;
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
	private String playerImgPath = "resources/image/springplayer.png"; //default cell background image.
	
	/**
	 * Constructor of Player.
	 * @param gridMaze the GridMaze this player is in.
	 */
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

	/**
	 * Move the player to next cell based on the direction supplied.
	 * @param direction indicates which direction this player would move to.
	 */
	public void moveSelf(String direction) {
		if (this.frozen) {
			return;
		}
		//we could define special direction to indicate the movement is from TeleportCell, but for simplicity, we ignore direction here
		// that means, what ever the direction is, as long as the movement is from teleportCell, we do this
		if (this.getCurrentCell() instanceof TeleportCell) {
			//set previous cell to current cell
			this.previousCell = this.getCurrentCell();
			int gridNum = gridMaze.getGridNum();
			Random randomGenerator = new Random();
			int nextPosX = randomGenerator.nextInt(gridNum-1);
			int nextPosY = randomGenerator.nextInt(gridNum-1);
			this.setCurrentCell(gridMaze.getGrid()[nextPosX][nextPosY]);
			done = true;
			//since player moved a step, subtract score 1.
			changeScore(stepCost);
			numberOfStep++;
			notifyAllPlayerMovementObervers(done);
			done = false;
		} 
		// other types of SquareCell.
		else {
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
						//shouldn't reach here.
					}
					this.setCurrentCell(newcell);
					done = true;
					//since player moved a step, subtract score 1.
					changeScore(stepCost);
					numberOfStep++;
					notifyAllPlayerMovementObervers(done);
					done = false;
				} else {
					done = false;
					notifyAllPlayerMovementObervers(done);
				}
		}
		if (isFinished()) {
			freezePlayer();
		}
	}
	
	public void setCurrentCell(SquareCell cell) {
		this.cell = cell;
	}
	
	public SquareCell getCurrentCell() {
		return cell;
	}
	/**
	 * 
	 * @return the cell this player was in before any movement.
	 */
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
	
	/**
	 * Reset the all scores of this player to initial state, including number of steps, 
	 * number of Gold collected, number of Traps encountered, total scores of this player.
	 */
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
	
	/**
	 * Set player's state to frozen such that this player can not be moved.
	 */
	public void freezePlayer() {
		this.frozen = true;
	}

	public void unfreezePlayer() {
		 this.frozen = false;
	}
	
	/**
	 * Test if player is in the exit cell.
	 * @return true if player is in the exit cell, otherwise false.
	 */
	public boolean isFinished() {
		 return ((cell.getX()/cell.getWidth() == gridMaze.getGridNum() -1) &&
				 (cell.getY()/cell.getWidth() == gridMaze.getGridNum() -1));
	}
}
