import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku {
		
	private int[][] puzzle;
	private int size;
	private int side;
	
	public Sudoku(int[][] puzzle){
				
		this.puzzle = puzzle;
		this.size = puzzle.length;
		this.side = (int) Math.sqrt(size);
		
	}
	
	public ArrayList<int[]> solve(){
		
		byte[][] constraintMatrix = createConstraintMatrix();
		for(int i = 0; i<constraintMatrix.length; i++){
			for(int j = 0; j<constraintMatrix[i].length; j++){

			}
		}
		DancingLinks dl = new DancingLinks(constraintMatrix);
		ArrayList<int[]> solutions = dl.getSolutions();
		
		
		
		
			
		return solutions;
		
	}
	
	private byte[][] createConstraintMatrix(){
		
		byte[][] constraintMatrix = new byte[size*size*size][4*size*size];
		
		//counter for keeping track of where we are in the matrix
		int colCounter = 0;
		
		
		//fills in the matrix with 1's for the constraint of having a number in each cell
		for(int row = 1; row <= size; row++){
            for(int col = 1; col <= size; col++, colCounter++){
                for(int num = 1; num <= size; num++){
                    constraintMatrix[getConstraintRow(row, col, num)][colCounter] = 1;
                }
            }
        }
		
		//fills in the matrix with 1's for the constraint of having only one of each number in every row
		for(int row = 1; row <= size; row++){
            for(int num = 1; num <= size; num++, colCounter++){
                for(int col = 1; col <= size; col++){
                    constraintMatrix[getConstraintRow(row, col, num)][colCounter] = 1;
                }
            }
        }
		
		//fills in the matrix with 1's for the constraint of having only one of each number in every column
        for(int col = 1; col <= size; col++){
            for(int num = 1; num <= size; num++, colCounter++){
                for(int row = 1; row <= size; row++){
                    constraintMatrix[getConstraintRow(row, col, num)][colCounter] = 1;
                }
            }
        }
		
      //fills in the matrix with 1's for the constraint of having only one of each number in every box
        for(int br = 1; br <= size; br += side){
            for(int bc = 1; bc <= size; bc += side){
                for(int num = 1; num <= size; num++, colCounter++){
                    for(int rDelta = 0; rDelta < side; rDelta++){
                        for(int cDelta = 0; cDelta < side; cDelta++){
                            constraintMatrix[getConstraintRow(br + rDelta, bc + cDelta, num)][colCounter] = 1;
                        }
                    }
                }
            }
        }
		
        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                int n = puzzle[i - 1][j - 1];
                if (n != 0){
                    for(int num = 1; num <= size; num++){
                        if (num != n){
                            Arrays.fill(constraintMatrix[getConstraintRow(i, j, num)], (byte) 0);
                        }
                    }
                }
            }
        }
        
        
		return constraintMatrix;
	}
		
	//returns the row number in the constraint matrix corresponding to placing number n in row r and column c
	public int getConstraintRow(int r, int c, int n){
		
		return (r-1)*size*size + (c-1)*size + (n-1);
	}
}
