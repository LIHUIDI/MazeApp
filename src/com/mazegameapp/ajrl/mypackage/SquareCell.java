package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Graphics;


public class SquareCell implements Cell{
	
	private int xPos;
    private int yPos;
    private int width;
    
    private boolean hasTopWall = true;
    private boolean hasButtomWall = true;
    private boolean hasLeftWall = true;
    private boolean hasRightWall = true;

    public SquareCell(int xPos, int yPos, int width) {
    	this.xPos = xPos;
    	this.yPos = yPos;
    	this.width = width;
    }
    
    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 
    
    public boolean hasTopWall() {
		return hasTopWall;
	}

	public boolean hasButtomWall() {
		return hasButtomWall;
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
	
	@Override
	public void paintCell(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(xPos,yPos,width,width);
	}
	
	public void removeRightWall(Graphics g) {
		hasRightWall = false;
		g.setColor(Color.WHITE);
        g.drawLine(xPos+width, yPos+1, xPos+width,yPos+width-1);
	}
	
	public void removeLeftWall(Graphics g) {
		hasLeftWall = false;
		g.setColor(Color.WHITE);
        g.drawLine(xPos, yPos+1, xPos,yPos+width-1);
	}
	
	public void removeBottomWall(Graphics g) {
		hasButtomWall = false;
		g.setColor(Color.WHITE);
        g.drawLine(xPos+1, yPos+width, xPos+width-1, yPos+width);
	}
	
	public void removeTopWall(Graphics g) {
		hasTopWall = false;
		g.setColor(Color.WHITE);
        g.drawLine(xPos+1, yPos, xPos+width-1, yPos);
	}
	
	public String toString() {
		return String.format("cell components are: x = %d, y = %d, width = %d", xPos, yPos, width);
	}
}
















