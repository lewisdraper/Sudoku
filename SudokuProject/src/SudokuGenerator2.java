
public class SudokuGenerator2 {
	
	private int[][] grid = new int[9][9];
	
	public int[][] generate(){
		
		//populates first row with digits 1 to 9 in random order
		grid[0] = randomArray();
		
		//fill in grid completely with valid digits
		SudokuSolver ss1 = new SudokuSolver(grid, 1);
		grid = ss1.solve();
		
		//removes digits up until it has more than one solution
		SudokuSolver ss2 = new SudokuSolver(grid, 2);
		int[][] prevGrid = new int[9][9];
		prevGrid = grid;
		while(!ss2.hasMultipleSolutions()){
			prevGrid = grid;
			removeNumber();		
		}
		
		return prevGrid;
	}
	
	//removes a random number from the sudoku puzzle
	private void removeNumber(){
		
		
		int randX = (int) Math.ceil(Math.random()*9)-1;
		int randY = (int) Math.ceil(Math.random()*9)-1;
		
		while(grid[randX][randY]==0){
			randX = (int) Math.ceil(Math.random()*9)-1;
			randY = (int) Math.ceil(Math.random()*9)-1;
		}
		
		grid[randX][randY] = 0;
		
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
/*	private int countGivens(){
		int count = 0;
		
		for(int i = 0; i<grid.length; i++){
			for(int j = 0; j<grid[0].length; j++){
				if (grid[i][j] != 0){
					count++;
				}
			}
		}
		
		return count;
		
	}*/
}
