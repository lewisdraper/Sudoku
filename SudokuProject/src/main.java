import java.util.ArrayList;

public class main {
	
	private static byte[][] matrix = { { 0, 0, 1, 0, 1, 1, 0 }, 
									   { 1, 0, 0, 1, 0, 0, 1 }, 
									   { 0, 1, 1, 0, 0, 1, 0 },
									   { 1, 0, 0, 1, 0, 0, 0 }, 
									   { 0, 1, 0, 0, 0, 0, 1 }, 
									   { 0, 0, 0, 1, 1, 0, 1 },
									   { 0, 0, 1, 0, 1, 1, 0 },
									   { 1, 0, 0, 1, 0, 0, 0 }
									 };
	
	private static int[][] puzzle1 = {{8, 0, 0 ,9, 3, 0, 0, 0, 2},
									 {0, 0, 9, 0, 0, 0, 0, 4, 0},
									 {7, 0, 2, 1, 0, 0, 9, 6, 0},
									 {2, 0, 0, 0, 0, 0, 0, 9, 0},
									 {0, 6, 0, 0, 0, 0, 0, 7, 0},
									 {0, 7 ,0 ,0, 0, 6, 0, 0, 5},
									 {0, 2, 7, 0, 0, 8, 4, 0, 6},
									 {0, 3, 0, 0, 0, 0, 5, 0, 0},
									 {5, 0, 0, 0, 6, 2, 0, 0, 8}
									};
	
	private static int[][] puzzle2 = {{3, 4, 1, 0},
									  {0, 2, 0, 0},
									  {0, 0, 2, 0},
									  {0, 1, 4, 3}
									 };

	private static int[][] puzzle3 = {{1, 2}, {2, 1}};
	
	private static int[][] puzzle4 = {{8, 0, 0 ,0, 0, 0, 0, 0, 0},
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
		
		for(int i = 0; i<puzzle4.length; i++){
			System.out.println();
			for(int j = 0; j<puzzle4[i].length; j++){
				if(puzzle4[i][j]==0){
					System.out.print("- ");
				}else{
					System.out.print(puzzle4[i][j] + " ");
				}
			}
		}
		
		
		Sudoku sudoku = new Sudoku(puzzle4);
		sudoku.solve();
		
	}

}
