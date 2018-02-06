
public class main {
	
	private static byte[][] matrix = { { 0, 0, 1, 0, 1, 1, 0 }, 
									   { 1, 0, 0, 1, 0, 0, 1 }, 
									   { 0, 1, 1, 0, 0, 1, 0 },
									   { 1, 0, 0, 1, 0, 0, 0 }, 
									   { 0, 1, 0, 0, 0, 0, 1 }, 
									   { 0, 0, 0, 1, 1, 0, 1 } };

	public static void main(String[] args) {
		
		DancingLinks dl = new DancingLinks(matrix);
		
	}

}
