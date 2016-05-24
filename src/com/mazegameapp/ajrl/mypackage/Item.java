package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
/**
 * Item represents the item in cell.
 * 
 *
 */
public interface Item {
	/**
	 * 
	 * @return the value of such item
	 */
	public int getValue();
	/**
	 * Draw item in such a squareCell.
	 * 
	 * @param g
	 * @param squareCell the square cell where the item is in.
	 */
	public void drawItem(Graphics g, SquareCell squareCell);
	/**
	 * 
	 * @param theme the theme which defines the image of the item.
	 */
	public void changeItemTheme(String theme);
}
