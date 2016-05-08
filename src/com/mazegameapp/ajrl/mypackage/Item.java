package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;

public interface Item {
	public int getValue();
	public void setValue(int value);
	public void drawItem(Graphics g, SquareCell squareCell);
}
