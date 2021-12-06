package covidAndNationInfo;

public class CityInfoDTO {
	private int id;
	private int danso;
	private String tenThanhPho;
	private String vitri;
	private String tenQuocGia;
	
	public CityInfoDTO( int danso, String tenThanhPho, String vitri, String tenQuocGia) {
		super();
		
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
	public String getViTri() {
		return vitri;
	}
	public void setViTri(String vitri) {
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
