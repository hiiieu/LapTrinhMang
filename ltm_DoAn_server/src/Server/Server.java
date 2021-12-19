package Server;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JOptionPane;

import DTO.Country;
import DTO.SinhVien;
import FetchInfo.ListCountry;
import MaHoa.MaHoaCongKhai;
import MaHoa.MaHoaDoiXung;

public class Server {
	private Socket socket=null;
	private ServerSocket server=null;
	//viết xử lý trong đây
	private class ClientHandler implements Runnable {
		BufferedReader in=null;
		BufferedWriter out=null;
	 	private final Socket socketClient;
	 	 public ClientHandler(Socket socket)
	        {
	            this.socketClient = socket;
	        }
	 	public void run(){
	 		//khi client kết nối hoàn tất
			System.out.println("client đã kết nối");
			
			
			try {
				InputStream input= socketClient.getInputStream();
				OutputStream output = socketClient.getOutputStream();
				in = new BufferedReader(new InputStreamReader(input));
				out = new BufferedWriter(new OutputStreamWriter(output));
				
				/*Thiết lập mã hóa*/
				MaHoaDoiXung aes = new MaHoaDoiXung();
				MaHoaCongKhai rsa = new MaHoaCongKhai();
				//1. gửi public key tới client
				String publicKey = MaHoaCongKhai.getPublicKeyString();
				out.write(publicKey);
				out.newLine();
				out.flush();
				//2. nhận key đối xứng và iv
				String clientKeyString_encode = in.readLine();
				String IV = in.readLine();
				//3. giải mã key để nhận clientKey
				String clientKeyString=  rsa.decrypt(clientKeyString_encode);
				String iv = rsa.decrypt(IV);
				aes.initFromStrings(clientKeyString, iv);
				System.out.println("thiết lập mã hóa thành công, private key:" + clientKeyString);
				
				/*chọn chức năng*/
				Transport transport = new Transport(aes);//truyền aes để thực hiện mã và giải
				do {
						String choose = transport.receive(in);
						if(choose.equals("Menuclose")) {
								System.out.println("client đã thoát");
								break;
						}
						if (choose.equals("covid")) {
								transport.send(out, "bạn đã chọn tra cứu thông tin covid");
								//viết covid (class covid)
						}
						
						if (choose.equals("nation")) {
								//transport.send(out, "bạn đã chọn tra cứu thông tin quốc gia");
//								SinhVien sv = new SinhVien("hieu", 10);
//								transport.send(output, sv);
								
		//						//quốc gia (class nation)
		//						//gửi danh sách quốc gia về client để đổ vào combobox
								ListCountry ls = new ListCountry();
								ls.init();
								transport.send(out, ls.ListNameToString());
								int i = 0;
								while(i != -1) {
								i= Integer.parseInt(transport.receive(in));
								transport.send(output, ls.getInfoByID(i));}
		//						sendToClient(out, lstCountry.getListName());//trả về dãy string ngăn cách bởi dấu ,
		//						//gửi danh sách thành phố
							
		//						/*
		//								tạo thêm đối tượng city trong country làm típ
		//						*/
		//						//idInfo theo quy định: quốc gia = n+stt ,  thành phố = c+stt
		//						String idInfo = receiveFromClient(in);
		//						Country country = new Country();
		//						if (idInfo.matches("^n\\d+$")) {
		//								//lấy thông tin quốc gia
		//								int id = Integer.parseInt( idInfo.replace("n", "") );
		//								country = lstCountry.getCountryById(id);
		//								//gửi thông tin 
		//								sendToClient(outObj, country);
		//						}
		//						if (idInfo.matches("^c\\d+$")) {
		//								//lấy thông tin thành phố
		//								int id = Integer.parseInt( idInfo.replace("c", "") );
		//								country = lstCountry.getCountryById(id);
		//								//gửi thông tin 
		//								//sendToClient(outObj, country.getCityById() );
		//						}
								
						}
						//Thread.sleep(5000);
				}while(true);
				socketClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 	}
}
	
	//tạo server đợi client
	public Server(int port) {
				//tạo server đợi client
				try {
						server = new ServerSocket(port);
				}catch ( BindException e ){
						JOptionPane.showMessageDialog(null, "server đang mở không mở thêm được");
						System.exit(0);
				} catch (IOException e1 ) {
						System.err.print("lỗi ở new ServerSocket"+e1);
				} 
				System.out.println("server bắt đầu chạy");
				boolean flag=true;
				ExecutorService executor = Executors.newFixedThreadPool(5);
				while(flag==true) {
							try {
								System.out.println("server đang đợi yêu cầu");
								socket = server.accept();
								ClientHandler client = new ClientHandler(socket);
								executor.execute(client);
								//new Thread(client).start();
							} catch (IOException e) {
								System.err.print("client đã ngắt kết nối.");
							}
				}
				executor.shutdown();
				//xong hết rồi đóng hết để giải phóng tài nguyên
				try {
					server.close();
					System.out.print("server đã tắt");
				} catch (IOException e) {
						System.err.print("không đóng được socket,server,inout");
				}
	}
	
	public static void main(String[] args) {
		Server ser=new Server(6000);
	}
}
