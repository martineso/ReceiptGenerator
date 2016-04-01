package store;

import java.util.Collection;
import cashier.Cashier;
import receipt.product.Product;
import receipt.product.exceptions.OutOfStockProductException;
import store.exceptions.UnsuccessfullOperationStoreException;

public interface Store {
	
	String getName();
	String getAddress();
	double getRevenue();
	Product getProduct(String name);
	
	
<<<<<<< HEAD
	int getNumberOfReceiptsIssued();
=======
	int getReceiptsIssued();
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
	void generateReceipt(); 
	Collection<? extends Product> getProductsList();
	Collection<Cashier> getCashierList();
	
	void sell(Product product, int quantity) throws OutOfStockProductException;
	void deleteProduct(String name) throws UnsuccessfullOperationStoreException; 
	void addNewStock(Product product);
	void addCashier(Cashier cashier);
	
}
