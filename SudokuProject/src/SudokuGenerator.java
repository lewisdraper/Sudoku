
public class SudokuGenerator {
	
	int [][] grid = {{0, 0, 0 ,0, 0, 0, 0, 0, 0},
			  		 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			  		 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			  		 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			  		 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			  		 {0, 0 ,0 ,0, 0, 0, 0, 0, 0},
			  		 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			  		 {0, 0, 0, 0, 0, 0, 0, 0, 0},
			  		 {0, 0, 0, 0, 0, 0, 0, 0, 0}
					}; 
	int difficulty;
	int clues = 30;
	
	public SudokuGenerator(){
		
		
	}
	
	
	public int[][] generate(){
		
		
		for(int i = 0; i < clues; i++){
			
			int num = randomNum();
			int row = randomNum() - 1;
			int col = randomNum() - 1;

			
			
			if(isValid(num, row, col)){
				grid[row][col] = num;
			} 
			
			
		}
		
		
		return grid;
	}
	
	//method that returns true if a placement of a number in the grid does not conflict with any other numbers
	private boolean isValid(int num, int row, int col){
		
		
		//checks if there is no occurence of the number in the row
		for(int i = 0; i<grid.length; i++){
			//System.out.println(num + " " + row);
			
			if(num == grid[row][i]){
				return false;
			}
		}
		
		//checks if there is no occurence of the number in the column
		for(int i = 0; i<grid.length; i++){
			
			if(num == grid[i][col]){
				return false;
			}
		}
		
		//checks if there is no occurence of the number in the 3x3 box
        int x1 = 3 * (row / 3);
        int y1 = 3 * (col / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;

        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                if (num == grid[x][y])
                    return false;
		
		
		
		return true;
	}
	
	//returns a random number between 1 and 9
	private static int randomNum(){
		return (int) Math.ceil(Math.random() * 9);
	}

}
