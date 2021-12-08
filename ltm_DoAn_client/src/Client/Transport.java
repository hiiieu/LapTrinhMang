package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.crypto.CipherOutputStream;
import javax.crypto.SealedObject;

import MaHoa.MaHoaDoiXung;

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
				ObjectInputStream objIn = new ObjectInputStream(inObj);
				Object obj = objIn.readObject();
				obj = aes.desealObj((SealedObject)obj);//deseal là giải mã
				return obj;
			} catch (IOException e) {
					e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
	}
}
