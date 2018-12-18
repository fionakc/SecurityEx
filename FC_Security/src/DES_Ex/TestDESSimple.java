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

import org.bouncycastle.jcajce.provider.symmetric.DES;

import sun.misc.BASE64Encoder;

/**
 *
 * @author ahmed
 */
public class TestDESSimple {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        try
        {
            DESSimple des1 = new DESSimple();
            String msg = "Welcome everybody. This is Ali :)";        
            
            System.out.println("The plain text: "+msg);            
            byte[] encText = des1.encrypt(msg);            
            System.out.println("The DES encrypted message 64: "+ (new BASE64Encoder().encode(encText)));            
            String decText = des1.decrypt(encText);
            System.out.println("The DES decrypted message: "+decText);
            
        }
        catch(Exception e)
        {
            System.out.println("Error in DES: "+e);   
            e.printStackTrace();
        }
    }
    
}
