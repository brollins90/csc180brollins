package exercise3;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecretObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	private byte[] secretText;
	
	public void encryptMessage(Integer key, String message)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(key.toString().getBytes());
			SecretKeySpec secretKey = new SecretKeySpec(digest.digest(), 0, 16, "AES");
			
			Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
			aes.init(Cipher.ENCRYPT_MODE, secretKey);
			secretText = aes.doFinal(message.getBytes());
		}
		catch(GeneralSecurityException e)
		{
			e.printStackTrace(System.out);
		}
	}
	
	public String decryptMessage(Integer key)
	{
		String message = null;
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(key.toString().getBytes());
			SecretKeySpec secretKey = new SecretKeySpec(digest.digest(), 0, 16, "AES");
			
			Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
			aes.init(Cipher.DECRYPT_MODE, secretKey);
			message = new String(aes.doFinal(secretText));
		}
		catch(GeneralSecurityException e)
		{
			e.printStackTrace(System.out);
		}
		
		return message;
	}
}
