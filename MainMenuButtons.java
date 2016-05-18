package com.mazegameapp.ajrl.mypackage;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainMenuButtons extends MenuPanel {
	// Difficulty options
	public static final String EASY = "Easy";
	public static final String MEDIUM = "Normal";
	public static final String HARD = "Hard";

	// Theme options
	// public static final String NO_THEME = "None";
	public static final String SPRING = "Spring";
	public static final String SUMMER = "Summer";
	public static final String AUTUMN = "Autumn";
	public static final String WINTER = "Winter";

	private static final long serialVersionUID = 2219525282053261502L;
	private static final String EXIT_GAME = "Exit Game";
	private static final String DIFFICULTY_TITLE = "Difficulty: ";
	private static final String THEME_TITLE = "Theme: ";
	private static final int NUM_ROWS = 5;
	private static final int V_GAP = 15;
	private static final int WIDTH = 225;
	private static final int HEIGHT = 313;

	private GridLayout layout;
	private JButton difficulty, theme, startGame, howToPlay, exitGame;

	/**
	 * Constructs a menu panel containing "Start Game, Options, How to Play & Exit Game".
	 */
	public MainMenuButtons() {
		System.out.println("Creating Main Menu Buttons...");
		layout = new GridLayout(NUM_ROWS, 1, 0, V_GAP);
		this.setLayout(layout);
		this.setOpaque(false);

		addButtons();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	/**
	 * Add the required buttons for the menu.
	 */
	private void addButtons() {
		startGame = new JButton(MazeViews.START_GAME);
		startGame.setName(MazeViews.START_GAME);
		prepareButton(startGame);
		
		difficulty = new JButton(DIFFICULTY_TITLE + MEDIUM);
		prepareButton(difficulty);

		theme = new JButton(THEME_TITLE + WINTER);
		prepareButton(theme);

		howToPlay = new JButton(MazeViews.HOW_TO_PLAY);
		howToPlay.setName(MazeViews.HOW_TO_PLAY);
		prepareButton(howToPlay);

		exitGame = new JButton(EXIT_GAME);
		prepareButton(exitGame);
	}
	
	/**
	 * Adds the actionListener for "Difficulty"
	 * @param listener A listener that changes the text on difficulty & updates game settings
	 */
	public void addDifficultyListener(ActionListener listener) {
		difficulty.addActionListener(listener);
	}
	
	/**
	 * Updates the difficulty one level, goes to the first level if at max level.
	 */
	public void changeDifficulty() {
		switch(difficulty.getText().replaceFirst(DIFFICULTY_TITLE, "")) {
		case EASY:
			difficulty.setText(DIFFICULTY_TITLE+MEDIUM);
			break;
		case MEDIUM:
			difficulty.setText(DIFFICULTY_TITLE+HARD);
			break;
		case HARD:
			difficulty.setText(DIFFICULTY_TITLE+EASY);
			break;
		default:
			throw new IllegalArgumentException("INVALID DIFFICULTY");
		}
	}
	
	/**
	 * Adds the actionListener for "Theme"
	 * @param listener A listener that changes the text on theme & updates game settings
	 */
	public void addThemeListener(ActionListener listener) {
		theme.addActionListener(listener);
	}
	
	/**
	 * Changes the theme
	 */
	public void changeTheme() {
		switch(theme.getText().replaceFirst(THEME_TITLE, "")) {
		case SPRING:
			theme.setText(THEME_TITLE+SUMMER);
			break;
		case SUMMER:
			theme.setText(THEME_TITLE+AUTUMN);
			break;
		case AUTUMN:
			theme.setText(THEME_TITLE+WINTER);
			break;
		case WINTER:
			theme.setText(THEME_TITLE+SPRING);
			break;
		default:
			throw new IllegalArgumentException("INVALID THEME");
		}
	}

	/**
	 * Adds the actionListener for "Start Game" & "How to Play".
	 * @param listener A listener that switches panels on request
	 */
	public void addViewRequestListener(ActionListener listener) {
		startGame.addActionListener(listener);
		howToPlay.addActionListener(listener);
	}

	/**
	 * Adds the actionListener for "Exit Game"
	 * @param listener A listener that exits the game
	 */
	public void addExitRequestListener(ActionListener listener) {
		exitGame.addActionListener(listener);
	}
}
