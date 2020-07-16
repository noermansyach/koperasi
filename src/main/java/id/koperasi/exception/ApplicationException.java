package id.koperasi.exception;

@SuppressWarnings("serial")
public class ApplicationException extends Exception {
	
	private int errorCode;
	
	public ApplicationException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
}
