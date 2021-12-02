package covidAndNationInfo;

import java.io.Serializable;
import java.util.List;

public class Covid {
	
	private static final long serialVersionUID = 2L;
	
	private String quocGia;
	private int caNhiem;
	private int khoiBenh;
	private int Chet;
	private String thoiGian;
	
	public Covid(String quocGia, int caNhiem, int khoiBenh, int Chet, String thoiGian ) {
		super();
		
		this.quocGia=quocGia;
		this.caNhiem=caNhiem;
		this.khoiBenh=khoiBenh;
		this.Chet=Chet;
		this.thoiGian=thoiGian;
	}
	
	public String getQuocGia() {
		return quocGia;
	}
	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}
	public int getCaNhiem() {
		return caNhiem;
	}
	public void setCaNhiem(int caNhiem) {
		this.caNhiem = caNhiem;
	}
	public int getKhoiBenh() {
		return khoiBenh;
	}
	public void setKhoiBenh(int khoiBenh) {
		this.khoiBenh = khoiBenh;
	}
	public int getChet() {
		return Chet;
	}
	public void setChet(int chet) {
		Chet = chet;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}
	
}
