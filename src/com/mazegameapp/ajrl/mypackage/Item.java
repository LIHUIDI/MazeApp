package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;

public interface Item {
	public int getValue();
	public void drawItem(Graphics g, SquareCell squareCell);
	public void changeItemTheme(String theme);
}
