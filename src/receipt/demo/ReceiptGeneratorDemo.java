package receipt.demo;

import java.util.ArrayList;
import java.util.Date;

import cashier.Cashier;
import receipt.product.Book;
import receipt.product.SoldProduct;
import receipt.receiptgenerator.Receipt;
import store.BookStore;

public class ReceiptGeneratorDemo {
	
	public static void main(String[] args) {
		
		BookStore BookStoreOne = new BookStore("BookStoreOne");
		
		String date = new Date().toString();
		ArrayList<Book> books = new ArrayList<>();
		books.add(new Book("Slaughterhouse 5", "Vonnegut", "Aurora", date, 35, 25.50, true));
		books.add(new Book("Of Mice and Men", "Steinbeck", "Peguin", "25/03/2013", 3453, 15.50, true));
		books.add(new Book("Oliver Twist", "Dickens", "Aurora", date, 345, 0.50, true));
		
		ArrayList<SoldProduct> soldBooks = new ArrayList<>();
		
		for(Book product : books) {
			
			// Sell 3 books of each book
			soldBooks.add(new SoldProduct(product, 25));
			
		}
		
		Receipt r = Receipt.generateReceipt(BookStoreOne, new Cashier("Ivan"), soldBooks);
		
		System.out.println(r);
		
		r.writeToFile();
		
	}
	
}