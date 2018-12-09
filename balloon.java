import java.io.*;
import java.util.*;


public class balloon {

	static int[] arrows; // this array stores the number of arrows required to pop balloons in each
							// problem
	static ArrayList<Integer> bList = new ArrayList<Integer>(); // Arraylist stores the list of balloons in each problem

	public static void inputFile() {

		try {
			FileReader file = new FileReader("testBalloons.txt");
			BufferedReader br = new BufferedReader(file);

			String line1 = br.readLine(); // read the first line in the input file
			int numofProblems = Integer.parseInt(line1); // converting the first line of strings into integers
			br.readLine(); // here we just read the second line

			arrows = new int[numofProblems]; // this array stores the number of arrows required for each problem
			int index = 0;
			String line;

			while ((line = br.readLine()) != null) {
				String[] sp = line.split("\\s");
				for (int i = 0; i < sp.length; i++) {
					bList.add(Integer.parseInt(sp[i]));
				}
				arrows[index] = requiredArrows(bList);
				index++;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void helperArrow(int initialHeight, ArrayList<Integer> bList) {

		int i = 0;
		while (i < bList.size()) {
			if (initialHeight == 0) { // if the height of the arrow is 0 then the loop stops because it hits the ground
				     break; 				
			}
			if (initialHeight == bList.get(i)) { // if the height of the balloon at the i-th position equals the initial
												// height of the arrow
				bList.remove(i); // the balloon is popped
				initialHeight--; // the arrow height decreases by 1 as it pops a balloon
				i--; // the index value is adjusted accordingly
			}
			i++; // increment of the while loop

		}
	}

	public static int requiredArrows(ArrayList<Integer> bList) {

		int numberofArrows = 0; // initially set the number of arrows required to zero
		int initialHeight = 0; // initial height of the arrow is initialized to 0

		while (bList.isEmpty() == false) {// if the arrayList balloonList is not empty
			initialHeight = bList.get(0); // we set the initial height to the height of the balloon in the problem
			bList.remove(0); // then we remove the balloon as it is popped
			initialHeight--; // arrow height reduces by 1 as it hits a balloon
			numberofArrows++; // and we increase the number of arrows by 1
			helperArrow(initialHeight, bList); // the helper method is called to get the number of arrows

		}
		return numberofArrows; // returns the number of arrows required to pop the balloons in each problem
	}

	/*
	 * method takes the input file and writes an output file
	 */
	public static void outputFile() {

		try {

			inputFile(); // we call the input file

			FileWriter file = new FileWriter("testBalloons_solution.txt");
			BufferedWriter bf = new BufferedWriter(file);

			for (int i = 0; i < arrows.length; i++) {
				bf.write(Integer.toString(arrows[i])); // here we just convert the number of integers in the array to
														// strings
				bf.newLine(); // and write it as an output
				// System.out.println(arrows[i]);
			}

			bf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		outputFile(); // we just call the output file method to get the output

	}

}
