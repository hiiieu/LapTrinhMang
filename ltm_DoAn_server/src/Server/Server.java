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
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JOptionPane;

import DTO.Country;
import DTO.Covid;
import DTO.SinhVien;
import FetchInfo.CovidInfo;
import FetchInfo.CovidMaxinfo;
import FetchInfo.ListCity;
import FetchInfo.ListCountry;
import FetchInfo.WeatherInfo;
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
						ListCountry ls = new ListCountry();
						ListCity lsc=new ListCity();
						
						WeatherInfo wt=new WeatherInfo();
						if(choose.equals("Menuclose")) {
								System.out.println("client đã thoát");
								break;
						}
						if (choose.equals("covid")) {
								
								ls.init();
								transport.send(out, ls.ListNameToString());
						}
						if(choose.equals("Search")) {
								int i = 0;							
								i= Integer.parseInt(transport.receive(in));
								
						//=============================================
								String time = transport.receive(in);
								String name = ls.getInfoByID(i).getTenQuocGia();
								String line = getCovidInfo(ls.getInfoByID(i).getTenQuocGia(),time);
								//kq
								transport.send(out,line );
								
								System.out.println(transport.receive(in));
								transport.send(out, "hihi");
								//kq2
								transport.send(out, getTotalCovidInfo(name));
								//kq3
								transport.send(out,getCovidMax());
						//=============================================
								transport.send(output, ls.getInfoByID(i));
								
						}
						
						if (choose.equals("nation")) {
								
								ls.init();
								
								transport.send(out, ls.ListNameToString());
								transport.send(out, lsc.ListName());
						}
						if(choose.equals("tracuuquocgia")) {								
								int i = 0;							
								i= Integer.parseInt(transport.receive(in));
								transport.send(output, ls.getInfoByID(i));
						}
						if(choose.equals("tracuuthanhpho")) {
								String a= transport.receive(in);
								
								transport.send(output, wt.getWeather(a));
						}
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
								
						
						//Thread.sleep(5000);
				}while(true);
				socketClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 	}
}

	public String getCovidInfo(String ten,String time) {
		CovidInfo tmp = new CovidInfo();
		String[]times = time.split(";");
		String time1=times[0];
		String time2=times[1];
		tmp.getLstCovid(ten, time1,time1);
		int m1=0,m2=0,m3=0,m4=0,m5=0,m6=0;
		for (Covid i : CovidInfo.lstCovid) {
			if(i.getThoiGian().compareTo(time1.toString())>=0) {
				if(m1<i.getCaNhiem())		
				m1= i.getCaNhiem();
				if(m2<i.getKhoiBenh())
				m2=i.getKhoiBenh();
				if(m3<i.getChet())		
				m3=i.getChet();
				}
			}
		tmp.getLstCovid(ten, time2,time2);
		for (Covid i : CovidInfo.lstCovid) {
			if(i.getThoiGian().compareTo(time2.toString())<=0) {
				if(m4<i.getCaNhiem())		
				m4= i.getCaNhiem();
				if(m5<i.getKhoiBenh())
				m5=i.getKhoiBenh();
				if(m6<i.getChet())		
				m6=i.getChet();
				}
			}
		int x =m4-m1,y=m5-m2,z=m6-m3;
		x=Math.abs(x);
		y=Math.abs(y);
		z=Math.abs(z);
		String str = ten+";"+x+";"+y+";"+z;
		return str;
		}
	public String getTotalCovidInfo(String quocgia) {
		CovidInfo c = new CovidInfo();
		
		return c.getMax(quocgia);
	}
	
	public String getCovidMax() {
		CovidMaxinfo max = new CovidMaxinfo();
		max.getListmax();
		String kq =max.canhiemmax()+";"+max.cachetmmax();
		return kq;
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
