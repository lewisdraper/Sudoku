import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class Display {

	private JFrame frame;
	private int[][] grid;
	private int[][] DEFAULT_GRID;
	private JPanel sudokuPanel;
	private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 24);

	public Display(int[][] grid) {
		this.grid = grid;
		createDisplay();
	}

	private void createDisplay() {

		frame = new JFrame("SuDoKu!");
		frame.setSize(1000, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(new GridLayout());

		frame.setLayout(new BorderLayout());
		drawGrid();
		
		JPanel sidePanel = new JPanel();
		sidePanel.add(new JButton("Solve"));
		sidePanel.add(new JButton("Generate"));

		frame.add(sidePanel, BorderLayout.CENTER);
		

	}

	private void drawGrid(){
		sudokuPanel= new JPanel(new GridLayout(3, 3, 0, 0));
		sudokuPanel.setBackground(Color.black);

		sudokuPanel.setPreferredSize(new Dimension(720, 720));
		sudokuPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 0, 0));
	
		for(int i = 0; i < 9; i += 3){
            for(int j = 0; j < 9; j+= 3){
            	JPanel smallPanel = new JPanel(new GridLayout(3, 3, 1, 1));
            	smallPanel.setBackground(Color.black);
            	JLabel[][] boxNumbers = new JLabel[3][3];
                for(int di = 0; di < 3; di++){
                    for(int dj = 0; dj < 3; dj++){
                    	String number;
        				if (grid[i+di][j+dj] == 0) {
        					number = " ";
        				} else {
        					number = Integer.toString(grid[i+di][j+dj]);
        				}
        				
        				boxNumbers[di][dj] = new JLabel(number, SwingConstants.CENTER);
        				boxNumbers[di][dj].setFont(LABEL_FONT);
        				boxNumbers[di][dj].setOpaque(true);
        				boxNumbers[di][dj].setBackground(Color.WHITE);
        				//boxNumbers[di][dj].setBorder(BorderFactory.createLineBorder(Color.black));
        				smallPanel.add(boxNumbers[di][dj]);
        				
        				
                    }
                }
                smallPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                sudokuPanel.add(smallPanel);
                
            }
        }
		
		frame.add(sudokuPanel, BorderLayout.WEST);
	}
}

