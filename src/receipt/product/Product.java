package receipt.product;

import receipt.product.exceptions.OutOfStockProductException;

public interface Product {
	
	String getId();
	String getName();
	double getPrice();
	int getQuantity();
	
	void decreaseQuantity(int quantity) throws OutOfStockProductException;
	void increaseQuantity(int quantity);
}
