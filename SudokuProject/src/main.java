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
	public static void main(String[] args) {
		
		Sudoku sudoku = new Sudoku(puzzle1);
		
	}

}
