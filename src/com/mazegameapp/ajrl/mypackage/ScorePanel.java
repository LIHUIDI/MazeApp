package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel implements PlayerScoreObserver, PlayerMovementObserver{
	
	private static final long serialVersionUID = 1L;
	private Player player;
	
	private BufferedImage scorePanelimage;
	private String scorePanelImgPath = "resources/image/scorePanel.png";
	private JLabel scoreboard, trapBoard, goldBoard, stepBoard;
	
	public ScorePanel(Player player) {
		super(new GridLayout(6,1));
		this.player = player;
		this.player.registerplayerMovementObservers(this);
		this.player.registerplayerScoreObservers(this);
		try {                
			scorePanelimage = ImageIO.read(new File(scorePanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
		
		ImageIcon scoreboardIcon = new ImageIcon("resources/image/scoreIcon.png", "score");
		ImageIcon trapBoardIcon = new ImageIcon("resources/image/trapIcon.png","trap");
		ImageIcon goldBoardIcon = new ImageIcon("resources/image/goldIcon.png","gold");
		ImageIcon stepBoardIcon = new ImageIcon("resources/image/stepIcon.png", "step");
              
		scoreboard = new JLabel("Score",scoreboardIcon,JLabel.CENTER);
		scoreboard.setVerticalTextPosition(JLabel.BOTTOM);
		scoreboard.setHorizontalTextPosition(JLabel.CENTER);
		scoreboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Score " + player.getScore() +"</font></html>");
		this.add(scoreboard);
		
		goldBoard = new JLabel("Score",goldBoardIcon,JLabel.CENTER);
		goldBoard.setVerticalTextPosition(JLabel.BOTTOM);
		goldBoard.setHorizontalTextPosition(JLabel.CENTER);
		goldBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Gold " + player.getNumberOfGold() +"</font></html>");
		this.add(goldBoard);
		
		trapBoard = new JLabel("Score",trapBoardIcon,JLabel.CENTER);
		trapBoard.setVerticalTextPosition(JLabel.BOTTOM);
		trapBoard.setHorizontalTextPosition(JLabel.CENTER);
		trapBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Trap " + player.getNumberOfTrap() +"</font></html>");
		this.add(trapBoard);
		
		stepBoard = new JLabel("Score",stepBoardIcon,JLabel.CENTER);
		stepBoard.setVerticalTextPosition(JLabel.BOTTOM);
		stepBoard.setHorizontalTextPosition(JLabel.CENTER);
		stepBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Step " + player.getNumberOfStep() +"</font></html>");
		this.add(stepBoard);
	}
	
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(scorePanelimage, 0, 0, this.getWidth(),this.getHeight(),null,null);
	}
	
	@Override
	public void updateScore() {
		scoreboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Score: " + player.getScore() +"</font></html>");
		goldBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Gold: " + player.getNumberOfGold() +"</font></html>");
		trapBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Trap: " + player.getNumberOfTrap()+"</font></html>");
		stepBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Step: " + player.getNumberOfStep() +"</font></html>");
	}
	
	@Override
	public void finishMovement(boolean done) {
		if (done) {
			scoreboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Score: " + player.getScore() +"</font></html>");
			goldBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Gold: " + player.getNumberOfGold() +"</font></html>");
			trapBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Trap: " + player.getNumberOfTrap()+"</font></html>");
			stepBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Step: " + player.getNumberOfStep() +"</font></html>");
		}
	}
}
