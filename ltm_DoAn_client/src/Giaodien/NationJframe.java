package Giaodien;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import Client.Transport;
import DTO.Country;
import DTO.Weather;
import MaHoa.MaHoaDoiXung;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.border.BevelBorder;


public class NationJframe extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField txtquocgia;
	private JTextField txtthanhpho;
	private JPanel panelthongtinquocgia;
	private JPanel panel;
	
	/**
	 * Launch the application.
	 */
//	public void init() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NationJframe frame = new NationJframe();
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

	public NationJframe(InputStream inobj,BufferedReader in,BufferedWriter out,Transport transport) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1045, 516);
		contentPane = new JPanel();
		contentPane.setName("aaa");
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_tong = new JPanel();
		panel_tong.setBackground(Color.WHITE);
		panel_tong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_tong.setBounds(0, 35, 1029, 404);		
		panel_tong.setLayout(null);
		contentPane.add(panel_tong);
		
		
		panelthongtinquocgia = new JPanel();
		panelthongtinquocgia.setBackground(Color.WHITE);
		panelthongtinquocgia.setLayout(null);
		panelthongtinquocgia.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelthongtinquocgia.setBounds(35, 117, 603, 248);
		panel_tong.add(panelthongtinquocgia);
		
		JLabel lblDanSo = new JLabel("D\u00E2n s\u1ED1:");
		lblDanSo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDanSo.setBounds(10, 11, 41, 15);
		panelthongtinquocgia.add(lblDanSo);
		
		JLabel lblDienTich = new JLabel("Di\u1EC7n t\u00EDch:");
		lblDienTich.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDienTich.setBounds(10, 36, 52, 15);
		panelthongtinquocgia.add(lblDienTich);
		
		JLabel lblTienTe = new JLabel("Ti\u1EC1n t\u1EC7:");
		lblTienTe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTienTe.setBounds(10, 61, 46, 14);
		panelthongtinquocgia.add(lblTienTe);
		
		JLabel lblThuDo = new JLabel("Th\u1EE7 \u0111\u00F4:");
		lblThuDo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThuDo.setBounds(10, 86, 46, 14);
		panelthongtinquocgia.add(lblThuDo);
		
		JLabel lblChauLuc = new JLabel("Ch\u00E2u l\u1EE5c:");
		lblChauLuc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChauLuc.setBounds(10, 111, 50, 15);
		panelthongtinquocgia.add(lblChauLuc);
		
		JLabel lblNgonNgu = new JLabel("Ng\u00F4n ng\u1EEF:");
		lblNgonNgu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNgonNgu.setBounds(10, 136, 59, 15);
		panelthongtinquocgia.add(lblNgonNgu);
		
		JLabel lblMuiGio = new JLabel("M\u00FAi gi\u1EDD:");
		lblMuiGio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMuiGio.setBounds(10, 161, 46, 14);
		panelthongtinquocgia.add(lblMuiGio);
		
		JLabel lblTiepGiap = new JLabel("Ti\u1EBFp gi\u00E1p:");
		lblTiepGiap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTiepGiap.setBounds(10, 186, 54, 15);
		panelthongtinquocgia.add(lblTiepGiap);
		
		JLabel lbldanso = new JLabel("Valuedanso");
		lbldanso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldanso.setBounds(72, 11, 62, 15);
		panelthongtinquocgia.add(lbldanso);
		
		JLabel lbldientich = new JLabel("Valuedientich");
		lbldientich.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldientich.setBounds(72, 36, 73, 15);
		panelthongtinquocgia.add(lbldientich);
		
		JLabel lbltiente = new JLabel("Valuetiente");
		lbltiente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltiente.setBounds(72, 61, 149, 15);
		panelthongtinquocgia.add(lbltiente);
		
		JLabel lblthudo = new JLabel("Valuethudo");
		lblthudo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblthudo.setBounds(72, 86, 63, 15);
		panelthongtinquocgia.add(lblthudo);
		
		JLabel lblchauluc = new JLabel("Valuechauluc");
		lblchauluc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblchauluc.setBounds(72, 111, 149, 15);
		panelthongtinquocgia.add(lblchauluc);
		
		JLabel lblngonngu = new JLabel("Valuengonngu");
		lblngonngu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblngonngu.setBounds(72, 136, 79, 15);
		panelthongtinquocgia.add(lblngonngu);
		
		JLabel lblmuigio = new JLabel("Valuemuigio");
		lblmuigio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblmuigio.setBounds(72, 161, 65, 15);
		panelthongtinquocgia.add(lblmuigio);
		
		
		
		JTextArea txttiepgiap = new JTextArea();
		txttiepgiap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txttiepgiap.setText("Valuetiepgiap");
		txttiepgiap.setBounds(71, 186, 188, 34);
		txttiepgiap.setLineWrap(true);
		txttiepgiap.setWrapStyleWord(true);
		panelthongtinquocgia.add(txttiepgiap);
		
		JLabel lblToaDoQuocGia = new JLabel("T\u1ECDa \u0111\u1ED9:");
		lblToaDoQuocGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblToaDoQuocGia.setBounds(10, 223, 52, 15);
		panelthongtinquocgia.add(lblToaDoQuocGia);
		
		JLabel lbltoadoquocgia = new JLabel("Valuetoadoquocgia");
		lbltoadoquocgia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbltoadoquocgia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltoadoquocgia.setBounds(72, 224, 104, 15);
		panelthongtinquocgia.add(lbltoadoquocgia);
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(260, 9, 333, 229);
		panelthongtinquocgia.add(panel);
		JPanel panelTC = new JPanel();
		panelTC.setBackground(Color.WHITE);
		panelTC.setBounds(35, 38, 256, 50);		
		panelTC.setLayout(null);
		panel_tong.add(panelTC);
		
		
		
		txtquocgia = new JTextField();
		txtquocgia.setBounds(0, 30, 161, 20);		
		txtquocgia.setEditable(false);
		txtquocgia.setColumns(10);	
		
		panelTC.add(txtquocgia);
		
		
		
		String kq = transport.receive(in);
		StringTokenizer tk=new StringTokenizer(kq,";");
		JComboBox cbbquocgia = new JComboBox();
		cbbquocgia.setEditable(true);
		for(;tk.hasMoreTokens();) {
			cbbquocgia.addItem(tk.nextToken());
		}
		AutoCompleteDecorator.decorate(cbbquocgia);
		cbbquocgia.setBounds(0, 0, 161, 20);
		panelTC.add(cbbquocgia);
		
