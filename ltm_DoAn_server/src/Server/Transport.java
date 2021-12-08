package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

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
		public void send(OutputStream outObj,Serializable obj) {
				try {
					ObjectOutputStream objOut = new ObjectOutputStream(outObj);
					SealedObject seal = aes.sealObj(obj); //seal là mã hóa 
					objOut.writeObject(seal); 
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
}
