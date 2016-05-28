package com.mazegameapp.ajrl.mypackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Panel where maze would be drawn on. It listens any key stroke that would communicates with {@code ControllerInterface} that
 * such user action has happened. When player finished a movement, it will redraw the cell and player that reflects these change. 
 *
 */
public class MyPanel extends JPanel implements KeyListener, PlayerMovementObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ControllerInterface controller;
	// several model class
	private GridMaze gridMaze = new GridMaze();
	private Player player;
	private boolean canDrawMaze = true;
	private boolean playerMovement = false;
	
	private BufferedImage myPanelimage;
	private String myPanelImgPath = "resources/image/myPanel.png";
	
    public MyPanel(GridMaze gridMaze, Player player, ControllerInterface controller) {
    	this.controller = controller;
    	this.gridMaze = gridMaze;
    	this.player = player;
		this.player.registerplayerMovementObservers(this);
		try {                
			myPanelimage = ImageIO.read(new File(myPanelImgPath));
	     } catch (IOException ex) {
	            // handle exception...
	     }
        addKeyListener(this);
        this.setSize(1000, 1000);
        setFocusable(true);
        requestFocusInWindow();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }
    
    public void setCanDrawMaze(boolean a) {
    	canDrawMaze = a;
    }
    /**
     * paint the maze, player.
     */
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(myPanelimage, 0, 0, 1000,1000,null,null);
    	
        if (canDrawMaze) {
        	drawGridMaze(g);
            player.paintPlayer(g);
            canDrawMaze = false;
        } else {
        	if (playerMovement) {
        		// paint rectangle from previous cell to current cell
        		int x1 = player.previousCell.getX();
        		int y1 = player.previousCell.getY();
        		int x2 = player.cell.getX();
        		int y2 = player.cell.getY();
        		int w = player.cell.getWidth();
        		paintRectangle(x1, y1, x2, y2, w, g);
        		player.paintPlayer(g);
        	}
        	playerMovement = false;
        }
    }
    
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			controller.move(ActionData.MoveDirection.LEFT.toString());
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			controller.move(ActionData.MoveDirection.RIGHT.toString());
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			controller.move(ActionData.MoveDirection.UP.toString());
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controller.move(ActionData.MoveDirection.DOWN.toString());
		} else {
			//
			System.out.println("shouldn't be here");
		}		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void drawGridMaze(Graphics g) {
		SquareCell[][] grid = gridMaze.getGrid();
		int width = (int)(this.getPreferredSize().getWidth()/grid.length);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j].setX(i * width);
				grid[i][j].setY(j * width);
				grid[i][j].setWidth(width);
				grid[i][j].paintCell(g);
			}
		}
	}
	/**
	 * Redraw the panel to reflect player has finished certain movement.
	 */
	@Override
	public void finishMovement(boolean done) {
		if (done) {
			this.playerMovement = done;
			SquareCell cell = player.getPreviousCell();
			int squareX = cell.getX();
		    int squareY = cell.getY();
		    int squareW = cell.getWidth();
		    int squareH = cell.getWidth();
		    repaint(squareX,squareY,squareW,squareH);
		    cell = player.getCurrentCell();
		    squareX = cell.getX();
		    squareY = cell.getY();
		    squareW = cell.getWidth();
		    squareH = cell.getWidth();
		    repaint(squareX,squareY,squareW,squareH);
		}
	}
	/**
	 * Set the panel state to "can draw", and repaint the panel.
	 */
	public void finishSetting() {
		setCanDrawMaze(true);
        repaint(0,0,this.getSize().width,this.getSize().width);
	}
	
	private void paintRectangle(int x1, int y1, int x2, int y2, int width, Graphics g){
		SquareCell[][] grid = gridMaze.getGrid();
		//current cell is in the south east corner of rectangle
		if (x2 >= x1 && y2 >= y1) {
			for (int i = x1/width; i <= x2/width; i = i + 1) {
				for (int j = y1/width; j <= y2/width; j = j + 1) {
					grid[i][j].paintCell(g);
				}
			}
		} else if (x2 >= x1 && y2 <= y1) {
			//north east
			for (int i = x1/width; i <= x2/width; i = i + 1) {
				for (int j = y1/width; j >= y2/width; j = j - 1) {
					grid[i][j].paintCell(g);
				}
			}
		} else if (x2 <= x1 && y2 >= y1) {
			//south west
			for (int i = x1/width; i >= x2/width; i = i - 1) {
				for (int j = y1/width; j <= y2/width; j = j + 1) {
					grid[i][j].paintCell(g);
				}
			}
		} else if (x1 >= x2 && y1 >= y2){
			//north west
			for (int i = x1/width; i >= x2/width; i = i - 1) {
				for (int j = y1/width; j >= y2/width; j = j - 1) {
					grid[i][j].paintCell(g);
				}
			}
		} else {
			//shouldn't be here.
		}
	}
}
