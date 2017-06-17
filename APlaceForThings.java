
public class APlaceForThings {
	//just some dumb extra code
	public void ugh() {
		int ind = 0;
		int x = 0;
		char letter = 'g';
		char ch = 'g';
		Rotor rightRotor = new Rotor(4);
		Rotor middleRotor = new Rotor(4);
		Rotor leftRotor = new Rotor(4);
		String reflector = "asdasd";
		
	//takes char at index i, gets ascii value
	x = letter + rightRotor.rotorPosition;
	
	System.out.println((char) x);
	
	//convert this char ascii value to an index in the standard alphabet map
	ind = x - 65;
	
	if(ind < 0) {
		ind = ind + 26;
	}
	
	if (ind > 25) {
		ind = ind - 26;
	}
	
	System.out.println(ind);
	
	//get the char value of the rotorMap at that index ind
	ch = rightRotor.rotorMap.charAt(ind);
	System.out.println(ch);
	
	//x = ch + 0;
	ind = ch - 65 - rightRotor.rotorPosition + middleRotor.rotorPosition;
	
	if(ind < 0) {
		ind = ind + 26;
	}
	
	if (ind > 25) {
		ind = ind - 26;
	}
	
	ch = middleRotor.rotorMap.charAt(ind);
	System.out.println(ch);
	
	ind = ch - 65 - middleRotor.rotorPosition;
	
	if(ind < 0) {
		ind = ind + 26;
	}
	
	if (ind > 25) {
		ind = ind - 26;
	}
	
	//
	
	ind = ind + leftRotor.rotorPosition;
	
	if(ind < 0) {
		ind = ind + 26;
	}
	
	if (ind > 25) {
		ind = ind - 26;
	}
	
	ch = leftRotor.rotorMap.charAt(ind);
	System.out.println(ch);
	
	ind = ch - 65 - leftRotor.rotorPosition;
	
	if(ind < 0) {
		ind = ind + 26;
	}
	
	if (ind > 25) {
		ind = ind - 26;
	}
	
	//this is where it hits the reflector
	//x is the int value (in ascii) of the char hitting the reflector
	//find the char at that index
	ch = reflector.charAt(ind);
	System.out.println(ch);
	
	//AFTER HERE
	
	//
	//ind = ind + leftRotor.rotorPosition;
	
	//if(ind < 0) {
	//	ind = ind + 26;
	//}
	
	//if (ind > 25) {
	//	ind = ind - 26;
	//}
	
	//ch = (char) (ind);
	//
	
	
	//take that char ch and bounce it back into the leftRotor
	//find that int within the rotor's lettermap
	ind = leftRotor.rotorMap.indexOf(ch);
	System.out.println(ind);
	
	//find corresponding char for that index
	//if the ch is t, then the index of it for rotor one is 11. 
	//i want the index + 65, converted to char. that is the char to be fed into the next rotor
	
	//ind is storing the index of ch
	//new ch is the char converted to the ascii value at index ind in the regular letter map
	
	//
	
	ind = ind + 65 + leftRotor.rotorPosition;
	
	//convert index to regular ascii value
	ch = (char) (ind);
	System.out.println(ch);
	/////////////////
	//find index of ch + middleRotor.rotorPosition within middle rotor
	ch = (char) (ch + middleRotor.rotorPosition);
	//ind = ind - 65 + middleRotor.rotorPosition;
	
	//if(ind < 0) {
	//	ind = ind + 26;
	//}
	
	//if (ind > 25) {
	//	ind = ind - 26;
	//}
	
	//ch = (char) ind;
	
	//take this char and find it in the next rotor
	ind = middleRotor.rotorMap.indexOf(ch);
	//repeat this for the next two rotors

	//ind = ind + middleRotor.rotorPosition;
	
	if(ind < 0) {
		ind = ind + 26;
	}
	
	if (ind > 25) {
		ind = ind - 26;
	}
	
	ch = (char) (ind + 65);
	System.out.println(ch);
	
	//ch = (char) (ind + 65 + rightRotor.rotorPosition);
	
	ind = rightRotor.rotorMap.indexOf(ch);
	ind = ind - rightRotor.rotorPosition;
	
	if(ind < 0) {
		ind = ind + 26;
	}
	
	if (ind > 25) {
		ind = ind - 26;
	}
	
	ch = (char) (ind + 65);
	System.out.println(ch);
}
}
