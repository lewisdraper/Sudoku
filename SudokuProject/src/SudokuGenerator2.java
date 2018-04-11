import java.util.Arrays;
public class SudokuGenerator2 {
	
	private int[][] grid = new int[9][9];
	
	public SudokuGenerator2(){
		
		grid[0] = randomArray();
		SudokuSolver.printSudoku(grid);
		SudokuSolver ss = new SudokuSolver(grid, 1);
		grid = ss.solve();
		SudokuSolver.printSudoku(grid);
		
		
		
	}

	private void removeNumbers(){
		
		
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
