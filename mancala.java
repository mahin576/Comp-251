import java.io.*;
import java.util.*;



public class mancala {

	    
	      static int numofProblems; // stores the number of problems in this question
	      static int[][] grid; // 2D array stores the playing grid of the mancala game which is 6 by 12
	      static int[] output; // this array stores the output of the mancala strategy
	    

	   // method takes the input file 
	    public static void inputFile() {
	    	  		  
	    	      //final long startTime = System.currentTimeMillis();
	    	        
	    	        try {
	    	        	 FileReader file= new FileReader("testMancala.txt");
	    	             BufferedReader br = new BufferedReader(file);
	    	           
	    	             numofProblems = Integer.parseInt(br.readLine()); // converting the first line of strings into integers
	    	             int cavities=12; //number of cavities in a board is 12 in a single line
	    	             grid= new int[numofProblems][cavities]; // we form the playing grid 6 by 12
	    	             output= new int[numofProblems]; // the output for each problem which is an integer is stored here 
	    	            
	    	            int i=0;
	    	            while (i<output.length) { // first we set all the slots in the output array to 12
	    	            	output[i]=12;
	    	            	i++;
	    	            }
	    	            
	    	            int initial=0;  
	    	            String line;
	    	             
	    	            while((line = br.readLine())!= null) {                    
	    	                String[] sp = line.split("\\s");
	    	                int[] board= new int[cavities]; // we make an array board the size of 12 which is the number of cavities in a single line
	    	                for (int j = 0; j < board.length; j++) { 
	    	                    board[j]=Integer.parseInt(sp[j]); // we add the pebbles in each problem in the array board after converting it to integers
	    	                }
	    	                
	    	                grid[initial] = board;
	    	                initial++;// keeps track of the index
	    	                
	    	            }
	    	            }
	    	            catch (IOException e) {
	    	            	 e.printStackTrace();
	    	            	
	    	            	}
	    }
	    
/*
 * method for the moves used in the mancala game
 */
	    
	    public static int[] movesUsed(int[] board, int position) { 

	        int[] gameMoves = board.clone(); // we make a copy of the board array
	        
	        
	        int A= position;   // Index 0 of a sequence
	        int B= position+1; // Index 1 of a sequence
	        int C= position+2; // Index 2 of a sequence
	        
	       // here we check the three cases of the sequence ABC
	        
	        if (gameMoves[A]==0) { // In a sequence ABC,if the index A is 0, then after the move the index becomes 1
	        	gameMoves[A]=1; 
	                 } 
	        else {
	        	gameMoves[A]=0; // else it becomes 0
	        	} 
	        
	           gameMoves[B] = 0; // the index B after a move is always 0 for a sequence of 110 or 011
	                            // so we always remove the pebble B(2nd index) in the sequence 110 or 011
	              
	        if (gameMoves[C]==0) { // if the index C is 0, then after the move the index becomes 1
	        	gameMoves[C]=1;
	                      } 
	        else {
	        	gameMoves[position+2]=0;// else it becomes 0
	        }
	        return gameMoves;
	    
	    } 
	    
	    public static void solve(int[] board, int Index) { 
	        
	       int cavities = 12; // number of cavities in a board is 12 in a single line
	       int checkCavities= cavities-2; // we need to run the loop 10 times because the last 2 cavities doesn't matter when we are looping
	       int i=0;
	       while ( i < checkCavities) {
	    	   int A= board[i]; 
	    	   int B= board[i+1]; 
	    	   int C= board[i+2];
	            if ((A == 0 && B == 1 && C == 1)// if there is a sequence  011
	                    || (A == 1 && B == 1 && C == 0)) { // or a sequence 110
	               solve(movesUsed(board,i), Index); // then we call the solve method to remove pebbles from the grid
	                }
	            i++;
	        }
	       
	       int pebble = 0; // initializes the number of pebbles in each problem to zero
	       int j=0;
	       while (j<board.length) { 
	           if (board[j]== 1) // if the index j in the board is 1
	               pebble += 1; // keeps track of the number of pebbles in each problem
	         j++; 
	       }       
	       
	       
	        if (pebble < output[Index]) { // if the pebbles in a line is less than the number of index in the output
	          output[Index] = pebble; // then the output is the number of pebbles left
	        }
	    }


	   //method takes the input file and writes an output file
	    public static void outputFile() {

	        try {
	        	
	        	inputFile(); //  calls the input method of the problem
	        	
				 FileWriter file= new FileWriter("testMancala_solution.txt");
		         BufferedWriter bf = new BufferedWriter(file);
		           
	          
	             for (int n = 0; n < numofProblems; n++) {
	                solve(grid[n], n);
	            }
	           
	            for (int i = 0; i < numofProblems; i++) {
	                bf.write(Integer.toString(output[i]));
	               // System.out.println(output[i]);
	                bf.newLine();
	               
	            }
	            bf.close();
	        }

	        catch (IOException e) {
	            e.printStackTrace();
	        }

	    }


	    public static void main(String[] args) {
	       
	        outputFile();// we just call the output file method to get the output
	      
	    }
	}