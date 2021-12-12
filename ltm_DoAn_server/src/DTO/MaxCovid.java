package DTO;

public class MaxCovid {
	private int id;
	private String quocgia;
	private int canhiemmax;
	private int chetmax;
	private int khoibenhmax;
	
	public MaxCovid(int id, String quocgia, int canhiemmax, int chetmax, int khoibenhmax) {
		super();
		this.id=id;
		this.quocgia=quocgia;
		this.canhiemmax=canhiemmax;
		this.chetmax=chetmax;
		this.khoibenhmax=khoibenhmax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuocgia() {
		return quocgia;
	}

	public void setQuocgia(String quocgia) {
		this.quocgia = quocgia;
	}

	public int getCanhiemmax() {
		return canhiemmax;
	}

	public void setCanhiemmax(int canhiemmax) {
		this.canhiemmax = canhiemmax;
	}

	public int getChetmax() {
		return chetmax;
	}

	public void setChetmax(int chetmax) {
		this.chetmax = chetmax;
	}

	public int getKhoibenhmax() {
		return khoibenhmax;
	}

	public void setKhoibenhmax(int khoibenhmax) {
		this.khoibenhmax = khoibenhmax;
	}

}
