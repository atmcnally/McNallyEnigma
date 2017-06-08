//Angelus McNally
//AP Comp Sci P1
//Enigma Project

public class Rotor {
	public String rotorMap = "";
	public int rotorNumber = 0;
	public int[] rotorLetterMap = new int[26];
	public int rotorPosition = -1;
	
	public Rotor(int rotorNumber) {
		setRotorMap(rotorNumber);
		this.rotorNumber = rotorNumber;
	}
	
	public String toString() {
		return Integer.toString(rotorNumber);
	}
	
	public void setRotorMap(int num) {
		if(num == 1) {
			rotorMap = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
		} else if (num == 2) {
			rotorMap = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
		} else if (num == 3) {
			rotorMap = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
		} else if (num == 4) {
			rotorMap = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
		} else {
			//num == 5
			rotorMap = "VZBRGITYUPSDNHLXAWMJQOFECK";
		}
		
		for(int i = 0; i < 26; i++) {
			rotorLetterMap[i] = (int) rotorMap.charAt(i);
		}
	}
}
