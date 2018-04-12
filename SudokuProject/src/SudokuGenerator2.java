
public class SudokuGenerator2 {
	
	//private int[][] grid = new int[9][9];
	private int difficulty;
	
	public int[][] generate(){
		
		int[][] grid = new int[9][9];
		
		int loops = 0;
		while(countGivens(grid)!=25){
			
			grid = generateFilled();
			grid = removeNumbers(grid);
			
			
			loops++;
		}
		//grid = generateFilled();
		//grid = removeNumbers(grid);
		
		System.out.println(loops);
		SudokuSolver.printSudoku(grid);
		return grid;
	}
	
	//generates a completed sudoku grid
	private int[][] generateFilled(){
		int[][] grid = new int[9][9];
		grid[0] = randomArray();
		
		//fill in grid completely with valid digits
		SudokuSolver ss1 = new SudokuSolver(grid, 1);
		grid = ss1.solve();
		
		return grid;
		
	}
	
	//removes numbers from grid untill puzzle has multiple solutions
	private int[][] removeNumbers(int[][] grid){
		
		int[][] tried = new int[9][9];
		
		SudokuSolver ss = new SudokuSolver(grid, 2);
		
		int randX = (int) Math.ceil(Math.random()*9)-1;
		int randY = (int) Math.ceil(Math.random()*9)-1;
		
		for(int i = 0; i<81; i++){
			while(tried[randX][randY]==1){
				randX = (int) Math.ceil(Math.random()*9)-1;
				randY = (int) Math.ceil(Math.random()*9)-1;
			}
			tried[randX][randY]=1;
			int temp = grid[randX][randY];
			grid[randX][randY]=0;
			if(ss.hasMultipleSolutions()){
				grid[randX][randY] = temp;
			}
			
		}
		
		
		return grid;
		
		
	}
	//creates an array of digits 1 to 9 in a random order. used for populating first row of grid
	private int[] randomArray(){
		
		int[] randomArray = new int[9];
		
		for(int i = 0; i<randomArray.length; i++){
			randomArray[i] = i+1;
		}
		
		for(int i = 0; i<randomArray.length; i++){
			
			int rand = (int) Math.ceil(Math.random() * 9);
			int pos = randomArray[i];
			
			randomArray[i] = randomArray[rand-1];
			randomArray[rand-1] = pos ;
			
		}
		
		return randomArray;
		
	}
	
	//counts number of digits given in a sudoku puzzle
	private int countGivens(int[][] grid){
		int count = 0;
		
		for(int i = 0; i<grid.length; i++){
			for(int j = 0; j<grid[0].length; j++){
				if (grid[i][j] != 0){
					count++;
				}
			}
		}
		
		return count;
		
	}
}
