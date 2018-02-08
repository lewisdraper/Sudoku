
public class Launcher {
	
	/*private static int[][] puzzle1 = {{8, 0, 0 ,9, 3, 0, 0, 0, 2},
									 {0, 0, 9, 0, 0, 0, 0, 4, 0},
									 {7, 0, 2, 1, 0, 0, 9, 6, 0},
									 {2, 0, 0, 0, 0, 0, 0, 9, 0},
									 {0, 6, 0, 0, 0, 0, 0, 7, 0},
									 {0, 7 ,0 ,0, 0, 6, 0, 0, 5},
									 {0, 2, 7, 0, 0, 8, 4, 0, 6},
									 {0, 3, 0, 0, 0, 0, 5, 0, 0},
									 {5, 0, 0, 0, 6, 2, 0, 0, 8}
									};*/
	
	private static int[][] puzzle2 = {{8, 0, 0 ,0, 0, 0, 0, 0, 0},
			 						  {0, 0, 3, 6, 0, 0, 0, 0, 0},
			 						  {0, 7, 0, 0, 9, 0, 2, 0, 0},
			 						  {0, 5, 0, 0, 0, 7, 0, 0, 0},
			 						  {0, 0, 0, 0, 4, 5, 7, 0, 0},
			 						  {0, 0 ,0 ,1, 0, 0, 0, 3, 0},
			 						  {0, 0, 1, 0, 0, 0, 0, 6, 8},
			 						  {0, 0, 8, 5, 0, 0, 0, 1, 0},
			 						  {0, 9, 0, 0, 0, 0, 4, 0, 0}
									 };
	
	public static void main(String[] args) {
		
		System.out.print("Puzzle to solve:");
		Sudoku.printSudoku(puzzle2);
				
		Sudoku sudoku = new Sudoku(puzzle2);
		int[][] solution = sudoku.solve();
		
		System.out.print("\n\nSolution: ");
		Sudoku.printSudoku(solution);
			
	}

}
