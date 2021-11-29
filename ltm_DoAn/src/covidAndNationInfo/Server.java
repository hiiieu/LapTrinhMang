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
	ObjectOutputStream out_obj=null;
	static String publicKey="";//làm típ phần này
	static String clientKey="";
	static String privateKey="";
	
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
				out_obj = new ObjectOutputStream(socket.getOutputStream());
				
				/*Thiết lập mã hóa*/
				//1. gửi public key tới client
				out.write(publicKey);
				out.newLine();
				out.flush();
				//2. nhận key đối xứng
				String clientKeyEncode = in.readLine();
				//3. giải mã key để nhận clientKey
				clientKey =  DecodeClientKey(clientKeyEncode);
				
				/*chọn chức năng*/
				String choose = receiveStringFromClient(in);
				if (choose.equals("covid")) {
						//viết covid (class covid)
				}
				
				if (choose.equals("nation")) {
						//viết quốc gia (class nation)
						//idInfo theo quy định: quốc gia = n+stt ,  thành phố = c+stt
						String idInfo = receiveStringFromClient(in);
						
				}
				
				
				//xong hết rồi đóng hết để giải phóng tài nguyên
				in.close();
				out.close();
				socket.close();
				server.close();
			} catch (IOException e) {
					System.out.println("client ngắt kết nối\n");
			}
			System.out.print("đóng kết nối");
	}
	public void sendStringToClient(BufferedWriter out,String data) {
			try {
				data = encodeData(data);//mã hóa
				out.write(data);
				out.newLine();
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public String receiveStringFromClient(BufferedReader in) {
			try {
				String data = in.readLine();
				data = decodeData(data);//giải mã
				return data;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
	}
	public String DecodeClientKey(String clientKeyEncode) {
		//viết thêm giải mã vô đây(giải mã = public key)
		return "";
}
	public String encodeData(String data) {
			//viết thêm mã hóa vô đây
			return "";
	}
	public String decodeData(String data) {
			//viết thêm giải mã vô đây
			return "";
}
	public static void main(String[] args) {
		Server ser=new Server(6000);
	}
}
