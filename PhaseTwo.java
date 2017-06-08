import java.util.ArrayList;
import java.util.Scanner;

//Angelus McNally
//AP Comp Sci P1
//Enigma Project

//HEY YOU!!!! CHANGE SOME OF THESE FIELDS TO PRIVATE!!! ACCESSOR METHODS!!!!!!

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
		
		System.out.println(leftRotor + " " + middleRotor + " " + rightRotor);
		
		//rotor positions
		leftRotor.rotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		middleRotor.rotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		rightRotor.rotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		//difference between the position and 
		System.out.println(leftRotor.rotorPosition + " " + middleRotor.rotorPosition + " " + rightRotor.rotorPosition);
		
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
		
		System.out.println(plugboardSettings);
		System.out.println(message);
		
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
		
		//x changes throughout the rotors and is the int/ascii value for the char being handled
		int x = 0;
		
		//ind is the index of whatever char i am looking at in relation to respective letter map
		int ind = -1;
		
		//l is the last char for the encryption, it is added to the string encrypted that is returned
		char l = 'a';
		
		//ch changes throughout the rotors and is the char at the index indicated by the previous/next rotor
		char ch = 'a';
		
		//SHOULD I CHANGE THESE CONVOLUTED CHANGING VARIABLES TO JUST OLD CHAR AND NEW CHAR THEN SWITCH AND END OF EACH ROTOR?
		
		for(int i = 0; i < word.length(); i++){
			
			//takes first char at gets in value
			x = word.charAt(i) + 0;
			
			//convert this char ascii value to an index in the alphabet
			ind = x - 65;
			
			//advance the right rotor forward 1
			//if at end, loop back to start and kick next rotor
			//check that middle and left don't need to be kicked
			//double kicking??
			
			rightRotor.rotorPosition++;
			
			if(rightRotor.rotorPosition == 26 && middleRotor.rotorPosition == 25) {
				if(leftRotor.rotorPosition == 25) {
					leftRotor.rotorPosition = 0;
				} else {
					middleRotor.rotorPosition = 0;
					rightRotor.rotorPosition = 0;
				}
			}
			
			System.out.println(leftRotor.rotorPosition + " " + middleRotor.rotorPosition + " " + rightRotor.rotorPosition);
			
			//get the char value of the rotorMap at that index ind
			ch = rightRotor.rotorMap.charAt(ind);
			System.out.println(ch);
			
			x = ch + 0;
			ind = x - 65;
			
			ch = middleRotor.rotorMap.charAt(ind);
			System.out.println(ch);
			
			x = ch + 0;
			ind = x - 65;
			
			ch = leftRotor.rotorMap.charAt(ind);
			System.out.println(ch);
			
			x = ch + 0;
			ind = x - 65;
			//this is where it hits the reflector
			//x is the int value (in ascii) of the char hitting the reflector
			//find the char at that index
			ch = reflector.charAt(ind);
			
			//take that char ch and bounce it back into the leftRotor
			//find that int within the rotor's lettermap
			ind = leftRotor.rotorMap.indexOf(ch);
			System.out.println(ch);
			
			
			//find corresponding char for that index
			//if the ch is t, then the index of it for rotor one is 11. 
			//i want the index + 65, converted to char. that is the char to be fed into the next rotor
			
			//ind is storing the index of ch
			//new ch is the char converted to the ascii value at index ind in the regular letter map
			
			//convert index to regular ascii value
			ch = (char) (ind + 65);
			
			//take this char and find it in the next rotor
			ind = middleRotor.rotorMap.indexOf(ch);
			
			//repeat this for the next two rotors
			
			
			
			l = (char) x;
			encrypted += l;
		}
		return encrypted;
	}
	
	
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
