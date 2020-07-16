package id.koperasi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.koperasi.dao.KategoriPinjamanDao;
import id.koperasi.dao.PenggunaDao;
import id.koperasi.dao.PinjamanDao;
import id.koperasi.entity.KategoriPinjaman;
import id.koperasi.entity.Pengguna;
import id.koperasi.entity.Pinjaman;
import id.koperasi.exception.ApplicationException;
import id.koperasi.interfaces.IErrorCode;

@Service
@Transactional
public class PinjamanService {
	
	@Autowired
	private PinjamanDao pinjamanDao;
	
	@Autowired
	private PenggunaDao penggunaDao;
	
	@Autowired KategoriPinjamanDao kategoriDao;
	
	public List<Pinjaman> getAllPinjaman() {
		List<Pinjaman> listPinjaman = pinjamanDao.findAll();
		return listPinjaman;
	}
	
	public void pinjamanSave(String token, String namaPinjaman, int pengguna, int kategoriPinjaman, int besarPinjaman, String tglPengajuanPinjaman, 
			String tglAccPinjaman, String tglPinjam, String tglPelunasan, String ket, String jatuhTempo) throws ApplicationException, ParseException {
		Pengguna user = penggunaDao.findPenggunaByToken(token);
		if (user == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		Date tanggalPengajuan = null;
		tanggalPengajuan = new SimpleDateFormat("yyyy-MM-dd").parse(tglPengajuanPinjaman);
		
		Date tanggalAcc = null;
		tanggalAcc = new SimpleDateFormat("yyyy-MM-dd").parse(tglAccPinjaman);
		
		Date tglPinjaman = null;
		tglPinjaman = new SimpleDateFormat("yyyy-MM-dd").parse(tglPinjam);
		
		Date tglLunas = null;
		tglLunas = new SimpleDateFormat("yyyy-MM-dd").parse(tglPelunasan);
		
		Pengguna anggota = penggunaDao.findById(pengguna);
		KategoriPinjaman kategori = kategoriDao.findById(kategoriPinjaman);
		

		pinjamanDao.postSimpanan(namaPinjaman, anggota, kategori, besarPinjaman, tanggalPengajuan, tanggalAcc, tglPinjaman, tglLunas, ket);				
	}
}



















