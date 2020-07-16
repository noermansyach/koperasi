package id.koperasi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.koperasi.exception.ApplicationException;
import id.koperasi.interfaces.IErrorCode;

import id.koperasi.dao.KategoriPinjamanDao;
import id.koperasi.dao.PenggunaDao;
import id.koperasi.entity.KategoriPinjaman;
import id.koperasi.entity.Pengguna;

@Service
@Transactional
public class KategoriPinjamanService {
	@Autowired
	private KategoriPinjamanDao kategoriPinjamanDao;
	
	@Autowired
	private PenggunaDao penggunaDao;
	
	/*
	 * Mengambil semua entry pada table kategori_pinjaman
	 */
	public List<KategoriPinjaman> getAllKategoriPinjaman() {
		List<KategoriPinjaman> listKategori = kategoriPinjamanDao.findAll();
		return listKategori;
	}
	
	public KategoriPinjaman getKategoriPinjamanById(int id) {
		KategoriPinjaman kategoriPinjaman = kategoriPinjamanDao.findById(id);
		return kategoriPinjaman;
	}
	
	public void addKategoriPinjaman(String token, String namaPinjaman) throws ApplicationException {
		Pengguna pengguna = penggunaDao.findPenggunaByToken(token);
		if (pengguna == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		kategoriPinjamanDao.addKategoriPinjaman(namaPinjaman);
	}
	
	public void editKategoriPinjaman(String token, int id, String namaPinjaman) throws ApplicationException {
		Pengguna pengguna = penggunaDao.findPenggunaByToken(token);
		if (pengguna == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		kategoriPinjamanDao.updateKategoriPinjaman(id, namaPinjaman);
	}
	
	public void deleteKategoriPinjaman(String token, int id) throws ApplicationException {
		Pengguna pengguna = penggunaDao.findPenggunaByToken(token);
		if (pengguna == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		kategoriPinjamanDao.deleteKategoriPinjaman(id);
	}
	
}
