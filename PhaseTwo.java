import java.util.ArrayList;
import java.util.Scanner;

//Angelus McNally
//AP Comp Sci P1
//Enigma Project

//HEY YOU!!!! CHANGE SOME OF THESE FIELDS TO PRIVATE!!! ACCESSOR METHODS!!!!!!
///I II III A A Z ABC

public class PhaseTwo {
	public static int leftRotorNum = 0;
	public static int middleRotorNum = 0;
	public static int rightRotorNum = 0;
	
	public static int leftRotorPosition = -1;
	public static int middleRotorPosition = -1;
	public static int rightRotorPosition = -1;
	
	public static String reflector= "YRUHQSLDPXNGOKMIEBFZCWVJAT";
	
	public static ArrayList<String> plugboardSettings = new ArrayList<String>();
	
	public static String message = "";
	
	public static void parseInput(String input, Scanner inputScan, String word) {
		//word contains the first rotor
		
		//rotor settings
		leftRotorNum = checkRotor(word);
		Rotor leftRotor = new Rotor(leftRotorNum);
		
		middleRotorNum = checkRotor(inputScan.next());
		Rotor middleRotor = new Rotor(middleRotorNum);
		
		rightRotorNum = checkRotor(inputScan.next());
		Rotor rightRotor = new Rotor(rightRotorNum);
		
		//System.out.println(leftRotor + " " + middleRotor + " " + rightRotor);
		
		//rotor positions
		leftRotor.rotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		middleRotor.rotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		rightRotor.rotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		//gives index within regular a-z map of letter position, translates to number of shifts needed
		//System.out.println(leftRotor.rotorPosition + " " + middleRotor.rotorPosition + " " + rightRotor.rotorPosition);
		
		//plugboard settings
		//take next only if next is two letters
		do {
			word = inputScan.next().toUpperCase();
			if(word.length() == 2) {
				//is a plugboard setting
				plugboardSettings.add(word);
				//for each plugboardSettings space, a string of two characters is contained
				//plugboardSettings.get[i].charAt(0) is first letter
				//plugboardSettings.get[i].charAt(1) is second letter
			} else {
				message = message + word;
			}
		} while(inputScan.hasNext());
		
		//System.out.println(plugboardSettings);
		//System.out.println(message);
		
		//set the rotors to indicated positions
		//set forward to the number indicated by the rotor positions
		
		//start parsing message to encode
		Scanner messageScan = new Scanner(message);
		//take each word and feed through method
		String phrase = "";
		do {
			phrase = phrase + encrypt(messageScan, leftRotor, middleRotor, rightRotor) + " ";
		} while(messageScan.hasNext());
		System.out.println(phrase);
		
	}
	
