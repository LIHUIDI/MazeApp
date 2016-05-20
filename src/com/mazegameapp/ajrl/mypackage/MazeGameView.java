package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class MazeGameView implements ActionListener{
	ControllerInterface controller;
	private GridMaze gridMaze;
	private Player player;
	
	private JFrame f;
	private MyPanel myPanelView;
	
	private JMenuBar menuBar;
	private JMenu menu, settingMenu, difficultySettingMenu, themeSettingMenu;
	private JMenuItem startMenuItem;
	private JMenuItem exitMenuItem;
	
	private String difficultySelelcted = "beginner";
	private String themeSelected = "winter";
	
	
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
	    f.setSize(1500,1000); 
	    f.setResizable(false);
	    f.setVisible(true);
	    
	    JPanel mainpane = (JPanel) f.getContentPane();
	    mainpane.setLayout(new BoxLayout(mainpane, BoxLayout.X_AXIS));
	    
	    this.myPanelView = new MyPanel(gridMaze,player,controller);
	    myPanelView.setBackground(Color.yellow);
	    
	    FillerPanel fillerPanel = new FillerPanel();
	    Dimension panelDimension = new Dimension(100, 60);
	    fillerPanel.setPreferredSize(panelDimension);
	    fillerPanel.setBackground(Color.BLUE);
	    
	    ScorePanel scorePanel = new ScorePanel();
	    scorePanel.setPreferredSize(new Dimension(400,60));
	    Scoreboard scoreboard = new Scoreboard(this.player);
	    scorePanel.add(scoreboard);
	    scorePanel.setBackground(Color.RED);
	    
	    mainpane.setOpaque(true);
	    mainpane.add(fillerPanel);
	    mainpane.add(myPanelView);
	    mainpane.add(scorePanel);
	    
	}
	
	public void createControls() {
		menuBar = new JMenuBar();
		
		//Game menu
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);
		
		//menu Items belong to Game menu
		startMenuItem = new JMenuItem("New Game(N)");
		startMenuItem.setMnemonic('N');
		startMenuItem.addActionListener(this);
		startMenuItem.setActionCommand("newgame");
		menu.add(startMenuItem);
		
		exitMenuItem = new JMenuItem("Exit(E)");
		exitMenuItem.setMnemonic('E');
		exitMenuItem.addActionListener(this);
		exitMenuItem.setActionCommand("exit");
		menu.add(exitMenuItem);
		
		//Setting Menu
		difficultySettingMenu = new JMenu("difficulty");
		difficultySettingMenu.setMnemonic('D');

		//create difficulty level radioButton
		JRadioButton beginnerButton = new JRadioButton("Beginner(B)");
	    beginnerButton.setMnemonic(KeyEvent.VK_B);
	    beginnerButton.setActionCommand("beginner");
	    beginnerButton.setSelected(true);
	    JRadioButton intermediateButton = new JRadioButton("Intermediate(I)");
	    intermediateButton.setMnemonic(KeyEvent.VK_I);
	    intermediateButton.setActionCommand("intermediate");
	    intermediateButton.setSelected(true);
	    JRadioButton advancedButton = new JRadioButton("Advanced(A)");
	    advancedButton.setMnemonic(KeyEvent.VK_A);
	    advancedButton.setActionCommand("advanced");
	    advancedButton.setSelected(true);
	    
	    beginnerButton.addActionListener(this);
	    intermediateButton.addActionListener(this);
	    advancedButton.addActionListener(this);
	    
	    ButtonGroup difficultyGroup = new ButtonGroup();
	    difficultyGroup.add(beginnerButton);
	    difficultyGroup.add(intermediateButton);
	    difficultyGroup.add(advancedButton);
	    
	    difficultySettingMenu.add(beginnerButton);
	    difficultySettingMenu.add(intermediateButton);
	    difficultySettingMenu.add(advancedButton);
	    
	    themeSettingMenu = new JMenu("Theme");
	    themeSettingMenu.setMnemonic('T');
	    
	    JRadioButton springButton = new JRadioButton("Spring(P)");
	    springButton.setMnemonic(KeyEvent.VK_P);
	    springButton.setActionCommand("spring");
	    //springButton.setSelected(true);
	    JRadioButton summerButton = new JRadioButton("summer(U)");
	    summerButton.setMnemonic(KeyEvent.VK_U);
	    summerButton.setActionCommand("summer");
	    //summerButton.setSelected(true);
	    JRadioButton autumnButton = new JRadioButton("Autumn(M)");
	    autumnButton.setMnemonic(KeyEvent.VK_M);
	    autumnButton.setActionCommand("autumn");
	    //autumnButton.setSelected(true);
	    JRadioButton winterButton = new JRadioButton("winter(W)",true);
	    winterButton.setMnemonic(KeyEvent.VK_W);
	    winterButton.setActionCommand("winter");
	    winterButton.setSelected(true);
	    
	    springButton.addActionListener(this);
	    summerButton.addActionListener(this);
	    autumnButton.addActionListener(this);
	    winterButton.addActionListener(this);
	    
	    ButtonGroup themeyGroup = new ButtonGroup();
	    themeyGroup.add(springButton);
	    themeyGroup.add(summerButton);
	    themeyGroup.add(autumnButton);
	    themeyGroup.add(winterButton);
	    
	    themeSettingMenu.add(springButton);
	    themeSettingMenu.add(summerButton);
	    themeSettingMenu.add(autumnButton);
	    themeSettingMenu.add(winterButton);
	    
	    settingMenu = new JMenu("Setting");
	    settingMenu.setMnemonic(KeyEvent.VK_S);
	    settingMenu.add(difficultySettingMenu);
	    settingMenu.add(themeSettingMenu);
		menuBar.add(settingMenu);
		
		f.setJMenuBar(menuBar);
	}
	
	public void enableReStartMenuItem() {
		startMenuItem.setEnabled(true);
	}
	
	public void disableReStartMenuItem() {
		startMenuItem.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("newgame")) {
			controller.reStartGame(difficultySelelcted, themeSelected);
		} else if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("beginner")) {
			difficultySelelcted = "beginner";
		} else if (e.getActionCommand().equals("intermediate")) {
			difficultySelelcted = "intermediate";
		} else if (e.getActionCommand().equals("advanced")) {
			difficultySelelcted = "advanced";
		} else if (e.getActionCommand().equals("spring")) {
			themeSelected = "spring";
		} else if (e.getActionCommand().equals("summer")) {
			themeSelected = "summer";
		} else if (e.getActionCommand().equals("autumn")) {
			themeSelected = "autumn";
		} else if (e.getActionCommand().equals("winter")){
			themeSelected = "winter";
		} else {
			// do nothing
		}
		
	}
}
