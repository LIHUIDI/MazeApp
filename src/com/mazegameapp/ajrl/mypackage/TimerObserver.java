package com.mazegameapp.ajrl.mypackage;
/**
 * The interface defines methods that any concrete Observer of {@code Timer} should implements.
 *
 */
public interface TimerObserver {
	/**
	 * Update some state when a second passed.
	 */
	public void updateTime();
	/**
	 * Change some state when certain interval of time has passed.
	 */
	public void TimeEnd();
}
