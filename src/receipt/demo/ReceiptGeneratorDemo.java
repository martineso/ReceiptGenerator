package receipt.demo;

import java.util.ArrayList;
import cashier.Cashier;
import receipt.product.Book;
import receipt.product.Product;
import receipt.product.SoldProduct;
import receipt.product.exceptions.OutOfStockProductException;
import store.BookStore;
import store.exceptions.UnsuccessfullOperationStoreException;


public class ReceiptGeneratorDemo {
	
	static Book b1 = new Book("Slaughterhouse 5", "Vonnegut", "Aurora", "20/07/1992", "id1", 35, 25.53, true);
	static Book b2 = new Book("Of Mice and Men", "Steinbeck", "Peguin", "25/03/2013", "id2", 3453, 15.50, true);
	static Book b3 = new Book("Oliver Twist", "Dickens", "Aurora", "13/12/1968", "id3", 2, 0.50, true);
	static Cashier Ivan = new Cashier("Ivan", "id");
	
	public static void main(String[] args) {
		
		BookStore BookStoreOne = new BookStore("BookStoreOne", "Buxton blvd. 708, Sofia");
		
		ArrayList<Book> books = new ArrayList<>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		
		ArrayList<SoldProduct> soldProductsTemp = new ArrayList<>();
		
		for(Product p : books) {
			
			soldProductsTemp.add(new SoldProduct(p, 4));
		}
		
		Cashier Ivan = new Cashier("Ivan", "id");
		BookStoreOne.addCashier(Ivan);

		BookStoreOne.addNewStock(b1);
		BookStoreOne.addNewStock(b1);
		
		@SuppressWarnings("unchecked")
		ArrayList<Book> bookstoresbooks = (ArrayList<Book>) BookStoreOne.getProductsList();
		System.out.println("Initial quantity: " + bookstoresbooks.get(0).getQuantity());
		
		
		
		try {
			
			BookStoreOne.sell(b1, 35);
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

		books.add(b2);
		books.add(b3);
		

		
		
	}
	
}