package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = -8564139536831634367L;
	
	private Image background;
	
	/**
	 * Construcst a panel with the Maze Game background.
	 * @param background
	 */
	public BackgroundPanel(Image background) {
		this.background = background;
	}
	
	/**
	 * Draws the background for the panel.
	 */
	protected void paintComponent(Graphics g) {
		if (background == null) {
			super.paintComponent(g);
			return;
		}
		
		Image newImage = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		g.drawImage(newImage, 0, 0, null);
	}
}
