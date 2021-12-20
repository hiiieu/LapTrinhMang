package DTO;

import java.io.Serializable;

public class Weather  implements Serializable {
	private static final long serialVersionUID = 10L;
	private int id;
	private int doam;
	private String mua;
	private double nhietdo;
	
	public Weather(int doam, String mua, double nhietdo) {
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
	public double getNhietDo() {
		return nhietdo;
	}
	public void setNhietDo(double nhietdo) {
		this.nhietdo=nhietdo;
	}
}
