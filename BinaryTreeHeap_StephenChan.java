import java.util.*;

/*
 * Stephen Chan
 * period 8
 * 1/28/13
 * BinaryTreeHeap_StephenChan- We continue to use the heap implementation but with TreeNodes
*/

public class BinaryTreeHeap_StephenChan<E extends Comparable<E>> {

	private TreeNode root;
	private int numElements;

	public BinaryTreeHeap_StephenChan() {

		root = new TreeNode(null, null, null, null);
		numElements = 0;
	}

	//returns true if there are no elements
	public boolean isEmpty() {

		if (root.value == null)
			return true;

		return false;
	}

	// adds node to tree, reheaps to keep the tree in order
	public void add(E item) {

		TreeNode current = root;

		if (isEmpty())
			root.value = item;
		
		else {

			String binary = Integer.toBinaryString(numElements+1);
			// traverse tree to parent
			for (int i = 1; i < binary.length() - 1; i++) {

				// moves left
				if (binary.charAt(i) == '0')
					current = current.getLeft();
				
				// moves right
				else if (binary.charAt(i) == '1') 
					current = current.getRight();
			}

			// adds node to parent on the left side
			if (binary.charAt(binary.length() - 1) % 2 == 0){ 
				
				current.left = new TreeNode(item, null, null, current);
				current = current.left;
			}
			
			// adds node to parent on the right side
			else{
				
				current.right = new TreeNode(item, null, null, current);
				current = current.right;
			}
			
			//reheap up
			boolean found = false;
			while(!found){
				
				//if parent is greater than child, it switches
				if(current.parent.getValue().compareTo(current.getValue()) > 0){
					
					E switchVal = current.parent.getValue();
					current.parent.value = current.getValue();
					current.value = switchVal;
				}
				
				else
					found=true;
			}
		}
		
		numElements++;			
	}

	//removes first node from tree, returns it and reheaps to keep the tree in order
	public E remove() {

		boolean done;
		E hold;
		E toReturn;
		TreeNode current = root;
		toReturn = current.value;
		if (numElements==0) 
			throw new NoSuchElementException();
		String binary = Integer.toBinaryString(numElements);
		// traverse tree to parent
		for (int i = 1; i < binary.length()-1; i++) {

			// moves left
			if (binary.charAt(i) == '0') 
				current = current.getLeft();
			
			// moves right
			else if (binary.charAt(i) == '1') 
				current = current.getRight();
		}
		
		// removes node to parent on the right side
		if (binary.charAt(binary.length() - 1) % 2 == 1){
			
			hold = current.right.value;
			current.right.parent = null;
			current.right = null;
			root.value = hold;
		
		}
		
		// removes node to parent on the left side
		else{
		
			hold = current.left.value;
			current.left.parent = null;
			current.left = null;
			root.value = hold;
		}
			
		numElements--;
		
		//reheap down
		boolean found = false;
		
		while(!found){
								
			if(current.getLeft() != null && current.getValue().compareTo(current.left.getValue()) > 0){
				
				//go left
				if(current.right.getValue().compareTo(current.left.getValue()) > 0){
					E switchVal = current.getValue();
					current.value = current.left.getValue();
					current.left.value = switchVal;
				}
			}
			
				
			else if(current.getRight() != null && current.getValue().compareTo(current.right.getValue()) > 0){
				
				//go right
				 if(current.left.getValue().compareTo(current.right.getValue()) > 0){
					
					E switchVal = current.getValue();
					current.value = current.right.getValue();
					current.right.value = switchVal;
				}
			}
			
			
			else
				found = true;
		}
		
		return toReturn;
	}

	//returns root element
	public E peek() {

		if (numElements == 0)
			throw new NoSuchElementException();
		return (E) root.getValue();
	}

	//treenode class
	private class TreeNode {

		private E value;
		private TreeNode left;
		private TreeNode right;
		private TreeNode parent;

		public TreeNode(E item, TreeNode l, TreeNode r, TreeNode p) {

			value = item;
			left = l;
			right = r;
			parent = p;
		}

		public E getValue() {

			return value;
		}

		public TreeNode getLeft() {

			return left;
		}

		public TreeNode getRight() {

			return right;
		}

		public TreeNode getParent() {

			return parent;
		}
	}
	

	public static void main(String[] args) {

		BinaryTreeHeap_StephenChan test = new BinaryTreeHeap_StephenChan();
		test.add("5");
		test.add("6");
		test.add("4");
		test.add("3");
		test.remove();
		System.out.println(test.peek());
		System.out.println(test.toString());
		
	}
}