	public static String encrypt(Scanner messageScan, Rotor leftRotor, Rotor middleRotor, Rotor rightRotor) {
		//encrypted word is returned from this method
		String encrypted = "";
		String word = messageScan.next().toUpperCase();
		
		//ind is the index of whatever char i am looking at in relation to respective letter map
		int ind = -1;
		
		//l is the last char for the encryption, it is added to the string encrypted that is returned
		char l = '-';
		
		//ch changes throughout the rotors and is the char at the index indicated by the previous/next rotor
		char ch = '-';
		
		//SHOULD I CHANGE THESE CONVOLUTED CHANGING VARIABLES TO JUST OLD CHAR AND NEW CHAR THEN SWITCH AND END OF EACH ROTOR?
		
		//advance the right rotor forward 1
		//if at end, loop back to start and kick next rotor
		//check that middle and left don't need to be kicked
		//double kicking??
		rightRotor.rotorPosition++;
		//this logic loops the rotor back around if the position gets out of bounds
		//maybe I don't need this?? since you can have a greater position than 25 but it just keeps looping over again
		if(rightRotor.rotorPosition == 26) {
			rightRotor.rotorPosition = 0;
			if(middleRotor.rotorPosition == 25) {
				middleRotor.rotorPosition = 0;
				if(leftRotor.rotorPosition == 25) {
					leftRotor.rotorPosition = 0;
				}
			}
		}
		
		if(rightRotor.rotorPosition == rightRotor.rotorKick) {
			middleRotor.rotorPosition++;
			if(middleRotor.rotorPosition == middleRotor.rotorKick) {
				leftRotor.rotorPosition++;
			}
		}

		
		char firstL = '-';
		//these loops shift the letter maps for each rotor according to their position
		for(int j = 0; j < rightRotor.rotorPosition; j++) {
			firstL = rightRotor.rotorMap.charAt(0);
			rightRotor.rotorMap = rightRotor.rotorMap.substring(1);
			rightRotor.rotorMap += firstL;
		}
		
		for(int j = 0; j < middleRotor.rotorPosition; j++) {
			firstL = middleRotor.rotorMap.charAt(0);
			middleRotor.rotorMap = middleRotor.rotorMap.substring(1);
			middleRotor.rotorMap += firstL;
		}
		
		for(int j = 0; j < leftRotor.rotorPosition; j++) {
			firstL = leftRotor.rotorMap.charAt(0);
			leftRotor.rotorMap = leftRotor.rotorMap.substring(1);
			leftRotor.rotorMap += firstL;
		}

		
		//cool, now your rotors are at the positions they should be at and are kicked if needed
		
		for(int i = 0; i < word.length(); i++) {
		
			//time to reset their alphabets
			//shift the lettermap by however many positions
			//for rotor 3, setting of A means that a char fed in with the ascii - 65 value of 0 matches to the char at the map's index of 0
			
			//b is at index zero. with a position shift of 1 -so that the initial setting is b- the whole letter map is shifted back 1 value
			//what was at index 1 goes to index 0
			//index 0 goes to index 25
			//basically, take the char at the front and move it to the end, on a loop
			
			//do this the first time for the number of positions, then shift just once for each rotation
			
			//ONLY THE FIRST ROTOR ROTATES MOST OF THE TIME, ONLY ROTATE THE OTHERS IF THE IF STATEMENTS ABOVE/BELOW APPLY
			
			if(i > 0) {
				rightRotor.rotorPosition++;
			
				if(rightRotor.rotorPosition == 26) {
					rightRotor.rotorPosition = 0;
					
					if(middleRotor.rotorPosition == 25) {
						middleRotor.rotorPosition = 0;
						firstL = middleRotor.rotorMap.charAt(0);
						middleRotor.rotorMap = middleRotor.rotorMap.substring(1);
						middleRotor.rotorMap += firstL;
						//System.out.println(middleRotor.rotorMap);
						
						if(leftRotor.rotorPosition == 25) {
							leftRotor.rotorPosition = 0;
							firstL = leftRotor.rotorMap.charAt(0);
							leftRotor.rotorMap = leftRotor.rotorMap.substring(1);
							leftRotor.rotorMap += firstL;
							//System.out.println(leftRotor.rotorMap);
						}
					}
				}
				
				firstL = rightRotor.rotorMap.charAt(0);
				rightRotor.rotorMap = rightRotor.rotorMap.substring(1);
				rightRotor.rotorMap += firstL;
				//System.out.println(rightRotor.rotorMap);
			}
			
			//System.out.println(leftRotor.rotorPosition + " " + middleRotor.rotorPosition + " " + rightRotor.rotorPosition);
			
			
			//System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			//System.out.println(leftRotor.rotorMap);
			//System.out.println(middleRotor.rotorMap);
			//System.out.println(rightRotor.rotorMap);
			
			//System.out.println(word.charAt(i));
			char letter = steckerPair(word.charAt(i));
			
			//
			//RIGHT HERE
			//
			
			ch = rotateF(rightRotor, middleRotor, letter, 1);
			//System.out.println(ch);
			
			ch = rotateF(middleRotor, leftRotor, ch, 2);
			//System.out.println(ch);
			ind = ch - 65;
			
			ind = carry(ind);
			
			ch = reflector.charAt(ind);
			//System.out.println("reflected");
			//System.out.println(ch);
			
			ch = rotateB(leftRotor, middleRotor, ch, 1);		
			//System.out.println(ch);
			ch = rotateB(middleRotor, rightRotor, ch, 2);
			//System.out.println(ch);
			
			//
			
			l = steckerPair(ch);
			
			//l = ch;
			encrypted += l;
		}
		return encrypted;
	}
	
	//this reads through the arrayList called plugboardSettings that contains the stecker pairs and performs that operation
	public static char steckerPair(char letter) {

		String l = letter + "";
		for(int i = 0; i < plugboardSettings.size(); i++) {
			if(plugboardSettings.get(i).contains(l)) {
				if(plugboardSettings.get(i).indexOf(l) == 0) {
					return (char) (plugboardSettings.get(i).charAt(plugboardSettings.get(i).indexOf(l) + 1));
				} else {
					return (char) (plugboardSettings.get(i).charAt(plugboardSettings.get(i).indexOf(l) - 1));
				}
			}
		}
		
		return letter;
	}
	
