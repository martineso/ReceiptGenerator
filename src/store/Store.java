package store;

import java.util.Collection;
import cashier.Cashier;
import receipt.product.Product;
import receipt.product.exceptions.OutOfStockProductException;

public interface Store {
	
	String getName();
	String getAddress();
	
	int getReceiptsIssued();
	Collection<? extends Product> getProductsList();
	Collection<Cashier> getCashierList();
	double getRevenue();
	
	void sell(Product product, int quantity) throws OutOfStockProductException;
	void generateReceipt(); 
	void addNewStock(Product product);
	void addCashier(Cashier cashier);
	
}
