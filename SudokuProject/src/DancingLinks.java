import java.util.ArrayList;

public class DancingLinks {
	//test comment 2
	
	class node {

	node up;
	node down;
	node left;
	node right;
	columnNode column;

	}

	class columnNode extends node {

		String name;
		int size;
	
		columnNode(String s){
			name = s;
		}

	}

	private columnNode head;
	private ArrayList solutions;

	public void run() {

	}

	private byte[][] matrix = { { 0, 0, 1, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 0, 1 }, { 0, 1, 1, 0, 0, 1, 0 },
			{ 1, 0, 0, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1, 0, 1 } };

	private void createSparse(byte[][] matrix) {
		
		head = new columnNode("head");
		
		int columns = matrix[0].length;
		int rows = matrix.length;	
		
	}

	
	//the main searching method. Finds the solutions and adds the rows to the solutions list
	private void search(int k) {

		if (head.right == head) {
			return;
		} else {

			columnNode c = chooseColumn();
			cover(c);

			// for every row in column c
			for (node row = c.down; row != c; row = row.down) {

				solutions.add(row);
				// for every node in the row
				for (node i = row.right; i != row; i = i.right) {
					cover(i.column);
				}

				search(k + 1);  //recursive step

				solutions.remove(row);
				c = row.column;
				// for every node in the row
				for (node i = row.left; i != row; i = i.left) {
					uncover(i.column);
				}

			}

			uncover(c);

		}
	}

	private void cover(columnNode c) {

		c.right.left = c.left;
		c.left.right = c.right;

		// for every node under c
		for (node i = c.down; i != c; i = i.down) {
			// for every node to the right
			for (node j = i.right; j != i; j = j.right) {

				j.down.up = j.up;
				j.up.down = j.down;
				j.column.size = j.column.size - 1;

			}
		}

	}

	private void uncover(columnNode c) {

		// for every node "above" c
		for (node i = c.up; i != c; i = i.up) {
			// for every node to the left
			for (node j = i.left; j != i; j = j.left) {

				j.column.size = j.column.size + 1;
				j.down.up = j;
				j.up.down = j;

			}

		}

		c.right.left = c;
		c.left.right = c;

	}

//heuristic for choosing column with smallest size
	private columnNode chooseColumn() {

		return null;
	}

}
