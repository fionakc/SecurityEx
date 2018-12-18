package AsymmetricKey_Ex;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

//import org.bouncycastle.util.encoders.Base64;

import sun.misc.BASE64Encoder;

public class Asymmetric_Keys {
	
	
	private PublicKey pub;
	private PrivateKey pvt;
	
	public Asymmetric_Keys() throws NoSuchAlgorithmException 
    {
        generateKey();
    }
	

	private void generateKey() throws NoSuchAlgorithmException {
		// code adapted from https://www.novixys.com/blog/how-to-generate-rsa-keys-java/
		
		//create a key pair generator
    	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    	
    	//initialise the generator with a size, 1024 or 2048
    	kpg.initialize(2048);
    	
    	//use generator to create a key pair
    	KeyPair kp = kpg.generateKeyPair();
    	
    	//extract and save the public and private keys
    	this.pub = kp.getPublic();
    	this.pvt = kp.getPrivate();
	}

	public byte[] encrypt(byte[] input, PublicKey key) 
		throws  GeneralSecurityException 
        {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            
            byte[] byteCipherText = cipher.doFinal(input);       
            return byteCipherText;
	}
        
	
    public String decrypt(byte[] input,  PrivateKey key) 
	throws GeneralSecurityException 
    {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
                      
        byte[] byteDecryptedText = cipher.doFinal(input);        
        return new String(byteDecryptedText);
	}


	public PublicKey getPub() {
		return pub;
	}


	public void setPub(PublicKey pub) {
		this.pub = pub;
	}


	public PrivateKey getPvt() {
		return pvt;
	}


	public void setPvt(PrivateKey pvt) {
		this.pvt = pvt;
	}

	

}
