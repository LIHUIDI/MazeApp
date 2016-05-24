package com.mazegameapp.ajrl.mypackage;
/**
 * The ControllerInterface is the glue that communicates with View and Mode classes. 
 * The concrete implementation of this interface should override the methods defined and supply acceptable parameter values, such as directions, 
 * difficultyLevels, theme.
 */
public interface ControllerInterface {
	/**
	 * Move the player to next cell based on the direction passed.
	 * @param direction indicates which direction an agent move to. 
	 */
	public void move(String direction);
	/**
	 * Reload the maze map based on user supplied difficulty level and theme.
	 * @param difficultyLevel indicates which difficulty level user has selected.
	 * @param theme indicates which theme user selected. Theme mainly refers to the appearance of elements of game, such as image of the player, 
	 * background of the cell.  
	 */
	public void reStartGame(String difficultyLevel, String theme);
}
