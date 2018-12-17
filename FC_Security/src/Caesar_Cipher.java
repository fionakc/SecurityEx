//have seperate strings for lowercase alphabet and numbers


public class Caesar_Cipher {
	
	//change string [] to string, so can use charat
	//private static String[] alphabet = new String[26];
	private static String alphabet = new String();
	private static String numbers = new String();
	private static int key = 3;
	
	public static void main(String [] args){
		initialiseAlphabet();
		initialiseNumbers();
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
		String plainLower = plainText.toLowerCase();
		//String [] plain = plainText.split("");
		char [] plain = plainLower.toCharArray();
		//loop through plainText
		for(int i=0;i<plain.length;i++) {
			int index=-1;
			
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
			
			//same loop, but checking for a number
			for(int n=0;n<numbers.length();n++) {
				//get character position in alphabet
				//if alphabet letter at index a = char from plain[i]
				if(numbers.charAt(n)==plain[i]) {
					//System.out.println("old index "+n);
					//implement algorithm to get new position
					index = (n+key)%numbers.length()+alphabet.length();
					//System.out.println("new index "+index);
				}
			} //end a loop
			
			
			char newLetter;
			
			//find character at new position
			if(index<0) {
				newLetter = plain[i];
				
			} else if(0<= index && index<26){
				newLetter = alphabet.charAt(index);
			} else {
				newLetter = numbers.charAt(index-26);
				//System.out.println("new letter "+newLetter);
			}
			
			
			//save that into cipher string
			cipher = cipher+newLetter;
		} //end i loop
		
		return cipher;
	}
	
	//working
	public static String decrypt (String cipherText,int key) {
		String plain="";
		int newKey = alphabet.length()-key;
		String cipherLower = cipherText.toLowerCase();
		//String [] plain = plainText.split("");
		char [] cipher = cipherLower.toCharArray();
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
					
					//System.out.println("new index "+index);
				}
			} //end a loop
			
			//same loop, but checking for a number
			for(int n=0;n<numbers.length();n++) {
				//get character position in alphabet
				//if alphabet letter at index a = char from plain[i]
				if(numbers.charAt(n)==cipher[i]) {
					//System.out.println("old index "+n);
					//implement algorithm to get new position
					index = (n+newKey)%numbers.length()+alphabet.length();
					//System.out.println("new index "+index);
				}
			} //end a loop
			
			char newLetter;
			
			
			//find character at new position
			if(index<0) {
				newLetter = cipher[i];
				
			} else if(0<= index && index<26){
				newLetter = alphabet.charAt(index);
			} else {
				newLetter = numbers.charAt(index-alphabet.length()-key*2); //this works but is janky
				//System.out.println("new letter "+newLetter);
			}
			
			//save that into cipher string
			plain = plain+newLetter;
			
		} //end i loop
		
		return plain;
	}
	
	
	//working
	public static void initialiseAlphabet() {
//		for(int i=0;i<alphabet.length;i++) {
//			char letter = (char)(i+'a');
//			alphabet[i]=Character.toString(letter);
//			//System.out.println(letter);
//		}
		
		
		for(int i=0;i<26;i++) {
			alphabet=alphabet+(char)(i+'a');
		}
		//System.out.println(alphabet);
	}
	
	//working
	private static void initialiseNumbers() {
		for(int i=0;i<10;i++) {
			numbers=numbers+(char)(i+'0');
		}
		System.out.println(numbers);
	}
}
