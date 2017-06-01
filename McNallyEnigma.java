

import java.util.Arrays;
import java.util.Scanner;

public class McNallyEnigma {

	private static int x;
	private static char c;
	private static String word;
	private static String encrypted = "";
	private static String decrypted = "";
	
	public static void main(String[] args) {
		Scanner userIn = new Scanner(System.in);
		String input = "";
		String phrase = "";
		int n;
		String num = "";
		
		//sets up affine maps
		AffineCipher affineMap = new AffineCipher();
		affineMap.alphaSwitch();
		affineMap.createAffineMap();
		
		while(!(input.equals("quit"))) {
			encrypted = "";
			decrypted = "";
			phrase = "";
			input = userIn.nextLine();
			Scanner inputScan = new Scanner(input);
			//word is the first "next" inputed
			word = inputScan.next();
			
			
			if(word.equalsIgnoreCase("encrypt") || word.equalsIgnoreCase("decrypt")) {
				num = inputScan.next();
				n = Integer.parseInt(num.substring(0, num.length() - 1));
				
				if(word.equalsIgnoreCase("encrypt")) {
					while(inputScan.hasNext()) {
						phrase = phrase + encrypt(inputScan, n) + " ";
					}
				} else if(word.equalsIgnoreCase("decrypt")) {
					while(inputScan.hasNext()) {
						phrase = phrase + decrypt(inputScan, n) + " ";
					}
				}
			} else if(word.equalsIgnoreCase("encrypt:")) {
				while(inputScan.hasNext()) {
					phrase = phrase + encrypt(inputScan, 1) + " ";
				}
			} else if(word.equalsIgnoreCase("decrypt:")) {
				while(inputScan.hasNext()) {
					phrase = phrase + decrypt(inputScan, 1) + " ";
				}
			} else if(word.equalsIgnoreCase("myaffineencrypt:")) {
				System.out.println(Arrays.toString(affineMap.newLMap));
				while(inputScan.hasNext()) {
					phrase = phrase + aaEncrypt(inputScan, affineMap) + " ";
				}
				
			} else if (word.equalsIgnoreCase("myaffinedecrypt:")) {
				System.out.println(Arrays.toString(affineMap.newLMap));
				while(inputScan.hasNext()) {
					phrase = phrase + aaDecrypt(inputScan, affineMap) + " ";
				}
			} else if(word.equalsIgnoreCase("affineencrypt:")) {
				System.out.println(Arrays.toString(affineMap.letterMap));
				while(inputScan.hasNext()) {
					phrase = phrase + aEncrypt(inputScan, affineMap) + " ";
				}
				
			} else if (word.equalsIgnoreCase("affinedecrypt:")) {
				System.out.println(Arrays.toString(affineMap.letterMap));
				while(inputScan.hasNext()) {
					phrase = phrase + aDecrypt(inputScan, affineMap) + " ";
				}
			} else if(word.equalsIgnoreCase("quit")) {
			
				userIn.close();
			}
			System.out.println(phrase);
		}
	}
	
	public static String encrypt(Scanner inputScan, int n) {
		encrypted = "";
		word = inputScan.next();
		for(int i = 0; i < word.length(); i++){
			x = word.charAt(i) + n;
			if((Character.isUpperCase(word.charAt(i)) && ((x) > 90)) || (Character.isLowerCase(word.charAt(i)) && ((x) > 122))) {
				x = word.charAt(i) - 26 + n;
			}
			c = (char) x;
			encrypted += c;
		}
		return encrypted;
	}
	
	public static String decrypt(Scanner inputScan, int n) {
		decrypted = "";
		word = inputScan.next();
		for(int i = 0; i < word.length(); i++){
			x = word.charAt(i) - n;
			if((Character.isUpperCase(word.charAt(i)) && ((x) < 65)) || (Character.isLowerCase(word.charAt(i)) && ((x) < 97))) {
				x = word.charAt(i) + 26 - n;
			}
			c = (char) x;
			decrypted += c;
		}
		return decrypted;
		
	}
	
	public static String aEncrypt(Scanner inputScan, AffineCipher affineMap) {
		//QWERTYUIOPASDFGHJKLZXCVBNM
		encrypted = "";
		word = inputScan.next().toUpperCase();
		
		//find that letter within affineMap.letterMap[]
		//change that letter to the letter one index ahead
		
		for(int i = 0; i < word.length(); i++){
			x = word.charAt(i) + 0;
			
			int k = 0;
			while (affineMap.letterMap[k] != x) {
				k++;
			}
			
			if (k == 25) {
				x = affineMap.letterMap[0];
			} else {
				x = affineMap.letterMap[k + 1];
			}
			
			c = (char) x;
			encrypted += c;
		}
		return encrypted;
	}
	
	public static String aDecrypt(Scanner inputScan, AffineCipher affineMap) {
		decrypted = "";
		word = inputScan.next();
		
		for(int i = 0; i < word.length(); i++){
			x = word.charAt(i) + 0;
			
			int k = 0;
			while (affineMap.letterMap[k] != x) {
				k++;
			}
			
			if (k == 0) {
				x = affineMap.letterMap[25];
			} else {
				x = affineMap.letterMap[k - 1];
			}
			
			c = (char) x;
			decrypted += c;
		}
		return decrypted;
	}
	
	public static String aaEncrypt(Scanner inputScan, AffineCipher affineMap) {
		encrypted = "";
		word = inputScan.next();
		for(int i = 0; i < word.length(); i++){
			x = word.charAt(i) + 0;
			x = affineMap.newLMap[x];
			c = (char) x;
			encrypted += c;
		}
		return encrypted;
	}
	
	public static String aaDecrypt(Scanner inputScan, AffineCipher affineMap) {
		decrypted = "";
		word = inputScan.next();
		for(int i = 0; i < word.length(); i++){
			x = word.charAt(i) + 0;
			
			int k = 0;
			while (affineMap.newLMap[k] != x) {
				k++;
			}
			
			x = k;
			
			c = (char) x;
			decrypted += c;
		}
		return decrypted;
	}
	
}

//int x = 'A' + 0;
//	    = 65

//char c = (char) 65;
//		 = 'A'
