package com.mazegameapp.ajrl.mypackage;
/**
 * Observer that monitors player's movement.
 *
 */
public interface PlayerMovementObserver {
	/**
	 * Update some state based on player's movement.
	 * @param done indicates such a player movement actually happened or not.
	 */
	public void finishMovement(boolean done);
}
