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
/**
 * The panel that show all the records that player has obtained at some point of the game. 
 * These records include total scores, number of gold collected, number of traps encountered, number of steps, time cost 
 * and game finish notification.
 *
 */
public class ScorePanel extends JPanel implements PlayerScoreObserver, PlayerMovementObserver, TimerObserver{
	
	private static final long serialVersionUID = 1L;
	private Player player;
	private Timer timer;
	
	private BufferedImage scorePanelimage;
	private String scorePanelImgPath = "resources/image/scorePanel.png";
	private JLabel scoreboard, trapBoard, goldBoard, stepBoard, timerboard, finishedBoard;
	
	public ScorePanel(Player player, Timer timer) {
		super(new GridLayout(7,1));
		this.player = player;
		this.player.registerplayerMovementObservers(this);
		this.player.registerplayerScoreObservers(this);
		
		this.timer = timer;
		this.timer.registerTimerObservers(this);
		
		try {                
			scorePanelimage = ImageIO.read(new File(scorePanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
		
		ImageIcon scoreboardIcon = new ImageIcon("resources/image/scoreIcon.png", "score");
		ImageIcon trapBoardIcon = new ImageIcon("resources/image/trapIcon.png","trap");
		ImageIcon goldBoardIcon = new ImageIcon("resources/image/goldIcon.png","gold");
		ImageIcon stepBoardIcon = new ImageIcon("resources/image/stepIcon.png", "step");
		ImageIcon timerBoardIcon = new ImageIcon("resources/image/timer.png", "timer");
		ImageIcon finishedBoardIcon = new ImageIcon("resources/image/finishedIcon.png", "finished");
		
		scoreboard = new JLabel("Score",scoreboardIcon,JLabel.CENTER);
		scoreboard.setVerticalTextPosition(JLabel.BOTTOM);
		scoreboard.setHorizontalTextPosition(JLabel.CENTER);
		scoreboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Score " + player.getScore() +"</font></html>");
		this.add(scoreboard);
		
		goldBoard = new JLabel("Gold",goldBoardIcon,JLabel.CENTER);
		goldBoard.setVerticalTextPosition(JLabel.BOTTOM);
		goldBoard.setHorizontalTextPosition(JLabel.CENTER);
		goldBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Gold " + player.getNumberOfGold() +"</font></html>");
		this.add(goldBoard);
		
		trapBoard = new JLabel("Trap",trapBoardIcon,JLabel.CENTER);
		trapBoard.setVerticalTextPosition(JLabel.BOTTOM);
		trapBoard.setHorizontalTextPosition(JLabel.CENTER);
		trapBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Trap " + player.getNumberOfTrap() +"</font></html>");
		this.add(trapBoard);
		
		stepBoard = new JLabel("Step",stepBoardIcon,JLabel.CENTER);
		stepBoard.setVerticalTextPosition(JLabel.BOTTOM);
		stepBoard.setHorizontalTextPosition(JLabel.CENTER);
		stepBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Step " + player.getNumberOfStep() +"</font></html>");
		this.add(stepBoard);
		
		timerboard = new JLabel("Timer", timerBoardIcon, JLabel.CENTER);
		timerboard.setVerticalTextPosition(JLabel.BOTTOM);
		timerboard.setHorizontalTextPosition(JLabel.CENTER);
		timerboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Time " + timer.getTime() +"</font></html>");
		this.add(timerboard);
		
		finishedBoard = new JLabel("Congratulations!", finishedBoardIcon, JLabel.CENTER);
		finishedBoard.setVerticalTextPosition(JLabel.BOTTOM);
		finishedBoard.setHorizontalTextPosition(JLabel.CENTER);
		finishedBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Congratulations on finshing the maze!<br>To restart, click 'New Game' in the game menu.</font></html>");
		finishedBoard.setVisible(false);
		this.add(finishedBoard);
	}
	
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(scorePanelimage, 0, 0, this.getWidth(),this.getHeight(),null,null);
	}
	
	/**
	 * Update all records to new records.
	 */
	@Override
	public void updateScore() {
		scoreboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Score " + player.getScore() +"</font></html>");
		goldBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Gold " + player.getNumberOfGold() +"</font></html>");
		trapBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Trap " + player.getNumberOfTrap()+"</font></html>");
		stepBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Step " + player.getNumberOfStep() +"</font></html>");
	}
	
	/**
	 * Change the records when player finished a move.
	 */
	@Override
	public void finishMovement(boolean done) {
		if (done) {
			scoreboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Score " + player.getScore() +"</font></html>");
			goldBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Gold " + player.getNumberOfGold() +"</font></html>");
			trapBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Trap " + player.getNumberOfTrap()+"</font></html>");
			stepBoard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Step " + player.getNumberOfStep() +"</font></html>");
			if (player.isFinished()) {
				timer.terminate();
				finishedBoard.setVisible(true);
			}
		}
	}

	/**
	 * indicates time has passed so far.
	 */
	@Override
	public void updateTime() {
		timerboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Time " + timer.getTime() +"</font></html>");
	}
	/**
	 * indicates Time has been used out.
	 */
	@Override
	public void TimeEnd() {
		timerboard.setText("<html> <font color = 'WHITE' face = 'Impact' size='5'>Time End "+"</font></html>");
	}
	/**
	 * Make the game finish notification invisible.
	 */
	public void resetFinishedBoard() {
		finishedBoard.setVisible(false);	
	}
}
