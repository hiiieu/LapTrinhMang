package covidAndNationInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		ObjectOutputStream outObj = null;
		
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
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
							outObj = new ObjectOutputStream(socket.getOutputStream());
							inObj = new ObjectInputStream(socket.getInputStream());
							
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
							Menu window = new Menu(in,out,inObj,outObj,aes);
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
