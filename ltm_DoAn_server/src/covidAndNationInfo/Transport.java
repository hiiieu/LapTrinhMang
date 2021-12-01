package covidAndNationInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		public void send(ObjectOutputStream outObj,Object obj) {
				try {
					outObj = aes.createEncrypt(outObj);
					outObj.writeObject(obj);
					outObj.flush();
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
		public Object receive(ObjectInputStream inObj) {
			try {
				inObj = aes.createDecrypt(inObj);
				Object obj = inObj.readObject();
				return obj;
			} catch (IOException e) {
					e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
	}
}
