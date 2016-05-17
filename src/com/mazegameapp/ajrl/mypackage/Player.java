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
				}
			}
			//update current cell to next cell according to move direction.
			SquareCell newcell = gridMaze.getGrid()[previousCell.getX()/previousCell.getWidth()][(previousCell.getY()- previousCell.getWidth())/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
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
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX())/previousCell.getWidth()][(previousCell.getY() + previousCell.getWidth())/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
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
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX()- previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
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
					((SquareCellWithItem) previousCell).collectItem();
				}
			}
			SquareCell newcell = gridMaze.getGrid()[(previousCell.getX() + previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
			this.setCurrentCell(newcell);
			done = true;
			notifyAllObervers(done);
			done = false;
		} else {
			done = false;
			notifyAllObervers(done);
		}
	}
	
	public Player(GridMaze gridMaze) {
		this.gridMaze = gridMaze;
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
}