	//commented out things as I went along that i might want back
	public static char rotateF(Rotor pRotor, Rotor nRotor, char letter, int n) {
		//n is the number shift- first or second
		//System.out.println(letter);
		//System.out.print(pRotor.rotorNumber + " ");
		//System.out.println(nRotor.rotorNumber);
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String pMap = pRotor.rotorMap;
		String nMap = nRotor.rotorMap;
		int ind = -1;
		char ch = '-';
		
		if(n == 1) {
		
			ind = alpha.indexOf(letter);
			//add shift
			ind += pRotor.rotorPosition;
			
			ind = carry(ind);
			
			//System.out.println((char)(ind + 65));
			
			ind -= pRotor.rotorPosition;
			ind = carry(ind);
			
			ch = pMap.charAt(ind);
			//System.out.println(ch);
			ind = alpha.indexOf(ch);
			//subtract shift
			ind -= pRotor.rotorPosition;
			
			ind = carry(ind);
		
			//get char at index of past ch
			//so if ch is now B from III then I want the char at index B from II
			ind += nRotor.rotorPosition;
			//System.out.println(ind);
		
			//ind = nMap.indexOf(ch);
			ind = carry(ind);
			//
			//ch = nMap.charAt(ind);
			//System.out.println(ch);
			ch = alpha.charAt(ind);
			ind -= nRotor.rotorPosition;
			ind = carry(ind);
			
			ch = nMap.charAt(ind);
			return ch;
		
		} else {
			//index of letter
			ind = alpha.indexOf(letter);
			ind -= pRotor.rotorPosition;
			ind = carry(ind);
			ch = alpha.charAt(ind);
			ind += nRotor.rotorPosition;
			ind = carry(ind);
			ch = alpha.charAt(ind);
			//System.out.println(ch);
			
			ind -= nRotor.rotorPosition;
			ind = carry(ind);
			
			ch = nMap.charAt(ind);
			//System.out.println(ch);
			
			ind = ch - 65 - nRotor.rotorPosition;
			ind = carry(ind);
			ch = alpha.charAt(ind);
			return ch;
			
		}
	}	
	
	//I commented out stuff I might want later but didn't need at the time
public static char rotateB(Rotor pRotor, Rotor nRotor, char letter, int n) {
		//n is the number shift- first or second
		//System.out.println(letter);
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String pMap = pRotor.rotorMap;
		String nMap = nRotor.rotorMap;
		int ind = -1;
		char ch = '-';
		
		if(n == 1) {
			ind = alpha.indexOf(letter);
			ind += pRotor.rotorPosition;
			ind = carry(ind);
			ch = alpha.charAt(ind);
			//System.out.println(ch);
			ind = pMap.indexOf(ch);
			ind += pRotor.rotorPosition;
			//a lot of switching happens in here
			ind = carry(ind);
			
			ch = alpha.charAt(ind);
			//System.out.println(ch);
			//
			ind = alpha.indexOf(ch) + nRotor.rotorPosition - pRotor.rotorPosition;
			
			ind = carry(ind);
			
			ch = alpha.charAt(ind);
			
			return ch;
		} else {
			ind = pMap.indexOf(letter);
			ind += pRotor.rotorPosition;
			ind = carry(ind);
			ch = alpha.charAt(ind);
			//System.out.println(ch);
			ind -= pRotor.rotorPosition;
			ind = carry(ind);
			ind += nRotor.rotorPosition;
			ind = carry(ind);
			ch = alpha.charAt(ind);
			ind = nMap.indexOf(ch);
			//ind += nRotor.rotorPosition;
			//ind = carry(ind);
			ch = alpha.charAt(ind);
			//ind -= nRotor.rotorPosition;
			//ch = alpha.charAt(ind);
			return ch;
		}
	}	
	
	//this method is used after every instance of the rotorPosition being added or subtracted
	//to ind to keep an out of bounds error from occurring
	public static int carry(int ind) {
		if(ind < 0) {
			ind = ind + 26;
		}
		
		if (ind > 25) {
			ind = ind - 26;
		}
		return ind;
	}
	//this is used to parse the input to find out which rotors are being used
	public static int checkRotor(String word) {
		if(word.equals("I")) {
			return 1;
		} else if (word.equals("II")) {
			return 2;
		} else if (word.equals("III")) {
			return 3;
		} else if (word.equals("IV")) {
			return 4;
		} else {
			//word.equals("V")
			return 5;
		}
	}
	
	
}
