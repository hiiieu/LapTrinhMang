package MaHoa;

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
		private static final String PRIVATE_KEY_STRING="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKwy6BeZw1JfPELX/lkEISNVuQS1qjg3tJD72XWrVz8lBBi0QxnijBtudcxD0iSAM/3QaqUh8ajzM7mWAiBGslbqYcOZOj46N522T0nEUafZeM2gBS3D3GAotUUTN6cPkYBQ4elbGZNh7duskvlBzFOeybhLsCCIO+U5PNra72nTAgMBAAECgYAm8OIsiCC0DYZepWLG9Zia2YVcK8rhKLPEP7f3FLbHuFK7VXSBM4q0n1+zQDkS+Q0I3mtniYwRRWJSmzpC20nz39RGDOtXrIgeB3yM8bp1O8HFfzSNK4S81R++mOKYvkH3wwWhkBv3F2IpeGWS8MzpRMV8/RTYqYf/STlorgNEgQJBANaJYPg8X1CmI0kRYo9DfmSo0vPHUtML9Mjb93SEolWft7Ak2XrKbu7pDhiyONs0Q6sPlvQimc7gZ1vTxYtHbIECQQDNeshvToKHJfEaBG+TYNU8e97qN7d6LG/v3T8Qmu20FcxgWpf8eQ+3GO3Vq+Oj/qgD2OpgfqHlrZhbwND82TxTAkBLYRu8POI0oRtWyg/6THm1fogYdZmJY/MPPBKXUx8CvMq6zkfH2CbgNOzqCnzlsFhyW1jr0NiAEuLeJeuMA32BAkEAioC7wAMsiopxpLbeunmn4hgg3sFlpC112jnIu5mipiOXWk9WaTD0/dcI4WD2lWEewVEiUp6o5BcKPraOaBDJzwJAb6qthjlM5WwBeDpv4hiANZJmK4nJIzOooQRjYy684InJp4VzE2F6lJcg+JIQ3jbV4fbtySITrGa2f+SKV23pyg==";
		
		private PrivateKey privateKey;
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
	            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY_STRING));

	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

	            publicKey = keyFactory.generatePublic(keySpecPublic);
	            privateKey = keyFactory.generatePrivate(keySpecPrivate);
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

	    public String decrypt(String encryptedMessage) {
	        byte[] encryptedBytes = decode(encryptedMessage);
	        Cipher cipher;
			try {
				cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		        cipher.init(Cipher.DECRYPT_MODE, privateKey);
		        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
		        return new String(decryptedMessage, "UTF8");
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
			} catch (UnsupportedEncodingException e) {
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
//	        System.err.println("Private key\n"+ encode(privateKey.getEncoded()));
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
	    
//	    public String encrypt(String message){
//	        byte[] messageToBytes = message.getBytes();
//	        Cipher cipher;
//			try {
//				cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//		        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
//		        return encode(encryptedBytes);
//			} catch (NoSuchAlgorithmException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NoSuchPaddingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvalidKeyException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalBlockSizeException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (BadPaddingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return "";
//	    }
		
		
		
}
