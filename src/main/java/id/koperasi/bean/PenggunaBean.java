package id.koperasi.bean;

public class PenggunaBean {

	private int id;
	
	private String namaPengguna;
	
	private String alamat;
	
	private String tglLhr;
		
	private String tmpLhr;
		
	private Boolean status;
		
	private String noTlp;
		
	private String ket;
		
	private int tipeAnggota;
	
	private String password;
	
	private String token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamaPengguna() {
		return namaPengguna;
	}

	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTglLhr() {
		return tglLhr;
	}

	public void setTglLhr(String tglLhr) {
		this.tglLhr = tglLhr;
	}

	public String getTmpLhr() {
		return tmpLhr;
	}

	public void setTmpLhr(String tmpLhr) {
		this.tmpLhr = tmpLhr;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNoTlp() {
		return noTlp;
	}

	public void setNoTlp(String noTlp) {
		this.noTlp = noTlp;
	}

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

	public int getTipeAnggota() {
		return tipeAnggota;
	}

	public void setTipeAnggota(int tipeAnggota) {
		this.tipeAnggota = tipeAnggota;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
