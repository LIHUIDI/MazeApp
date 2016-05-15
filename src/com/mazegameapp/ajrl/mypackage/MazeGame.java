package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MazeGame {
	
	public static void main(String[] args) {
		
		GridMaze gridMaze = new GridMaze();
		ControllerInterface controller = new MazeGameController(gridMaze);
	}
}