package receipt.product;

import receipt.product.exceptions.OutOfStockProductException;

public class SoldProduct implements Sellable {

	private int soldProducts;
	private Product product;
	
	
	public SoldProduct(Product product, int quantity) {
		
		this.product = product;
		this.soldProducts = quantity;
		
		this.reduceQuantity(product, quantity);
		
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
	
	private void reduceQuantity(Product product, int quantity) {
		
		try {
			
			product.decreaseQuantity(quantity);
			
		} catch(OutOfStockProductException ex) {
			
			// Implement later
			// 
			System.out.println(ex.getMessage());
		}
		
	}

}
