package store.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import cashier.Cashier;
import receipt.product.Book;
import receipt.product.exceptions.OutOfStockProductException;
import receipt.receiptgenerator.Receipt;
import store.BookStore;
import store.exceptions.CashierNotFoundException;
import store.exceptions.UnsuccessfullOperationStoreException;
import store.ui.main.ReceiptWindow;

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
		
		for(Book book : BookStoreOne.getProductsList()) {
			System.out.println(book);
		}
		
		ReceiptWindow r = new ReceiptWindow(BookStoreOne);
		r.setVisible(true);
/*		try {
			BookStoreOne.sell(BookStoreOne.getProduct("Oliver Twist"), 1);
			BookStoreOne.generateReceipt();
		} catch(OutOfStockProductException e) {
			e.printStackTrace();
		}
		
		try {
			BookStoreOne.writeLastReceiptToFile();
		} catch (UnsuccessfullOperationStoreException e) {
			e.printStackTrace();
		}
		String receipt = BookStoreOne.getLastReceipt().generateReceiptAsString();
		System.out.println(receipt);*/

	}
}
