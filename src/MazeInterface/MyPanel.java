package MazeInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridMaze gridMaze = new GridMaze();

    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(gridMaze);
      //add several listener here

    }

    

    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("This is my custom Panel!",10,20);
        gridMaze.generateMaze(g);
    }  

}
