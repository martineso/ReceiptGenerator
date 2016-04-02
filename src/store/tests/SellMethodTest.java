package store.tests;

import java.util.ArrayList;
import cashier.Cashier;
import receipt.product.Book;
import receipt.product.exceptions.OutOfStockProductException;
import store.BookStore;
import store.exceptions.UnsuccessfullOperationStoreException;

public class SellMethodTest {

	static Book b1 = new Book("Slaughterhouse 5", "Vonnegut", "Aurora", "20/07/1992", "id1", 35, 25.53, true);
	static Book b2 = new Book("Of Mice and Men", "Steinbeck", "Peguin", "25/03/2013", "id2", 3453, 15.50, true);
	static Book b3 = new Book("Oliver Twist", "Dickens", "Aurora", "13/12/1968", "id3", 2, 0.50, true);
	static Cashier Ivan = new Cashier("Ivan", "id");
	
	public static void main(String[] args) {
		
		BookStore BookStoreOne = new BookStore("BookStoreOne", "Buxton blvd. 708, Sofia");
		BookStoreOne.addCashier(Ivan);
		
		ArrayList<Book> books = new ArrayList<>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		
		BookStoreOne.addNewStock(b1);
		BookStoreOne.addNewStock(b1);
	
		try {
			
			BookStoreOne.sell(b1, 3);
			
		} catch (OutOfStockProductException e) {
			
			System.err.println(e.getMessage());
		}

		
		
		@SuppressWarnings("unchecked")
		ArrayList<Book> storeListOfBooks = (ArrayList<Book>) BookStoreOne.getProductsList();
		System.out.println("Copies of the first book in store's storage: " + storeListOfBooks.get(0).getCopies());
		System.out.println("Items: " + storeListOfBooks.size());
		System.out.println(storeListOfBooks.contains(b1));
		
		try {
			
			BookStoreOne.saveDatabase();

		} catch(UnsuccessfullOperationStoreException e) {
			System.err.println("error: " + e.getMessage());
		}

	}
}
