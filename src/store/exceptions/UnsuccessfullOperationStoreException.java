package store.exceptions;

public class UnsuccessfullOperationStoreException extends Exception {
	
	private static final long serialVersionUID = -2864082021439889605L;

	public UnsuccessfullOperationStoreException() {
		super();
	}
	
	public UnsuccessfullOperationStoreException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UnsuccessfullOperationStoreException(String message) {
		super(message);
	}
	
	public UnsuccessfullOperationStoreException(Throwable cause) {
		
		super(cause);
	}
	
}
