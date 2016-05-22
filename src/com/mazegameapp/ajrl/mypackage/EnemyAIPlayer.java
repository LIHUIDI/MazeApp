package com.mazegameapp.ajrl.mypackage;

import java.util.Random;

public class EnemyAIPlayer extends Player {
	

	public EnemyAIPlayer(GridMaze gridMaze) {
		super(gridMaze);
		setPlayerImgPath("resources/image/winterAIplayer.png");
	}
	
	@Override
	public void definePlayerImage() {
		playerImgs.put("spring", "resources/image/springAIplayer.png");
		playerImgs.put("summer", "resources/image/summerAIplayer.png");
		playerImgs.put("autumn", "resources/image/autumnAIplayer.png");
		playerImgs.put("winter", "resources/image/winterAIplayer.png");
	}
	
	@Override
	public void moveSelf(String direction) {
		boolean validMovement = false;
		while (!validMovement) {
			Random rand = new Random();
			int randNum = (rand.nextInt() % 4) + 1;
			if (randNum == 1 && !this.getCurrentCell().hasTopWall()) {
				direction = "up";
				validMovement = true;
			} else if (randNum == 2 && !this.getCurrentCell().hasButtomWall()) {
				direction = "down";
				validMovement = true;
			} else if (randNum == 3 && (!this.getCurrentCell().hasLeftWall() && this.getCurrentCell().getX() != 0)) {
				direction = "left";
				validMovement = true;
			} else if (randNum == 4 && (!this.getCurrentCell().hasRightWall() && this.getCurrentCell().getX()/this.getCurrentCell().getWidth() != gridMaze.getGrid().length - 1)) {
				direction = "right";
				validMovement = true;
			} else {
				//should not reach here
			}
		}
		//set previous cell to current cell
		this.previousCell = this.getCurrentCell();
		//update current cell to next cell according to move direction.
		SquareCell newcell = null;
		if (direction.equals("up")) {
			newcell = gridMaze.getGrid()[previousCell.getX()/previousCell.getWidth()][(previousCell.getY()- previousCell.getWidth())/previousCell.getWidth()];
		} else if (direction.equals("down")) {
			newcell = gridMaze.getGrid()[(previousCell.getX())/previousCell.getWidth()][(previousCell.getY() + previousCell.getWidth())/previousCell.getWidth()];
		} else if (direction.equals("left")) {
			newcell = gridMaze.getGrid()[(previousCell.getX()- previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
		} else if (direction.equals("right")) {
			newcell = gridMaze.getGrid()[(previousCell.getX() + previousCell.getWidth())/previousCell.getWidth()][previousCell.getY()/previousCell.getWidth()];
		} else {
			//shouldn't reach here.
		}
		this.setCurrentCell(newcell);
		done = true;
		notifyAllPlayerMovementObervers(done);
		done = false;
	}

}
