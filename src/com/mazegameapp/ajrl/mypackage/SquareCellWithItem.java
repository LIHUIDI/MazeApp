package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;

public class SquareCellWithItem extends SquareCell{
	
	Item item = null;
	
	public SquareCellWithItem(int xPos, int yPos, int width, Item item) {
		super(xPos, yPos, width);
		this.item = item;
	}

	@Override
	public void paintCell(Graphics g) {
		super.paintCell(g);
		item.drawItem(g, this);
	}
}
