package com.mazegameapp.ajrl.mypackage;

import java.util.ArrayList;
/**
 *Timer represent a clock, for 1000 milliseconds it will notify any {@code TimerObserver} that such point of time is reached.
 */
public class Timer implements Runnable{
	private int times = 0;
	private boolean timeOut = false;
	private int timeLimit = 1;
	
	private volatile boolean running = true;
	
	public Timer(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	/**
	 * Terminate the timer.
	 */
    public void terminate() {
        running = false;
    }
	
	ArrayList<TimerObserver> timerObservers = new ArrayList<>();
	
	public int getTime() {
		return times;
	}
	
	/**
	 * 
	 */
	public void resetTimer() {
		times = 0;
		running = true;
		timeOut = false;
	}
	/**
	 * Set the maximum time point that this timer can reach to.
	 * @param t the maximum time that this timer can reach to.
	 */
	public void setTimeLimit(int t) {
		timeLimit = t*60;
	}
	
	public void registerTimerObservers(TimerObserver o) {
		timerObservers.add(o);
	}
	
	public void removeTimerObservers(TimerObserver o) {
		timerObservers.remove(o);
	}
	
	private void notifyAllTimerObervers() {
		for (TimerObserver o : timerObservers) {
			o.updateTime();
			if (timeOut) {
				o.TimeEnd();
			}
		}
	}
	
	/**
	 * Make the timer run, every seconds it will notify all {@code TimerObserver} that the event.
	 */
	@Override
	public void run() {
		while (times <= timeLimit && running) {
			try {
				Thread.sleep(999);
			} catch (Exception e) {
				e.printStackTrace();
				running = false;
			}
			times++;
			notifyAllTimerObervers();
		}
		timeOut = true;
		running = false;
		notifyAllTimerObervers();
	}
}
