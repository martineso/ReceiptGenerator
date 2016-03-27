package store;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import cashier.Cashier;
import receipt.product.Book;
import receipt.product.Product;
import receipt.product.SoldProduct;
import receipt.product.exceptions.OutOfStockProductException;
import receipt.receiptgenerator.Receipt;
import store.exceptions.CashierNotFoundException;
import store.exceptions.UnsuccessfullOperationStoreException;

public class BookStore implements Store {

	private String name, address;
	private List<Cashier> cashiers;
	private Cashier activeCashier;
	private List<Book> books;
	private List<SoldProduct> soldBooks;
	private List<Receipt> receiptsIssued;
	private static double generatedRevenue = 0;
	
	public BookStore(String name, String address) {
		
		this.name = name;
		this.address = address;
		
		cashiers = new ArrayList<>(1);
		this.activeCashier = new Cashier("Ivan");
		
		books = new ArrayList<>(1);
		soldBooks = new ArrayList<>(1);
		receiptsIssued = new ArrayList<>(1);
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
	public void sell(Product product, int quantity) throws OutOfStockProductException {
		
		if(product != null) {
		
			try {
				
				books.get(books.indexOf((Book)product)).decreaseQuantity(quantity);
				
				SoldProduct productToSell = new SoldProduct(product, quantity);
				soldBooks.add(productToSell); 
				
				generatedRevenue += productToSell.getGeneratedRevenue();
				
				
			} catch (OutOfStockProductException ex) {
				
				throw new OutOfStockProductException("No sufficient availability!");
			}
			
		} else {
			
			throw new OutOfStockProductException("No sufficient availability!");
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
		
		receiptsIssued.add(Receipt.generateReceipt(this, this.activeCashier, this.soldBooks));
		
	}
	
	public void selectCashier(String name) throws CashierNotFoundException {
		
		for(Cashier cashier : cashiers) {
			
			if(cashier.getName().equalsIgnoreCase(name)) {
				
				this.activeCashier = cashier;
				return;
				
			} else {
				
				throw new CashierNotFoundException();
				
			}
			
		}
		
	}
	
	public void writeReceiptsToFile() {
		
		for(Receipt r : receiptsIssued) {
			
			r.writeToFile();
			
		}
		
		// Empty the receiptsIssued array
		receiptsIssued.clear();
		
	}
	
	/*private boolean isInStock(Product product) {
		
		return books.contains((Book) product);
		
	}*/

	@Override
	public void deleteProduct(String name) throws UnsuccessfullOperationStoreException{
		
		
		if(getProduct(name) != null) {
			
			books.remove(getProduct(name));
		} else {
			
			throw new UnsuccessfullOperationStoreException("Delete operation unsuccessful");
			
		}
		
	}

	@Override
	public Product getProduct(String name) {
		
		for(int i = 0; i < books.size(); ++i) {
			
			if(books.get(i).getName().equalsIgnoreCase(name)) {
				
				return books.get(i);
				
			} 
		} 
		
		return null;
	}
	
	// Load and Save methods
	
	private void loadDatabase() throws UnsuccessfullOperationStoreException{
		
		try(ObjectInputStream os = new ObjectInputStream
				(new BufferedInputStream(new FileInputStream("res" + File.separator + this.getName() + " database")))) {
			
			try {
				this.books = (List<Book>) os.readObject();
			} catch (ClassNotFoundException e) {
				
				throw new UnsuccessfullOperationStoreException("Cannot read the file. Try again!");
				
			}
			
		} catch (FileNotFoundException e) {
			
			throw new UnsuccessfullOperationStoreException("Cannot read the file. Try again!");
			
		} catch (IOException e) {
			
			throw new UnsuccessfullOperationStoreException("Cannot read the file. Try again!");
		}
		
	}
	
	public void saveData() {
		
		// to be implemented
	}

}
