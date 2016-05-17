package com.mazegameapp.ajrl.mypackage;

import java.awt.Font;

import javax.swing.JLabel;

public class Scoreboard extends JLabel {

	private static final long serialVersionUID = 1L;
	private Player player;
	
	public Scoreboard (Player player) {
		super("Player 1 Score = " + player.getScore());
		this.player = player;
		super.setFont(new Font("Serif", Font.BOLD, 24));

	}
	
	public void update() {
		super.setText("Player 1 Score = " + this.player.getScore());
	}

}
