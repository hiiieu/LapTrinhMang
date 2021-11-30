package covidAndNationInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
		private Socket socket=null;
		BufferedReader in=null;
		BufferedWriter out=null;
		ObjectInputStream inObj=null;
		
		public Client(String address,int port) {
				//tạo socket kết nối tới server
				while (socket ==null) {
						try {
								socket = new Socket(address,port);
						}catch (IOException e) {
								int rs = JOptionPane.showConfirmDialog(null, "Kết nối lại", "Lỗi kết nối",JOptionPane.YES_NO_CANCEL_OPTION);
								if(rs==JOptionPane.NO_OPTION || rs == JOptionPane.CANCEL_OPTION || rs == JOptionPane.CLOSED_OPTION) 
										System.exit(0);
						}
				}
				
				
				try {
					
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					inObj = new ObjectInputStream(socket.getInputStream());
					//kết nối xong thì show menu
					Menu window = new Menu(in,out,inObj);
					window.frmTraCuThng.setVisible(true);
					//thiết lập mã hóa với server
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
//				
//				String line = "";
//				do {
//					//lấy dữ liệu nhập từ màn hình
//					line = stdIn.readLine();
//					if(line.toLowerCase().equals("bye")) break;
//					//ghi vào socket
//					out.write(line);
//					out.newLine();
//					out.flush();
//					//nhập kết quả gửi từ server
//					String result=in.readLine();
//					System.out.println(result);
//				}while (true);
//				//xong hết thì đóng
//				in.close();
//				out.close();
//				socket.close();
		}
		public static void main(String[] args) {
			Client cli=new Client("localhost", 6000);
		}
}
