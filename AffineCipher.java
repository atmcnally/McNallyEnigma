import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AffineCipher {
	//given abcdefghijklmnopqrstuvwxyz or ABCDEFGHIJKLMNOPQRSTUVWXYZ
	//shuffle the letters each time the program is run
	//use same map each time run
	//sets up before any input made
	//independent of messages
	
	public static int[] letterMap = new int[26];
	
	public static void createAffineMap() {
		String letterMap1 = "QWERTYUIOPASDFGHJKLZXCVBNM";
		for(int i = 0; i < 26; i++) {
			letterMap[i] = (int) letterMap1.charAt(i);
		}
	}
	
	public static int[] newLMap = new int[123];
	
	//this preserves which letters are upper or lowercase
	public static void alphaSwitch() {
		ArrayList<Integer> alphaList = new ArrayList<>();
		
		//add uppercase nums
		for(int j = 65; j <= 90; j++) {
			alphaList.add(j);
		}
		
		//add lowercase nums
		for(int j = 97; j <= 122; j++) {
			alphaList.add(j);
		}
		
		
		//repeat this part onward for multiple ciphers
		Collections.shuffle(alphaList);
		
		int count = 0;
		//is uppercase
		for(int i = 65; i <= 90; i++) {
			//write letter to space in array
			newLMap[i] = alphaList.get(count);
			//-65
			count++;
		}
			
		//is lowercase
		for(int i = 97; i <= 122; i++) {
			//write letter to space in array
			newLMap[i] = alphaList.get(count);
			//-70
			count++;
		}
			
	
	}
}

