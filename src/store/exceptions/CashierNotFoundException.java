package store.exceptions;

public class CashierNotFoundException extends Exception {

	private static final long serialVersionUID = -9166845851049961596L;

	public CashierNotFoundException() {
		super();
	}
	
	public CashierNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CashierNotFoundException(String message) {
		super(message);
	}
	
	public CashierNotFoundException(Throwable cause) {
		
		super(cause);
	}
}
