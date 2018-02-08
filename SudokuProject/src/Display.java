import javax.swing.*;


public class Display {

	private JFrame frame;
	private int[][] grid;
	

	public Display(int[][] grid){
		this.grid = grid;
		createDisplay();
	}
	
	private void createDisplay(){
		
		frame = new JFrame("SuDoKu!");
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
}
