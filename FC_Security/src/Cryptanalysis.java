import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cryptanalysis {

	private static String alphabet = new String();
	private static List<String> dictionary = new ArrayList<String>();
	private static double threshold = 0.5;
	
	public static void main(String [] args){
		initialiseAlphabet();
		initialiseDictionary();
		//String cipher = "the quick brown fox";
		//String cipher = "ever ever ever ever";
		String cipher = "The New Zealand constitution is to be found in formal legal documents, ";
//				+ "in decisions of the courts, and in practices (some of which are described as conventions). "
//				+ "It reflects and establishes that New Zealand is a constitutional monarchy, "
//				+ "that it has a parliamentary system of government, and that it is a democracy. "
//				+ "It increasingly reflects the fact that the Treaty of Waitangi is regarded as a founding "
//				+ "document of government in New Zealand. The constitution must also be seen in its "
//				+ "international context, because New Zealand governmental institutions must increasingly "
//				+ "have regard to international obligations and standards.";	
		
		
		System.out.println("input text");
		System.out.println(cipher);
		System.out.println();
		
		String encrypted = encrypt(cipher,3);
		System.out.println("encrypted text");
		System.out.println(encrypted);
		System.out.println();
		
//		ArrayList<String> bruteDecrypt = bruteForceDecrypt(encrypted);
////		
////		//key is alphabet.length()-index
//		for(String out:bruteDecrypt) {
//			System.out.println("key of "+(bruteDecrypt.indexOf(out))+": "+out);
//		}
		
		char keyGuess = 'e';
		//String plain = letterFrequencyDecrypt(encrypted,keyGuess);
		String plain = letterFrequencyDecryptCompDict(encrypted);
		System.out.println("letter frequency decryption with dictionary comparison");
		System.out.println(plain);
		
//		System.out.println("brute force decryption with dictionary comparison");
//		String bruteDecryptCompDict = bruteForceDecryptCompareDict(encrypted);
//		System.out.println(bruteDecryptCompDict);
		
		
		
	}
	
	
	//brute force method
	//need a human eye to detect if answer is found
	//or compare to a dictionary file, if more than threshold is in dict, declare as solved
	//or use online api
	//working
	public static ArrayList<String> bruteForceDecrypt(String cipherText) {
		
		ArrayList<String> plainTexts = new ArrayList<String>();
		String cipherLower = cipherText.toLowerCase();
		char [] cipher = cipherLower.toCharArray();
		//loop through plainText
		
		//loop through every key option
		for(int k=0;k<alphabet.length();k++) {
			String plain="";
			for(int i=0;i<cipher.length;i++) {
				int index=-1;
				
				//check if inside alphabet, if not, copy over directly
				for(int a=0;a<alphabet.length();a++) {
					//get character position in alphabet
					//if alphabet letter at index a = char from plain[i]
					if(alphabet.charAt(a)==cipher[i]) {
						//System.out.println("old index "+a);
						//implement algorithm to get new position
						index = (a+alphabet.length()-k)%alphabet.length();
						//or could factor in newKey here
						//System.out.println("new index "+index);
					}
				} //end a loop
				
												
//				int charPos = alphabet.indexOf(cipher[i]);
//				index = (k+charPos)%alphabet.length();
				//System.out.println("index "+index);
				char newLetter;
				//find character at new position
				if(index<0) {
					newLetter = cipher[i];				
				} else {
					newLetter = alphabet.charAt(index);
				}
				
				//System.out.println("new letter "+newLetter);
				//save that into cipher string
				plain = plain+newLetter;
				
				
			} //end i loop
			//plain+=k;
			plainTexts.add(plain);
		} //end k loop
		
		return plainTexts;
	}
	
	public static String bruteForceDecryptCompareDict(String cipherText) {
		
		String plain="";
		//ArrayList<String> plainTexts = new ArrayList<String>();
		String cipherLower = cipherText.toLowerCase();
		char [] cipher = cipherLower.toCharArray();
		//loop through plainText
		
		//loop through every key option
		for(int k=0;k<alphabet.length();k++) {
			plain="";
			for(int i=0;i<cipher.length;i++) {
				int index=-1;
				
				//check if inside alphabet, if not, copy over directly
				for(int a=0;a<alphabet.length();a++) {
					//get character position in alphabet
					//if alphabet letter at index a = char from plain[i]
					if(alphabet.charAt(a)==cipher[i]) {
						//System.out.println("old index "+a);
						//implement algorithm to get new position
						index = (a+alphabet.length()-k)%alphabet.length();
						//or could factor in newKey here
						//System.out.println("new index "+index);
					}
				} //end a loop
				
												
//				int charPos = alphabet.indexOf(cipher[i]);
//				index = (k+charPos)%alphabet.length();
				//System.out.println("index "+index);
				char newLetter;
				//find character at new position
				if(index<0) {
					newLetter = cipher[i];				
				} else {
					newLetter = alphabet.charAt(index);
				}
				
				//System.out.println("new letter "+newLetter);
				//save that into cipher string
				plain = plain+newLetter;
				
				
			} //end i loop
//			System.out.println("plain at key "+k);
//			System.out.println(plain);
//			System.out.println();
			//plain+=k;
			double realWords = checkDictionary(plain);
//			System.out.println("comp value "+realWords);
			if(realWords>threshold) {
//				System.out.println("above threshold");
				return plain;
			}
		} //end k loop
		
		return plain;
	}
	
	//check brute force method by comparing to a dictionary
	//open dictionary, check if each word (deliniated string) is is dict
	//if is, record a 1, else record a 0
	//find average at the end.
	//if average over threshold, declare decryption success, end loop early
	public static double checkDictionary(String plain) {
		//need to tokenise plain into string arraylist
		List<String> plainWords = tokenize(plain,' ');
		
//		System.out.println("look inside plainWords list");
//		for(String word:plainWords) {
//			System.out.println(word);
//		}
//		System.out.println();
		
		double wordCount=0;
		double inDict=0;
		for(String w:plainWords) {
			if(dictionary.contains(w)) {
				//System.out.println("word in dict");
				inDict++;
			}
			wordCount++;
		}
		
//		System.out.println("word count "+wordCount);
//		System.out.println("inDict "+inDict);
		if(wordCount>0) {
			double percentage = inDict/wordCount;
			//System.out.println("percentage "+percentage); //percentage is returning 0
			return percentage;
		} else {
			return 0;
		}
		 

	} //end checkDict
	
	
	
	//open dictionary file, read into arraylist
	//working
	public static void initialiseDictionary() {
		String path="C:\\Users\\crookfion\\Documents\\dictionarylarge.txt";
		
		try {
			dictionary = Files.readAllLines(Paths.get(path));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*
		 * try {
			List<String> allLines = Files.readAllLines(Paths.get("/Users/pankaj/Downloads/myfile.txt"));
			for (String line : allLines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */
		//System.out.println("dict 0 "+dictionary.get(0));
		
		
	}
	
	//needs changed still
	public static String letterFrequencyDecryptCompDict(String cipherText) { //need to implement for comp dict
		String plain = "";
		
		//change cipher string to char array
		String cipherLower = cipherText.toLowerCase();
		char [] cipher = cipherLower.toCharArray();
		
		//cycle through all in array, find frequency
		HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
		for(char let: cipher) {
			//only want values in alphabet
			boolean inAlphabet=false;
			for(int i=0;i<alphabet.length();i++) {
				if(alphabet.charAt(i)==let) {
					inAlphabet=true;
					break;
				}
			}
			
			//if let in alphabet
			if(inAlphabet) {				
				if(freq.containsKey(let)) {
					freq.put(let, freq.get(let)+1);
				} else {
					freq.put(let,1);
				}
			}
			
		} //end freq hashmap loop
		
		
		
		//find most frequent letter
		char mostFreq = 0;
		int mostFreqNum = -1;
		for(char let: freq.keySet()) {
			//System.out.println("let "+let);
			//System.out.println("freq in hash "+freq.get(let));
			if(freq.get(let)>mostFreqNum) {
				//System.out.println("inside loop");
				mostFreqNum=freq.get(let);
				mostFreq=let;
				//System.out.println("mostFreq "+mostFreq);
				
			}
		}
		
		
		
		
		for(int k=0;k<alphabet.length();k++) {
			
			plain="";
			//map the two letters, find difference
			int inputIndex = k;
			//System.out.println("input index "+k);
			int freqIndex = alphabet.indexOf(mostFreq);
			//System.out.println("most freq index "+freqIndex);
			//System.out.println("most freq letter "+alphabet.charAt(freqIndex));
			int key = freqIndex-inputIndex; //this may need to be the other way around
			//int key = inputIndex - freqIndex;
			
			//use keydiff for normal decrypt
			if(key<0) {
				key=alphabet.length()+key;
			}
			int newKey = alphabet.length()-key;
		
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
			double realWords = checkDictionary(plain);
//			System.out.println("comp value "+realWords);
			if(realWords>threshold) {
//				System.out.println("above threshold");
				return plain;
			}
		} //end k loop
		
		return plain;
	}
	
	
	//letter frequency
	//pass in a letter
	//find frequency of letters in the cipher text
	//map these two letters, find difference
	//use this as the key do decipher
	
	//working
	public static String letterFrequencyDecrypt(String cipherText, char letter) { //need to implement for comp dict
		String plain = "";
		
		//change cipher string to char array
		String cipherLower = cipherText.toLowerCase();
		char [] cipher = cipherLower.toCharArray();
		
		//cycle through all in array, find frequency
		HashMap<Character,Integer> freq = new HashMap<Character,Integer>();
		for(char let: cipher) {
			//only want values in alphabet
			boolean inAlphabet=false;
			for(int i=0;i<alphabet.length();i++) {
				if(alphabet.charAt(i)==let) {
					inAlphabet=true;
					break;
				}
			}
			
			//if let in alphabet
			if(inAlphabet) {				
				if(freq.containsKey(let)) {
					freq.put(let, freq.get(let)+1);
				} else {
					freq.put(let,1);
				}
			}
			
		} //end freq hashmap loop
		
		
		
		//find most frequent letter
		char mostFreq = 0;
		int mostFreqNum = -1;
		for(char let: freq.keySet()) {
			//System.out.println("let "+let);
			//System.out.println("freq in hash "+freq.get(let));
			if(freq.get(let)>mostFreqNum) {
				//System.out.println("inside loop");
				mostFreqNum=freq.get(let);
				mostFreq=let;
				//System.out.println("mostFreq "+mostFreq);
				
			}
		}
		
		//map the two letters, find difference
		int inputIndex = alphabet.indexOf(letter);
		//System.out.println("input index "+inputIndex);
		int freqIndex = alphabet.indexOf(mostFreq);
		//System.out.println("most freq index "+freqIndex);
		//System.out.println("most freq letter "+alphabet.charAt(freqIndex));
		int key = freqIndex-inputIndex; //this may need to be the other way around
		//int key = inputIndex - freqIndex;
		
		//use keydiff for normal decrypt
		if(key<0) {
			key=alphabet.length()+key;
		}
		int newKey = alphabet.length()-key;
		
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
		//alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .0123456789_-~`!@#$%^&*(){}:;,<>?";
				
		for(int i=0;i<26;i++) {
			alphabet=alphabet+(char)(i+'a');
		}
		//System.out.println(alphabet);
	}
	
	//from caesar_cipher_v2
	//working
	public static String encrypt (String plainText, int key) {
		String cipher = "";
		if(key<0) {
			key=alphabet.length()+key;
		}
		
		String plainLower = plainText.toLowerCase();
		//String [] plain = plainText.split("");
		char [] plain = plainLower.toCharArray();
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
	
	
	
	
	//from assign5 - testing
	//break string into tokens, based on separator, returns list of tokens
	private  static List<String> tokenize( String phrase, char separator ){

		List<String> terms = new ArrayList<String>();
		int pos = -1;

		do{
			pos = findNextTerm( phrase, separator );
			if( pos != -1 ){

				String term = phrase.substring( 0, pos );

				terms.add( term.toLowerCase() );
				

				phrase = phrase.substring( pos + 1, phrase.length() );
			}

			else if( ! phrase.equals( "" ) ) {
				terms.add( phrase.toLowerCase() );
			}

		} while( pos != -1 );


		return terms;
	}
	
	//from assign5 - testing
	//takes a string, finds the end of the next term based on the seperator, returns end of term position	
	private static int findNextTerm( String phrase, char separator ){

		for( int i = 0; phrase != null && i < phrase.length(); ++i ) {
			if( phrase.charAt( i ) == separator ) {
				return i;
			}
		}

		return -1;
	}
}
