package com.mazegameapp.ajrl.mypackage;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	
	public FillerPanel(Player player, MyPanel myPanelView) {
		super(new FlowLayout(1,10,400));//layout decides what position the lable
		this.myPanelView = myPanelView;

		try {                
			fillPanelimage = ImageIO.read(new File(fillPanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
		
		this.player = player;
		this.player.registerplayerMovementObservers(this);
	
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
		Image img;
		
		try {//resize the image, it would be nice if dimensions are in scale
			img = ImageIO.read(new File("resources/image/hintImage.jpg"));
			Image newimg = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon hintIcon = new ImageIcon(newimg);
			
		hintBoard = new JLabel("",hintIcon,JLabel.CENTER);
		this.add(hintBoard);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(fillPanelimage, 0, 0,this.getWidth(),this.getHeight(),null, null);
	}

	@Override
	public void finishMovement(boolean done) {
		if (done) {
			String path = null;
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
			else{
				path = "resources/image/hintImage.jpg";
			}
			Image img;
			try {
				img = ImageIO.read(new File(path));
			
			Image newimg = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon hintIcon = new ImageIcon(newimg);
			hintBoard.setIcon(hintIcon);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
