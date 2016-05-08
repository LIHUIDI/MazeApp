package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Graphics;

public class Gold implements Item{
	int value;

	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public void setValue(int value) {
		this.value = value;
	}


	@Override
	public void drawItem(Graphics g, SquareCell squareCell) {
		g.setColor(Color.YELLOW);
		g.fillOval(squareCell.getX()+squareCell.getWidth()/4, squareCell.getY()+squareCell.getWidth()/2, squareCell.getWidth()/4, squareCell.getWidth()/2);
	}
}
