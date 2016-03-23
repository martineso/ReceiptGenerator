package receipt.product;
import java.util.UUID;

import receipt.product.exceptions.OutOfStockProductException;

public class Book implements Product {

	private String name, author, publisher;
	private String issueDate;
	private int copies;
	private double price;
	private boolean isForeign;
	private String bookID;


	public Book(String name, String author, String publisher, String issueDate, int copies, double price, boolean isForeign) {
		
		this.bookID = UUID.randomUUID().toString();
		
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.issueDate = issueDate;
		this.copies = copies;
		this.price = price;
		this.isForeign = isForeign;

	}

	public String getName() {

		return name;

	}

	public String getAuthor() {

		return author;
	}

	public String getDate() {

		return issueDate;
	}

	public double getPrice() {

		return price;
	}
	
	public int getCopies() {
		
		return copies;
		
	}
	
	public String getPublisher() {
		
		return publisher;
	
	}
	
	public boolean isForeign() {
		return isForeign;
	}

	public String getId() {
	
		return this.bookID;
	}

	public int getQuantity() {
		
		return this.copies;
		
	}

	@Override
	public void decreaseQuantity(int quantity) throws OutOfStockProductException {
		
		if(quantity > this.copies) {
			
			throw new OutOfStockProductException("Out of stock, check storage!");
			
		} else {
			
			this.copies -= quantity;
		}
		
	}
	
	@Override
	public void increaseQuantity(int quantity) {
		
		this.copies += quantity;
		
	}
	

}
