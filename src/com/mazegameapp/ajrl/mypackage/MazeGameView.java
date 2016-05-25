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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import com.mazegameapp.ajrl.mypackage.ActionData.Difficultylevel;
import com.mazegameapp.ajrl.mypackage.ActionData.Theme;

/**
 * The top container of the View. It contains three Panels, 
 * {@code MyPanel} is the panel where maze map sitting on, {@code ScorePanel} shows the progress information such as scores that player got,
 * {@code FillerPanel} shows hint label where user want to check hint. 
 * It also contains menu bar and menu items that supplies settings and other controls of the game such as restart and exit.
 *
 */
public class MazeGameView implements ActionListener{
	ControllerInterface controller;
	private GridMaze gridMaze;
	private Player player;
	private Timer timer;
	
	private JFrame f;
	private MyPanel myPanelView;
	private ScorePanel scorePanel;
	
	private JMenuBar menuBar;
	private JMenu menu, settingMenu, difficultySettingMenu, themeSettingMenu, timerMenu;
	private JMenuItem startMenuItem, exitMenuItem, startTimer, setTimer;
	
	private String difficultySelelcted = ActionData.Difficultylevel.BEGINNER.toString();
	private String themeSelected = ActionData.Theme.WINTER.toString();
	private String timeSelected = "2";
	
	public MazeGameView (GridMaze gridMaze, Player player, Timer timer, ControllerInterface controller) {
		this.controller = controller;
    	this.gridMaze = gridMaze;
    	this.player = player;
    	this.timer = timer;
	}
	
	public MyPanel getMazePanel() {
		return myPanelView;
	}
	
	/**
	 * creates all the components and add them to the frame.
	 */
	public void createAndShowGUI() {
	    f = new JFrame("Maze Game");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    f.setSize(1500,1000); 
	    f.setResizable(false);
	    f.setVisible(true);
	    
	    f.setSize(1500,1000);
	    f.setVisible(true);
	    
	    JPanel mainpane = (JPanel) f.getContentPane();
	    mainpane.setLayout(new BoxLayout(mainpane, BoxLayout.X_AXIS));
	    
	    this.myPanelView = new MyPanel(gridMaze,player,controller);
	    myPanelView.setBackground(Color.yellow);
	    

	    FillerPanel fillerPanel = new FillerPanel(player,myPanelView);
	    Dimension panelDimension = new Dimension(100, 60);
	    fillerPanel.setPreferredSize(panelDimension);
	    fillerPanel.setBackground(Color.BLUE);
	    
	    scorePanel = new ScorePanel(this.player, this.timer);
	    scorePanel.setPreferredSize(new Dimension(400,60));
	    scorePanel.setBackground(Color.RED);

	    
	    mainpane.setOpaque(true);
	    mainpane.add(fillerPanel);
	    mainpane.add(myPanelView);

	    mainpane.add(scorePanel);
	    myPanelView.requestFocusInWindow();
	}
	
	/**
	 * creates menu bar and all menu items and add them to the frame.
	 */
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
	    beginnerButton.setActionCommand(ActionData.Difficultylevel.BEGINNER.toString());
	    beginnerButton.setSelected(true);
	    JRadioButton intermediateButton = new JRadioButton("Intermediate(I)");
	    intermediateButton.setMnemonic(KeyEvent.VK_I);
	    intermediateButton.setActionCommand(ActionData.Difficultylevel.INTERMEDIATE.toString());
	    intermediateButton.setSelected(true);
	    JRadioButton advancedButton = new JRadioButton("Advanced(A)");
	    advancedButton.setMnemonic(KeyEvent.VK_A);
	    advancedButton.setActionCommand(ActionData.Difficultylevel.ADVANCED.toString());
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
	    springButton.setActionCommand(ActionData.Theme.SPRING.toString());
	    
	    JRadioButton summerButton = new JRadioButton("summer(U)");
	    summerButton.setMnemonic(KeyEvent.VK_U);
	    summerButton.setActionCommand(ActionData.Theme.SUMMER.toString());
	    
	    JRadioButton autumnButton = new JRadioButton("Autumn(M)");
	    autumnButton.setMnemonic(KeyEvent.VK_M);
	    autumnButton.setActionCommand(ActionData.Theme.AUTUMN.toString());
	   
	    JRadioButton winterButton = new JRadioButton("winter(W)",true);
	    winterButton.setMnemonic(KeyEvent.VK_W);
	    winterButton.setActionCommand(ActionData.Theme.WINTER.toString());
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
	    
	    timerMenu = new JMenu("Timer");
	    startTimer = new JMenuItem("Start Timer");
	    startTimer.addActionListener(this);
	    startTimer.setActionCommand("startTimer");
	    timerMenu.add(startTimer);
	    
		setTimer = new JMenu("Set Time");
		String[] timeStrings = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
		JComboBox timeList = new JComboBox(timeStrings);
		timeList.setEditable(true);
		timeList.setSelectedIndex(0);
		//timeList.setActionCommand("settimer");
		timeList.addActionListener(this);
		setTimer.add(timeList);
	    timerMenu.add(setTimer);
	    
	    menuBar.add(timerMenu);
		f.setJMenuBar(menuBar);
	}
	
	public void enableReStartMenuItem() {
		startMenuItem.setEnabled(true);
	}
	
	public void disableReStartMenuItem() {
		startMenuItem.setEnabled(false);
	}
	
	public void disableStartTimerMenuItem() {
		startTimer.setEnabled(false);
	}
	
	public void enableStartTimerMenuItem(){
		startTimer.setEnabled(true);
	}
	
	/**
	 * Set the actions performed for menu items.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("newgame")) {
			controller.reStartGame(difficultySelelcted.toString(), themeSelected.toString());
		} else if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("startTimer")) {
			controller.startTimer(Integer.parseInt(timeSelected));
		} else if (e.getSource() instanceof JComboBox) {
			System.out.println("user has selected timer");
			timeSelected = (String)((JComboBox)e.getSource()).getSelectedItem();
			System.out.println("selected timer is " + timeSelected);
		} else if (e.getActionCommand().equals(ActionData.Difficultylevel.BEGINNER.toString())) {
			difficultySelelcted = ActionData.Difficultylevel.BEGINNER.toString();
		} else if (e.getActionCommand().equals(ActionData.Difficultylevel.INTERMEDIATE.toString())) {
			difficultySelelcted = ActionData.Difficultylevel.INTERMEDIATE.toString();
		} else if (e.getActionCommand().equals(ActionData.Difficultylevel.ADVANCED.toString())) {
			difficultySelelcted = ActionData.Difficultylevel.ADVANCED.toString();
		} else if (e.getActionCommand().equals(ActionData.Theme.SPRING.toString())) {
			themeSelected = ActionData.Theme.SPRING.toString();
		} else if (e.getActionCommand().equals(ActionData.Theme.SUMMER.toString())) {
			themeSelected = ActionData.Theme.SUMMER.toString();
		} else if (e.getActionCommand().equals(ActionData.Theme.AUTUMN.toString())) {
			themeSelected = ActionData.Theme.AUTUMN.toString();
		} else if (e.getActionCommand().equals(ActionData.Theme.WINTER.toString())){
			themeSelected = ActionData.Theme.WINTER.toString();
		} else {
			// do nothing
		}
	}
	
	public void cancelFinishedMessage() {
		 scorePanel.resetFinishedBoard();
	}
}
