package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Teleport implements Item{
	private SquareCell toCell;
	
	private BufferedImage image;
	private HashMap<String, String> TeleportImgs = new HashMap <>();
	private String TeleportImgPath = "resources/image/winterTeleport.png"; //default cell background image.
	
	public Teleport() {
		defineTeleportImage();
	}
	
	public Teleport(SquareCell toCell) {
		this.toCell = toCell;
		defineTeleportImage();
	}
	
	public SquareCell getToCell(){
		return this.toCell;
	}
	
	private void defineTeleportImage() {//may change after more png is found
		TeleportImgs.put("spring", "resources/image/Teleport.png");
		TeleportImgs.put("summer", "resources/image/Teleport.png");
		TeleportImgs.put("autumn", "resources/image/Teleport.png");
		TeleportImgs.put("winter", "resources/image/Teleport.png");
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
