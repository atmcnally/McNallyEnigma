import java.util.ArrayList;
import java.util.Random;

public class AffineCipher {
	//given abcdefghijklmnopqrstuvwxyz or ABCDEFGHIJKLMNOPQRSTUVWXYZ
	//shuffle the letters each time the program is run
	//use same map each time run
	//sets up before any input made
	//independent of messages
	
	//this preserves which letters are upper or lowercase
	public static void alphaSwitch(int n) {
		String newLetterMap = "";
		ArrayList<Character> newLMap = new ArrayList<Character>();
		int[] newLMap = new int[122];
		
		if(n >= 65 && n <= 90) {
			//is uppercase
			for(int i = 65; i <= 90; i++) {
				Random ran = new Random();
				int x = ran.nextInt(90) + 65;
				
				if(newLMap.get(i).equals(null)) {
					//write letter to space in array
					newLMap[i]
				} else {
					//find next blank space for letter
					x = ran.nextInt(90) + 65;
				}
			}
			
		} else if(n >= 97 && n <= 122) {
			//is lowercase
			
			
		}
	}
}
