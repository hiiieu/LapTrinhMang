package Giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Client.Transport;
import DTO.Country;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

public class GiaodienCovid extends JFrame {

	private JPanel contentPane;
	private JTextField txtCountry;
	private JTextField txtDate;
	JPanel CountryFlag;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GiaodienCovid frame = new GiaodienCovid();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GiaodienCovid(InputStream inObj,BufferedReader in,BufferedWriter out,Transport tranport) {
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Thông tin bệnh nhân covid");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 732, 552);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCountry = new JLabel("Quốc gia:");
		lblCountry.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCountry.setBounds(289, 89, 65, 17);
		contentPane.add(lblCountry);
		
		JLabel lblNewLabel = new JLabel("Thời gian:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(289, 129, 68, 17);
		contentPane.add(lblNewLabel);
		
		txtCountry = new JTextField();
		txtCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCountry.setBounds(375, 82, 168, 31);
		contentPane.add(txtCountry);
		txtCountry.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDate.setColumns(10);
		txtDate.setBounds(375, 122, 168, 31);
		contentPane.add(txtDate);
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(375, 164, 168, 22);
		String kq=tranport.receive(in);
		StringTokenizer tk=new StringTokenizer(kq,";");
		for(;tk.hasMoreTokens();) {
			comboBox.addItem(tk.nextToken());
		}
		CountryFlag = new JPanel();
		CountryFlag.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CountryFlag.setBounds(55, 65, 170, 96);
		contentPane.add(CountryFlag);
		CountryFlag.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCountryFlag = new JLabel();
		CountryFlag.add(lblCountryFlag);
		
		contentPane.add(comboBox);
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tranport.send(out,"Search");
				tranport.send(out, ""+comboBox.getSelectedIndex());
				Country cts;
				cts=(Country)tranport.receive(inObj);
//				System.out.print("haa");
				String path = cts.getQuocKy();
				try {
					
					URL url = new URL(path);
					BufferedImage image = ImageIO.read(url);
					lblCountryFlag.setIcon(new ImageIcon(image));
					lblCountryFlag.setBounds(0, 0, 0, 0);
					
					}catch(Exception e1) {
						System.out.print(e1);
					}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(575, 89, 89, 45);
		contentPane.add(btnSearch);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(699, 0, 17, 513);
		contentPane.add(scrollBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK));
		panel.setBounds(55, 223, 609, 290);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel txtInfect = new JLabel("Số ca nhiễm: ");
		txtInfect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInfect.setBounds(42, 23, 106, 17);
		panel.add(txtInfect);
		
		JLabel txtCure = new JLabel("Số ca khỏi bệnh:");
		txtCure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCure.setBounds(42, 61, 106, 17);
		panel.add(txtCure);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tử vong:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(42, 95, 106, 17);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tỉ lệ tử vong:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(42, 133, 106, 17);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Thời gian:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(42, 173, 106, 17);
		panel.add(lblNewLabel_1_4);
		
		
		
		
		
		
		
		
	}
}
