package com.nav.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

	private String mazeFile;
	private int[][] maze;
	private int startRowPos;
	private int startColPos;
	private boolean[][] visited;
	private int rows;
	private int cols;

	public Maze(String fileName) {
		this.mazeFile = fileName;
		computeRowsAndCols();
		maze = new int[rows][cols];
		visited = new boolean[rows][cols];
		populateMaze();
	}
	
	public Maze(int[][] arr, int i, int j) {
		maze = arr;
		startRowPos = i;
		startColPos = j;
		rows = maze.length;
		cols = maze[0].length;
		visited = new boolean[rows][cols];
		//populateMaze();
	}

	private void computeRowsAndCols(){
		try {
			int i = 0;
			int j = 0;
			int firstCol = 0;
			Scanner scanner = new Scanner(new File(this.mazeFile));
			while(scanner.hasNextLine()){
				j = 0;
				Scanner line = new Scanner(scanner.nextLine());
				while (line.hasNextInt()){
					int val = line.nextInt();
					System.out.print( val + " ");
					j++;
				}
				System.out.println();
				if (firstCol == 0) firstCol = j;
				if (j != firstCol) {
					System.out.println("Maze is incorrectly reprresented");
					throw new RuntimeException("Maze is incorrectly reprresented");
				}
				i++;
			}
			System.out.println("Rows = " + i);
			this.rows = i;
			System.out.println("cols = " + j);
			this.cols = j;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void populateMaze(){

		try {
			Scanner scanner = new Scanner(new File(this.mazeFile));
			for (int i = 0; i < this.rows; i++)
			{
				for(int j = 0; j < this.cols; j++)
				{
					int val = scanner.nextInt();
					this.maze[i][j] = val ;
					if (val == 2){
						startRowPos = i;
						startColPos = j;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void printMaze(){

		for (int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.cols; j++)
			{
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Start position (" + startRowPos + " , " + startColPos + ")");
	}

	public void findWayOut(){
		try {
			dfs(startRowPos,startColPos);
			System.out.println("No exit found");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

	}
	
/*	1 1 1 1
	2 0 1 1
	0 0 1 1
	0 0 3 1
	1 1 1 1 */

	private void dfs(int rowIndex, int colIndex){
		// if maze of (row,col) = 3 Throw ExitFoundException
		// if row is out of bound return
		// if col is out of bound return
		// if (row,col) = visted, return
		// add (row,col) to visited
		// dfs(row ,col - 1) // Left
		// dfs(row, col + 1) //Right
		// dfs(row -1, col) //up
		// dfs(row +1, col)  //down
		System.out.println("Visiting row index " + rowIndex + " and col index " + colIndex);
		if (maze[rowIndex][colIndex] == 3){
			throw new RuntimeException("Exit found at [" + rowIndex + "," + colIndex + "]");
		}

		if (visited[rowIndex][colIndex]){
			System.out.println("--> Already visited");
			return;
		}
		else if (rowIndex < 0 || rowIndex >= rows  )
			return;
		else if (colIndex < 0 || colIndex >= cols )
			return;
		else if (maze[rowIndex][colIndex] == 1)
			return;
		
		visited[rowIndex][colIndex] = true;

		dfs(rowIndex + 1, colIndex);
		dfs(rowIndex , colIndex+1);	
		dfs(rowIndex , colIndex-1);
		dfs(rowIndex - 1, colIndex);
	}
	
	public static void main(String[] args) {
		int[][] array =  { {1,1,1,1},
						   {2,1,3,1}, //1,2
						   {0,1,0,1},
						   {0,0,0,1},
						   {1,1,1,1},						   
		                  };
		System.out.println(array[0].length);
		Maze maze = new Maze(array, 1, 0);
		maze.findWayOut();
		
	}

}
