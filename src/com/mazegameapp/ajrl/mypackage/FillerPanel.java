package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FillerPanel extends JPanel{
	private BufferedImage fillPanelimage;
	private String fillPanelImgPath = "resources/image/fillPanel.png";
	
	public FillerPanel() {
		try {                
			fillPanelimage = ImageIO.read(new File(fillPanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(fillPanelimage, 0, 0,this.getWidth(),this.getHeight(),null, null);
	}
}
