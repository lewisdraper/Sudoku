import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Display implements ActionListener{

	private JFrame frame;
	private int[][] grid;
	private final int[][] DEFAULT_GRID = new int[9][9];
	private JPanel sudokuPanel;
	private JButton solve;
	private JButton generate;
	private JButton reset;
	private JComboBox difficultyList;
	private int difficulty = 35;
	private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 55);

	public Display(int[][] grid) {
		this.grid = grid;
		createDisplay();
	}

	private void createDisplay() {

		frame = new JFrame("SuDoKu!");
		frame.setSize(2000, 1440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(new GridLayout());
		frame.setLayout(new BorderLayout());
		sudokuPanel = new JPanel(new GridLayout(3, 3, 0, 0));

		drawGrid();

		JPanel sidePanel = new JPanel();
		
		sidePanel.setLayout(new GridLayout(3, 1, 100, 100));
		Font buttonFont = new Font(Font.DIALOG, Font.PLAIN, 55);
		sidePanel.setPreferredSize(new Dimension(600, 1440));
		
		//JPanel solvePanel = new JPanel();
		solve = new JButton("Solve");
		solve.setFont(buttonFont);
		solve.addActionListener(this);
		sidePanel.add(solve);
		
		JPanel genPanel = new JPanel();
		genPanel.setLayout(new GridLayout(3, 1, 0, 0));
		String[] difficulties = new String[]{"35 (Easy)", "34 (Easy)", "33 (Easy)", "32 (Easy)", 
											 "31 (Easy)", "30 (Medium", "29 (Medium)", "28 (Medium)",
											 "27 (Medium)", "26 (Medium)", "25 (Hard)", "24 (Hard)", 
											 "23 (Hard)", "22 (Hard)"};
		
		sidePanel.add(genPanel);
		generate = new JButton("Generate");
		generate.setFont(buttonFont);
		generate.addActionListener(this);

		JComboBox difficultyList = new JComboBox(difficulties);
		difficultyList.addActionListener(
				new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JComboBox combo = (JComboBox)e.getSource();
                        String currentQuantity = (String)combo.getSelectedItem();
                        difficulty = Integer.parseInt(currentQuantity.substring(0, 2));
                    }
				}
				
				
		);
		difficultyList.setFont(buttonFont);

		JLabel numGivens = new JLabel("Number of Clues: ");
		numGivens.setFont(buttonFont);
		genPanel.add(numGivens);
		genPanel.add(difficultyList);
		genPanel.add(generate);
		
		reset = new JButton("Reset");
		reset.setFont(buttonFont);
		reset.addActionListener(this);
		sidePanel.add(reset);
		
		frame.add(sidePanel, BorderLayout.CENTER);
		frame.add(sudokuPanel, BorderLayout.WEST);
		frame.pack();

	}

	private void drawGrid() {
		
		sudokuPanel.setPreferredSize(new Dimension(1440, 1440));
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
				long start = System.currentTimeMillis();
				grid = solver.solve();
				long end = System.currentTimeMillis();
				System.out.println(end-start + "ms");
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
				grid = gen.generate(difficulty);
				drawGrid();
				sudokuPanel.revalidate();
			}
		

	}
}
