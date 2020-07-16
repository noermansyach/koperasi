package id.koperasi.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.koperasi.dao.PenggunaDao;
import id.koperasi.entity.Pengguna;
import id.koperasi.exception.ApplicationException;
import id.koperasi.interfaces.IErrorCode;

@Service
@Transactional
public class PenggunaService {
	@Autowired
	private PenggunaDao penggunaDao;
	
	public Pengguna login(String namaPengguna, String password) throws ApplicationException {
		Pengguna user = penggunaDao.findPenggunaByUserName(namaPengguna);
		if (user == null) {
			throw new ApplicationException("Nama pengguna tidak ditemukan", IErrorCode.ERROR_LOGIN_USER_NOT_FOUND);
		}
		String hashPassword = encodePassword(password).toString();
		if (!user.getPassword().equals(hashPassword)) {
			throw new ApplicationException("Password tidak sesuai", IErrorCode.ERROR_LOGIN_PASSWORD_INVALID);
		}
		
		if (user.getStatus() == false) {
			throw new ApplicationException("Pengguna tidak aktif", IErrorCode.ERROR_USER_AKTIF);
		}
		
		String random = System.currentTimeMillis() + "";
		int ret = penggunaDao.updateToken(user.getId(), random);
		if (ret != 1) {
			throw new ApplicationException("Login update token error", IErrorCode.ERROR_UPDATE_ENTITY);
		}	
		user.setToken(random);
		
		return user;
	}
	
	public void logout(String token) throws ApplicationException {
		Pengguna user = penggunaDao.findPenggunaByToken(token);
		if (user == null) {
			throw new ApplicationException("Token pengguna tidak ditemukan", IErrorCode.ERROR_TOKEN_NOT_FOUND);
		}
		String random = System.currentTimeMillis() + "";
		int ret = penggunaDao.updateToken(user.getId(), random);
		if (ret != 1) {
			throw new ApplicationException("Login update token error", IErrorCode.ERROR_UPDATE_ENTITY);
		}		
	}
	
	/*
	 * Mengambil semua entry pada table pengguna
	 */
	public List<Pengguna> getAllPengguna() {
		List<Pengguna> listPengguna = penggunaDao.findAll();
		return listPengguna;
	}
	
	public List<Pengguna> getAllAnggota() {
		List<Pengguna> listPengguna = penggunaDao.findAllAnggota();
		return listPengguna;
	}
	
	public Pengguna getPenggunaById(int id) {
		Pengguna pengguna = penggunaDao.findById(id);
		return pengguna;
	}
	
	public Pengguna getPenggunaByToken(String token) {
		Pengguna pengguna = penggunaDao.findPenggunaByToken(token);
		return pengguna;
	}
	
	public void addPengguna(String token, String namaPengguna, String alamat, String tglLhr, String tmpLhr, Boolean status, 
			String noTlp, String ket, int tipeAnggota, String password) throws ParseException, ApplicationException {
		StringBuilder sb = encodePassword(password);
		Pengguna pengguna = penggunaDao.findPenggunaByToken(token);
		if (pengguna == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		
		password = sb.toString();
		
		Date date = null;
		date = new SimpleDateFormat("yyyy-MM-dd").parse(tglLhr);
		
		penggunaDao.addPengguna(namaPengguna, alamat, date, tmpLhr, status, noTlp, ket, tipeAnggota, password);
	}
	
	public void editPengguna(String token, int id, String namaPengguna, String alamat, String tglLhr, String tmpLhr, Boolean status, 
			String noTlp, String ket, int tipeAnggota) throws ParseException, ApplicationException {
		Pengguna pengguna = penggunaDao.findPenggunaByToken(token);
		if (pengguna == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		Date date = null;
		date = new SimpleDateFormat("yyyy-MM-dd").parse(tglLhr);
		
		penggunaDao.updatePengguna(id, namaPengguna, alamat, date, tmpLhr, status, noTlp, ket, tipeAnggota);
	}
	
	/**
	 * Enkrip / hash password.
	 * @param password
	 * @return Password yang dienkrip menggunakan hash MD5
	 */
	public StringBuilder encodePassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// just log
			e.printStackTrace();
		}
		byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
		
		StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        
        return sb;
	}
	
	public void deletePengguna(String token, int id) throws ApplicationException {
		Pengguna pengguna = penggunaDao.findPenggunaByToken(token);
		if (pengguna == null) {
			throw new ApplicationException("Token tidak valid", IErrorCode.ERROR_INVALID_TOKEN);
		}
		penggunaDao.deletePengguna(id);
	}
	
	
}
