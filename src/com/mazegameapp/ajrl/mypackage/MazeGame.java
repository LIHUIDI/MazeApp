package com.mazegameapp.ajrl.mypackage;
/**
 * Main class of the Game.
 *
 */
public class MazeGame {
	
	public static void main(String[] args) {
		GridMaze gridMaze = new GridMaze();
		ControllerInterface controller = new MazeGameController(gridMaze);
	}
}