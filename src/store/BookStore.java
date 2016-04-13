package store;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cashier.Cashier;
import receipt.product.Book;
import receipt.product.Product;
import receipt.product.SoldProduct;
import receipt.product.exceptions.OutOfStockProductException;
import receipt.receiptgenerator.Receipt;
import store.exceptions.CashierNotFoundException;
import store.exceptions.UnsuccessfullOperationStoreException;
import store.file.service.StoreFileManager;

public class BookStore implements Store {

	private String name, address;
	private List<Cashier> cashiers;
	private Cashier activeCashier;
	private List<Book> books;
	private List<SoldProduct> soldBooks;
	private Receipt lastReceipt;
	private List<Receipt> receiptsIssued;
	
	private static double generatedRevenue = 0;
	private static int generatedReceipts = 0;

	public BookStore(String name, String address) {

		this.name = name;
		this.address = address;

		try {
		
			loadCashiers();
			
		} catch(UnsuccessfullOperationStoreException e) {
			
			this.cashiers = new ArrayList<>();
		}
		
		try {

			loadDatabase();

		} catch(UnsuccessfullOperationStoreException ex) {

			this.books = new ArrayList<>();
		}
		
		soldBooks = new ArrayList<>();
		receiptsIssued = new ArrayList<>();
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

		return generatedReceipts;

	}

	public List<Book> getProductsList() {

		return books;

	}

	@Override
	public List<Cashier> getCashierList() {

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

				product.decreaseQuantity(quantity);

				SoldProduct productToSell = new SoldProduct(product, quantity);
				soldBooks.add(productToSell); 

				generatedRevenue += productToSell.getGeneratedRevenue();


			} catch (OutOfStockProductException e) {

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


		if(product == null) {
			return;
		}

		if(!books.contains((Book)product)) {

			books.add((Book)product);

		} else {

			product.increaseQuantity(product.getQuantity());

		}

	}

	@Override
	public void addCashier(Cashier cashier) {
		
		if(!cashiers.contains(cashier)) {

			cashiers.add(cashier);

		}

	}

	@Override
	public void generateReceipt() {

		lastReceipt = Receipt.generateReceipt(this, this.activeCashier, this.soldBooks);
		receiptsIssued.add(lastReceipt);
		this.soldBooks.clear();
		generatedReceipts++;
	}
	
	public Receipt getLastReceipt() {
		return lastReceipt;
	}

	public void selectCashier(String name) throws CashierNotFoundException {

		for(Cashier cashier : cashiers) {

			if(cashier.getName().equalsIgnoreCase(name)) {

				this.activeCashier = cashier;
				return;

			} else {
				// Selects the first cashier in the list of cashiers instead
				this.activeCashier = cashiers.get(0);
				
				throw new CashierNotFoundException("The cashier is not found. Default cashier selected: " + activeCashier.getName());

			}

		}

	}
	
	public void writeLastReceiptToFile() throws UnsuccessfullOperationStoreException {
		
		File directory = new File("res");
		
		try {
			lastReceipt.writeToFile(directory);
		} catch(IOException e) {
			throw new UnsuccessfullOperationStoreException("Cannot write receipt to file!");
		}
	}

	public void writeReceiptsToFile() throws UnsuccessfullOperationStoreException {
		
		// Create a directory and pass it as argument to the receipt. 

		File directory = new File("res" + File.separator + getName() + "-receipts");
		directory.mkdirs();
		
		for(Receipt r : receiptsIssued) {

			try {
				
				r.writeToFile(directory);

			} catch (IOException e) {
				throw new UnsuccessfullOperationStoreException("Cannot write receipts to file!");
			}

		}


		// Empty the receiptsIssued list
		// Can implement a save method in the future 
		// if necessary
		receiptsIssued.clear();

	}


	@Override
	public void deleteProduct(String name) throws UnsuccessfullOperationStoreException{

		Product product = getProduct(name);
		
		if(product != null) {

			books.remove(product);

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
	
	public List<Book> find(String keyword) {
		
		List<Book> searchResult = new ArrayList<>();
		
		String regex = "(" + keyword.toLowerCase() + ")+"; 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		for(Book book : books) {
			String bookName = book.getName().toLowerCase();
			matcher = pattern.matcher(bookName);
			
			if(matcher.find()) {
				searchResult.add(book);
			}
		}
		return searchResult;
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
	
	private void loadCashiers() throws UnsuccessfullOperationStoreException {
		
		File path = new File("res" + File.separator + this.getName() + "-Cashiers.db");
		try {
			this.cashiers = StoreFileManager.loadCashiersList(path);
		} catch (FileNotFoundException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		} catch (IOException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		}
	}
	
	public void saveCashiersList() throws UnsuccessfullOperationStoreException {
		
		File path = new File("res" + File.separator + this.getName() + "-Cashiers.db");
		
		try {
			StoreFileManager.saveCashiersList(cashiers, path);
		} catch (UnsuccessfullOperationStoreException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		} catch (IOException e) {
			throw new UnsuccessfullOperationStoreException(e.getMessage());
		}
	}

}
