package store;

import java.util.Collection;
import cashier.Cashier;
import receipt.product.Product;

public interface Store {
	
	String getName();
	String getAddress();
	
	int getReceiptsIssued();
	Collection<? extends Product> getProductsList();
	Collection<Cashier> getCashierList();
	double getRevenue();
	
	void sell(Cashier cashier, Product product, int quantity);
	void generateReceipt(); 
	void addNewStock(Product product);
	void addCashier(Cashier cashier);
	
}
