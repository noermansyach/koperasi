package id.koperasi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.koperasi.dao.PenggunaDao;
import id.koperasi.dao.SimpananDao;
import id.koperasi.entity.Pengguna;
import id.koperasi.entity.Simpanan;
import id.koperasi.exception.ApplicationException;
import id.koperasi.interfaces.IErrorCode;

@Service
@Transactional
public class SimpananService {

	@Autowired
	private SimpananDao simpananDao;
	
	@Autowired 
	private PenggunaDao penggunaDao;
	
	public List<Simpanan> getAllSimpanan() {
		List<Simpanan> listSimpanan = simpananDao.findAll();
		return listSimpanan;
	}
	
	public List<Simpanan> getSimpananAnggota(String token) throws ApplicationException {
		Pengguna user = penggunaDao.findPenggunaByToken(token);
		if (user == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		List<Simpanan> listSimpananAnggota = simpananDao.findByAnggota(user);
		return listSimpananAnggota;
	} 
	
	public void simpananSave(String token, int pengguna, String namaSimpanan, String tglSimpan, int jumlahTambahan,  String ket) throws ApplicationException, ParseException {
		Pengguna user = penggunaDao.findPenggunaByToken(token);
		if (user == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		Date tanggalSimpan = null;
		tanggalSimpan = new SimpleDateFormat("yyyy-MM-dd").parse(tglSimpan);
		
		Pengguna anggota = penggunaDao.findById(pengguna);
		if (anggota == null) {
			throw new ApplicationException("Pengguna tidak ditemukan", IErrorCode.ERROR_ENTITY_NOT_FOUND);
		}
		
		int simpananSkrng = anggota.getTotalSimpanan();
		int tambahSimpanan = jumlahTambahan + simpananSkrng; 
		
		simpananDao.postSimpanan(anggota, namaSimpanan, tanggalSimpan, jumlahTambahan, ket);
		simpananDao.updateTotalSimpanan(anggota.getId(), tambahSimpanan);
	}
	
}
