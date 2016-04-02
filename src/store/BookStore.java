package store;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	private List<Receipt> receiptsIssued;
	
	private static double generatedRevenue = 0;
	private static int generatedReceipts = 0;

	public BookStore(String name, String address) {

		this.name = name;
		this.address = address;

		try {
		
			loadCashiers();
			
		} catch(UnsuccessfullOperationStoreException e) {
			
			cashiers = new ArrayList<>(1);
		}
		
		try {

			loadDatabase();

		} catch(UnsuccessfullOperationStoreException ex) {

			this.books = new ArrayList<>(1);
		}
		
		soldBooks = new ArrayList<>(0);
		receiptsIssued = new ArrayList<>(0);
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
		generatedReceipts++;
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


		// Empty the receiptsIssued list
		// Can implement a save method in the future 
		// if necessary
		receiptsIssued.clear();

	}


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
