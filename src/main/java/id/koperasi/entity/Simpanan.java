package id.koperasi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "simpanan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Simpanan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Pengguna pengguna;
	
	@Column(name = "nama_simpanan", nullable = false, length = 50)
	private String namaSimpanan;
	
	@Column(name = "tgl_simpanan", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tglSimpanan;
	
	@Column(name = "jumlah_tambah", nullable = false)
	private int jumlahTambahan;
	
	@Column(name = "ket", nullable = false, length = 255)
	private String ket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public Date getTglSimpanan() {
		return tglSimpanan;
	}

	public void setTglSimpanan(Date tglSimpanan) {
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
