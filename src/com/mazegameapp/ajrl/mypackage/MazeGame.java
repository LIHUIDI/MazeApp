package com.mazegameapp.ajrl.mypackage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MazeGame {
	
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Maze Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel());
        f.setSize(600,500);
        f.setVisible(true);
    }
}
