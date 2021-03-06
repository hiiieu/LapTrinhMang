package Client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Giaodien.Menu;
import MaHoa.MaHoaCongKhai;
import MaHoa.MaHoaDoiXung;

public class Client {
		private Socket socket=null;
		BufferedReader in=null;
		BufferedWriter out=null;
		
		MaHoaCongKhai rsa = new MaHoaCongKhai();
		MaHoaDoiXung aes = new MaHoaDoiXung();
		
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
							InputStream input = socket.getInputStream();
							OutputStream output = socket.getOutputStream();
							in = new BufferedReader(new InputStreamReader(input));
							out = new BufferedWriter(new OutputStreamWriter(output));
							
							/*thiết lập mã hóa */
							//nhận public key
							String publicKey = in.readLine();
							if(!publicKey.equals(MaHoaCongKhai.getPublicKeyString())) {
									JOptionPane.showConfirmDialog(null, "public key của server không khớp", "Be not ok!", JOptionPane.DEFAULT_OPTION);
									System.exit(0);
							}
							//mã hóa secretKey và gửi lại server
							aes.init();//tạo secretKey
							String key = aes.exportKey();String IV = aes.exportIV();
							String Skey = rsa.encrypt( key );
							String SIV = rsa.encrypt( IV );
							out.write(Skey);out.newLine();out.flush();
							out.write(SIV);out.newLine();out.flush();
							
							//kết nối xong thì show menu
							Menu window = new Menu(input,in,out,aes);
							window.frmTraCuThng.setVisible(true);
							
							
							//xong hết thì đóng
						} catch (IOException e) {
							e.printStackTrace();
						}
						
		}
		public static void main(String[] args) {
			Client cli=new Client("localhost", 6000);
		}
}
