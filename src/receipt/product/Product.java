package receipt.product;

import receipt.product.exceptions.OutOfStockProductException;

public interface Product {
	
	String getId();
	String getName();
	double getPrice();
	int getQuantity();
	
	void setQuantity(int quantity);
	void decreaseQuantity(int quantity) throws OutOfStockProductException;
	void increaseQuantity(int quantity);
}
