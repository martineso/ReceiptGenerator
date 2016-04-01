package store;

<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
=======
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
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

<<<<<<< HEAD
=======
import com.sun.corba.se.impl.orbutil.ObjectWriter;
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3

import cashier.Cashier;
import receipt.product.Book;
import receipt.product.Product;
import receipt.product.SoldProduct;
import receipt.product.exceptions.OutOfStockProductException;
import receipt.receiptgenerator.Receipt;
import store.exceptions.CashierNotFoundException;
import store.exceptions.UnsuccessfullOperationStoreException;
<<<<<<< HEAD
import store.file.service.StoreFileManager;
=======
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3

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
		
		try {
			
			loadDatabase();
			
		} catch(UnsuccessfullOperationStoreException ex) {
			
			this.books = new ArrayList<>(1);
		}
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
	public int getNumberOfReceiptsIssued() {
		
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
		
		if(isInStock(product)) {
		
			try {
				
				books.get(books.indexOf((Book)product)).decreaseQuantity(quantity);
				
				SoldProduct productToSell = new SoldProduct(product, quantity);
				soldBooks.add(productToSell); 
				
				generatedRevenue += productToSell.getGeneratedRevenue();
				
				
			} catch (OutOfStockProductException ex) {
				
				throw new OutOfStockProductException("No sufficient availability!");
			}
			
		} else {
			
			throw new OutOfStockProductException("The book is not in stock!");
		}
		
	}
	
	private boolean isInStock(Product product) {
		
		return books.contains((Book) product);
	}
	
	@Override
	public void addNewStock(Product product) {
		
		// If the store does not have the book
		// add the book in a new slot.
		// If the book is already in stock, find the book in the collection
		// and increase the number of books by with the number of copies
		// the product being added has.
<<<<<<< HEAD
		
		if(product == null) {
			return;
		}
=======
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
		
		if(!books.contains((Book)product)) {
			
			books.add((Book)product);
			
		} else {
			
			books.get(books.indexOf((Book)product)).increaseQuantity(product.getQuantity());
			
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
	
	public void writeReceiptsToFile() throws UnsuccessfullOperationStoreException {
		
		for(Receipt r : receiptsIssued) {
			
			try {
				r.writeToFile();
				
			} catch (IOException e) {
				throw new UnsuccessfullOperationStoreException("Cannot write receipt to file!");
			}
			
		}
		
<<<<<<< HEAD
		// Empty the receiptsIssued list
=======
		// Empty the receiptsIssued array
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
		// Can implement a save method in the future 
		// if necessary
		receiptsIssued.clear();
		
	}
	

	@Override
<<<<<<< HEAD
	public void deleteProduct(String name) throws UnsuccessfullOperationStoreException {
=======
	public void deleteProduct(String name) throws UnsuccessfullOperationStoreException{
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
		
		
		if(getProduct(name) != null) {
			
			books.remove(getProduct(name));
			
		} else {
			
			throw new UnsuccessfullOperationStoreException("Delete operation unsuccessful");
			
		}
<<<<<<< HEAD
		
	}
=======
		
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
	
	private void loadDatabase() throws UnsuccessfullOperationStoreException {
		
		try(ObjectInputStream in = new ObjectInputStream
				(new BufferedInputStream(new FileInputStream("res" + File.separator + this.getName() + "-Books")))) {
			
			try {
				this.books = (List<Book>) in.readObject();
			} catch (ClassNotFoundException e) {
				
				throw new UnsuccessfullOperationStoreException("Error while casting to \"List<Book>\"");
				
			}
			
		} catch (FileNotFoundException e) {
			
			throw new UnsuccessfullOperationStoreException("Cannot read the file. File not found!");
			
		} catch (IOException e) {
			
			throw new UnsuccessfullOperationStoreException("Cannot read the file. System error!");
		}
		
	}
	
	public void saveDatabase() throws UnsuccessfullOperationStoreException {
		
		try(ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream("res" + File.separator + this.getName() + "-Books.db"))) {
			
			objectWriter.writeObject(this.books);
			
		} catch (FileNotFoundException e) {
			throw new UnsuccessfullOperationStoreException("Error! Cannot write to file!");
		} catch (IOException e) {
			throw new UnsuccessfullOperationStoreException("Write to file error (unspecified)!");
		}
		
	}
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3

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
	private void loadDatabase() throws UnsuccessfullOperationStoreException {
		
		File path = new File("res" + File.separator + this.getName() + "-Books.db");
		
		try {
			this.books = StoreFileManager.loadBooksDatabase(path);
			
		} catch (FileNotFoundException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		} catch (IOException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		}
	}
	
	public void saveDatabase() throws UnsuccessfullOperationStoreException {
		
		File path = new File("res" + File.separator + this.getName() + "-Books.db");
		
		try {
			StoreFileManager.saveBooksDatabase(this.books, path);
			
		} catch (FileNotFoundException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		} catch (IOException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		}
	}
}
