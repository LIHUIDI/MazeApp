package com.mazegameapp.ajrl.mypackage;

import java.util.ArrayList;

public class Timer implements Runnable{
	private int times = 0;
	private boolean timeOut = false;
	private int timeLimit = 1;
	
	private volatile boolean running = true;
	
	public Timer(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
    public void terminate() {
        running = false;
    }
	
	ArrayList<TimerObserver> timerObservers = new ArrayList<>();
	
	public int getTime() {
		return times;
	}
	
	public void resetTimer() {
		times = 0;
		running = true;
		timeOut = false;
	}
	
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
