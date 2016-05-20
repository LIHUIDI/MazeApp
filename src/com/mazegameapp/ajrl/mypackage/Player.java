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
	ArrayList<PlayerObserver> playerObservers = new ArrayList<>();

	private static final int INI_SCORE = 100;
	//move a step will minus 1 from player's score.
	private static final int MOVE_SCORE = -1;
	private int score;

	
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
	
	public void registerplayerObservers(PlayerObserver o) {
		playerObservers.add(o);
	}
	
	public void removeplayerObservers(PlayerObserver o) {
		playerObservers.remove(o);
	}
	
	private void notifyAllObervers(boolean done) {
		for (PlayerObserver o : playerObservers) {
			o.finishMovement(done);
		}
	}

	public void moveSelfUp() {
		if (!this.getCurrentCell().hasTopWall()) {
			//set previous cell to current cell
			this.previousCell = this.getCurrentCell();
			//collect item if old cell has item
			if ((previousCell instanceof SquareCellWithItem)) {
				if (((SquareCellWithItem) previousCell).hasItem()) {
					//update player's score based on the value of item of just visited cell
					changeScore(((SquareCellWithItem) previousCell).getItem().getValue());
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			//update current cell to next cell according to move direction.
			SquareCell newcell = gridMaze.getGrid()[previousCell.getX()/previousCell.getWidth()][(previousCell.getY()- previousCell.getWidth())/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			//since player moved a step, subtract score 1.
			changeScore(MOVE_SCORE);
			notifyAllObervers(done);
			done = false;
		} else {
			done = false;
			notifyAllObervers(done);
		}
	}
	
	public void moveSelfDown() {
		if (!this.getCurrentCell().hasButtomWall()) {
			this.previousCell = this.getCurrentCell();
			//collect item if old cell has item
			if ((previousCell instanceof SquareCellWithItem)) {
				if (((SquareCellWithItem) previousCell).hasItem()) {
					changeScore(((SquareCellWithItem) previousCell).getItem().getValue());
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX())/previousCell.getWidth()][(previousCell.getY() + previousCell.getWidth())/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			changeScore(MOVE_SCORE);
			notifyAllObervers(done);
			done = false;
		} else {
			done = false;
			notifyAllObervers(done);
		}
	}
	
	public void moveSelfLeft() {
		if (!this.getCurrentCell().hasLeftWall() && this.getCurrentCell().getX() != 0) {
			this.previousCell = this.getCurrentCell();
			//collect item if old cell has item
			if ((previousCell instanceof SquareCellWithItem)) {
				if (((SquareCellWithItem) previousCell).hasItem()) {
					changeScore(((SquareCellWithItem) previousCell).getItem().getValue());
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX()- previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			changeScore(MOVE_SCORE);
			notifyAllObervers(done);
			done = false;
		} else {
			done = false;
			notifyAllObervers(done);
		}
	}
	
	public void moveSelfRight() {
		if (!this.getCurrentCell().hasRightWall() && this.getCurrentCell().getX()/this.getCurrentCell().getWidth() != gridMaze.getGrid().length - 1) {
			this.previousCell = this.getCurrentCell();
			//collect item if old cell has item
			if ((previousCell instanceof SquareCellWithItem)) {
				if (((SquareCellWithItem) previousCell).hasItem()) {
					changeScore(((SquareCellWithItem) previousCell).getItem().getValue());
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX() + previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			changeScore(MOVE_SCORE);
			notifyAllObervers(done);
			done = false;
		} else {
			done = false;
			notifyAllObervers(done);
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
	
	public int getScore() {
		return this.score;
	}
}
