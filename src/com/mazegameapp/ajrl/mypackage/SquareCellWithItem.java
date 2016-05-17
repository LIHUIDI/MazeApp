package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;

public class SquareCellWithItem extends SquareCell{
	
	Item item = null;
	boolean hasItem = false;
	
	public SquareCellWithItem(int xPos, int yPos, int width, Item item) {
		super(xPos, yPos, width);
		this.item = item;
		hasItem = true;
	}

	public boolean hasItem() {
		return hasItem;
	}
	
	public void collectItem() {
		hasItem = false;
	}
	
	@Override
	public void paintCell(Graphics g) {
		super.paintCell(g);
		if (hasItem) {
			item.drawItem(g, this);
		}
	}
}
