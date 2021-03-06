package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
/**
 * 
 * Gold is a Item and has positive value.
 *
 */
public class Gold implements Item{
	
	// the default value of gold.
	private static final int INI_VALUE = 10;
	private int value = INI_VALUE;
	
	private BufferedImage image;
	private HashMap<String, String> goldImgs = new HashMap <>();
	//default cell background image.
	private String goldImgPath = "resources/image/springcoin.png"; 
	
	/**
	 * Constructor of Gold, using the default value and image of gold.
	 */
	public Gold() {
		defineGoldImage();
	}
	
	/**
	 * Constructor of Gold, using the value passed in and image of gold.
	 * @param value the value that this gold will be set to.
	 */
	public Gold(int value) {
		this.value = value;
		defineGoldImage();
	}
	
	public String getGoldImgPath() {
		return goldImgPath;
	}

	public void setGoldImgPath(String goldImgPath) {
		this.goldImgPath = goldImgPath;
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
		g.drawImage(image, squareCell.getX()+6, squareCell.getY()+6, squareCell.getWidth()-10, squareCell.getWidth()-10, null, null);
	}

	@Override
	public void changeItemTheme(String theme) {
		setGoldImgPath(goldImgs.get(theme));
	}
}
