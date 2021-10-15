package question2;

import java.util.Arrays;

public class BSTtoSkewed {
	static Node prevNode = null;
	static Node headNode = null;

	// A binary tree node structure
	static class Node {
		int data;
		Node left;
		Node right;
	};

	// index pointer to pointer to the array index
	static int index;

	// Function that stores inorder traversal of a tree rooted with node 
	private static void storeInorder(Node node, int inorder[]) {
		// Base Case
		if (node == null)
			return;

		/* first store the left subtree */
		storeInorder(node.left, inorder);

		/* Copy the root's data */
		inorder[index] = node.data;
		index++; // increase index for next entry

		/* finally store the right subtree */
		storeInorder(node.right, inorder);
	}

	/* A helper function to count nodes in a Binary Tree */
	private static int countNodes(Node root) {
		if (root == null)
			return 0;
		return countNodes(root.left) + countNodes(root.right) + 1;
	}

	/*
	 * A helper function that copies contents of arr[] to Binary Tree. This function
	 * basically does Inorder traversal of Binary Tree and one by one copy arr[]
	 * elements to Binary Tree nodes
	 */
	private static void arrayToBST(int[] arr, Node root) {
		// Base Case
		if (root == null)
			return;

		/* first update the left subtree */
		arrayToBST(arr, root.left);

		/* Now update root's data and increment index */
		root.data = arr[index];
		index++;

		/* finally update the right subtree */
		arrayToBST(arr, root.right);
	}

	// This is the main function that converts a given Binary Tree to BST
	private static void binaryTreeToBST(Node root) {
		// base case: tree is empty
		if (root == null)
			return;

		// Count the number of nodes in Binary Tree for determining the size of
		// array to be created

		int n = countNodes(root);

		// Create a temporary array arr[] and store inorder traversal of tree in arr[]
		int arr[] = new int[n];

		storeInorder(root, arr);

		// using the default sort method (Dual Pivot Quick Sort)
		Arrays.sort(arr);

		// Copy array elements back to Binary Tree
		index = 0;
		arrayToBST(arr, root);
	}

	// Method to add data
	static Node newNode(int data) {
		Node temp = new Node();
		temp.data = data;
		temp.left = null;
		temp.right = null;
		return temp;
	}

	private static void bstToSkewedTree(Node root) {

		// Base Case
		if (root == null) {
			return;
		}

		bstToSkewedTree(root.left);

		Node rightNode = root.right;

		// Condition to check if the root Node
		// of the skewed tree is not defined
		if (headNode == null) {
			headNode = root;
			root.left = null;
			prevNode = root;
		} else {
			prevNode.right = root;
			root.left = null;
			prevNode = root;
		}

		// Similarly recurse for the right subtree
		bstToSkewedTree(rightNode);

	}

	// Function to traverse the right skewed tree using recursion
	private static void traverseRightSkewed(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		traverseRightSkewed(root.right);
	}

	// Driver function to test above functions
	public static void main(String args[]) {
		Node root = null;

		root = newNode(50);
		root.left = newNode(30);
		root.right = newNode(60);
		root.left.left = newNode(10);
		root.right.right = newNode(40);

		
		
		
//--------------INPUT BINARY TREE------------------//				
		//    50
		//   /  \
		//  30  60
		//  /   /
		// 10  40
//-------------------------------------------------//	
		
		// convert Binary Tree to BST
		binaryTreeToBST(root);

//---------CONVERTED BINARY TREE TO BST------------//	
		//    40
		//   /  \
		//  30  60
		//  /   /
		// 10  50
//-------------------------------------------------//
		
		
		// Performs the BST to Skewed Tree Conversion
		bstToSkewedTree(root);
		
//------CONVERTED BST TO RIGHT SKEWED TREE---------//		
		//	  10
		//      \
		//      30
		//        \
		//        40
		//          \
		//          50
		//            \
		//            60
//-------------------------------------------------//
		
		// Prints the Right Skewed Tree
		traverseRightSkewed(headNode);

	}
}
