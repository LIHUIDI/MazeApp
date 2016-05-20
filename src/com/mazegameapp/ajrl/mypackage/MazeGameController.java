package com.mazegameapp.ajrl.mypackage;

import javax.swing.SwingUtilities;

public class MazeGameController implements ControllerInterface{
	GridMaze gridMaze;
	Player player;
	MazeGameView mazeGameView;
	// indicate if user has changed the default difficult level.
	
	public MazeGameController(GridMaze gridMaze) {
		this.gridMaze = gridMaze;
		this.gridMaze.initializeGridMaze();
		
		this.player = new Player(gridMaze);
        player.setCurrentCell((gridMaze.getGrid())[0][0]);
        
		mazeGameView = new MazeGameView(gridMaze, player, this);
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	mazeGameView.createAndShowGUI(); 
	        	mazeGameView.createControls();
	        	mazeGameView.enableReStartMenuItem();
	        }
	   });
		
	}
	
	//user don't like default maze map, reset all kinds of options, like theme, difficulty level and 
	//restart the game
	@Override
	public void reStartGame(String difficultyLevel, String theme) {
		
		changeDifficulty(difficultyLevel);
		gridMaze.initializeGridMaze();
	    player.setCurrentCell((gridMaze.getGrid())[0][0]);
	    player.reSetScore();
		changeTheme(theme);
		mazeGameView.getMazePanel().finishSetting();
	}
	
	public void moveUp() {
		player.moveSelfUp();
	}
	
	@Override
	public void moveLeft() {
		player.moveSelfLeft();
	}
	
	@Override
	public void moveRight() {
		player.moveSelfRight();
	}
	
	@Override
	public void moveDown() {
		player.moveSelfDown();
	}
	
	/**
	 * set difficulty level implicitly means needing to regenerate maze.
	 */
	
	private void changeDifficulty(String level) {
		if (level.toLowerCase().equals(Difficultylevel.BEGINNER.toString())) {
			gridMaze.setGridNum(10);
			//set the proportion of common cell, gold cell and trap cell to default 60%, 30%, 10%
			gridMaze.setCellRatio(60,30,10);
		} else if (level.toLowerCase().equals(Difficultylevel.INTERMEDIATE.toString())) {
			// set the grid size to 30 * 30
			gridMaze.setGridNum(20);
			//set the proportion of common cell, gold cell and trap cell to 70%, 20%, 10%
			gridMaze.setCellRatio(70,20,10);
		} else if (level.toLowerCase().equals(Difficultylevel.ADVANCED.toString())){
			// set the grid size to 50 * 50
			gridMaze.setGridNum(40);
			//set the proportion of common cell, gold cell and trap cell to 80%, 5%, 15%
			gridMaze.setCellRatio(80,5,15);
		} else {
			// do nothing
		}
	}

	
	private void changeTheme(String theme) {
		SquareCell[][] grid = gridMaze.getGrid();
		
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid.length; j++) {
					grid[i][j].changeSquareCellBackGroundImgPath(theme.toLowerCase());
					grid[i][j].changeWallImgs(theme.toLowerCase());
					if (grid[i][j] instanceof SquareCellWithItem) {
						((SquareCellWithItem)grid[i][j]).getItem().changeItemTheme(theme.toLowerCase());
					}
				}
			}
			player.changePlayerImgPath(theme);
	}
	
	public enum Difficultylevel {
		BEGINNER ("beginner"), INTERMEDIATE ("intermediate"), ADVANCED("advanced");
		
		private String level;
		
		private Difficultylevel(String level) {
			this.level = level;
		}
		
		public String toString() {
			if (this.equals(BEGINNER)) {
				return "beginner";
			} else if (this.equals(INTERMEDIATE)) {
				return "intermediate";
			} else {
				return "advanced";
			}
		}
	}
	
	public enum Theme {
		SPRING ("spring"), SUMMER ("summer"), AUTUMN("autumn"), WINTER("winter");
		
		private String q;
		
		private Theme(String q) {
			this.q = q;
		}
		
		public String toString() {
			if (this.equals(SPRING)) {
				return "spring";
			} else if (this.equals(SUMMER)) {
				return "summer";
			} else if (this.equals(AUTUMN)){
				return "autumn";
			} else {
				return "winter";
			}
		}
	}
}
