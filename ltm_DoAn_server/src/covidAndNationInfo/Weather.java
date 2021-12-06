package covidAndNationInfo;

public class Weather {
	private int id;
	private int doam;
	private String mua;
	private float nhietdo;
	
	public Weather(int doam, String mua, float nhietdo) {
		super();
		
		this.doam=doam;
		this.mua=mua;
		this.nhietdo=nhietdo;
	}
	public float getDoAm() {
		return doam;
	}
	public void setDoAm(int doam) {
		this.doam=doam;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getMua() {
		return mua;
	}
	public void setMua(String mua) {
		this.mua=mua;
	}
	public float getNhietDo() {
		return nhietdo;
	}
	public void setNhietDo(float nhietdo) {
		this.nhietdo=nhietdo;
	}
}
