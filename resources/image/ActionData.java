package com.mazegameapp.ajrl.mypackage;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButton;

/**
 * ActionData services as a communication protocol between View, controller and Mode. 
 * It defines Difficulty levels, themes and move directions that game supports.
 * In case game developed to multiply users p2p or client-server online game, more communication protocol messages can be defined in this class.
 */
public class ActionData {
	
	/**
	 * defines difficulty levels maze game supports.
	 */
	public static enum Difficultylevel {
		BEGINNER , INTERMEDIATE , ADVANCED;
		
		public String toString() {
			switch(this) {
			case BEGINNER:
				return "beginner";
			case INTERMEDIATE:
				return "intermediate";
			case ADVANCED:
				return "advanced";
			default:
			    throw new AssertionError(this);
			}
		}
	}
	
	/**
	 * defines themes maze game supports.
	 */
	public static enum Theme {
		SPRING , SUMMER , AUTUMN, WINTER;
		
		public String toString() {
			switch(this) {
			case SPRING:
				return "spring";
			case SUMMER:
				return "summer";
			case AUTUMN:
				return "autumn";
			case WINTER:
				return "winter";
			default:
			    throw new AssertionError(this);
			}
		}
	}
	
	/**
	 * defines directions of a movement of player that game supports.
	 */
	public enum MoveDirection {
		UP, DOWN, LEFT, RIGHT;
		
		public String toString() {
			switch(this) {
			case UP:
				return "up";
			case DOWN:
				return "down";
			case LEFT:
				return "left";
			case RIGHT:
				return "right";
			default:
			    throw new AssertionError(this);
			}
		}
	}
}

/*
 * setTimer = new JMenu("Set Time");
		JRadioButton timeButton1 = new JRadioButton("1");
		timeButton1.setActionCommand("2");
	    JRadioButton timeButton2 = new JRadioButton("5");
	    timeButton2.setActionCommand("5");
	    JRadioButton timeButton3 = new JRadioButton("10");
	    timeButton3.setActionCommand("10");
	    JRadioButton timeButton4 = new JRadioButton("30",true);
	    timeButton4.setActionCommand("30");
	    timeButton1.setSelected(true);
	    timeButton1.addActionListener(this);
	    timeButton2.addActionListener(this);
	    timeButton3.addActionListener(this);
	    timeButton4.addActionListener(this);
	    ButtonGroup timeGroup = new ButtonGroup();
	    timeGroup.add(timeButton1);
	    timeGroup.add(timeButton2);
	    timeGroup.add(timeButton3);
	    timeGroup.add(timeButton4);
	    setTimer.add(timeButton1);
	    setTimer.add(timeButton2);
	    setTimer.add(timeButton3);
	    setTimer.add(timeButton4);
	    timerMenu.add(setTimer);
 */
