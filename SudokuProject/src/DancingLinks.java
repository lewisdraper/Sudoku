import java.util.ArrayList;

public class DancingLinks {

	// subclass node to hold the information of each node in the matrix
	class node {

		node up;
		node down;
		node left;
		node right;
		columnNode column;

		public node() {
			up = this;
			down = this;
			left = this;
			right = this;

		}

		public node(columnNode c) {
			this();
			this.column = c;
		}

		// toroidally doubly links the given node below this node.
		public node linkDown(node n) {

			n.down = this.down;
			n.down.up = n;
			n.up = this;
			this.down = n;
			return n;

		}

		// toroidally doubly links the given node to right of this node.
		public node linkRight(node n) {

			n.right = this.right;
			n.right.left = n;
			n.left = this;
			this.right = n;
			return n;

		}

	}

	// columnNode subclass that extends node and holds extra information for the
	// columnNodes
	// such as size and name.
	class columnNode extends node {

		String name;
		int size;

		columnNode(String s) {
			super();
			name = s;
			size = 0;
			column = this;
		}

	}

	private columnNode head;
	private ArrayList solutions;

	public DancingLinks(byte[][] matrix) {

		createSparse(matrix);
		search(0);

		System.out.println("Complete");
	}

	private columnNode createSparse(byte[][] matrix) {

		head = new columnNode("head");

		int columns = matrix[0].length;
		int rows = matrix.length;
		ArrayList<columnNode> columnNodes = new ArrayList<columnNode>();

		// toroidally doubly link all of the column nodes together
		for (int i = 0; i < columns; i++) {
			columnNode cn = new columnNode(Integer.toString(i));
			columnNodes.add(cn);
			head = (columnNode) head.linkRight(cn);
		}

		// reset the head as the start of the list
		head = head.right.column;

		for (int i = 0; i < rows; i++) {

			node last = null;

			for (int j = 0; j < columns; j++) {
				if (matrix[i][j] == 1) {
					columnNode c = columnNodes.get(j);
					node newNode = new node(c);

					if (last == null) {
						last = newNode;
					} else {

						c.up.linkDown(newNode);
						last = last.linkRight(newNode);
						c.size++;
					}
				}

			}

		}
		
		head.size = columns;
		System.out.println("sparse created");
		return head;
	}

	// the main searching method. Finds the solutions and adds the rows to the
	// solutions list
	private void search(int k) {
		System.out.println("search entered");

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

				search(k + 1); // recursive step

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

	// covers a given column (i.e. temporarily removes it from the matrix)
	private void cover(columnNode c) {
		System.out.println("cover entered");

		c.right.left = c.left;
		c.left.right = c.right;

		// for every node under c
		for (node i = c.down; i != c; i = i.down) {
			System.out.println("loop 1 entered");
			// for every node to the right
			for (node j = i.right; j != i; j = j.right) {

				j.down.up = j.up;
				j.up.down = j.down;
				j.column.size = j.column.size - 1;
				System.out.println(i.column.name + " " + j.column.name);

			}
		}

	}

	// uncovers a given column (i.e reinserts it into the matrix)
	private void uncover(columnNode c) {
		System.out.println("uncover entered");

		// for every node "above" c
		for (node i = c.up; i != c; i = i.up) {
			System.out.println("loop 1 uncover entered");
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

	// heuristic for choosing column with smallest size. greatly reduces search time
	private columnNode chooseColumn() {
		
		int min = Integer.MAX_VALUE;
		columnNode smallest = null;
		
		for(columnNode c = (columnNode) head.right; c!=head; c=(columnNode)c.right){
			
			if(c.size<min){
				min = c.size;
				smallest = c;
			}
			
		}
		
		return smallest;
		
	}

}
