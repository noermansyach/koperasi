package id.koperasi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="pinjaman")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pinjaman {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nama_pinjaman", nullable = false, length = 50)
	private String namaPinjaman;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Pengguna pengguna;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private KategoriPinjaman kategoriPinjaman;
	
	@Column(name = "besar_pinjaman", nullable = false)
	private int besarPinjaman;
	
	@Column(name = "tgl_pengajuan_pinjaman", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tglPengajuanPinjaman;
	
	@Column(name = "tgl_acc_pinjaman", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tglAccPinjaman;
	
	@Column(name = "tgl_pinjaman", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tglPinjaman;
	
	@Column(name = "tgl_pelunasan", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tglPelunasan;
	
	@Column(name = "ket")
	private String ket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamaPinjaman() {
		return namaPinjaman;
	}

	public void setNamaPinjaman(String namaPinjaman) {
		this.namaPinjaman = namaPinjaman;
	}

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public KategoriPinjaman getKategoriPinjaman() {
		return kategoriPinjaman;
	}

	public void setKategoriPinjaman(KategoriPinjaman kategoriPinjaman) {
		this.kategoriPinjaman = kategoriPinjaman;
	}

	public int getBesarPinjaman() {
		return besarPinjaman;
	}

	public void setBesarPinjaman(int besarPinjaman) {
		this.besarPinjaman = besarPinjaman;
	}

	public Date getTglPengajuanPinjaman() {
		return tglPengajuanPinjaman;
	}

	public void setTglPengajuanPinjaman(Date tglPengajuanPinjaman) {
		this.tglPengajuanPinjaman = tglPengajuanPinjaman;
	}

	public Date getTglAccPinjaman() {
		return tglAccPinjaman;
	}

	public void setTglAccPinjaman(Date tglAccPinjaman) {
		this.tglAccPinjaman = tglAccPinjaman;
	}

	public Date getTglPinjaman() {
		return tglPinjaman;
	}

	public void setTglPinjaman(Date tglPinjaman) {
		this.tglPinjaman = tglPinjaman;
	}

	public Date getTglPelunasan() {
		return tglPelunasan;
	}

	public void setTglPelunasan(Date tglPelunasan) {
		this.tglPelunasan = tglPelunasan;
	}

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

}
