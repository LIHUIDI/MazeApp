package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ChronoRift implements Item{
	private int toPosX;
	private int toPosY;
	private BufferedImage image;
	private HashMap<String, String> TeleportImgs = new HashMap <>();
	private String TeleportImgPath = "resources/image/winterTeleport.png"; //default cell background image.
	private boolean used = false;
	public ChronoRift() {
		defineTeleportImage();
	}
	
	public ChronoRift(int x, int y) {
		this.toPosX = x;
		this.toPosY = y;
		defineTeleportImage();
	}

	public int getToPosX(){
		return this.toPosX;
	}
	
	public int getToPosY(){
		return this.toPosY;
	}
	
	public boolean getUsed(){
		return this.used;
	}
	
	public void setUsed(boolean b){
		this.used = b;
	}
	
	private void defineTeleportImage() {//may change after more png is found
		TeleportImgs.put("spring", "resources/image/rift.jpeg");
		TeleportImgs.put("summer", "resources/image/rift.jpeg");
		TeleportImgs.put("autumn", "resources/image/rift.jpeg");
		TeleportImgs.put("winter", "resources/image/rift.jpeg");
    }
	
	private void loadImage() {
	     try {                
	        image = ImageIO.read(new File(TeleportImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
	public String getTeleportImgPath() {
		return TeleportImgPath;
	}

	public void setTeleportImgPath(String TeleportImgPath) {
		this.TeleportImgPath = TeleportImgPath;
	}


	@Override
	public void drawItem(Graphics g, SquareCell squareCell) {
		loadImage();
		g.drawImage(image, squareCell.getX()+2, squareCell.getY()+2, squareCell.getWidth()-4, squareCell.getWidth()-4, null, null);
	}

	@Override
	public void changeItemTheme(String theme) {
		setTeleportImgPath(TeleportImgs.get(theme));
	}


@Override
public int getValue() {
	// TODO Auto-generated method stub
	return 0;
}}
