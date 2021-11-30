package covidAndNationInfo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MaHoaCongKhai {
		//chạy hàm init để lấy public key và private key rồi lấy xài quài luôn
		private static final String PUBLIC_KEY_STRING="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsMugXmcNSXzxC1/5ZBCEjVbkEtao4N7SQ+9l1q1c/JQQYtEMZ4owbbnXMQ9IkgDP90GqlIfGo8zO5lgIgRrJW6mHDmTo+Ojedtk9JxFGn2XjNoAUtw9xgKLVFEzenD5GAUOHpWxmTYe3brJL5QcxTnsm4S7AgiDvlOTza2u9p0wIDAQAB";
		
		private PublicKey publicKey;
		
//		public void init(){//hàm tạo key - xài 1 lần rồi ẩn thôi
//	        try {
//	            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//	            generator.initialize(1024);
//	            KeyPair pair = generator.generateKeyPair();
//	            privateKey = pair.getPrivate();
//	            publicKey = pair.getPublic();
//	        } catch (Exception ignored) {
//	        }
//	    } public void printKeys(){
//	        System.err.println("Public key\n"+ encode(publicKey.getEncoded()));
//	        System.err.println("Private key\n"+ encode(privateKey.getEncoded()));
//	    }
		public void initKeyFromString() {
				try{
	            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY_STRING));

	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

	            publicKey = keyFactory.generatePublic(keySpecPublic);
	        }catch (Exception ignored){}
		}
		public MaHoaCongKhai() {
				initKeyFromString();
		}
		
		private static String encode(byte[] data) {
	        return Base64.getEncoder().encodeToString(data);
	    }
	    private static byte[] decode(String data) {
	        return Base64.getDecoder().decode(data);
	    }
		
	    public String encrypt(String message){
	        byte[] messageToBytes = message.getBytes();
	        Cipher cipher;
			try {
				cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
		        return encode(encryptedBytes);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
	    }
	    

	 
		public static String getPublicKeyString() {
			return PUBLIC_KEY_STRING;
		}

//	    public void printKeys(){
//	        System.err.println("Public key\n"+ encode(publicKey.getEncoded()));
//	    }
//	    public static void main(String[] args) {
//	        MaHoaCongKhai rsa = new MaHoaCongKhai();
//	        rsa.initKeyFromString();
//	        rsa.init();
//	        rsa.printKeys();
//	        try{
//	            String encryptedMessage = rsa.encrypt("Hello World");
//	            String decryptedMessage = rsa.decrypt(encryptedMessage);
//
//	            System.out.println("Encrypted:\n"+encryptedMessage);
//	            System.out.println("Decrypted:\n"+decryptedMessage);
//	          
//	         }catch (Exception ingored){}
//	    
//	    }
		
		
		
}
