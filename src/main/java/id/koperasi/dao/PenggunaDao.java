package id.koperasi.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import id.koperasi.entity.Pengguna;

@Repository
public class PenggunaDao {
	private EntityManager em;
	
	/*
	 * Constructor
	 */
	public PenggunaDao (EntityManager em) {
		this.em = em;
	}
	
	/*
	 * Mengambil semua entry pada table pengguna 	
	 */
	@SuppressWarnings("unchecked")
	public List<Pengguna> findAll() {
		Query q = em.createQuery("Select p from Pengguna p");
		List<Pengguna> ret = q.getResultList();
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pengguna> findAllAnggota() {
		Query q = em.createQuery("Select p from Pengguna p "
				+ "WHERE p.tipeAnggota = '2'");
		List<Pengguna> ret = q.getResultList();
		return ret;
	}
	
	public Pengguna findById(int id) {
		Query q = em.createQuery("SELECT p FROM Pengguna p " 
				+ "WHERE p.id = :id");
		q.setParameter("id", id);
		Pengguna ret = null;
		ret = (Pengguna) q.getSingleResult();
		return ret;
	}
	
	public Pengguna findPenggunaByUserName(String namaPengguna) {
		Query query = em.createQuery("from Pengguna where namaPengguna=:namaPengguna");
		query.setParameter("namaPengguna", namaPengguna);
		Pengguna ret = null;
		try {
			ret = (Pengguna) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return ret;
	}
	
	public Pengguna findPenggunaByToken(String token) {
		Query q = em.createQuery("FROM Pengguna u WHERE u.token = :token");
		q.setParameter("token", token);
		Pengguna ret = null;
		try {
			ret = (Pengguna) q.getSingleResult();
		} catch (Exception e) {
			// just log
		}
		return ret;
	}
	
	public void addPengguna(String namaPengguna, String alamat, Date tglLhr, String tmpLhr, Boolean status, 
			String noTlp, String ket, int tipeAnggota, String hashPassword) {
		Pengguna en1 = new Pengguna();
		en1.setNamaPengguna(namaPengguna);
		en1.setAlamat(alamat);
		en1.setTglLhr(tglLhr);
		en1.setTmpLhr(tmpLhr);
		en1.setStatus(status);
		en1.setNoTlp(noTlp);
		en1.setKet(ket);
		en1.setTipeAnggota(tipeAnggota);
		en1.setPassword(hashPassword);
		en1.setToken("");
		em.persist(en1);
	}
	
	public int updatePengguna(int id, String namaPengguna, String alamat, Date tglLhr, String tmpLhr, Boolean status, 
			String noTlp, String ket, int tipeAnggota) {
		Query q = em.createQuery("UPDATE Pengguna p SET p.namaPengguna = :namaPengguna,  "
								+ "p.alamat = :alamat, "
								+ "p.tglLhr = :tglLhr, "
								+ "p.status = :status, "
								+ "p.noTlp = :noTlp, "
								+ "p.ket = :ket, "
								+ "p.tipeAnggota = :tipeAnggota "
								+ "WHERE p.id = :id ");
		q.setParameter("id", id);
		q.setParameter("namaPengguna", namaPengguna);
		q.setParameter("alamat", alamat);
		q.setParameter("tglLhr", tglLhr);
		q.setParameter("status", status);
		q.setParameter("noTlp", noTlp);
		q.setParameter("ket", ket);
		q.setParameter("tipeAnggota", tipeAnggota);
		return q.executeUpdate();
	}
	
	public int deletePengguna(int id) {
		Query query = em.createQuery("delete from Pengguna where id=:id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result;
	}
	
	public int updateToken(int id, String token) {
		Query q = em.createQuery("UPDATE Pengguna p SET p.token = :token WHERE p.id = :id");
		q.setParameter("token", token);
		q.setParameter("id", id);
		int ret = q.executeUpdate();
//		em.flush();
		return ret;
	}
}
