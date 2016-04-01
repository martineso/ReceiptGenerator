package receipt.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import cashier.Cashier;
import receipt.product.Book;
import receipt.product.Product;
import receipt.product.SoldProduct;
import receipt.product.exceptions.OutOfStockProductException;
import store.BookStore;
import store.exceptions.UnsuccessfullOperationStoreException;


public class ReceiptGeneratorDemo {
	
	public static void main(String[] args) {
		
		BookStore BookStoreOne = new BookStore("BookStoreOne", "Buxton blvd. 708, Sofia");
		
		Product b = new Book("Slaughterhouse 5", "Vonnegut", "Aurora", "12/07/1965", 35, 25.53, true);
		Book b1 = new Book("Slaughterhouse 5", "Vonnegut", "Aurora", "12/07/1965", 35, 25.53, true);
		String date = new Date().toString();
		ArrayList<Book> books = new ArrayList<>();
		books.add((Book)b);
		books.add(b1);
		books.add(new Book("Slaughterhouse 5", "Vonnegut", "Aurora", date, 35, 25.53, true));
		books.add(new Book("Of Mice and Men", "Steinbeck", "Peguin", "25/03/2013", 3453, 15.50, true));
		books.add(new Book("Oliver Twist", "Dickens", "Aurora", date, 2, 0.50, true));
		
		ArrayList<SoldProduct> soldProductsTemp = new ArrayList<>();
		
		for(Product p : books) {
			
			soldProductsTemp.add(new SoldProduct(p, 4));
		}
		
		Cashier Ivan = new Cashier("Ivan");
		BookStoreOne.addCashier(Ivan);

		BookStoreOne.addNewStock(b);
		BookStoreOne.addNewStock(b1);
		
		@SuppressWarnings("unchecked")
		ArrayList<Book> bookstoresbooks = (ArrayList<Book>) BookStoreOne.getProductsList();
		System.out.println("Initial quantity: " + bookstoresbooks.get(0).getQuantity());
		
		
		
		try {
			
			BookStoreOne.sell(b, 35);
			BookStoreOne.generateReceipt();
			BookStoreOne.writeReceiptsToFile();
			BookStoreOne.getNumberOfReceiptsIssued();
			
		} catch(OutOfStockProductException e) {
			
			System.out.println(e.getMessage());
			
		} catch (UnsuccessfullOperationStoreException e) {
			System.err.println(e.getMessage());
		}
		

		
		System.out.println("Quantity of item after being sold: " + bookstoresbooks.get(0).getQuantity());
		System.out.println("Revenue generated: " + BookStoreOne.getRevenue());
		
	//	Receipt r = Receipt.generateReceipt(BookStoreOne, Ivan, soldProductsTemp);
		//r.writeToFile();
		
	}
	
}