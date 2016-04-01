package store.tests;

import java.util.ArrayList;
import java.util.Date;

import cashier.Cashier;
import receipt.product.Book;
<<<<<<< HEAD
=======
import receipt.product.SoldProduct;
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
import receipt.product.exceptions.OutOfStockProductException;
import store.BookStore;
import store.exceptions.UnsuccessfullOperationStoreException;

public class SellMethodTest {

	static String date = new Date().toString();
	static Book b1 = new Book("Slaughterhouse 5", "Vonnegut", "Aurora", date, 35, 25.53, true);
	static Book b2 = new Book("Of Mice and Men", "Steinbeck", "Peguin", "25/03/2013", 3453, 15.50, true);
	static Book b3 = new Book("Oliver Twist", "Dickens", "Aurora", date, 2, 0.50, true);
	static Cashier Ivan = new Cashier("Ivan");
	
	public static void main(String[] args) {
		
		BookStore BookStoreOne = new BookStore("BookStoreOne", "Buxton blvd. 708, Sofia");
		BookStoreOne.addCashier(Ivan);
		
		ArrayList<Book> books = new ArrayList<>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		
<<<<<<< HEAD
		BookStoreOne.addNewStock(books.get(0));
		BookStoreOne.addNewStock(books.get(0));
		BookStoreOne.addNewStock(books.get(0));
=======
		BookStoreOne.addNewStock(b1);
		BookStoreOne.addNewStock(b1);
		
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
		try {
			
			BookStoreOne.sell(b1, 3);
			
		} catch (OutOfStockProductException e) {
			
			System.err.println(e.getMessage());
		}
<<<<<<< HEAD
		
		
		ArrayList<Book> storeListOfBooks = (ArrayList<Book>) BookStoreOne.getProductsList();
		System.out.println("Copies of the first book in store's storage: " + storeListOfBooks.get(0).getCopies());
		System.out.println("Items: " + storeListOfBooks.size());
		System.out.println(storeListOfBooks.contains(b1));
		
		/*try {
			
			BookStoreOne.saveDatabase();
			System.out.println(BookStoreOne.getName() + "'s database saved!");
=======
	
		ArrayList<Book> storeListOfBooks = (ArrayList<Book>) BookStoreOne.getProductsList();
		System.out.println("Copies of the first book in store's storage: " + storeListOfBooks.get(0).getCopies());
		
		try {
			
			BookStoreOne.saveDatabase();
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
			
		} catch (UnsuccessfullOperationStoreException e) {

			e.printStackTrace();
<<<<<<< HEAD
		}*/
=======
		}
>>>>>>> 4b4507b76882164d4aeaacc7d3f454747a71ffc3
	}
}
