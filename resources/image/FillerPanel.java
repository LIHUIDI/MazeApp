package com.mazegameapp.ajrl.mypackage;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FillerPanel extends JPanel implements PlayerMovementObserver{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage fillPanelimage;
	private String fillPanelImgPath = "resources/image/fillPanel.png";
	private JLabel hintBoard;
	private Player player;
	private boolean showHint = false;
	private MyPanel myPanelView;
	
	public FillerPanel(Player player, final MyPanel myPanelView) {
		super(new FlowLayout(FlowLayout.CENTER,10,10));
		this.myPanelView = myPanelView;

		try {                
			fillPanelimage = ImageIO.read(new File(fillPanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
		
		this.player = player;
		this.player.registerplayerMovementObservers(this);
		ImageIcon hintIcon = new ImageIcon("resources/image/hintImage.jpg", "hint");
		hintBoard = new JLabel("",hintIcon,JLabel.CENTER);
		this.add(hintBoard);
		JCheckBox hintSwitch = new JCheckBox("<html> <font color = 'WHITE' face = 'Impact' size='5'>Show hint</font></html>",null,false);
		this.add(hintSwitch);
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	 if (showHint == false){
		    		 showHint = true;
		    	 }else{
		    		 showHint = false;
		    	 }
		    	 finishMovement(true);
		    	 myPanelView.requestFocus();
		      }
		    };
		hintSwitch.addActionListener(actionListener);
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(fillPanelimage, 0, 0,this.getWidth(),this.getHeight(),null, null);
	}

	@Override
	public void finishMovement(boolean done) {
		if (done) {
			String path = "resources/image/hintImage.jpg";
			if(showHint){
				char h = player.getCurrentCell().getHint();
				if(h == 'r'){
					path = "resources/image/hintImageR.jpg";
				}else if(h == 'l'){
					path = "resources/image/hintImageL.jpg";
				}else if(h == 't'){
					path = "resources/image/hintImageT.jpg";
				}else if(h == 'd'){
					path = "resources/image/hintImageD.jpg";
				}
			}
			
			ImageIcon hintIcon = new ImageIcon(path);
			hintBoard.setIcon(hintIcon);
			
		}
	}
}
