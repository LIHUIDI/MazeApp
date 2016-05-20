package com.mazegameapp.ajrl.mypackage;

public class MazeGame {
	
	public static void main(String[] args) {
		
		GridMaze gridMaze = new GridMaze();
		ControllerInterface controller = new MazeGameController(gridMaze);
	}
}