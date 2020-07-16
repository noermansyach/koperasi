package id.koperasi.bean;

public class SimpananBean {
	
	private int id;
	
	int pengguna;
	
	private String namaSimpanan;
	
	private String tglSimpanan;
	
	private int jumlahTambahan;
	
	private String ket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPengguna() {
		return pengguna;
	}

	public void setPengguna(int pengguna) {
		this.pengguna = pengguna;
	}

	public String getTglSimpanan() {
		return tglSimpanan;
	}

	public void setTglSimpanan(String tglSimpanan) {
		this.tglSimpanan = tglSimpanan;
	}

	public int getJumlahTambahan() {
		return jumlahTambahan;
	}

	public void setJumlahTambahan(int jumlahTambahan) {
		this.jumlahTambahan = jumlahTambahan;
	}

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

	public String getNamaSimpanan() {
		return namaSimpanan;
	}

	public void setNamaSimpanan(String namaSimpanan) {
		this.namaSimpanan = namaSimpanan;
	}
	
}
