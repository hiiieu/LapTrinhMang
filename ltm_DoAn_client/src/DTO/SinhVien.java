package DTO;


import java.io.Serializable;

public class SinhVien implements Serializable{
		private static final long serialVersionUID = 5L;//để xác định phiên bản class 2 phía trùng mới ok
		private String ten;
		private int lop;
		public SinhVien() {}
		public SinhVien(String ten, int lop) {
			super();
			this.ten = ten;
			this.lop = lop;
		}
		public String getTen() {
			return ten;
		}
		public void setTen(String ten) {
			this.ten = ten;
		}
		public int getLop() {
			return lop;
		}
		public void setLop(int lop) {
			this.lop = lop;
		}
		
}
