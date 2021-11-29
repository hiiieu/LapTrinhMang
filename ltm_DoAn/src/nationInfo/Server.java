package nationInfo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private Socket socket=null;
	private ServerSocket server=null;
	BufferedReader in=null;
	BufferedWriter out=null;
	
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
				//Thiết lập mã hóa
				

				
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
	public static void main(String[] args) {
		Server ser=new Server(6000);
	}
}
