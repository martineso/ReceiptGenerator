package store;

import java.util.List;
import cashier.Cashier;
import receipt.product.Product;
import receipt.product.exceptions.OutOfStockProductException;
import store.exceptions.UnsuccessfullOperationStoreException;

public interface Store {
	
	String getName();
	String getAddress();
	double getRevenue();
	Product getProduct(String name);
	
	

	int getNumberOfReceiptsIssued();
	void generateReceipt(); 
	List<Cashier> getCashierList();
	
	void sell(Product product, int quantity) throws OutOfStockProductException;
	void deleteProduct(String name) throws UnsuccessfullOperationStoreException; 
	void addNewStock(Product product);
	void addCashier(Cashier cashier) throws UnsuccessfullOperationStoreException;
	
}
