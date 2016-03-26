package receipt.product;

public interface Sellable {
	
	String getName();
	double getPrice();
	int getQuantitySold();
	double getGeneratedRevenue();
	String getId();
}
