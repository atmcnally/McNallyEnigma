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
		
		while(!(input.equals("quit"))) {
			encrypted = "";
			decrypted = "";
			phrase = "";
			input = userIn.nextLine();
			Scanner inputScan = new Scanner(input);
			word = inputScan.next();
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
			//if(Character.isUpperCase(x) && (((x + n) > 90))) {
			//x = x - 26 + n;
			if(((x <= 90) && ((x) > 90)) || ((x >= 97) && ((x) > 122))) {
				x = x - 26 + n;
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
			if(word.charAt(i) == 'a' || word.charAt(i) == 'A') {
				x = word.charAt(i) + 25;
			} else {
				x = word.charAt(i) - n;
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