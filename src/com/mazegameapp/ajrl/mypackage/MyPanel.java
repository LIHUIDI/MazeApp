package com.mazegameapp.ajrl.mypackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridMaze gridMaze = new GridMaze();
	Player player;
	
	private int squareX;
    private int squareY;
    private int squareW;
    private int squareH;

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.white);
        this.add(gridMaze);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    

    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }
    
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (player == null) {
        	gridMaze.initializeGridMaze();
            gridMaze.generateMaze(g);
            player = new Player(gridMaze);
            player.setCurrentCell((gridMaze.getGrid())[0][0]);
            player.paintPlayer(g);
        } else {
        	player.paintPlayer(g);
        }
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("hiohio" + "key code = " + e.getKeyCode()
        + " ("
        + KeyEvent.getKeyText(e.getKeyCode())
        + ")");
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown();
		} else {
			//
			System.out.println("shouldn't be here");
		}		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void moveUp() {
    	SquareCell cell = player.getCurrentCell();
		if (!cell.hasTopWall()) {
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
		    int OFFSET = 1;
		    repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
			//update current cell the player is in.
			cell = gridMaze.getGrid()[cell.getX()/cell.getWidth()][(cell.getY()- cell.getWidth())/cell.getWidth()];
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
			player.setCurrentCell(cell);
			repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
			//cell.getX(),cell.getY(), cell.getWidth(), cell.getWidth()
		}
	}
	
	private void moveDown() {
		SquareCell cell = player.getCurrentCell();
		if (!cell.hasButtomWall()) {
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
		    int OFFSET = 1;
		    repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
			//update current cell the player is in.
			cell = gridMaze.getGrid()[(cell.getX())/cell.getWidth()][(cell.getY() + cell.getWidth())/cell.getWidth()];
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
			player.setCurrentCell(cell);
			repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
		}
	}
	
	private void moveLeft() {
		SquareCell cell = player.getCurrentCell();
		if (!cell.hasLeftWall() && cell.getX() != 0) {
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
		    int OFFSET = 1;
		    repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
			//update current cell the player is in.
			cell = gridMaze.getGrid()[(cell.getX()- cell.getWidth())/cell.getWidth()][cell.getY()/cell.getWidth()];
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
			player.setCurrentCell(cell);
			repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
		}
	}
	
	private void moveRight() {
		SquareCell cell = player.getCurrentCell();
		if (!cell.hasRightWall() && cell.getX()/cell.getWidth() != gridMaze.getGrid().length - 1) {
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
		    int OFFSET = 1;
		    repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
			//update current cell the player is in.
			cell = gridMaze.getGrid()[(cell.getX() + cell.getWidth())/cell.getWidth()][cell.getY()/cell.getWidth()];
			squareX = cell.getX()+cell.getWidth()/4;
		    squareY = cell.getY()+cell.getWidth()/4;
		    squareW = cell.getWidth()/2;
		    squareH = cell.getWidth()/2;
			player.setCurrentCell(cell);
			repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
		}
	}

}