//		JLabel lblimage = new JLabel((Icon) null);
//		lblimage.setBackground(Color.WHITE);
//		lblimage.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lblimage.setBounds(192, 11, 279, 209);
//		panelthongtinquocgia.add(lblimage);
		JLabel img = new JLabel();
		panel.add(img);
		
		JButton btnTC = new JButton("Tra c\u1EE9u");
		btnTC.setBackground(Color.LIGHT_GRAY);
		btnTC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTC.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnTC.setBounds(167, 0, 89, 20);
		btnTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = cbbquocgia.getSelectedIndex();
				if(n>=0 && n<250) {
						transport.send(out, "tracuuquocgia");
						transport.send(out, ""+cbbquocgia.getSelectedIndex());
						Country ct;
						ct=(Country)transport.receive(inobj);
						lbldanso.setText(""+ct.getDanSo());
						lbldientich.setText(""+ct.getDienTich());
						lbltiente.setText(ct.getTienTe());
						lblthudo.setText(ct.getThuDo());
						lblchauluc.setText(ct.getChauLuc());
						lblngonngu.setText(ct.getNgonNgu());
						lblmuigio.setText(ct.getMuiGio());
						txttiepgiap.setText(ct.getTiepGiap());
						lbltoadoquocgia.setText(ct.getToaDo());
		//				lblimage.setText(ct.getQuocKy());
		//				Image(ct.getQuocKy());
						
						
						//khúc này lỗi nhé, ra hình mà chưa đổi khi chọn mới dc
						String path = ct.getQuocKy();
						try {
							
							URL url = new URL(path);
							BufferedImage image = ImageIO.read(url);
							img.setIcon(new ImageIcon(image));
							img.setBounds(0, 0, 0, 0);
							
							}catch(Exception e1) {
								System.out.print(e1);
							}
				}else {
					JOptionPane.showMessageDialog(null, "sai tên nước");
				}
				
			}
		});		
		panelTC.add(btnTC);
		
		JPanel panelthongtinthanhpho = new JPanel();
		panelthongtinthanhpho.setBackground(Color.WHITE);
		panelthongtinthanhpho.setLayout(null);
		panelthongtinthanhpho.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelthongtinthanhpho.setBounds(648, 117, 371, 248);
		panel_tong.add(panelthongtinthanhpho);
		
		JLabel lblDanSoThanhPho = new JLabel("D\u00E2n s\u1ED1 c\u1EE7a th\u00E0nh ph\u1ED1:");
		lblDanSoThanhPho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDanSoThanhPho.setBounds(10, 11, 125, 15);
		panelthongtinthanhpho.add(lblDanSoThanhPho);
		
		JLabel lblDienTich_1 = new JLabel("Di\u1EC7n t\u00EDch:");
		lblDienTich_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDienTich_1.setBounds(10, 36, 52, 15);
		panelthongtinthanhpho.add(lblDienTich_1);
		
		JLabel lblQuocGia = new JLabel("Qu\u1ED1c gia:");
		lblQuocGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuocGia.setBounds(10, 61, 52, 15);
		panelthongtinthanhpho.add(lblQuocGia);
		
		JLabel lblThoiTiet = new JLabel("Th\u1EDDi ti\u1EBFt");
		lblThoiTiet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThoiTiet.setBounds(39, 127, 47, 15);
		panelthongtinthanhpho.add(lblThoiTiet);
		
		JLabel lblNhietDo = new JLabel("Nhi\u1EC7t \u0111\u1ED9:");
		lblNhietDo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNhietDo.setBounds(10, 152, 51, 15);
		panelthongtinthanhpho.add(lblNhietDo);
		
		JLabel lblMua = new JLabel("M\u01B0a:");
		lblMua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMua.setBounds(10, 177, 51, 14);
		panelthongtinthanhpho.add(lblMua);
		
		JLabel lblDoAm = new JLabel("\u0110\u1ED9 \u1EA9m:");
		lblDoAm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoAm.setBounds(10, 202, 46, 14);
		panelthongtinthanhpho.add(lblDoAm);
		
		JLabel lbldansothanhpho = new JLabel("Valuedansothanhpho");
		lbldansothanhpho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldansothanhpho.setBounds(145, 11, 115, 15);
		panelthongtinthanhpho.add(lbldansothanhpho);
		
		JLabel lbldientichthanhpho = new JLabel("Valuedientichthanhpho");
		lbldientichthanhpho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldientichthanhpho.setBounds(72, 37, 126, 15);
		panelthongtinthanhpho.add(lbldientichthanhpho);
		
		JLabel lblquocgia = new JLabel("Valuequocgia");
		lblquocgia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblquocgia.setBounds(72, 61, 72, 15);
		panelthongtinthanhpho.add(lblquocgia);
		
		JLabel lblnhietdo = new JLabel("Valuenhietdo");
		lblnhietdo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnhietdo.setBounds(71, 152, 72, 15);
		panelthongtinthanhpho.add(lblnhietdo);
		
		JLabel lblmua = new JLabel("Valuemua");
		lblmua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblmua.setBounds(71, 177, 68, 14);
		panelthongtinthanhpho.add(lblmua);
		
		JLabel lbldoam = new JLabel("Valuedoam");
		lbldoam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldoam.setBounds(71, 202, 60, 15);
		panelthongtinthanhpho.add(lbldoam);
		
		JLabel lblToaDoThanhPho = new JLabel("T\u1ECDa \u0111\u1ED9:");
		lblToaDoThanhPho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblToaDoThanhPho.setBounds(10, 87, 52, 15);
		panelthongtinthanhpho.add(lblToaDoThanhPho);
		
		JLabel lbltoadothanhpho = new JLabel("Valuetoadothanhpho");
		lbltoadothanhpho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltoadothanhpho.setBounds(72, 88, 115, 15);
		panelthongtinthanhpho.add(lbltoadothanhpho);
		
		JPanel panelTCTP = new JPanel();
		panelTCTP.setBackground(Color.WHITE);
		panelTCTP.setBounds(648, 38, 256, 50);		
		panelTCTP.setLayout(null);
		panel_tong.add(panelTCTP);
		
		
		
		txtthanhpho = new JTextField();
		txtthanhpho.setBounds(0, 30, 161, 20);		
		txtthanhpho.setEditable(false);
		txtthanhpho.setColumns(10);
		panelTCTP.add(txtthanhpho);
		
		
		String kq1=transport.receive(in);
		StringTokenizer tk1=new StringTokenizer(kq1,";");
		JComboBox cbbthanhpho = new JComboBox();
		cbbthanhpho.setEditable(true);
		for(;tk1.hasMoreTokens();) {
			cbbthanhpho.addItem(tk1.nextToken());
		}
		AutoCompleteDecorator.decorate(cbbthanhpho);
		cbbthanhpho.setBounds(0, 0, 161, 20);		
		panelTCTP.add(cbbthanhpho);
		
		JButton btnTCTP = new JButton("Tra c\u1EE9u");
		btnTCTP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTCTP.setBackground(Color.LIGHT_GRAY);
		btnTCTP.setBounds(167, 0, 89, 20);
		btnTCTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transport.send(out, "tracuuthanhpho");
				transport.send(out, ""+cbbthanhpho.getSelectedItem());
				
				Weather wt;
				wt=(Weather)transport.receive(inobj);
				lblnhietdo.setText(""+wt.getNhietDo()+"°C");
				lbldoam.setText(""+wt.getDoAm());
				lblmua.setText(wt.getMua());
				lbltoadothanhpho.setText(wt.getToaDo());
				lblquocgia.setText(wt.getCountry());
			}
		});
		panelTCTP.add(btnTCTP);
		
		JLabel lblTraCuuThongTinQuocGia = new JLabel("Tra C\u1EE9u Th\u00F4ng Tin Qu\u1ED1c Gia");
		lblTraCuuThongTinQuocGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTraCuuThongTinQuocGia.setBounds(10, 11, 207, 20);
		contentPane.add(lblTraCuuThongTinQuocGia);
	}
	public void Image(String s) {
		String path = s;
		try {
			
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			JLabel img = new JLabel(new ImageIcon(image));
			img.setBounds(0, 0, 0, 0);
			panel.add(img);
			}catch(Exception e1) {
				System.out.print(e1);
			}
		
	}
}
