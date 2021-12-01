package covidAndNationInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
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
		    
		    public ObjectInputStream createDecrypt(ObjectInputStream inObj){
			       
				try {
				    Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
					GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
			        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
			        CipherInputStream ciperIn = new CipherInputStream(inObj, decryptionCipher);
			        return new ObjectInputStream(ciperIn);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					e.printStackTrace();
			    }catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (InvalidAlgorithmParameterException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        return null;
		    }
		    
		    public  ObjectOutputStream createEncrypt(ObjectOutputStream outObj) {
				    try {
					    	Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
							GCMParameterSpec spec = new GCMParameterSpec(T_LEN,IV);
						    encryptionCipher.init(Cipher.ENCRYPT_MODE, key,spec);
				    		CipherOutputStream ciperOut = new CipherOutputStream(outObj, encryptionCipher);
				    		return new ObjectOutputStream(ciperOut);
				    } catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						e.printStackTrace();
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (InvalidAlgorithmParameterException e) {
						e.printStackTrace();
				    } catch (IOException e) {
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
		       
				try {
					byte[] messageInBytes = decode(encryptedMessage);
				    Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
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
