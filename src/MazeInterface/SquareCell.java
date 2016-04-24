package MazeInterface;

import java.awt.Color;
import java.awt.Graphics;


public class SquareCell extends Cell{
	
	private int xPos;
    private int yPos;
    private int width;

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
		g.setColor(Color.WHITE);
        g.fillRect(xPos,yPos,width,width);
        g.setColor(Color.BLACK);
        g.drawRect(xPos,yPos,width,width);
		
	}
	
	public void removeRightWall(Graphics g) {
		g.setColor(Color.WHITE);
        g.drawLine(xPos+width, yPos, xPos+width,yPos+width);
	}
	
	public void removeLeftWall(Graphics g) {
		g.setColor(Color.WHITE);
        g.drawLine(xPos, yPos, xPos,yPos+width);
	}
	
	public void removeBottomWall(Graphics g) {
		g.setColor(Color.WHITE);
        g.drawLine(xPos, yPos+width, xPos+width, yPos+width);
	}
	
	public void removeTopWall(Graphics g) {
		g.setColor(Color.WHITE);
        g.drawLine(xPos, yPos, xPos+width, yPos);
	}
}
















