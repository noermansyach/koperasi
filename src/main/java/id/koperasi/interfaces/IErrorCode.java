package id.koperasi.interfaces;

public interface IErrorCode {
	
	/**
	 * Fungsi berhasil dijalankan tanpa error.
	 */
	public static final int NO_ERROR = 0;
	/**
	 * Login tidak berhasil karena username pada tabel pengguna tidak ditemukan.
	 */
	public static final int ERROR_LOGIN_USER_NOT_FOUND = 1;
	/**
	 * Login tidak berhasil karena password tidak valid.
	 */
	public static final int ERROR_LOGIN_PASSWORD_INVALID = 2;
	/**
	 * Fungsi tidak berhasil dijalankan karena suatu entry pada tabel tertentu tidak ditemukan.
	 */
	public static final int ERROR_ENTITY_NOT_FOUND = 3;
	/**
	 * Fungsi tidak berhasil dijalankan karena ada duplikasi entry pada tabel tertentu.
	 */
	public static final int ERROR_DUPLICATE_ENTITY = 4;
	/**
	 * Fungsi update pada entry tertentu pada tabel tidak berhasil dijalankan.
	 */
	public static final int ERROR_UPDATE_ENTITY = 5;
	/**
	 * Fungsi delete pada entry tertentu pada tabel tidak berhasil dijalankan.
	 */
	public static final int ERROR_DELETE_ENTITY = 6;
	/**
	 * Fungsi tertentu tidak berhasil dijalankan karena stok barang tidak mencukupi.
	 */
	public static final int ERROR_STOCK = 7;
	/**
	 * Token pengamanan yang dikirimkan dari klien tidak ditemukan pada tabel pengguna.
	 * Error code ini muncul apabila Pengguna dengan token tertentu pada tabel pengguna tidak ditemukan. 
	 */
	public static final int ERROR_TOKEN_NOT_FOUND = 8;
	/**
	 * Token pengamanan yang dikirimkan dari klien tidak ditemukan pada tabel pengguna. 
	 * Error code ini muncul apabila Pengguna dengan token tertentu pada tabel pengguna tidak ditemukan.
	 */	
	public static final int ERROR_INVALID_TOKEN = 9;
	/**
	 * Error yang disebabkan oleh sebab-sebab lain.
	 */	
	public static final int ERROR_GENERAL = -99;
	
	public static final int ERROR_USER_AKTIF = 46;
	
}
