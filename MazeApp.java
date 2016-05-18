package com.mazegameapp.ajrl.mypackage;

import javax.swing.JFrame;

public class MazeApp {
	
	private JFrame frame;
	private MazeViews views;

	@SuppressWarnings("unused")
	private MazeViewsController controller;

	/**
	 * Main program for the maze game
	 */
	public MazeApp() {
		System.out.println("Creating Maze App...");
		prepareFrame();
		prepareViews();
		prepareController();
	}

	/**
	 * Initialise the controller
	 */
	private void prepareController() {
		controller = new MazeViewsController(views);
	}

	/**
	 * Initialise the cards
	 */
	private void prepareViews() {
		views = new MazeViews();
		frame.add(views);
	}

	/**
	 * Initialise the frame
	 */
	private void prepareFrame() {
		System.out.println("Creating Frame...");
		frame = new JFrame("Maze Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 800);
	}

	/**
	 * Run the application
	 */
	public void run() {
		System.out.println("Opening Frame...");
		frame.setVisible(true);
	}

	/**
	 * Entry point for the maze game
	 * @param args Given arguments
	 */
	public static void main(String[] args) {
		MazeApp game = new MazeApp();
		game.run();
	}
}
