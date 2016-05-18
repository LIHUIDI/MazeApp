package com.mazegameapp.ajrl.mypackage;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MazeViews extends JPanel {
	// Name of views
	public final static String MAIN_MENU = "Main Menu";
	public final static String START_GAME = "Start Game";
	public final static String HOW_TO_PLAY = "How to Play";
	
	private static final long serialVersionUID = -2014874943278582418L;
	
	private Image background;
	private CardLayout layout;
	private MainMenuView mainMenu;
	
	/**
	 * Constructs the required views for the maze game, placing it into a cardlayout.
	 */
	public MazeViews() {
		System.out.println("Creating Maze Views...");
		getBackgroundImg();
		this.layout = new CardLayout();
		this.setLayout(layout);
		addCards();
	}

	/**
	 * Add the cards.
	 */
	private void addCards() {
		addMainMenu();
	}

	/**
	 * Add the main menu card.
	 */
	private void addMainMenu() {
		mainMenu = new MainMenuView(background);
		this.add(MAIN_MENU, mainMenu);
	}
	
	/**
	 * Adds the actionListener for "Difficulty"
	 * @param listener A listener that changes the text on difficulty & updates game settings
	 */
	public void addDifficultyListener(ActionListener listener) {
		mainMenu.addDifficultyListener(listener);
	}
	
	/**
	 * Updates the difficulty one level, goes to the first level if at max level.
	 */
	public void changeDifficulty() {
		mainMenu.changeDifficulty();
	}
	
	/**
	 * Adds the actionListener for "Theme"
	 * @param listener A listener that changes the text on theme & updates game settings
	 */
	public void addThemeListener(ActionListener listener) {
		mainMenu.addThemeListener(listener);
	}
	
	/**
	 * Changes the theme
	 */
	public void changeTheme() {
		mainMenu.changeTheme();
	}
	
	/**
	 * Adds the actionListener for all buttons that request to see another view
	 * @param listener A listener that switches panels on request
	 */
	public void addViewRequestListener(ActionListener listener) {
		mainMenu.addViewRequestListener(listener);
	}
	
	/**
	 * Adds the actionListener for "Exit Game"
	 * @param listener A listener that exits the game
	 */
	public void addExitRequestListener(ActionListener listener) {
		mainMenu.addExitRequestListener(listener);
	}
	
	/**
	 * Read in the image to used for the background.
	 */
	private void getBackgroundImg() {
		try {
			background = ImageIO.read(new File("resources/MenuBackground2.jpg"));
		} catch (IOException e) {
			background = null;
			e.printStackTrace();
		}
	}
	
	/**
	 * Switch cards to given input
	 * @param viewName Name of view to switch to
	 */
	public void switchTo(String viewName) {
		layout.show(this, viewName);
	}
}
