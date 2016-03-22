package receipt.demo;

import java.util.ArrayList;
import java.util.Date;

import cashier.Cashier;
import receipt.product.Book;
import receipt.receiptgenerator.Receipt;
import store.BookStore;

public class ReceiptGeneratorDemo {
	
	public static void main(String[] args) {
		
		BookStore BookStoreOne = new BookStore("BookStoreOne");
		
		String date = new Date().toString();
		ArrayList<Book> books = new ArrayList<>();
		books.add(new Book("Slaughterhouse 5", "Vonnegut", "Aurora", date, 5, 25.50, true));
		books.add(new Book("Of Mice and Men", "Steinbeck", "Peguin", "25/03/2013", 5, 15.50, true));
		books.add(new Book("Oliver Twist", "Dickens", "Aurora", date, 3, 0.50, true));
//		books.clear();
		
		Receipt r = Receipt.generateReceipt(BookStoreOne, new Cashier("Ivan"), books);
		
		System.out.println(r);
		
		r.writeToFile();
		
	}
	
}