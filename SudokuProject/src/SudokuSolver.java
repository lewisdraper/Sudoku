import java.util.ArrayList;
import java.util.Arrays;

public class SudokuSolver implements Runnable{

	private int[][] puzzle;
	private int size;
	private int side;

	public SudokuSolver(int[][] puzzle) {

		this.puzzle = puzzle;
		this.size = puzzle.length;
		this.side = (int) Math.sqrt(size);

	}

	//solving method. passes the sudoku to a dancing links object which returns all solutions. The first solution
	//is then mapped to a sudoku grid and then returned
	public int[][] solve() {

		byte[][] constraintMatrix = createConstraintMatrix();
		int[][] solutionGrid = new int[size][size];

		DancingLinks dl = new DancingLinks(constraintMatrix);
		ArrayList<int[]> solutions = dl.getSolutions();
		
		
		solutionGrid = solutionToGrid(solutions.get(0));
		
		System.out.println("solution found");
		return solutionGrid;
		
	}

	//this method maps the solution from the dancing links object to an actual sudoku grid
	private int[][] solutionToGrid(int[] dancingLinksSolution) {

		int[][] solutionGrid = new int[size][size];
		for (int j = 0; j < dancingLinksSolution.length; j++) {

			int row = (dancingLinksSolution[j] / (size * size));
			int col = (((dancingLinksSolution[j] / size) % size));
			int num = (dancingLinksSolution[j] % (size) + 1);

			solutionGrid[row][col] = num;
		}

		return solutionGrid;
	}

	//this method created the constraint matrix for our puzzle that will be passed to the dancing links class
	private byte[][] createConstraintMatrix() {

		byte[][] constraintMatrix = new byte[size * size * size][4 * size * size];

		// counter for keeping track of where we are in the matrix
		int colCounter = 0;

		// fills in the matrix with 1's for the constraint of having a number in
		// each cell
		for (int row = 1; row <= size; row++) {
			for (int col = 1; col <= size; col++, colCounter++) {
				for (int num = 1; num <= size; num++) {
					constraintMatrix[getConstraintRow(row, col, num)][colCounter] = 1;
				}
			}
		}

		// fills in the matrix with 1's for the constraint of having only one of
		// each number in every row
		for (int row = 1; row <= size; row++) {
			for (int num = 1; num <= size; num++, colCounter++) {
				for (int col = 1; col <= size; col++) {
					constraintMatrix[getConstraintRow(row, col, num)][colCounter] = 1;
				}
			}
		}

		// fills in the matrix with 1's for the constraint of having only one of
		// each number in every column
		for (int col = 1; col <= size; col++) {
			for (int num = 1; num <= size; num++, colCounter++) {
				for (int row = 1; row <= size; row++) {
					constraintMatrix[getConstraintRow(row, col, num)][colCounter] = 1;
				}
			}
		}

		// fills in the matrix with 1's for the constraint of having only one of
		// each number in every box
		for (int br = 1; br <= size; br += side) {
			for (int bc = 1; bc <= size; bc += side) {
				for (int num = 1; num <= size; num++, colCounter++) {
					for (int rDelta = 0; rDelta < side; rDelta++) {
						for (int cDelta = 0; cDelta < side; cDelta++) {
							constraintMatrix[getConstraintRow(br + rDelta, bc + cDelta, num)][colCounter] = 1;
						}
					}
				}
			}
		}

		// customises the constraint matrix for our specific puzzle
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				int n = puzzle[i - 1][j - 1];
				if (n != 0) {
					for (int num = 1; num <= size; num++) {
						if (num != n) {
							Arrays.fill(constraintMatrix[getConstraintRow(i, j, num)], (byte) 0);
						}
					}
				}
			}
		}

		return constraintMatrix;
	}

	// returns the row number in the constraint matrix corresponding to placing
	// number n in row r and column c
	private int getConstraintRow(int r, int c, int n) {

		return (r - 1) * size * size + (c - 1) * size + (n - 1);
	}

	public static void printSudoku(int[][] grid) {

		for (int i = 0; i < grid.length; i++) {

			System.out.println();
			if (i == 3 || i == 6) {
				System.out.println("--------------------");
			}
			for (int j = 0; j < grid[i].length; j++) {

				if (grid[i][j] == 0) {
					System.out.print("- ");
				} else {
					System.out.print(grid[i][j] + " ");
				}
				if (j == 2 || j == 5) {
					System.out.print("|");
				}
			}
		}
	}

	public void run() {
		
		
	}
}
