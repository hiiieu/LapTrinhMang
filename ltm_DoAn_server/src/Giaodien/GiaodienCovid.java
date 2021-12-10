package Giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GiaodienCovid extends JFrame {

	private JPanel contentPane;
	private JTextField txtCountry;
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaodienCovid frame = new GiaodienCovid();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GiaodienCovid() {
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Thông tin bệnh nhân covid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 552);
		contentPane = new JPanel();
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
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(575, 89, 89, 45);
		contentPane.add(btnSearch);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(699, 0, 17, 513);
		contentPane.add(scrollBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK));
		panel.setBounds(55, 212, 609, 290);
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
		
		JLabel lblNewLabel_1_5 = new JLabel("Hình quốc kỳ:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(426, 23, 106, 17);
		panel.add(lblNewLabel_1_5);
		
		JPanel CountryFlag = new JPanel();
		CountryFlag.setBorder(new LineBorder(new Color(0, 0, 0)));
		CountryFlag.setBounds(55, 65, 170, 96);
		contentPane.add(CountryFlag);
		
		
		
		
	}
}
