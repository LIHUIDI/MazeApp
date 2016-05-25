package com.mazegameapp.ajrl.mypackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class SquareCell{
	
	private int xPos;
    private int yPos;
    private int width;
    private char hint; // the direction towards ending point in r l d t 
    
    private int thicknessOfWall = 5;
    private boolean hasTopWall = true;
    private boolean hasBottomWall = true;
    private boolean hasLeftWall = true;
    private boolean hasRightWall = true;
    
    private HashMap<String, String> squareCellBackGroundImgs = new HashMap <>();
    private HashMap<String, HashMap<String, String>> wallImgs = new HashMap <>();
    
    private String squareCellBackGroundImgPath = "resources/image/wintercell.png"; //default cell background image.
    
    private String topWallImag = "resources/image/winterupwall.png";
	private String bottomWallImag = "resources/image/winterdownwall.png";
    private String leftWallImag = "resources/image/winterleftwall.png";
    private String rightWallImag = "resources/image/winterrightwall.png";
    
    public SquareCell() {
    	defineSquareCellBackGroudImage();
    	defineWallImage();
    }
    
    private void setTopWallImag(String topWallImag) {
		this.topWallImag = topWallImag;
	}

	private void setBottomWallImag(String bottomWallImag) {
		this.bottomWallImag = bottomWallImag;
	}

	private void setLeftWallImag(String leftWallImag) {
		this.leftWallImag = leftWallImag;
	}

	private void setRightWallImag(String rightWallImag) {
		this.rightWallImag = rightWallImag;
	}
	
	public String getTopWallImag() {
		return topWallImag;
	}

	public String getBottomWallImag() {
		return bottomWallImag;
	}

	public String getLeftWallImag() {
		return leftWallImag;
	}

	public String getRightWallImag() {
		return rightWallImag;
	}
    
    public String getSquareCellBackGroundImgPath() {
		return squareCellBackGroundImgPath;
	}

	private void setSquareCellBackGroundImgPath(String squareCellBackGroundImgPath) {
		this.squareCellBackGroundImgPath = squareCellBackGroundImgPath;
	}
	
    public SquareCell(int xPos, int yPos, int width) {
    	this.xPos = xPos;
    	this.yPos = yPos;
    	this.width = width;
    	defineSquareCellBackGroudImage();
    	defineWallImage();
    }
    
    private void defineSquareCellBackGroudImage() {
    	squareCellBackGroundImgs.put("spring", "resources/image/springcell.png");
    	squareCellBackGroundImgs.put("summer", "resources/image/summercell.png");
    	squareCellBackGroundImgs.put("autumn", "resources/image/autumncell.png");
    	squareCellBackGroundImgs.put("winter", "resources/image/wintercell.png");
    }
    
    private void defineWallImage() {
    	HashMap<String,String> springWallImgs = new HashMap<>();
    	springWallImgs.put("up", "resources/image/springupwall.png");
    	springWallImgs.put("down","resources/image/springdownwall.png");
    	springWallImgs.put("left","resources/image/springleftwall.png");
    	springWallImgs.put("right","resources/image/springrightwall.png");
    	wallImgs.put("spring", springWallImgs);
    	HashMap<String,String> summerWallImgs = new HashMap<>();
    	summerWallImgs.put("up", "resources/image/summerupwall.png");
    	summerWallImgs.put("down","resources/image/summerdownwall.png");
    	summerWallImgs.put("left","resources/image/summerleftwall.png");
    	summerWallImgs.put("right","resources/image/summerrightwall.png");
    	wallImgs.put("summer", summerWallImgs);
    	HashMap<String,String> autumnWallImgs = new HashMap<>();
    	autumnWallImgs.put("up", "resources/image/autumnupwall.png");
    	autumnWallImgs.put("down","resources/image/autumndownwall.png");
    	autumnWallImgs.put("left","resources/image/autumnleftwall.png");
    	autumnWallImgs.put("right","resources/image/autumnrightwall.png");
    	wallImgs.put("autumn", autumnWallImgs);
    	HashMap<String,String> winterWallImgs = new HashMap<>();
    	winterWallImgs.put("up", "resources/image/winterupwall.png");
    	winterWallImgs.put("down","resources/image/winterdownwall.png");
    	winterWallImgs.put("left","resources/image/winterleftwall.png");
    	winterWallImgs.put("right","resources/image/winterrightwall.png");
    	wallImgs.put("winter", winterWallImgs);
    }
    
    public char getHint(){
    	return this.hint;
    }
    
    public void setHint(char c){
    	this.hint = c;
    }
    
    public void setX(int x) {
    	this.xPos = x;
    }
    
    public int getX(){
        return xPos;
    }
    
    public void setY(int y) {
    	this.yPos = y;
    }
    
    public int getY(){
        return yPos;
    }
    
    public void setWidth(int width){
    	this.width = width;
    }
    
    public int getWidth(){
        return width;
    } 
    
    public boolean hasTopWall() {
		return hasTopWall;
	}

	public boolean hasButtomWall() {
		return hasBottomWall;
	}

	public boolean hasLeftWall() {
		return hasLeftWall;
	}

	public boolean hasRightWall() {
		return hasRightWall;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) return true;
		if (other == null) return false;
		boolean result = false;
		if (other instanceof SquareCell) {
			SquareCell that = (SquareCell) other;
			return (that.canEqual(this)) && that.xPos == this.xPos 
					&& that.yPos == this.yPos && that.width == this.width;
		}
		return result;
	}
	
	public boolean canEqual(Object other) {
		return (other instanceof SquareCell);
	}
	
	@Override
	public int hashCode() {
		return 41 * (41 + (41 * (41 + xPos) + yPos)) + width;
	}
	
	public void paintCell(Graphics g) {
		 BufferedImage image = null;
	     try {	 
	    	 String path = this.getSquareCellBackGroundImgPath();
	    	 image = ImageIO.read(new File(path));
		    }
		     catch (IOException ex){
		    }
		     g.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getWidth(), null, null);
		     drawWalls(g);
	}
	
	public void removeRightWall() {
		hasRightWall = false;
	}
	
	public void removeLeftWall() {
		hasLeftWall = false;
	}
	
	public void removeBottomWall() {
		hasBottomWall = false;
	}
	
	public void removeTopWall() {
		hasTopWall = false;
	}
	
	private void drawWalls(Graphics g){
		BufferedImage topWallimage = null;
		BufferedImage bottomWallimage = null;
		BufferedImage leftWallimage = null;
		BufferedImage rightWallimage = null;
	     try {
	    	 topWallimage = ImageIO.read(new File(this.getTopWallImag()));
	    	 bottomWallimage = ImageIO.read(new File(this.getBottomWallImag()));
	    	 leftWallimage = ImageIO.read(new File(this.getLeftWallImag()));
	    	 rightWallimage = ImageIO.read(new File(this.getRightWallImag()));
		    }
		     catch (IOException ex){
		    }
	    
		if(this.hasRightWall) g.drawImage(rightWallimage, xPos+width-thicknessOfWall, yPos, thicknessOfWall,width, null,null);
		if(this.hasLeftWall) g.drawImage(leftWallimage, xPos, yPos, thicknessOfWall, width, null, null);
		if(this.hasBottomWall) g.drawImage(bottomWallimage, xPos, yPos+width-thicknessOfWall, width, thicknessOfWall,null,null);
		if(this.hasTopWall) g.drawImage(topWallimage,xPos, yPos, width, thicknessOfWall,null,null);
	}

	public void changeSquareCellBackGroundImgPath(String themeName) {
		setSquareCellBackGroundImgPath(squareCellBackGroundImgs.get(themeName));
	}
	
	public void changeWallImgs(String themeName) {
		setTopWallImag(wallImgs.get(themeName).get("up"));
		setBottomWallImag(wallImgs.get(themeName).get("down"));
		setLeftWallImag(wallImgs.get(themeName).get("left"));
		setRightWallImag(wallImgs.get(themeName).get("right"));
	}
}
















