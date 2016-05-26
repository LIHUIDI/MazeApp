package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Skull implements Item{
	private static final int INI_VALUE = -10;
	private int value = INI_VALUE;
	
	private BufferedImage image;
	private HashMap<String, String> skullImgs = new HashMap <>();
	private String skullImgPath = "resources/image/winterskull.png"; //default cell background image.
	
	public Skull() {
		defineSkullImage();
	}
	
	public Skull(int value) {
		this.value = value;
		defineSkullImage();
	}
	
	private void defineSkullImage() {
		skullImgs.put("spring", "resources/image/springskull.png");
		skullImgs.put("summer", "resources/image/summerskull.png");
		skullImgs.put("autumn", "resources/image/autumnskull.png");
		skullImgs.put("winter", "resources/image/winterskull.png");
    }
	
	private void loadImage() {
	     try {                
	        image = ImageIO.read(new File(skullImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
	public String getSkullImgPath() {
		return skullImgPath;
	}

	public void setSkullImgPath(String skullImgPath) {
		this.skullImgPath = skullImgPath;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void drawItem(Graphics g, SquareCell squareCell) {
		loadImage();
		g.drawImage(image, squareCell.getX()+6, squareCell.getY()+6, squareCell.getWidth()-10, squareCell.getWidth()-10, null, null);
	}

	@Override
	public void changeItemTheme(String theme) {
		setSkullImgPath(skullImgs.get(theme));
	}
}
