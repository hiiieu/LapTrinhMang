package covidAndNationInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MaHoaDoiXung {
			
			private SecretKey key;
		    private int KEY_SIZE = 256;
		    private int T_LEN = 128;
		    private byte[] IV;//1 phần của key gửi đi (lúc gửi, gửi nó di chung key)
		    	
		    public void init(){
		        KeyGenerator generator;
				try {
					generator = KeyGenerator.getInstance("AES");
					generator.init(KEY_SIZE);
			        key = generator.generateKey();
			        IV = decode("e3IYYJC2hxe24/EO");//IV tự tạo(xuất IV = exportIV rồi lấy xài luôn - chạy hàm main ở dưới cùng)
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
		        
		    }
		
		    public void initFromStrings(String secretKey, String IV){
		        key = new SecretKeySpec(decode(secretKey),"AES");
		        this.IV = decode(IV);
		    }
		    public SealedObject sealObj(Serializable obj) {
			    	Cipher encryptionCipher;
					try {
						encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
						encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
						return new SealedObject(obj, encryptionCipher);
					} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |IOException e) {
						e.printStackTrace();
					}
					return null;
		    }
		    public Object desealObj(SealedObject seal) {
				try {
					Cipher decryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			        decryptionCipher.init(Cipher.DECRYPT_MODE, key);
					return seal.getObject(decryptionCipher);
				} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | IOException | ClassNotFoundException | BadPaddingException e) {
					e.printStackTrace();
				}
				return null;
		    }
		    
		    public String encrypt(String message) {
				try {
			        byte[] messageInBytes = message.getBytes();
			        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
					GCMParameterSpec spec = new GCMParameterSpec(T_LEN,IV);
				    encryptionCipher.init(Cipher.ENCRYPT_MODE, key,spec);
				    byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
				    return encode(encryptedBytes);
				} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				} 
				return "";
		    }
		
		    public String decrypt(String encryptedMessage){
		       
				try {
					byte[] messageInBytes = decode(encryptedMessage);
				    Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
					GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
			        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
			        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
			        return new String(decryptedBytes);
			        
				} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
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

		    public String exportKey() {
		    		return encode(key.getEncoded());
		    }
		    public String exportIV() {
		    		return encode(IV);
		    }
		    
//		    public static void main(String[] args) {
//	        try {
//	            MaHoaDoiXung aes= new MaHoaDoiXung();
//	            //aes.init();
//	            aes.initFromStrings("CHuO1Fjd8YgJqTyapibFBQ==","e3IYYJC2hxe24/EO");
//	            String encryptedMessage = aes.encrypt("TheXCoders_2");
//	            String decryptedMessage = aes.decrypt(encryptedMessage);
//
//	            System.err.println("Encrypted Message : " + encryptedMessage);
//	            System.err.println("Decrypted Message : " + decryptedMessage);
//	            System.err.println("IV: " + aes.exportIV() );
//	        } catch (Exception ignored) {
//	        }
//	    }
}
