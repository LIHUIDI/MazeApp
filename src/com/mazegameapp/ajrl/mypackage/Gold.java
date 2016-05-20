package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Gold implements Item{
	private static final int INI_VALUE = 10;
	private int value = INI_VALUE;
	
	private BufferedImage image;
	private HashMap<String, String> goldImgs = new HashMap <>();
	private String goldImgPath = "resources/image/wintercoin.png"; //default cell background image.
	
	public String getGoldImgPath() {
		return goldImgPath;
	}

	public void setGoldImgPath(String goldImgPath) {
		this.goldImgPath = goldImgPath;
	}
	
	public Gold() {
		defineGoldImage();
	}
	
	public Gold(int value) {
		this.value = value;
		defineGoldImage();
	}
	
	private void defineGoldImage() {
		goldImgs.put("spring", "resources/image/springcoin.png");
		goldImgs.put("summer", "resources/image/summercoin.png");
		goldImgs.put("autumn", "resources/image/autumncoin.png");
		goldImgs.put("winter", "resources/image/wintercoin.png");
    }
	
	private void loadImage() {
	     try {                
	        image = ImageIO.read(new File(goldImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public void drawItem(Graphics g, SquareCell squareCell) {
		loadImage();
		g.drawImage(image, squareCell.getX()+2, squareCell.getY()+2, squareCell.getWidth()-4, squareCell.getWidth()-4, null, null);
	}

	@Override
	public void changeItemTheme(String theme) {
		setGoldImgPath(goldImgs.get(theme));
	}
}
