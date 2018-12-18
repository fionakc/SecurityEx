package AsymmetricKey_Ex;

//code adapted from TestDESSimple by Ali

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import sun.misc.BASE64Encoder;

public class TestAsymmetric_Keys {


	

	public static void main(String[] args){
        // TODO code application logic here
        try
        {
        	
          Asymmetric_Keys des1 = new Asymmetric_Keys();
          String msg = "Welcome everybody. This is Ali :)";        
          

          
          System.out.println("The plain text: "+msg);   
          System.out.println();
          PublicKey pub = des1.getPub();
          System.out.println("My public key "+pub.toString());
          byte[] encText = des1.encrypt(msg.getBytes(),pub);   
          System.out.println();
          //this line just writes the encoded text to the screen
          System.out.println("The RSA encrypted message 64: "+ (new BASE64Encoder().encode(encText)));
          System.out.println();
          PrivateKey pvt = des1.getPvt();
//          System.out.println("My private key "+pvt.toString());	//doesn't give useful output
//          System.out.println();
          String decText = des1.decrypt(encText,pvt);
          System.out.println("The RSA decrypted message: "+decText);
        	
        	
            
        }
        catch(Exception e)
        {
            System.out.println("Error in DES: "+e);   
            e.printStackTrace();
        }
    }
	
	
}
