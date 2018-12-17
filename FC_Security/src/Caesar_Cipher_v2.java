//have a single alphabet for every character


public class Caesar_Cipher_v2 {
	
	//change string [] to string, so can use charat
	//private static String[] alphabet = new String[26];
	private static String alphabet = new String();
	//private static String numbers = new String();
	private static int key = -3;
	
	public static void main(String [] args){
		initialiseAlphabet();
		
		String text = "lazy123";		
		System.out.println("Starting text: "+text);
		System.out.println("Key shift: "+key);
		String encrypted = encrypt(text,key);
		System.out.println("encrypted: "+encrypted);
		String decrypted = decrypt(encrypted,key);
		System.out.println("decrypted: "+decrypted);
	}

	

	//working
	public static String encrypt (String plainText, int key) {
		String cipher = "";
		if(key<0) {
			key=alphabet.length()+key;
		}
		
		//String plainLower = plainText.toLowerCase();
		//String [] plain = plainText.split("");
		char [] plain = plainText.toCharArray();
		//loop through plainText
		for(int i=0;i<plain.length;i++) {
			int index=-1;
			
			/*
			 * this isn't the most efficient way of doing this,
			 * could be better achieved with
			 * character = plainText.charAt(i);            
             *	int charPosition = ALPHABET.indexOf(character);
             * instead of this second for loop
			 */
			
			//check if inside alphabet, if not, copy over directly
			for(int a=0;a<alphabet.length();a++) {
				//get character position in alphabet
				//if alphabet letter at index a = char from plain[i]
				if(alphabet.charAt(a)==plain[i]) {
					//System.out.println("old index "+a);
					//implement algorithm to get new position
					index = (a+key)%alphabet.length();
					//System.out.println("new index "+index);
				}
			} //end a loop
			
			
			
			char newLetter;
			
			//find character at new position
			if(index<0) {
				newLetter = plain[i];
				
			} else {
				newLetter = alphabet.charAt(index);
			} 
			
			
			//save that into cipher string
			cipher = cipher+newLetter;
		} //end i loop
		
		return cipher;
	}
	
	//working
	public static String decrypt (String cipherText,int key) {
		String plain="";
		if(key<0) {
			key=alphabet.length()+key;
		}
		int newKey = alphabet.length()-key;
		//String cipherLower = cipherText.toLowerCase();
		//String [] plain = plainText.split("");
		char [] cipher = cipherText.toCharArray();
		//loop through plainText
		for(int i=0;i<cipher.length;i++) {
			int index=-1;
			
			//check if inside alphabet, if not, copy over directly
			for(int a=0;a<alphabet.length();a++) {
				//get character position in alphabet
				//if alphabet letter at index a = char from plain[i]
				if(alphabet.charAt(a)==cipher[i]) {
					//System.out.println("old index "+a);
					//implement algorithm to get new position
					index = (a+newKey)%alphabet.length();
					//or could factor in newKey here
					//System.out.println("new index "+index);
				}
			} //end a loop
			
			
			
			char newLetter;
			//find character at new position
			if(index<0) {
				newLetter = cipher[i];				
			} else {
				newLetter = alphabet.charAt(index);
			}
			
			
			//save that into cipher string
			plain = plain+newLetter;
			
		} //end i loop
		
		return plain;
	}
	
	
	//working
	public static void initialiseAlphabet() {
		alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .0123456789_-~`!@#$%^&*(){}:;,<>?";
		
		
//		for(int i=0;i<26;i++) {
//			alphabet=alphabet+(char)(i+'a');
//		}
		//System.out.println(alphabet);
	}
	
	
}
