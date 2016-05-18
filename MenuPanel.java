package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1958180799659925883L;

	/**
	 * Sets the required fields for the button & adds it to the menu panel.
	 * @param button
	 */
	protected void prepareButton(JButton button) {
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(Color.WHITE);
		setFont(button);		
		this.add(button);
	}
	
	/**
	 * Sets the font for the button.
	 * @param button
	 */
	protected void setFont(JButton button) {
		button.setFont(new Font("Century", Font.BOLD, 20));
	}
}
