package covidAndNationInfo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private Socket socket=null;
	private ServerSocket server=null;
	BufferedReader in=null;
	BufferedWriter out=null;
	ObjectOutputStream outObj=null;
	static String clientKey="";
	
	MaHoaDoiXung aes = new MaHoaDoiXung();
	MaHoaCongKhai rsa = new MaHoaCongKhai();
	
	public Server(int port) {
			try {
				//tạo server đợi client
				server = new ServerSocket(port); 
				System.out.println("server bắt đầu chạy");
				System.out.println("server đang đợi yêu cầu");
				socket = server.accept();
				//khi client kết nối hoàn tất
				System.out.println("client đã kết nối");
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				outObj = new ObjectOutputStream(socket.getOutputStream());
				
				/*Thiết lập mã hóa*/
				//1. gửi public key tới client
				out.write(rsa.getPublicKey());
				out.newLine();
				out.flush();
				//2. nhận key đối xứng
				String clientKeyEncode = in.readLine();
				//3. giải mã key để nhận clientKey
				clientKey =  rsa.decryption(clientKeyEncode);
				aes.setKey(clientKey);
				
				
				
				/*chọn chức năng*/
				String choose = receiveFromClient(in);
				if (choose.equals("covid")) {
						//viết covid (class covid)
				}
				
				if (choose.equals("nation")) {
						//quốc gia (class nation)
						//gửi danh sách quốc gia về client để đổ vào combobox
						ListCountry lstCountry = new ListCountry();
						sendToClient(out, lstCountry.getListName());//trả về dãy string ngăn cách bởi dấu ,
						//gửi danh sách thành phố
						/*
								tạo thêm đối tượng city trong country làm típ
						*/
						//idInfo theo quy định: quốc gia = n+stt ,  thành phố = c+stt
						String idInfo = receiveFromClient(in);
						Country country = new Country();
						if (idInfo.matches("^n\\d+$")) {
								//lấy thông tin quốc gia
								int id = Integer.parseInt( idInfo.replace("n", "") );
								country = lstCountry.getCountryById(id);
								//gửi thông tin 
								sendToClient(outObj, country);
						}
						if (idInfo.matches("^c\\d+$")) {
								//lấy thông tin thành phố
								int id = Integer.parseInt( idInfo.replace("c", "") );
								country = lstCountry.getCountryById(id);
								//gửi thông tin 
								sendToClient(outObj, country.getCityById() );
						}
						
				}
				
				
				//xong hết rồi đóng hết để giải phóng tài nguyên
				in.close();
				out.close();
				outObj.close();
				socket.close();
				server.close();
			} catch (IOException e) {
					System.out.println("client ngắt kết nối\n");
			}
			System.out.print("đóng kết nối");
	}
	public void sendToClient(BufferedWriter out,String data) {
			try {
				data = aes.encryption(data);//mã hóa
				out.write(data);
				out.newLine();
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void sendToClient(ObjectOutputStream outObj,Object obj) {
			try {
				obj = aes.encryption(obj);//mã hóa
				outObj.writeObject(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public String receiveFromClient(BufferedReader in) {
			try {
				String data = in.readLine();
				data = aes.decryption(data);//giải mã
				return data;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
	}
	public static void main(String[] args) {
		Server ser=new Server(6000);
	}
}
