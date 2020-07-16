package id.koperasi.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import id.koperasi.entity.Pengguna;
import id.koperasi.entity.Simpanan;

@Repository
public class SimpananDao {
	
	private EntityManager em;
	
	public SimpananDao (EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Simpanan> findAll() {
		Query q = em.createQuery("Select s from Simpanan s order by tglSimpanan desc");
		List<Simpanan> ret = q.getResultList();
		return ret;
	}
	
	public List<Simpanan> findByAnggota(Pengguna pengguna) {
		Query q = em.createQuery("Select s from Simpanan s Where pengguna = :pengguna order by tglSimpanan desc");
		q.setParameter("pengguna", pengguna);
		@SuppressWarnings("unchecked")
		List<Simpanan> ret = q.getResultList();
		return ret;
	} 
	
	public void postSimpanan(Pengguna pengguna, String namaSimpanan, Date tglSimpanan, int jumlahTambahan, String ket) {
		Simpanan simpanan = new Simpanan();
		simpanan.setPengguna(pengguna);
		simpanan.setNamaSimpanan(namaSimpanan);
		simpanan.setTglSimpanan(tglSimpanan);
		simpanan.setJumlahTambahan(jumlahTambahan);
		simpanan.setKet(ket);
		
		em.persist(simpanan);
	}
	
	public int updateTotalSimpanan(int id, int totalSimpanan) {
		Query q = em.createQuery("UPDATE Pengguna P SET P.totalSimpanan = :totalSimpanan "
				+ "WHERE P.id = :id");
		q.setParameter("id", id);
		q.setParameter("totalSimpanan", totalSimpanan);
		return q.executeUpdate();
	}
}
