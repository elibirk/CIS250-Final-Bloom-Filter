import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BloomTest {
	/*Main program to test the BloomFilter class 
	 * Designed for: CIS250 Algorithms and Data Structures Spring 2016 Final Project
	 * Designed by Leah Perry*/

	public static void main(String[]args) throws IOException, FileNotFoundException{
		PrintWriter out = new PrintWriter("output.txt");
		Scanner keyboard = new Scanner(System.in);
		
	//Bloom Filters
		
		BloomFilter bloom = new BloomFilter();//creates new bloom filter for storage
		String temp;//takes in user entries and queries
		char userChoice = 'y';//user choice of whether or not to continue
		ArrayList<String> entries = new ArrayList<String>();//stores entries for checking query results
		long startTime;//start, end, elapsed, and average all used for experimental study
		long endTime;
		long elapsed;
		long average = 0; //average time initialized to 0
		ArrayList<Long> times = new ArrayList<Long>();//array to store times for experimental study
		
		while(userChoice == 'y' || userChoice == 'Y'){
			System.out.println("Please enter a string to add it to the bloom filter.");
			out.println("Please enter a string to add it to the bloom filter.");
			temp = keyboard.nextLine();
			out.println(temp);//prints to output file to show entries
			entries.add(temp);//add to array storing actual entries
			bloom.add(temp);//add to bloom filter
			System.out.println("Do you want to add more? Enter 'y' or press enter to continue, or another character to stop.");
			out.println("Do you want to add more? Enter 'y' or press enter to continue, or another character to stop.");
			try{
				userChoice = keyboard.nextLine().charAt(0);
				out.println(userChoice);
			} catch (Exception e){
				userChoice = 'y';
			}
		}//end while
			
		userChoice = 'y';
		int queries = 0; //number of queries entered by user
		int falsePos = 0; //number of false positives
		int falseNeg = 0; //number of false negatives
		System.out.println("\n\nAlright! It's query time!");
		out.println();
		out.println();//blank outfile writing to replace \n\n
		out.println("Alright! It's query time!");
		while(userChoice == 'y' || userChoice == 'Y'){
			System.out.println("Enter a string to query.");
			out.println("Enter a string to query.");
			temp = keyboard.nextLine();
			out.println(temp);//prints to output file to show queries
			startTime = System.nanoTime();
			boolean bloomResult = bloom.query(temp);//result of bloom query
			endTime = System.nanoTime();
			elapsed = endTime - startTime;
			times.add(elapsed);//add times to times
			boolean actualResult = contains(entries, temp);//comparison to entries in entries array
			System.out.println("It is " + bloomResult + " that this may be in the bloom filter.");
			out.println("It is " + bloomResult + " that this may be in the bloom filter.");
			queries++; //counts queries entered
				
			if(bloomResult && !actualResult){//if bloom is true, but actual is false
				falsePos++;//false positive
			}//end if
			else if(bloomResult && actualResult){//if bloom and actual are both true
				//do nothing, correct result, true positive
			}//end else if
			else if(!bloomResult && !actualResult){//if bloom and actual are both false
				//do nothing, correct result, true negative
			}//end else if
			else if(!bloomResult && actualResult){//if bloom is false and actual is true
				falseNeg++;//false negative
			}//end else if
				
			System.out.println("Do you want to do more queries? Enter 'y' or press enter for yes, or any other character for no.");
			out.println("Do you want to do more queries? Enter 'y' or press enter for yes, or any other character for no.");
			try{
				userChoice = keyboard.nextLine().charAt(0);
				out.println(userChoice);
			} catch (Exception e){
				userChoice = 'y';
			}
		}//end while
		
		
		double falsePosRate = 100*falsePos/(queries+0.0);//0.0 so the math is done in double
		double falseNegRate = 100*falseNeg/(queries+0.0);
		System.out.println(queries + " queries resulted in " + falsePos + " false positives and " + falseNeg + " false negatives. This shows " + 
				falsePosRate + "% false positives and " + falseNegRate + "% false negatives.");
		out.println(queries + " queries resulted in " + falsePos + " false positives and " + falseNeg + " false negatives. This shows " + 
				falsePosRate + "% false positives and " + falseNegRate + "% false negatives.");
				
		System.out.println("\nThere were " + queries + " queries, taking the time listed below (in nanoseconds):\n");
		out.println("\nThere were " + queries + " queries, taking the time listed below (in nanoseconds):\n");
		for(int i = 0; i < times.size(); i++){
			average = average + times.get(i);//adds together all times into a total
			System.out.println("Query " + (i+1) + ":\t" + times.get(i));
			out.println("Query " + (i+1) + ":\t" + times.get(i));
		}
		average = average/queries; //averages the query times
		System.out.println("\nResulting in an average time of " + average + " nanoseconds.");
		out.println();
		out.println("Resulting in an average time of " + average + " nanoseconds.");
		
		//close all streams
		out.close();
		keyboard.close();
	}//end main
		

	
		
	public static void printArrayListSquared(ArrayList<ArrayList<String>> list, PrintWriter out){
		for(int i = 0; i < list.size(); i++){
			System.out.println();
			out.println();
			for(int j= 0; j < list.get(i).size(); j++){
				System.out.print(list.get(i).get(j) + "\t");
				out.print(list.get(i).get(j) + "\t");
			}//end j for
		}//end i for
	}//end print array list squared
	
	public static void printAASquared(ArrayList<ArrayList<String[]>> list, PrintWriter out){
		for(int i = 0; i < list.size(); i++){
			System.out.println();
			for(int j= 0; j < list.get(i).size(); j++){
				for(int k = 0; k < list.get(i).get(j).length; k++){
					System.out.print(list.get(i).get(j)[k] + "\t");
					out.print(list.get(i).get(j)[k] + "\t");
				}//end k for (array)
				System.out.println();
				out.println();
			}//end j for (connections)
		}//end i for(nodes)
	}//end print array list squared
	
	public static boolean contains(ArrayList<String> array, String s) {
	    for (int a = 0; a < array.size(); a++){//iterate through array
	        if (array.get(a) == s){//if array value is s, return true
	            return true;
	        }//end if
	    }//end for

	    return false;//if nothing has been returned, return false
	}//end contains

}//end BloomTest
