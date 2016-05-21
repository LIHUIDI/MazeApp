package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel implements PlayerScoreObserver, PlayerMovementObserver{
	
	private static final long serialVersionUID = 1L;
	private Player player;
	
	private BufferedImage scorePanelimage;
	private String scorePanelImgPath = "resources/image/scorePanel.png";
	private JLabel scoreboard;
	
	public ScorePanel(Player player) {
		this.player = player;
		this.player.registerplayerMovementObservers(this);
		this.player.registerplayerScoreObservers(this);
		try {                
			scorePanelimage = ImageIO.read(new File(scorePanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
		createComponents();
	}
	
	private void createComponents() {
		scoreboard = new JLabel();
		scoreboard.setText("<html> <font color = 'WHITE'>Player Score: " + player.getScore() +"</font></html>");
		this.add(scoreboard);
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(scorePanelimage, 0, 0, this.getWidth(),this.getHeight(),null,null);
	}
	
	@Override
	public void updateScore() {
		scoreboard.setText("<html> <font color = 'WHITE'>Player Score: " + player.getScore() +"</font></html>");
	}
	
	@Override
	public void finishMovement(boolean done) {
		if (done) {
			scoreboard.setText("<html> <font color = 'WHITE'>Player Score: " + player.getScore() +"</font></html>");
		}
	}
}
