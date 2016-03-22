package store;

import java.util.Collection;
import java.util.List;

import cashier.Cashier;
import receipt.product.Book;
import receipt.product.Product;
import receipt.receiptgenerator.Receipt;

public class BookStore implements Store {

	private String name;
	private List<Cashier> cashiers;
	private List<Book> books;
	private List<Receipt> receiptsIssued;
	private static double generatedRevenue = 0;
	
	public BookStore(String name) {
		
		this.name = name;
	}
	
	@Override
	public String getName() {
		
		return this.name;
	}

	@Override
	public int getReceiptsIssued() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<? extends Product> getProductsList() {
		
		return this.books;
	}

	@Override
	public Collection<Cashier> getCashierList() {
		
		return this.cashiers;
	}

	@Override
	public double getRevenue() {
		
		return generatedRevenue;
	}

	@Override
	public void sell(Cashier cashier, Product product, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewStock(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCashier(Cashier cashier) {
		// TODO Auto-generated method stub
		
	}

}
