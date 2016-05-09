package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player{
	GridMaze gridMaze;
	SquareCell cell;
	private BufferedImage image;

	private void loadImage() {
	     try {                
	        image = ImageIO.read(new File("resources/player0.png"));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
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
		loadImage();
		g.drawImage(image, cell.getX()+1, cell.getY()+1, cell.getWidth()-2, cell.getWidth()-2, null, null);
	}
}
