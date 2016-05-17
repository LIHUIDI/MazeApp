package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player{
	GridMaze gridMaze;
	SquareCell previousCell;
	SquareCell cell;
	private BufferedImage image;
	ArrayList<PlayerObserver> playerObservers = new ArrayList<>();
	private int score;
	private Scoreboard scoreboard;
	
	//indicate some movement done or cann't do.
	boolean done = false;

	private void loadImage() {
	     try {                
	        image = ImageIO.read(new File("resources/image/player0.png"));
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
					((SquareCellWithItem) previousCell).collectItem();
					//Updates Players score according to value of that item
					((SquareCellWithItem) previousCell).getItem().updatePlayer(this);
					scoreboard.update();
				}
			}
			//update current cell to next cell according to move direction.
			SquareCell newcell = gridMaze.getGrid()[previousCell.getX()/previousCell.getWidth()][(previousCell.getY()- previousCell.getWidth())/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			notifyAllObervers(done);
			done = false;
			//Subtract from score since made a move
			score--;
			scoreboard.update();
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
					((SquareCellWithItem) previousCell).collectItem();
					//Updates Players score according to value of that item
					((SquareCellWithItem) previousCell).getItem().updatePlayer(this);
					scoreboard.update();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX())/previousCell.getWidth()][(previousCell.getY() + previousCell.getWidth())/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			notifyAllObervers(done);
			done = false;
			score--;
			scoreboard.update();
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
					((SquareCellWithItem) previousCell).collectItem();
					//Updates Players score according to value of that item
					((SquareCellWithItem) previousCell).getItem().updatePlayer(this);
					scoreboard.update();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX()- previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			notifyAllObervers(done);
			done = false;
			score--;
			scoreboard.update();
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
					((SquareCellWithItem) previousCell).collectItem();
					//Updates Players score according to value of that item
					((SquareCellWithItem) previousCell).getItem().updatePlayer(this);
					scoreboard.update();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX() + previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			notifyAllObervers(done);
			done = false;
			score--;
			scoreboard.update();
		} else {
			done = false;
			notifyAllObervers(done);
		}
	}
	
	public Player(GridMaze gridMaze) {
		this.gridMaze = gridMaze;
		this.score = 500;
		//Hopefully no one will take over 500 moves (at least not with all the gold) every move subtracts a score
	}
	
	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
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

	public void paintPlayer(Graphics g) {
		loadImage();
		g.drawImage(image, cell.getX()+1, cell.getY()+1, cell.getWidth()-2, cell.getWidth()-2, null, null);
	}
	
	public void addScore(int i) {
		this.score += i;
	}
	
	public void subtractScore(int i) {
		this.score -= i;
	}
	
	public int getScore() {
		return this.score;
	}
}