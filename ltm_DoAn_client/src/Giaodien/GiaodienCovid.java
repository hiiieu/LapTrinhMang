package Giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiaodienCovid extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	JPanel CountryFlag;
	private JTextField txtCaKhoi;
	private JTextField txtTuVong;
	private JTextField txtTyLeChet;
	private JTextField txtCaNhiem;
	private JTextField txtDate_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtTotal1;
	private JTextField txtTotal2;
	private JTextField txtTotal3;
	
	
	public static boolean isDateValid(String date) 
	{
	        try {
	            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	}
	
	public boolean checkEmpty() {
		int f1=0, f2=0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		LocalDateTime yesterday = now.minusDays(2);
		now=now.minusDays(1);
		if(txtDate.getText().isEmpty())
			f1=1;
		if(txtDate.getText().isEmpty())
			f2=1;
		if(f1==1)
			if(f2==1) {
				 
				txtDate.setText(dtf.format(yesterday));
				txtDate_2.setText(dtf.format(now));
				return true;
			}
			else {
				txtDate.setText(dtf.format(now));
				return true;
			}
		else {
			if(f2==1) txtDate_2.setText(dtf.format(now));
			return true;
		}
				
	}
	
	
	
	
	
	
	
	

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
		setBounds(100, 100, 1002, 552);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCountry = new JLabel("Quốc gia:");
		lblCountry.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCountry.setBounds(515, 66, 65, 17);
		contentPane.add(lblCountry);
		
		JLabel lblNewLabel = new JLabel("Thời gian:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(235, 133, 68, 17);
		lblNewLabel.setBounds(461, 110, 68, 17);
		contentPane.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setToolTipText("yyyy-mm-dd");
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDate.setColumns(10);
		txtDate.setBounds(402, 130, 141, 23);
		contentPane.add(txtDate);
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(628, 65, 168, 23);
		txtDate.setBounds(628, 107, 141, 23);
		contentPane.add(txtDate);
		String kq=tranport.receive(in);
		StringTokenizer tk=new StringTokenizer(kq,";");
		for(;tk.hasMoreTokens();) {
			comboBox.addItem(tk.nextToken());
		}
		CountryFlag = new JPanel();
		CountryFlag.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CountryFlag.setBounds(55, 27, 326, 171);
		contentPane.add(CountryFlag);
		CountryFlag.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCountryFlag = new JLabel();
		CountryFlag.add(lblCountryFlag);
		
		contentPane.add(comboBox);
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkEmpty();
				if(isDateValid(txtDate.getText()))
				{
					if(isDateValid(txtDate.getText())) {
						
						tranport.send(out,"Search");
						tranport.send(out, ""+comboBox.getSelectedIndex());
						//====================================================================
						tranport.send(out,txtDate.getText()+";"+txtDate_2.getText());
						String kq = tranport.receive(in);
						String[] kqs = kq.split(";");
						txtCaNhiem.setText(kqs[1]);
						txtCaKhoi.setText(kqs[2]);
						txtTuVong.setText(kqs[3]);
						tranport.send(out, "oke");
						System.out.println(tranport.receive(in));
						String kq2=tranport.receive(in);
						String[] kq2s = kq2.split(";");
						txtTotal1.setText(kq2s[0]);
						txtTotal2.setText(kq2s[1]);
						txtTotal3.setText(kq2s[2]);
						DecimalFormat df = new DecimalFormat("0.00");
						double rate = (1.0)*Integer.parseInt(kq2s[2])/Integer.parseInt(kq2s[0])*100;
						 
						txtTyLeChet.setText(""+df.format(rate));
						
						
						String[] kq3s = tranport.receive(in).split(";");
						textField.setText(kq3s[0]);
						textField_1.setText(kq3s[2]);
						textField_3.setText(kq3s[1]);
						textField_4.setText(kq3s[3]);
						
						//====================================================================
						Country cts;
						cts=(Country)tranport.receive(inObj);
//						System.out.print("haa");
						String path = cts.getQuocKy();
						try {
							
							URL url = new URL(path);
							BufferedImage image = ImageIO.read(url);
							lblCountryFlag.setIcon(new ImageIcon(image));
							lblCountryFlag.setBounds(0, 0, 0, 0);
							
							}catch(Exception e1) {
								System.out.print(e1);
							}
					}else JOptionPane.showMessageDialog(null, "Hãy nhập đúng thời gian theo định dạng YYYY-MM-DD");
				}
				else JOptionPane.showMessageDialog(null, "Hãy nhập đúng thời gian theo định dạng YYYY-MM-DD");
                        
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(831, 82, 89, 45);
		contentPane.add(btnSearch);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK));
		panel.setBounds(55, 223, 921, 290);
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
		
		txtCaKhoi = new JTextField();
		txtCaKhoi.setBounds(209, 61, 171, 20);
		panel.add(txtCaKhoi);
		txtCaKhoi.setColumns(10);
		
		txtTuVong = new JTextField();
		txtTuVong.setColumns(10);
		txtTuVong.setBounds(209, 95, 171, 20);
		panel.add(txtTuVong);
		
		txtTyLeChet = new JTextField();
		txtTyLeChet.setColumns(10);
		txtTyLeChet.setBounds(209, 130, 171, 20);
		panel.add(txtTyLeChet);
		
		txtCaNhiem = new JTextField();
		txtCaNhiem.setColumns(10);
		txtCaNhiem.setBounds(209, 23, 86, 20);
		panel.add(txtCaNhiem);
		
		JLabel lblNewLabel_2 = new JLabel("%");
		lblNewLabel_2.setBounds(305, 130, 22, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quốc gia có số ca nhiễm cao nhất :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(24, 174, 217, 17);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quốc gia có số ca tử vong cao nhất :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(11, 228, 230, 17);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Số ca:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(202, 202, 39, 17);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Số ca");
		lblNewLabel_6_1.setBounds(315, 209, 44, 27);
		txtCaNhiem.setBounds(209, 23, 171, 20);
		panel.add(txtCaNhiem);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(274, 174, 290, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(274, 228, 290, 20);
		panel.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(274, 201, 86, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(274, 255, 86, 20);
		panel.add(textField_4);
		
		JLabel lblTngSCa = new JLabel("Tổng số ca nhiễm: ");
		lblTngSCa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngSCa.setBounds(460, 26, 118, 17);
		panel.add(lblTngSCa);
		
		JLabel lblTngSCa_1 = new JLabel("Tổng số ca khỏi bệnh: ");
		lblTngSCa_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngSCa_1.setBounds(460, 64, 141, 17);
		panel.add(lblTngSCa_1);
		
		JLabel lblTngSCa_2 = new JLabel("Tổng số ca tử vong :");
		lblTngSCa_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngSCa_2.setBounds(460, 98, 131, 17);
		panel.add(lblTngSCa_2);
		
		txtTotal1 = new JTextField();
		txtTotal1.setColumns(10);
		txtTotal1.setBounds(613, 23, 190, 20);
		panel.add(txtTotal1);
		
		txtTotal2 = new JTextField();
		txtTotal2.setColumns(10);
		txtTotal2.setBounds(613, 61, 190, 20);
		panel.add(txtTotal2);
		
		txtTotal3 = new JTextField();
		txtTotal3.setColumns(10);
		txtTotal3.setBounds(613, 95, 190, 20);
		panel.add(txtTotal3);
		
		JLabel lblNewLabel_6_2 = new JLabel("Số ca:");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6_2.setBounds(202, 256, 39, 17);
		panel.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_1 = new JLabel("Từ ngày");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(534, 113, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Đến ngày");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(534, 148, 65, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtDate_2 = new JTextField();
		txtDate_2.setToolTipText("yyyy-mm-dd");
		txtDate_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDate_2.setColumns(10);
		txtDate_2.setBounds(402, 169, 141, 23);
		txtDate_2.setBounds(628, 146, 141, 23);
		contentPane.add(txtDate_2);
		
	
		
	}
}
