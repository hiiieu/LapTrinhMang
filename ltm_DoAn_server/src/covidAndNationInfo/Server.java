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
	
	public Server(int port) {
				//tạo server đợi client
				try {
						server = new ServerSocket(port);
				} catch (IOException e1) {
						System.err.print("lỗi ở new ServerSocket"+e1);
				} 
				System.out.println("server bắt đầu chạy");
				boolean flag=true;
				while(flag==true) {
						try {
							System.out.println("server đang đợi yêu cầu");
							socket = server.accept();
							//khi client kết nối hoàn tất
							System.out.println("client đã kết nối");
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
							outObj = new ObjectOutputStream(socket.getOutputStream());
							
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
											transport.send(out, "bạn đã chọn tra cứu thông tin quốc gia");
					//						//quốc gia (class nation)
					//						//gửi danh sách quốc gia về client để đổ vào combobox
					//						ListCountry lstCountry = new ListCountry();
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
							}while(true);
									

						} catch (IOException e) {
							System.err.print("client đã ngắt kết nối.");
						}
						
				}
				//xong hết rồi đóng hết để giải phóng tài nguyên
				try {
					in.close();
					out.close();
					outObj.close();
					socket.close();
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
