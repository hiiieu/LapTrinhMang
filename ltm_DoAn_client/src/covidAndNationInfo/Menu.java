package covidAndNationInfo;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Menu {

		public JFrame frmTraCuThng;
		BufferedReader in=null;
		BufferedWriter out=null;
		ObjectInputStream inObj=null;
		Transport transport = new Transport();
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
		public Menu(BufferedReader in,BufferedWriter out,ObjectInputStream inObj) {
			initialize();
			this.in=in;
			this.out=out;
			this.inObj=inObj;
		}
	
		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frmTraCuThng = new JFrame();
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
						String kq=transport.receive(in);
						JOptionPane.showConfirmDialog(null, kq, "Be ok!", JOptionPane.DEFAULT_OPTION);
				}
			});
			btnCountry.setFont(new Font("Tahoma", Font.PLAIN, 24));
			frmTraCuThng.getContentPane().add(btnCountry);
		}

}
