package DTO;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable{
		
		private static final long serialVersionUID = 2L;
	
	
		private int id;
		private String tenQuocGia;
		private int danSo;
		private float dienTich;
		private String tienTe;
		private String thuDo;
		private String chauLuc;
		private String ngonNgu;
		private String muiGio;
		private String tiepGiap;
		private String quocKy;
		private String cca3;
		private String toaDo;
		

		public Country() {}
		
		public Country(int id,String tenQuocGia, int danSo, float dienTich, String tienTe, String thuDo, String chauLuc,
				String ngonNgu, String muiGio, String tiepGiap, String quocKy, String cca3, String toaDo) {
			super();
			this.id=id;
			this.tenQuocGia = tenQuocGia;
			this.danSo = danSo;
			this.dienTich = dienTich;
			this.tienTe = tienTe;
			this.thuDo = thuDo;
			this.chauLuc = chauLuc;
			this.ngonNgu = ngonNgu;
			this.muiGio = muiGio;
			this.tiepGiap = tiepGiap;
			this.quocKy = quocKy;
			this.cca3 = cca3;
			this.toaDo = toaDo;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public String getTenQuocGia() {
			return tenQuocGia;
		}
		public void setTenQuocGia(String tenQuocGia) {
			this.tenQuocGia = tenQuocGia;
		}
		public int getDanSo() {
			return danSo;
		}
		public void setDanSo(int danSo) {
			this.danSo = danSo;
		}
		public float getDienTich() {
			return dienTich;
		}
		public void setDienTich(float dienTich) {
			this.dienTich = dienTich;
		}
		public String getTienTe() {
			return tienTe;
		}
		public void setTienTe(String tienTe) {
			this.tienTe = tienTe;
		}
		public String getThuDo() {
			return thuDo;
		}
		public void setThuDo(String thuDo) {
			this.thuDo = thuDo;
		}
		public String getChauLuc() {
			return chauLuc;
		}
		public void setChauLuc(String chauLuc) {
			this.chauLuc = chauLuc;
		}
		public String getNgonNgu() {
			return ngonNgu;
		}
		public void setNgonNgu(String ngonNgu) {
			this.ngonNgu = ngonNgu;
		}
		public String getMuiGio() {
			return muiGio;
		}
		public void setMuiGio(String muiGio) {
			this.muiGio = muiGio;
		}
		public String getTiepGiap() {
			return tiepGiap;
		}
		public void setTiepGiap(String tiepGiap) {
			this.tiepGiap = tiepGiap;
		}
		public String getQuocKy() {
			return quocKy;
		}
		public void setQuocKy(String quocKy) {
			this.quocKy = quocKy;
		}
		public String getCca3() {
			return cca3;
		}

		public void setCca3(String cca3) {
			this.cca3 = cca3;
		}
		public String getToaDo() {
			return toaDo;
		}

		public void setToaDo(String toaDo) {
			this.toaDo = toaDo;
		}
}
