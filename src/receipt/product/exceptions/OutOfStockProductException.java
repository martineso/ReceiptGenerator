package receipt.product.exceptions;

public class OutOfStockProductException extends Exception {

	private static final long serialVersionUID = -7597449746477632976L;
	
	public OutOfStockProductException() {
		super();
	}
	
	public OutOfStockProductException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OutOfStockProductException(String message) {
		super(message);
	}
	
	public OutOfStockProductException(Throwable cause) {
		
		super(cause);
	}
	
}
