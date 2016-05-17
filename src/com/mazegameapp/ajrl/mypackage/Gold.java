package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gold implements Item{
	int value;
	
	private BufferedImage image;

	private void loadImage() {
	     try {                
	        image = ImageIO.read(new File("resources/image/gold.png"));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
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
		loadImage();
		g.drawImage(image, squareCell.getX()+2, squareCell.getY()+2, squareCell.getWidth()-4, squareCell.getWidth()-4, null, null);
	}
}
