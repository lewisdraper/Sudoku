import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Display implements ActionListener{

	private JFrame frame;
	private int[][] grid;
	private final int[][] DEFAULT_GRID = {{0, 0, 0 ,0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0 ,0 ,0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}
										 };
	private JPanel sudokuPanel;
	private JButton solve;
	private JButton generate;
	private JButton reset;
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
		sudokuPanel = new JPanel(new GridLayout(3, 3, 0, 0));

		drawGrid();

		JPanel sidePanel = new JPanel();
		
		solve = new JButton("Solve");
		solve.addActionListener(this);
		sidePanel.add(solve);
		
		generate = new JButton("Generate");
		generate.addActionListener(this);
		sidePanel.add(generate);
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		sidePanel.add(reset);
		
		frame.add(sidePanel, BorderLayout.CENTER);
		frame.add(sudokuPanel, BorderLayout.WEST);
		frame.pack();

	}

	private void drawGrid() {
		
		sudokuPanel.setPreferredSize(new Dimension(720, 720));
		sudokuPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				JPanel smallPanel = new JPanel(new GridLayout(3, 3, 1, 1));
				smallPanel.setBackground(Color.gray);
				JLabel[][] boxNumbers = new JLabel[3][3];
				for (int di = 0; di < 3; di++) {
					for (int dj = 0; dj < 3; dj++) {
						String number;
						if (grid[i + di][j + dj] == 0) {
							number = " ";
						} else {
							number = Integer.toString(grid[i + di][j + dj]);
						}

						boxNumbers[di][dj] = new JLabel(number, SwingConstants.CENTER);
						boxNumbers[di][dj].setFont(LABEL_FONT);
						boxNumbers[di][dj].setOpaque(true);
						boxNumbers[di][dj].setBackground(Color.WHITE);
						smallPanel.add(boxNumbers[di][dj]);

					}
				}
				smallPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				sudokuPanel.add(smallPanel);

			}
		}

	}
	

	public void actionPerformed(ActionEvent e) {
		SudokuSolver solver = new SudokuSolver(grid, 0);
		SudokuGenerator2 gen = new SudokuGenerator2();
		
			if (e.getSource() == solve && grid != DEFAULT_GRID) {			
				sudokuPanel.removeAll();
				grid = solver.solve();
				drawGrid();
				sudokuPanel.revalidate();
				
			}else if(e.getSource() == reset){
				sudokuPanel.removeAll();
				grid = DEFAULT_GRID;
				sudokuPanel.removeAll();
				drawGrid();
				sudokuPanel.revalidate();
				
			}else if(e.getSource()==generate){
				sudokuPanel.removeAll();
				grid = gen.generate();
				drawGrid();
				sudokuPanel.revalidate();
			}
		

	}
}
