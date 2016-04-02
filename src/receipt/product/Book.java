package receipt.product;
import java.io.Serializable;
import java.util.UUID;

import receipt.product.exceptions.OutOfStockProductException;

public class Book implements Product, Serializable {

	
	public static final int FIELDS_COUNT = 8;
	private static final long serialVersionUID = 1L;

	private String name, author, publisher;
	private String issueDate;
	private int copies;
	private double price;
	private boolean isForeign;
	private String bookID;

	
	public Book(String name, String author, String publisher, String issueDate, String bookID, int copies, double price, boolean isForeign) {
		
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.issueDate = issueDate;
		this.copies = copies;
		this.price = price;
		this.isForeign = isForeign;
		
		if(bookID != null && bookID.length() != 0) {
			this.bookID = bookID;
		} else {
			this.bookID = UUID.randomUUID().toString();
		}

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

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
	public void setQuantity(int quantity) {
		this.copies = quantity;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public void setForeign(boolean isForeign) {
		this.isForeign = isForeign;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookID == null) ? 0 : bookID.hashCode());
		result = prime * result + copies;
		result = prime * result + (isForeign ? 1231 : 1237);
		result = prime * result + ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookID == null) {
			if (other.bookID != null)
				return false;
		} else if (!bookID.equals(other.bookID))
			return false;
		if (isForeign != other.isForeign)
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		return true;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (isForeign != other.isForeign)
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		return true;
	}*/
	

}
