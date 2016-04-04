package store.tests;

import java.util.ArrayList;
import cashier.Cashier;
import receipt.product.Book;
import receipt.product.exceptions.OutOfStockProductException;
import store.BookStore;
import store.exceptions.CashierNotFoundException;
import store.exceptions.UnsuccessfullOperationStoreException;

public class SellMethodTest {

	static Book b1 = new Book("Slaughterhouse 5", "Vonnegut", "Aurora", "20/07/1992", "", 35, 25.53, true);
	static Book b2 = new Book("Of Mice and Men", "Steinbeck", "Peguin", "25/03/2013", "", 3453, 15.50, true);
	static Book b3 = new Book("Oliver Twist", "Dickens", "Aurora", "13/12/1968", "", 2, 0.50, true);
	static Cashier Viktor = new Cashier("Viktor", "id");
	
	public static void main(String[] args) {
		
		BookStore BookStoreOne = new BookStore("BookStoreOne", "Buxton blvd. 708, Sofia");
		
		BookStoreOne.addCashier(Viktor);
		
		try {
			BookStoreOne.selectCashier("Viktor");
		} catch (CashierNotFoundException e1) {
			e1.printStackTrace();
		}
		ArrayList<Book> books = new ArrayList<>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		
		try {
			BookStoreOne.deleteProduct("Slaughterhouse 5");
		} catch (UnsuccessfullOperationStoreException e1) {
			e1.printStackTrace();
		}
		
		try {
			BookStoreOne.deleteProduct("Of Mice And Men");
		} catch (UnsuccessfullOperationStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*try {
			
			BookStoreOne.sell(b1, 3);
			//BookStoreOne.sell(b1, 354);
			BookStoreOne.generateReceipt();
			BookStoreOne.writeReceiptsToFile();
			
		} catch (OutOfStockProductException e) {
			
			System.err.println(e.getMessage());
		} catch (UnsuccessfullOperationStoreException e) {
			System.err.println(e.getMessage());
		}*/
		
		try {
			
			BookStoreOne.saveDatabase();
			BookStoreOne.saveCashiersList();

		} catch(UnsuccessfullOperationStoreException e) {
			System.err.println("error: " + e.getMessage());
		}

	}
}
