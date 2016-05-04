package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Graphics;

public class Player{
	GridMaze gridMaze;
	SquareCell cell;
	
	public Player(GridMaze gridMaze) {
		this.gridMaze = gridMaze;
	}
	
	public void setCurrentCell(SquareCell cell) {
		this.cell = cell;
	}
	
	public SquareCell getCurrentCell() {
		return cell;
	}
	
	public void paintPlayer(Graphics g) {
		g.setColor(Color.BLACK);
        g.fillArc(cell.getX()+cell.getWidth()/4, cell.getY()+cell.getWidth()/4, cell.getWidth()/2, cell.getWidth()/2, 0, 360);
	}
}
