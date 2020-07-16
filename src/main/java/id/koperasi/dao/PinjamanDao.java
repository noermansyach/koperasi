package id.koperasi.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import id.koperasi.entity.KategoriPinjaman;
import id.koperasi.entity.Pengguna;
import id.koperasi.entity.Pinjaman;

@Repository
public class PinjamanDao {
	
	private EntityManager em;
	
	public PinjamanDao(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pinjaman> findAll() {
		Query q = em.createQuery("Select p from Pinjaman p order by tglPengajuanPinjaman desc");
		List<Pinjaman> ret = q.getResultList();
		return ret;
	}
	
	public Pinjaman findByTglAccAnggota(Date tglAcc, Pengguna pengguna) {
		Query q = em.createQuery("SELECT P FROM Pinjaman P " 
				+ "WHERE P.tglAccPinjaman = :tglAcc and P.pengguna = :pengguna");
		q.setParameter("tglAcc", tglAcc);
		q.setParameter("pengguna", pengguna);
		Pinjaman ret = null;
		ret = (Pinjaman) q.getSingleResult();
		return ret;
	}
	
	
	public void postSimpanan(String namaPinjaman, Pengguna pengguna, KategoriPinjaman kategoriPinjaman, int besarPinjaman, Date tglPengajuanPinjaman, 
			Date tglAccPinjaman, Date tglPinjaman, Date tglPelunasan, String ket) {
		Pinjaman pinjaman = new Pinjaman();
		pinjaman.setNamaPinjaman(namaPinjaman);
		pinjaman.setPengguna(pengguna);
		pinjaman.setKategoriPinjaman(kategoriPinjaman);
		pinjaman.setBesarPinjaman(besarPinjaman);
		pinjaman.setTglPengajuanPinjaman(tglPengajuanPinjaman);
		pinjaman.setTglAccPinjaman(tglAccPinjaman);
		pinjaman.setTglPinjaman(tglPinjaman);
		pinjaman.setTglPelunasan(tglPelunasan);
		pinjaman.setKet(ket);
		em.persist(pinjaman);
	}
	
}
