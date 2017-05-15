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
		
		while(!(input.equals("quit"))) {
			encrypted = "";
			decrypted = "";
			phrase = "";
			input = userIn.nextLine();
			Scanner inputScan = new Scanner(input);
			word = inputScan.next();
			
			if(word.equalsIgnoreCase("encrypt:")) {
				while(inputScan.hasNext()) {
					phrase = phrase + encrypt(inputScan) + " ";
				}
			} else if(word.equalsIgnoreCase("decrypt:")) {
				while(inputScan.hasNext()) {
					phrase = phrase + decrypt(inputScan) + " ";
				}
			} else if(word.equalsIgnoreCase("quit")) {
				userIn.close();
			}
			System.out.println(phrase);
		}
	}
	
	public static String encrypt(Scanner inputScan) {
		encrypted = "";
		word = inputScan.next();
		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i) == 'z' || word.charAt(i) == 'Z') {
				x = word.charAt(i) - 25;
			} else {
				x = word.charAt(i) + 1;
			}
			c = (char) x;
			encrypted += c;
		}
		return encrypted;
	}
	
	public static String decrypt(Scanner inputScan) {
		decrypted = "";
		word = inputScan.next();
		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i) == 'a' || word.charAt(i) == 'A') {
				x = word.charAt(i) + 25;
			} else {
				x = word.charAt(i) - 1;
			}
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