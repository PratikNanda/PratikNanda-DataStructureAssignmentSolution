package question1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Skyscraper {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("enter total number of floors in the building");
		int noOfFloors = scanner.nextInt();

		int[] floorArray = new int[noOfFloors];

		int temp = noOfFloors; // used to update.
		int count = 0; // used to update

		// Input Array
		for (int i = 0; i < noOfFloors; i++) {
			System.out.println("enter the floor size given on day: " + (i + 1));
			floorArray[i] = scanner.nextInt();
		}

		Stack<Integer> stack = new Stack<Integer>(); // Using Stack Data Structure

		// Output
		System.out.println();
		System.out.println("The order of construction is as follows\n");
		
		for (int i = 0; i < noOfFloors; i++) {

			stack.push(floorArray[i]);
			sortStack(stack, stack.size()); // sorting in ascending order
			System.out.println("Day: " + (i + 1));
			System.out.println();
			if (stack.peek() == temp) { // The Main Logic
				for (int j = 0; j < (i + 1); j++) {
					if (!stack.isEmpty()) {
						count++;
						int a = stack.pop();
						System.out.print(a + " ");
						if (!stack.isEmpty()) {
							if (stack.peek() == (a - 1))
								continue;
							else
								System.out.println();
							break;
						}
					}
				}
				System.out.println();
				temp = noOfFloors - count;
			} else
				continue;
		}
		scanner.close();
	}

	// This method is used to sort the Stack in Ascending order
	private static void sortStack(Stack<Integer> stack, int k) {
		int[] newarray = new int[k];
		for (int i = 0; i < k; i++) {
			if (!stack.isEmpty())
				newarray[i] = stack.pop();
		}
		Arrays.sort(newarray);
		for (int i = 0; i < k; i++)
			stack.push(newarray[i]);
	}

}