package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	
	private BufferedImage scorePanelimage;
	private String scorePanelImgPath = "resources/image/scorePanel.png";
	
	public ScorePanel() {
		try {                
			scorePanelimage = ImageIO.read(new File(scorePanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    g.drawImage(scorePanelimage, 0, 0, this.getWidth(),this.getHeight(),null,null);
	}
}
