package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TeleportCell extends SquareCell{
	//The position of the next cell player will be transfered to.
	private int toPosX;
	private int toPosY;
	// private char hint; // hint should contains another char to indicates there is no hint at all.
	private String TeleportCellBackGroundImgPath = "resources/image/blackhole.png"; //default background image
	
	public TeleportCell(int i, int j, int width) {
		super(i,j,width);
	}
	
	public int getToPosX() {
		return toPosX;
	}

	public int getToPosY() {
		return toPosY;
	}
	
	public void setToPosX(int x) {
		toPosX = x;
	}
	
	public void setToPosY(int y) {
		toPosY = y;
	}
	
	//simply overwrite this method to paint the teleport cell.
	public void paintCell(Graphics g) {
		 BufferedImage image = null;
	     try {	 
	    	 String path = TeleportCellBackGroundImgPath;
	    	 image = ImageIO.read(new File(path));
		    }
		     catch (IOException ex){
		    }
		     g.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getWidth(), null, null);
		     super.drawWalls(g);
	}
}
