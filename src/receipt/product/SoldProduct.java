package receipt.product;

public class SoldProduct implements Sellable {

	private int soldProducts;
	private Product product;
	
	
	public SoldProduct(Product product, int quantity) {
		
		this.product = product;
		this.soldProducts = quantity;
		
	}
	
	@Override
	public String getName() {
		
		return this.product.getName();
	}

	@Override
	public double getPrice() {
		
		return this.product.getPrice();
	}

	@Override
	public int getQuantitySold() {
		
		return this.soldProducts;
	}
	
	@Override
	public String getId() {
		
		return this.product.getId();
	}

}
