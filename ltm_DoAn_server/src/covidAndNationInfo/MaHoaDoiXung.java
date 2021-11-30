package covidAndNationInfo;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MaHoaDoiXung {
			private SecretKey key;
		    private int KEY_SIZE = 256;
		    private int T_LEN = 128;
		    private byte[] IV;
		    	
		    public static void main(String[] args) {
		        try {
		            MaHoaDoiXung aes= new MaHoaDoiXung();
		            aes.initFromStrings("CHuO1Fjd8YgJqTyapibFBQ==","e3IYYJC2hxe24/EO");
		            String encryptedMessage = aes.encrypt("TheXCoders_2");
		            String decryptedMessage = aes.decrypt(encryptedMessage);

		            System.err.println("Encrypted Message : " + encryptedMessage);
		            System.err.println("Decrypted Message : " + decryptedMessage);
		            //aes.exportKeys();
		        } catch (Exception ignored) {
		        }
		    }
		    
		    public void init() throws Exception {
		        KeyGenerator generator = KeyGenerator.getInstance("AES");
		        generator.init(KEY_SIZE);
		        key = generator.generateKey();
		    }
		
		    public void initFromStrings(String secretKey, String IV){
		        key = new SecretKeySpec(decode(secretKey),"AES");
		        this.IV = decode(IV);
		    }
		
		    public String encryptOld(String message) throws Exception {
		        byte[] messageInBytes = message.getBytes();
		        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
		        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
		        IV = encryptionCipher.getIV();
		        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
		        return encode(encryptedBytes);
		    }
		
		    public String encrypt(String message) {
		        byte[] messageInBytes = message.getBytes();
		        Cipher encryptionCipher;
				try {
					encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
					GCMParameterSpec spec = new GCMParameterSpec(T_LEN,IV);
				    encryptionCipher.init(Cipher.ENCRYPT_MODE, key,spec);
				    byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
				    return encode(encryptedBytes);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (InvalidAlgorithmParameterException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				}
				return "";
		    }
		
		    public String decrypt(String encryptedMessage){
		        byte[] messageInBytes = decode(encryptedMessage);
		        Cipher decryptionCipher;
				try {
					decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
					GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
			        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
			        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
			        return new String(decryptedBytes);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (InvalidAlgorithmParameterException e) {
					e.printStackTrace();
				}
		        return "";
		    }
		
		    private String encode(byte[] data) {
		        return Base64.getEncoder().encodeToString(data);
		    }
		
		    private byte[] decode(String data) {
		        return Base64.getDecoder().decode(data);
		    }
		
		    private void exportKeys(){
		        System.err.println("SecretKey : "+encode(key.getEncoded()));
		        System.err.println("IV : "+encode(IV));
		    }
}
