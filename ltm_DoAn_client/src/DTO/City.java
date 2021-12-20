package DTO;

import java.io.Serializable;

public class City implements Serializable{
	private static final long serialVersionUID = 9L;
	private String tenThanhPho;
	private String tenQuocGia;
	private int id;
	public String getTenQuocGia() {
		return tenQuocGia;
	}
	public void setTenQuocGia(String tenQuocGia) {
		this.tenQuocGia = tenQuocGia;
	}
	public String getTenThanhPho() {
		return tenThanhPho;
	}
	public void setTenThanhPho(String tenThanhPho) {
		this.tenThanhPho = tenThanhPho;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public City(int id, String tenQuocGia, String tenThanhPho) {
		super();
		this.id=id;
		this.tenQuocGia=tenQuocGia;
		this.tenThanhPho=tenThanhPho;
	}
}
