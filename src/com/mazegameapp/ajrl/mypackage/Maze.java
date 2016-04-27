package com.mazegameapp.ajrl.mypackage;

import java.awt.Component;
import java.awt.Graphics;

public abstract class Maze extends Component{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void generateMaze(Graphics g);
}
