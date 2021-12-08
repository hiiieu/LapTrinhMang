package Giaodien;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Client.Transport;
import MaHoa.MaHoaDoiXung;
import Tenlagibaygio.SinhVien;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu {

		public JFrame frmTraCuThng;
		BufferedReader in=null;
		BufferedWriter out=null;
		Transport transport = null;
		Socket socket;
		InputStream InObj=null;
		/**
		 * Launch the application.
		 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					Menu window = new Menu();
	//					window.frmTraCuThng.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}
	
		/**
		 * Create the application.
		 */
		public Menu(InputStream InObj,BufferedReader in,BufferedWriter out,MaHoaDoiXung aes) {
			initialize();
			this.InObj=InObj;
			this.in=in;
			this.out=out;
			transport = new Transport(aes);
		}
	
		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frmTraCuThng = new JFrame();
			frmTraCuThng.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					transport.send(out, "Menuclose");
						try {
							in.close();
							out.close();
							InObj.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
				}
			});
			frmTraCuThng.setTitle("Tra cứu thông tin");
			frmTraCuThng.setResizable(false);
			frmTraCuThng.setBounds(100, 100, 450, 300);
			frmTraCuThng.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//mở lên nó hiện giữa màn hình
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frmTraCuThng.setLocation(dim.width/2-frmTraCuThng.getSize().width/2, dim.height/2-frmTraCuThng.getSize().height/2);
			frmTraCuThng.getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
			
			JButton btnCovid = new JButton("Tra cứu thông tin covid");
			btnCovid.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
							transport.send(out, "covid");
							String kq=transport.receive(in);
							JOptionPane.showConfirmDialog(null, kq, "Be ok!", JOptionPane.DEFAULT_OPTION);
				}
			});
			
			btnCovid.setFont(new Font("Tahoma", Font.PLAIN, 25));
			frmTraCuThng.getContentPane().add(btnCovid);
			
			JButton btnCountry = new JButton("Tra cứu thông tin quốc gia");
			btnCountry.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						transport.send(out, "nation");
						//String kq=transport.receive(in);
						SinhVien sv;
							sv = (SinhVien)transport.receive(InObj);
							JOptionPane.showConfirmDialog(null, sv.getLop() + sv.getTen(), "Be ok!", JOptionPane.DEFAULT_OPTION);
						
				}
			});
			btnCountry.setFont(new Font("Tahoma", Font.PLAIN, 24));
			frmTraCuThng.getContentPane().add(btnCountry);
		}
		

}
