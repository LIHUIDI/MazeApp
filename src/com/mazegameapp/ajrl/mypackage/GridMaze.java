package com.mazegameapp.ajrl.mypackage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class GridMaze{//generate 那里需要加一下ratio 和 条件去造teleport cell。
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_GRID_SIZE = 10;
	private int gridNum = DEFAULT_GRID_SIZE;
	
	private static final int DEFAULT_RATIO_COMMON_CELL = 60;
	private static final int DEFAULT_RATIO_GOLD_CELL = 26;
	private static final int DEFAULT_RATIO_TRAP_CELL = 10;
	private static final int DEFAULT_RATIO_Teleport_CELL = 4;

	private int ratioOfCommonCell = DEFAULT_RATIO_COMMON_CELL;
	private int ratioOfGoldCell = DEFAULT_RATIO_GOLD_CELL;
	private int ratioOfTrapCell = DEFAULT_RATIO_TRAP_CELL;
	private int ratioOfTeleportCell = DEFAULT_RATIO_TRAP_CELL;
	ArrayList<GridMazeObserver> gridMazeObservers = new ArrayList<>();
			
	// a predetermined arrangement of cells, a rectangular grid, default size is 10*10.
	SquareCell[][] grid = new SquareCell[gridNum][gridNum];
	
	/**
	 * Set the number of cells such a grid can contain to: n * n. The measurement of the density difficulty.
	 * @param n the number of cells in one dimension. 
	 */
	public void setGridNum(int n){
		this.gridNum = n;
		grid = new SquareCell[gridNum][gridNum];
	}
	
	public int getGridNum() {
		return gridNum;
	}
	
	public void setCellRatio(int ratioOfCommonCell,int ratioOfGoldCell,int ratioOfTrapCell) {
		this.ratioOfCommonCell = ratioOfCommonCell;
		this.ratioOfGoldCell = ratioOfGoldCell;
		this.ratioOfTrapCell = ratioOfTrapCell;
	}
	
	public SquareCell[][] getGrid() {
		return grid;
	}
	
	public void registerGridMazeObserver(GridMazeObserver o) {
		gridMazeObservers.add(o);
	}
	
	public void removeGridMazeObserver(GridMazeObserver o) {
		gridMazeObservers.remove(o);
	}

	/**
	 * 
	 */
	public void initializeGridMaze() {
		// Firstly, initialize a grid
		Random randomGenerator = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				//randomly assign some types of cell into grid.
				int randomInt = randomGenerator.nextInt(100);
				if (randomInt <= ratioOfCommonCell) {
					grid[i][j] = new SquareCell(i, j, 1);
				} else if (randomInt <= (ratioOfCommonCell +  ratioOfGoldCell) && randomInt > ratioOfCommonCell){
					Gold gold = new Gold();
					grid[i][j] = new SquareCellWithItem(i, j, 1, gold);
				} else {
					Skull skull = new Skull();
					grid[i][j] = new SquareCellWithItem(i, j, 1, skull);
				}
			}
		}
		generateMaze();
	}
	
	private void generateMaze() {
		//using Depth-first search to remove adjacent wall between current cell and one of neighbors.
		Set<SquareCell> visited = new HashSet<>();
		Deque<SquareCell> stack = new ArrayDeque<>();
		//mark the top left corner cell as the first visited cell, the entrance of the grid, remove the left wall.
		SquareCell currentCell = grid[0][0];
		currentCell.removeLeftWall();
		visited.add(currentCell);
		stack.push(currentCell);
		// while there are unvisited cells
		char hint = 'n';
		while(visited.size() < grid.length * grid.length) {
			//any unvisited neighbors?
			boolean unVisited = false;
			for (SquareCell neighbor : getNeighbors(currentCell, grid)) {
				if (!visited.contains(neighbor)) {
					stack.push(neighbor);
					visited.add(neighbor);
					//Remove the wall between the current cell and the chosen cell
					if (currentCell.getX() < neighbor.getX()) {
						// neighbor is in the right hand of currentCell
						hint = 'l';
						currentCell.removeRightWall();
						neighbor.removeLeftWall();
					} else if(currentCell.getX() > neighbor.getX()) {
						// neighbor is in the left hand of currentCell
						hint = 'r';
						currentCell.removeLeftWall();
						neighbor.removeRightWall();
					} else if(currentCell.getY() < neighbor.getY()) {
						// neighbor is in the down side of currentCell
						hint = 't';
						currentCell.removeBottomWall();
						neighbor.removeTopWall();
					} else if(currentCell.getY() > neighbor.getY()) {
						// neighbor is on the top of currentCell
						hint = 'd';
						currentCell.removeTopWall();
						neighbor.removeBottomWall();
					} else {
						// shouldn't reach here.
					}
					neighbor.setHint(hint);
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
		//set the bottom right corner cell the exit,remove right wall.
		grid[grid.length-1][grid.length-1].removeRightWall();
		directionHintCorrection();
	}
	
	/**
	 * corrects the hints in Square cells.
	 * All cells along the shortest path from starting cell to ending cell are incorrect.
	 * Each holds the reversed hint of the previous cell.
	 */
	private void directionHintCorrection(){
	    int last = gridNum -1;
	    SquareCell curCell = grid[last][last];

	    int[] curPos = new int[2];
	    curPos[0] = last;
	    curPos[1] = last;
	    
	    int[] prePos = findPrevCellPos(curPos);
	    SquareCell preCell =grid[prePos[0]][prePos[1]];
	    char curDir= curCell.getHint();
	    char preDir ;
	    int[] tempPos;
	    
	    while(preCell != null){
	    	tempPos = findPrevCellPos(prePos);
		    preDir = preCell.getHint();
	    	preCell.setHint(reverseDir(curDir));
	    	curDir = preDir;
	    	curPos = prePos;
	    	curCell = preCell;
	    	if(tempPos == null){break;}
	    	prePos = tempPos;
	    	preCell = grid[prePos[0]][prePos[1]];
	    }
	    grid[last][last].setHint('n');
	}
	
	
	private char reverseDir(char dir){
		if(dir == 'r') 	return 'l';
    	if(dir == 'l') 	return 'r';
    	if(dir == 'd') 	return 't';
    	if(dir == 't') 	return 'd';
    	return 'n';
	}
	
	private int[] findPrevCellPos(int[] pos){
		SquareCell s = grid[pos[0]][pos[1]];
		char dir = s.getHint();
		int i = pos[0], j = pos[1];
		if(dir == 'l'){
			i--;
		}else  if(dir == 'r'){
			i++;
		}else if(dir == 'd'){
			j++;
		}else if(dir == 't'){
			j--;
		}else{
			return null;
		}
		pos[0] = i;
		pos[1] = j;

		return pos;
	}
	
	private List<SquareCell> getNeighbors(SquareCell currentCell, SquareCell[][] grid) {
		
		Set<SquareCell> neighbors = new HashSet<>(); 
		int x = currentCell.getX(); int y = currentCell.getY();
		
		//in grid, there are 9 kinds of currentCell based on their condition of neighbors.
		if (x == 0 && y == 0) { // currentCell is in left-up corner of grid.
			neighbors.add(grid[x + 1][y]);
			neighbors.add(grid[x][y + 1]);
		} else if (x == (grid.length - 1)&& y == 0) {
			neighbors.add(grid[x - 1][y]);
			neighbors.add(grid[x][y + 1]);
		} else if (x == 0 && y == (grid.length - 1)) {
			neighbors.add(grid[x][y- 1]);
			neighbors.add(grid[x + 1][y]);
		} else if (x == (grid.length - 1) && y == (grid.length - 1)){
			neighbors.add(grid[x][y - 1]);
			neighbors.add(grid[x - 1][y]);
		} else if (y == 0) { // currentCell is in the first row of gird except corners.
			neighbors.add(grid[x + 1][y]);
			neighbors.add(grid[x][y + 1]);
			neighbors.add(grid[x - 1][y]);
		} else if (x == 0) {
			neighbors.add(grid[x][y - 1]);
			neighbors.add(grid[x + 1][y]);
			neighbors.add(grid[x][y + 1]);
		} else if (x == (grid.length - 1)) {
			neighbors.add(grid[x][y - 1]);
			neighbors.add(grid[x - 1][y]);
			neighbors.add(grid[x][y + 1]);
		} else if (y == (grid.length - 1)) {
			neighbors.add(grid[x][y - 1]);
			neighbors.add(grid[x - 1][y]);
			neighbors.add(grid[x + 1][y]);
		} else {
			neighbors.add(grid[x][y - 1]);
			neighbors.add(grid[x][y + 1]);
			neighbors.add(grid[x - 1][y]);
			neighbors.add(grid[x + 1][y]);
		}
		// before return neighbors, may be we should randomly permute the set.
		List<SquareCell> list = new ArrayList<SquareCell>(neighbors);
		Collections.shuffle(list);
		return list;
	}
}
