package com.mazegameapp.ajrl.mypackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MazeViewsController {
	private MazeViews views;
	
	/**
	 * Constructs a controller to determine which cards is to be shown.
	 * @param cards
	 */
	public MazeViewsController(MazeViews views) {
		System.out.println("Creating Maze View Controller...");
		this.views = views;
		addDifficultyListener();
		addThemeListener();
		addViewRequestListener();
		addExitRequestListener();
	}

	private void addDifficultyListener() {
		views.addDifficultyListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Changing Difficulty...");
				views.changeDifficulty();
			}
		});
	}
	
	private void addThemeListener() {
		views.addThemeListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Changing Theme...");
				views.changeTheme();
			}
		});
	}

	private void addViewRequestListener() {
		views.addViewRequestListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String viewName = ((JButton)e.getSource()).getName();
				System.out.println("Switching to View \"" + viewName + "\"...");
				views.switchTo(viewName);
			}
		});
	}
	
	private void addExitRequestListener() {
		views.addExitRequestListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exiting Game...");
				System.exit(0);
			}
		});
	}
}
