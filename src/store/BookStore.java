package store;

import java.util.Collection;
import java.util.List;

import cashier.Cashier;
import receipt.product.Book;
import receipt.product.Product;
import receipt.product.SoldProduct;
import receipt.product.exceptions.OutOfStockProductException;
import receipt.receiptgenerator.Receipt;

public class BookStore implements Store {

	private String name, address;
	private List<Cashier> cashiers;
	private List<Book> books;
	private List<SoldProduct> soldBooks;
	private List<Receipt> receiptsIssued;
	private static double generatedRevenue = 0;
	
	public BookStore(String name, String address) {
		
		this.name = name;
		this.address = address;
		
	}
	
	@Override
	public String getName() {
		
		return this.name;
	}
	
	@Override
	public String getAddress() {
		
		return this.address;
	}

	@Override
	public int getReceiptsIssued() {
		
		return receiptsIssued.size();
		
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
		
		if(books.contains((Book)product)) {
		
			try {
				
				books.get(books.indexOf((Book)product)).decreaseQuantity(quantity);
				
				SoldProduct productToSell = new SoldProduct(product, quantity);
				soldBooks.add(productToSell); 
				
				
			} catch (OutOfStockProductException ex) {
				
				System.out.println(ex.getMessage());
				return;
			}
			
		} else {
			
			System.out.println("Book not found!");
		}
		
	}

	@Override
	public void addNewStock(Product product) {
		
		// If the store does not have the book
		// add a new product to its stock
		// If not, find the book in the collection
		// and increase the number of books by 1
		
		if(!books.contains((Book)product)) {
			
			books.add((Book)product);
			
		} else {
			
			books.get(books.indexOf((Book)product)).increaseQuantity(1);
			
		}
		
	}

	@Override
	public void addCashier(Cashier cashier) {
		
		if(!cashiers.contains(cashier)) {
			
			cashiers.add(cashier);
			
		} 
		
		else return;
		
	}

	@Override
	public void generateReceipt() {
		
		
		
	}
	
	boolean isInStock(Product product) {
		
		return books.contains((Book) product);
		
	}

}
