package covidAndNationInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.crypto.CipherOutputStream;

public class Transport {
		MaHoaDoiXung aes = null;
		
		public Transport() {}

		public Transport(MaHoaDoiXung aes) {
				this.aes = aes;
		}
		public void send(BufferedWriter out,String data) {
			try {
				data = aes.encrypt(data);//mã hóa
				out.write(data);
				out.newLine();
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void send(OutputStream outObj,Object obj) {
				try {
					ObjectOutputStream objOut = new ObjectOutputStream(outObj);//không mã hóa
					//ObjectOutputStream objOut = aes.createEncrypt(outObj);//này mã hóa chưa làm dc 
					objOut.writeObject(obj);
					//objOut.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		public String receive(BufferedReader in) {
				try {
					String data = in.readLine();
					data = aes.decrypt(data);//giải mã
					return data;
				} catch (IOException e) {
						return "";
				}
		}
		public Object receive(InputStream inObj) {
			try {
				ObjectInputStream objIn = new ObjectInputStream(inObj);//không mã hóa
				//ObjectInputStream objIn = aes.createDecrypt(inObj);//này mã hóa chưa làm dc 
				Object obj = objIn.readObject();
				return obj;
			} catch (IOException e) {
					e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
	}
}
