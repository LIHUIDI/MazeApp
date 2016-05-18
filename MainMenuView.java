package com.mazegameapp.ajrl.mypackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class MainMenuView extends BackgroundPanel {	
	private static final long serialVersionUID = 2278627000847076976L;
	private MainMenuButtons menu;
	
	/**
	 * Constructs the main panel (The main menu when game is started)
	 */
	public MainMenuView(Image background) {
		super(background);
		System.out.println("Creating Main Menu View...");		
		preparePanel();
	}
	
	/**
	 * Initialise fields.
	 */
	private void preparePanel() {
		this.setLayout(new GridBagLayout());
		addMenu();
	}
	
	/**
	 * Add the menu panel.
	 */
	private void addMenu() {
		menu = new MainMenuButtons();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(225, 0, 0, 0);
		this.add(menu, gbc);
	}
	
	/**
	 * Adds the actionListener for "Difficulty"
	 * @param listener A listener that changes the text on difficulty & updates game settings
	 */
	public void addDifficultyListener(ActionListener listener) {
		menu.addDifficultyListener(listener);
	}
	
	/**
	 * Updates the difficulty one level, goes to the first level if at max level.
	 */
	public void changeDifficulty() {
		menu.changeDifficulty();
	}
	
	/**
	 * Adds the actionListener for "Theme"
	 * @param listener A listener that changes the text on theme & updates game settings
	 */
	public void addThemeListener(ActionListener listener) {
		menu.addThemeListener(listener);
	}
	
	/**
	 * Changes the theme
	 */
	public void changeTheme() {
		menu.changeTheme();
	}
	
	/**
	 * Adds the actionListener for the menu
	 * @param listener A listener that switches panels on request
	 */
	public void addViewRequestListener(ActionListener listener) {
		menu.addViewRequestListener(listener);
	}
	
	/**
	 * Adds the actionListener for "Exit Game"
	 * @param listener A listener that exits the game
	 */
	public void addExitRequestListener(ActionListener listener) {
		menu.addExitRequestListener(listener);
	}
}
