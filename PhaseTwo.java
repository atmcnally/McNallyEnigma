import java.util.ArrayList;
import java.util.Scanner;

//Angelus McNally
//AP Comp Sci P1
//Enigma Project

public class PhaseTwo {
	public static int leftRotorNum = 0;
	public static int middleRotorNum = 0;
	public static int rightRotorNum = 0;
	
	public static int leftRotorPosition = -1;
	public static int middleRotorPosition = -1;
	public static int rightRotorPosition = -1;
	
	static ArrayList<String> plugboardSettings = new ArrayList<String>();
	
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
		leftRotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		middleRotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		rightRotorPosition = inputScan.next().toUpperCase().charAt(0) - 65;
		//difference between the position and 
		System.out.println(leftRotorPosition + " " + middleRotorPosition + " " + rightRotorPosition);
		
		//plugboard settings
		//take next only if next is two letters
		while(inputScan.hasNext()) {
			word = inputScan.next().toUpperCase();
			if(word.length() == 2) {
				//is a plugboard setting
				plugboardSettings.add(word);
				//for each plugboardSettings space, a string of two characters is contained
				//plugboardSettings.get[i].charAt(0) is first letter
				//plugboardSettings.get[i].charAt(1) is second letter
			} else {
				message = word;
			}
		}
		
		System.out.println(plugboardSettings);
		System.out.println(message);
		
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
