package id.koperasi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import id.koperasi.entity.KategoriPinjaman;

@Repository
public class KategoriPinjamanDao {
	private EntityManager em;
	
	/*
	 * Constructor
	 */
	public KategoriPinjamanDao (EntityManager em) {
		this.em = em;
	}
	
	/*
	 * Mengambil semua entry pada table kategori_pinjaman 	
	 */
	@SuppressWarnings("unchecked")
	public List<KategoriPinjaman> findAll() {
		Query q = em.createQuery("Select k from KategoriPinjaman k");
		List<KategoriPinjaman> ret = q.getResultList();
		return ret;
	}
	
	public KategoriPinjaman findById(int id) {
		Query q = em.createQuery("SELECT k FROM KategoriPinjaman k " 
				+ "WHERE k.id = :id");
		q.setParameter("id", id);
		KategoriPinjaman ret = null;
		ret = (KategoriPinjaman) q.getSingleResult();
		return ret;
	}
	
	public void addKategoriPinjaman(String namaPinjaman) {
		KategoriPinjaman en1 = new KategoriPinjaman();
		en1.setNamaPinjaman(namaPinjaman);
		em.persist(en1);
	}
	
	public int updateKategoriPinjaman(int id, String namaPinjaman) {
		Query q = em.createQuery("UPDATE KategoriPinjaman k SET k.namaPinjaman = :namaPinjaman " 
								+ "WHERE k.id = :id ");
		q.setParameter("id", id);
		q.setParameter("namaPinjaman", namaPinjaman);
		return q.executeUpdate();
	}
	
	public int deleteKategoriPinjaman(int id) {
		Query query = em.createQuery("delete from KategoriPinjaman where id=:id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result;
	}
}
