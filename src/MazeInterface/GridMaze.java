package MazeInterface;

import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridMaze extends Maze{
	private static final long serialVersionUID = 1L;
	// a predetermined arrangement of cells, a rectangular grid, default size is 10*10.
	SquareCell[][] grid = new SquareCell[10][10];

	@Override
	public void generateMaze(Graphics g) {
		// Firstly, generate a grid
		int width = (int)this.getParent().getPreferredSize().getWidth()/grid.length;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = new SquareCell(i * width, j * width, width);
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j].paintCell(g);
			}
		}
		
		//Then, using Depth-first search to remove adjacent wall between current cell and one of neighbors.
		Set<SquareCell> visited = new HashSet<>();
		Deque<SquareCell> stack = new ArrayDeque<>();
		//mark the top left corner cell as the first visited cell, the entrance of the grid.
		SquareCell currentCell = grid[0][0];
		visited.add(currentCell);
		stack.push(currentCell);
		// while there are unvisited cells
		while(visited.size() < grid.length * grid.length) {
			//any unvisited neighbors?
			boolean unVisited = false;
			for (SquareCell neighbor : getNeighbors(currentCell, grid)) {
				if (!visited.contains(neighbor)) {
					stack.push(neighbor);
					visited.add(neighbor);
					System.out.println("the cell pushed into stack is :" + currentCell.getX() +","+ currentCell.getY());
					//Remove the wall between the current cell and the chosen cell
					if (currentCell.getX() < neighbor.getX()) {
						// neighbor is in the right hand of currentCell
						currentCell.removeRightWall(g);
						neighbor.removeLeftWall(g);
					} else if(currentCell.getX() > neighbor.getX()) {
						// neighbor is in the left hand of currentCell
						currentCell.removeLeftWall(g);
						neighbor.removeRightWall(g);
					} else if(currentCell.getY() < neighbor.getY()) {
						// neighbor is in the down side of currentCell
						currentCell.removeBottomWall(g);
						neighbor.removeTopWall(g);
					} else if(currentCell.getY() > neighbor.getY()) {
						// neighbor is on the top of currentCell
						currentCell.removeTopWall(g);
						neighbor.removeBottomWall(g);
					} else {
						// shouldn't reach here.
					}
					currentCell = neighbor;
					unVisited = true;
					break;
				}
			}
			//all neighbors of the current cell are visited and stack is not empty. 
			//this condition is true implies deep first search reached a end point, but still unvisited cells exist.
			if (!unVisited && !stack.isEmpty()) {
				currentCell = stack.pop();
			}
		}
	}
	
	private List<SquareCell> getNeighbors(SquareCell currentCell, SquareCell[][] grid) {
		
		Set<SquareCell> neighbors = new HashSet<>(); 
		int width = (int)this.getParent().getPreferredSize().getWidth()/grid.length;
		int x = currentCell.getX(); int y = currentCell.getY();
		
		//in grid, there are 9 kinds of currentCell based on their condition of neighbors.
		if (x == 0 && y == 0) { // currentCell is in left-up corner of grid.
			neighbors.add(grid[x/width + 1][y/width]);
			neighbors.add(grid[x/width][y/width + 1]);
		} else if (x == (grid.length - 1) * width && y == 0) {
			neighbors.add(grid[x/width - 1][y/width]);
			neighbors.add(grid[x/width][y/width + 1]);
		} else if (x == 0 && y == (grid.length - 1) * width) {
			neighbors.add(grid[x/width][y/width - 1]);
			neighbors.add(grid[x/width + 1][y/width]);
		} else if (x == (grid.length - 1) * width && y == (grid.length - 1) * width){
			neighbors.add(grid[x/width][y/width - 1]);
			neighbors.add(grid[x/width - 1][y/width]);
		} else if (y == 0) { // currentCell is in the first row of gird except corners.
			neighbors.add(grid[x/width + 1][y/width]);
			neighbors.add(grid[x/width][y/width + 1]);
			neighbors.add(grid[x/width - 1][y/width]);
		} else if (x == 0) {
			neighbors.add(grid[x/width][y/width - 1]);
			neighbors.add(grid[x/width + 1][y/width]);
			neighbors.add(grid[x/width][y/width + 1]);
		} else if (x == (grid.length - 1) * width) {
			neighbors.add(grid[x/width][y/width - 1]);
			neighbors.add(grid[x/width - 1][y/width]);
			neighbors.add(grid[x/width][y/width + 1]);
		} else if (y == (grid.length - 1) * width) {
			neighbors.add(grid[x/width][y/width - 1]);
			neighbors.add(grid[x/width - 1][y/width]);
			neighbors.add(grid[x/width + 1][y/width]);
		} else {
			neighbors.add(grid[x/width][y/width - 1]);
			neighbors.add(grid[x/width][y/width + 1]);
			neighbors.add(grid[x/width - 1][y/width]);
			neighbors.add(grid[x/width + 1][y/width]);
		}
		// before return neighbors, may be we should randomly permute the set.
		List<SquareCell> list = new ArrayList<SquareCell>(neighbors);
		Collections.shuffle(list);
		return list;
	}
}
























