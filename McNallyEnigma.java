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
	
}

//int x = 'A' + 0;
//	    = 65

//char c = (char) 65;
//		 = 'A'
