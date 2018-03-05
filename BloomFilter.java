

public class BloomFilter {
	/*Class to implement a bloom filter data structure
	 * Designed by Leah Perry for CIS250 Algorithms and Data Structures Final Project, Spring 2016*/
	int[] characters = new int[0x100];//an array for holding the number of each ascii value out of 256
	
	public BloomFilter() {
		for(int i = 0; i < characters.length; i++){
			characters[i] = 0;
		}
	}//end BloomFiter constructor
	
	public void add(String s){
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			characters[(int) c]++;//adds each character into it's respective spot in the ascii array
		}//end for
	}//end add
	
	public boolean query(String s){
		int[] temp = new int[0x100];//temporary array to hold 
		
		//first count each letter in the word
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			temp[(int) c]++;//adds each character into it's respective spot in the ascii array
		}//end for
		
		//then, compare each letter with it's place in the characters array
		for(int j = 0; j < 0x100; j++){//going through both arrays
			if(characters[j] < temp[j]){//if # of a letter > # of same letter in the characters array...
				return false;//then return false
			}//end if
		}//end for
		
		//if no character returns false, return maybe true
		return true;//only runs if the for loop hasn't already returned
	}//end query

	
}//end bloomFiter class