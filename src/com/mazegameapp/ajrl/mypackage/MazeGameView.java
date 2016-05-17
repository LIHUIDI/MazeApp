package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MazeGameView{
	ControllerInterface controller;
	private GridMaze gridMaze;
	private Player player;
	
	private JFrame f;
	private MyPanel myPanelView;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem startMenuItem;
	
	
	public MazeGameView (GridMaze gridMaze, Player player, ControllerInterface controller) {
		this.controller = controller;
    	this.gridMaze = gridMaze;
    	this.player = player;
    	
	}
	public MyPanel getMazePanel() {
		return myPanelView;
	}
	
	public void createAndShowGUI() {
	    f = new JFrame("Maze Game");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    JPanel mainpane = (JPanel) f.getContentPane();
	    mainpane.setLayout(new BoxLayout(mainpane, BoxLayout.X_AXIS));
	    
	    this.myPanelView = new MyPanel(gridMaze,player,controller);
	    
	    Dimension panelDimension = new Dimension(50, 60);
	    
	    JPanel fillerPanel = new JPanel();
	    fillerPanel.setPreferredSize(panelDimension);
	    fillerPanel.setBackground(Color.BLUE);
	    
	    //Panel 3 can contain the scoreboard
	    JPanel Panel3 = new JPanel();
	    Panel3.setPreferredSize(panelDimension);
	    Panel3.setBackground(Color.red);
	    //If we want more than one player can create a scoreboard for each player
	    Scoreboard scoreboard = new Scoreboard(this.player);
	    this.player.setScoreboard(scoreboard);
	    Panel3.add(scoreboard);
	    
	    mainpane.add(fillerPanel);
	    mainpane.add(myPanelView);
	    mainpane.add(Panel3);
	    
	    f.setSize(1500,1000);
	    f.setVisible(true);
	}
	
	public void createControls() {
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		startMenuItem = new JMenuItem("restart");
		menu.add(startMenuItem);
		startMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//this part of the code is just for testing purpose
				controller.reStartGame("intermediate","winter");
			}
		});
		menuBar.add(menu);
		f.setJMenuBar(menuBar);
	}
	
	public void enableReStartMenuItem() {
		startMenuItem.setEnabled(true);
	}
	
	public void disableReStartMenuItem() {
		startMenuItem.setEnabled(false);
	}
}
