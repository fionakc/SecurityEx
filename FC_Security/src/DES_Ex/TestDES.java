/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES_Ex;

//code from Ali

//import com.sun.xml.internal.messaging.saaj.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;

/**
 *
 * @author ahmed
 */
public class TestDES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        try
        {
            DES des1 = new DES();
            String msg = "Welcome everybody. This is Ali :)";
            
            
            System.out.println("The plain text: "+msg);
            
            byte[] encText = des1.encrypt(msg);
            
            //String cipherText = new String(encText);
            //System.out.println("The DES encrypted message: "+ cipherText);
            System.out.println("The DES encrypted message 64: "+ (new BASE64Encoder().encode(encText)));
            
            des1.saveKey("sessionKey1.key");
                        
            DES des2 = new DES();
            des2.loadKey("sessionKey1.key");
            //des2.setSecretkey(des1.getSecretkey());      
         
            
            String decText = des2.decrypt(encText);
            System.out.println("The DES decrypted message: "+decText);
            
        }
        catch(Exception e)
        {
            System.out.println("Error in DES: "+e);   
            e.printStackTrace();
        }
    }
    
}
