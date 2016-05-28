package com.mazegameapp.ajrl.mypackage;

import javax.swing.SwingUtilities;

/**
 * MazeGameController is the only controller in game. 
 * It initialize {@code GridMaze} and {@code Player}, creates {@code MazeGameView} and forwards any requests from View to Mode classes.
 *
 */
public class MazeGameController implements ControllerInterface{
	GridMaze gridMaze;
	Player player;
	Timer timer;
	Thread thread;
	MazeGameView mazeGameView;
	
	public MazeGameController(GridMaze gridMaze) {
		this.gridMaze = gridMaze;
		this.gridMaze.initializeGridMaze();
		this.player = new Player(gridMaze);
        player.setCurrentCell((gridMaze.getGrid())[0][0]);
        
        this.timer = new Timer(120);
		mazeGameView = new MazeGameView(gridMaze, player, timer,this);
		
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	mazeGameView.createAndShowGUI(); 
	        	mazeGameView.createControls();
	        	mazeGameView.enableReStartMenuItem();
	        }
	   });
	}
	
	@Override
	public void reStartGame(String difficultyLevel, String theme) {
		changeDifficulty(difficultyLevel);
		gridMaze.initializeGridMaze();
	    player.setCurrentCell((gridMaze.getGrid())[0][0]);
	    player.reSetScore();
	    player.unfreezePlayer();
	    stopTimer();
		changeTheme(theme);
		mazeGameView.getMazePanel().finishSetting();
		mazeGameView.enableStartTimerMenuItem();
		mazeGameView.cancelFinishedMessage();
	}
	
	@Override
	public void move(String direction) {
		player.moveSelf(direction);
	}
	
	/**
	 * Set difficulty levels.
	 */
	
	private void changeDifficulty(String level) {
		if (level.equals(ActionData.Difficultylevel.BEGINNER.toString())) {
			gridMaze.setGridNum(10);
			gridMaze.setCellRatio(60,28,10,2);
		} else if (level.equals(ActionData.Difficultylevel.INTERMEDIATE.toString())) {
			// set the grid size to 30 * 30
			gridMaze.setGridNum(20);
			//set the proportion of common cell, gold cell and trap cell to 70%, 20%, 10%
			gridMaze.setCellRatio(60,15,15,10);
		} else if (level.equals(ActionData.Difficultylevel.ADVANCED.toString())){
			// set the grid size to 50 * 50
			gridMaze.setGridNum(40);
			//set the proportion of common cell, gold cell and trap cell to 80%, 5%, 15%
			gridMaze.setCellRatio(50,10,25,15);
		} else {
			// do nothing
		}
	}

	private void changeTheme(String theme) {
		SquareCell[][] grid = gridMaze.getGrid();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j].changeSquareCellBackGroundImgPath(theme);
				grid[i][j].changeWallImgs(theme);
				if (grid[i][j] instanceof SquareCellWithItem) {
					((SquareCellWithItem)grid[i][j]).getItem().changeItemTheme(theme);
				}
			}
		}
		player.changePlayerImgPath(theme);
	}

	@Override
	public void startTimer(int timeLimit) {
		// create a thread, let timer run.
		timer.resetTimer();
		timer.setTimeLimit(timeLimit);
	    thread = new Thread(timer);
		thread.start();
		mazeGameView.disableStartTimerMenuItem();
	}
	
	private void stopTimer() {
		if (thread != null) {
			timer.terminate();
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
