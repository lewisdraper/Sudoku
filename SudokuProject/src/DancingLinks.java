import java.util.*;

//Dancing Links class. This class is responsible for solving of the exact cover problem. It is not limited to sudoku and can accept any exact cover problem
//represented as a matrix of 0's and 1's. It finds the subset of rows such that there is a 1 in every column. It takes a byte array as input and returns 
//an ArrayList of int arrays each containing a solution (i.e an array of row numbers)

public class DancingLinks {

	// subclass 'node' to hold the information of each node in the matrix
	class node {

		node up;
		node down;
		node left;
		node right;
		int rowID;
		columnNode column;
		
		//when a node is created, initialise all variables to point to itself
		public node() {
			up = this;
			down = this;
			left = this;
			right = this;
			rowID = -1;

		}

		public node(columnNode c, int r) {
			this();
			this.column = c;
			this.rowID = r;
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

	// 'columnNode' subclass that extends node and holds extra information for the columnNodes such as size and name.
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
	private List<node> solutionTemp = new ArrayList<node>();
	private List<int[]> solutions = new ArrayList<int[]>();

	//constructor. Creates the sparse matrix with the passed matrix and calls the search method.
	public DancingLinks(byte[][] matrix) {

		createSparse(matrix);
		search(0);

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
				//if a 1 appears in the matrix, create a node for it and insert into the structure
				if (matrix[i][j] == 1) {
					columnNode c = columnNodes.get(j);
					node newNode = new node(c, i);

					if (last == null) 
						last = newNode;

					c.up.linkDown(newNode);
					last = last.linkRight(newNode);
					c.size++;
				}

			}

		}
		
		head.size = columns;
		return head;
	}

	private void search(int k) {

		if (head.right == head) {
			manageSolution(solutionTemp);
			return;
		} else {

			columnNode c = chooseColumn();
			cover(c);

			// for every row in column c
			for (node row = c.down; row != c; row = row.down) {
				
				solutionTemp.add(row);
				// for every node in the row
				for (node i = row.right; i != row; i = i.right) {
					cover(i.column);
				}

				search(k + 1); // recursive step

				solutionTemp.remove(row);
				c = row.column;
				// for every node in the row
				for (node i = row.left; i != row; i = i.left) {
					uncover(i.column);
				}

			}

			uncover(c);

		}

	}
	
	public ArrayList<int[]> getSolutions(){
		
		return (ArrayList<int[]>) solutions;
			
	}
	
	private void manageSolution(List<node> solution){
		
		int[] solutionArray = new int[solution.size()];

		
		for(int i = 0; i < solutionTemp.size(); i++){
			
			solutionArray[i] = solution.get(i).rowID;
				
		}
		
		solutions.add(solutionArray);
	}

	// covers a given column (i.e. temporarily removes it from the matrix)
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

	// uncovers a given column (i.e reinserts it into the matrix)
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
