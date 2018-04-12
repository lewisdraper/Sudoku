import java.util.Arrays;
public class SudokuGenerator2 {
	
	private int[][] grid = new int[9][9];
	
	public int[][] generate(){
		grid[0] = randomArray();
		SudokuSolver ss1 = new SudokuSolver(grid, 1);
		grid = ss1.solve();
		
		SudokuSolver ss2 = new SudokuSolver(grid, 2);
		int[][] prevGrid = new int[9][9];
		prevGrid = grid;
		while(!ss2.hasMultipleSolutions()){
			prevGrid = grid;
			removeNumber();		
		}
		
		return prevGrid;
	}
	
	private void removeNumber(){
		
		
		int randX = (int) Math.ceil(Math.random()*9)-1;
		int randY = (int) Math.ceil(Math.random()*9)-1;
		
		while(grid[randX][randY]==0){
			randX = (int) Math.ceil(Math.random()*9)-1;
			randY = (int) Math.ceil(Math.random()*9)-1;
		}
		
		grid[randX][randY] = 0;
		
	}
	
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
}
