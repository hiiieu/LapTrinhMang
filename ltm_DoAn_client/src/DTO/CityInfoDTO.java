package DTO;

public class CityInfoDTO {
	private int id;
	private int danso;
	private String tenThanhPho;
	private float vitri;
	private String tenQuocGia;
	
	public CityInfoDTO(int id, int danso, String tenThanhPho, float vitri, String tenQuocGia) {
		super();
		this.id=id;
		this.danso=danso;
		this.tenThanhPho=tenThanhPho;
		this.vitri=vitri;
		this.tenQuocGia=tenQuocGia;
	}
	public String getTenThanhPho() {
		return tenThanhPho;
	}
	public void setDoAm(String tenThanhPho) {
		this.tenThanhPho=tenThanhPho;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public float getViTri() {
		return vitri;
	}
	public void setViTri(float vitri) {
		this.vitri=vitri;
	}
	public String getTenQuocGia() {
		return tenQuocGia;
	}
	public void setTenQuocGia(String tenQuocGia) {
		this.tenQuocGia=tenQuocGia;
	}
	public int getDanSo() {
		return danso;
	}
	public void setDanSo(int danso) {
		this.danso=danso;
	}
}
