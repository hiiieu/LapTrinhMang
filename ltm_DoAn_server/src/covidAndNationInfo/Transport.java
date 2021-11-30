package covidAndNationInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Transport {
		MaHoaDoiXung aes = new MaHoaDoiXung();
		MaHoaCongKhai rsa = new MaHoaCongKhai();
		public Transport() {}
		public void send(BufferedWriter out,String data) {
			try {
				//data = aes.encryption(data);//mã hóa
				out.write(data);
				out.newLine();
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void send(ObjectOutputStream outObj,Object obj) {
				try {
					obj = aes.encryption(obj);//mã hóa
					outObj.writeObject(obj);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		public String receive(BufferedReader in) {
				try {
					String data = in.readLine();
					//data = aes.decryption(data);//giải mã
					return data;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "";
		}
}
