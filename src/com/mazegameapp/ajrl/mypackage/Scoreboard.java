
package com.mazegameapp.ajrl.mypackage;

import java.awt.Font;

import javax.swing.JLabel;

public class Scoreboard extends JLabel implements PlayerMovementObserver, PlayerScoreObserver{
	private static final long serialVersionUID = 1L;
	
	// here we only has one player, if multiple players, may use map to store player, store values.
	private Player player;
	
	public Scoreboard (Player player) {
		super("Player 1 Score = " + "\n" + player.getScore());
		this.player = player;
		this.player.registerplayerMovementObservers(this);
		this.player.registerplayerScoreObservers(this);
		super.setFont(new Font("Serif", Font.BOLD, 24));
	}
	
	@Override
	public void finishMovement(boolean done) {
		if (done) {
			super.setText("Player 1 Score = " + "\n" + this.player.getScore());
		}
	}

	@Override
	public void updateScore() {
		super.setText("Player 1 Score = " + "\n" + this.player.getScore());
	}
}

