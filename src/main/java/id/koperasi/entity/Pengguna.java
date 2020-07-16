package id.koperasi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pengguna")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pengguna {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nama_pengguna", length = 50, nullable = false)
	private String namaPengguna;
	
	@Column(name = "alamat", length = 255, nullable = false)
	private String alamat;
	
	@Column(name = "tgl_lhr", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tglLhr;
	
	@Column(name = "tmp_lhr", length = 20, nullable = false)
	private String tmpLhr;
	
	@Column(name = "status", nullable = false)
	private Boolean status;
	
	@Column(name = "no_tlp", length = 15, nullable = false)
	private String noTlp;
	
	@Column(name = "ket", length = 255)
	private String ket;
	
	@Column(name = "tipe_anggota",  length = 1, nullable = false)
	private int tipeAnggota;
	
	@Column(name = "total_simpanan", nullable = true)
	private int totalSimpanan;
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@Column(name = "token", length = 50)
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

	public Date getTglLhr() {
		return tglLhr;
	}

	public void setTglLhr(Date tglLhr) {
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

	public int getTotalSimpanan() {
		return totalSimpanan;
	}

	public void setTotalSimpanan(int totalSimpanan) {
		this.totalSimpanan = totalSimpanan;
	}
	
}
