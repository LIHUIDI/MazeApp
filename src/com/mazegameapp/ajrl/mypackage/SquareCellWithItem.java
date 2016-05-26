package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
/**
 * SquareCellWithItem has an extra state {@code Item} that indicates it is a special {@code SquareCell}.
 *
 */
public class SquareCellWithItem extends SquareCell{
	
	Item item = null;
	boolean hasItem = false;
	
	public SquareCellWithItem(int xPos, int yPos, int width, Item item) {
		super(xPos, yPos, width);
		this.item = item;
		hasItem = true;
	}
	
	/**
	 * 
	 * @return the Item this cell has.
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * Test if this cell still has any item.
	 * @return
	 */
	public boolean hasItem() {
		return hasItem;
	}
	/**
	 * change the state of the cell, it will has no item at all.
	 */
	public void collectItem() {
		hasItem = false;
	}
	
	/**
	 * Paint the cell first, then paint the item it contains.
	 */
	@Override
	public void paintCell(Graphics g) {
		super.paintCell(g);
		if (hasItem) {
			item.drawItem(g, this);
		}
	}
}
