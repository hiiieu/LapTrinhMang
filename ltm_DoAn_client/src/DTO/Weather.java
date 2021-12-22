package DTO;

import java.io.Serializable;

public class Weather  implements Serializable {
	private static final long serialVersionUID = 10L;
	private int id;
	private int doam;
	private String mua;
	private double nhietdo;
	private String toado;
	private String country;
	public Weather(int doam, String mua, double nhietdo, String toado, String country) {
		super();
		this.toado=toado;		
		this.doam=doam;
		this.mua=mua;
		this.nhietdo=nhietdo;
		this.country=country;
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
	public String getToaDo() {
		return toado;
	}
	public void setToaDo(String toado) {
		this.toado=toado;
	}	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country=country;
	}
}
