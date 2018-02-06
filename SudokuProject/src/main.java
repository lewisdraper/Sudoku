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
	public static void main(String[] args) {
		
		DancingLinks dl = new DancingLinks(matrix);
		ArrayList<int[]> solutions = dl.getSolutions();
		
		for(int i = 0; i<solutions.size(); i++){
			int[] array = solutions.get(i);
			System.out.println("Solution " + (i+1));
			for(int j = 0; j<array.length; j++){
				
				System.out.println("row: " + (array[j]+1));
				
			}
			
		}
		
		
	}

}
