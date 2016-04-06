package receipt.receiptgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import cashier.Cashier;
import receipt.product.Sellable;
import store.Store;

public class Receipt implements Serializable {


	private static final long serialVersionUID = 3880371106220952098L;
	
	private static final int PRODUCT_NAME_LENGTH = 30;
	private static final int PRODUCT_QUANTITY_LENGTH = 10;
	private static final int PRODUCT_PRICE_LENGTH = 10;
	
	private List<? extends Sellable> soldProducts;
	private String receiptID; 
	private String issuedOn;
	private Cashier cashier;
	private Store store;
	
	private static int generatedReceipts = 0;
	private static double generatedRevenue = 0;
	
	
	public static Receipt generateReceipt(Store store, Cashier cashier, Collection<? extends Sellable> soldProducts) {
		
		Receipt receipt = new Receipt(store, cashier, soldProducts);
		
		return receipt;
		
	}
	
	private Receipt(Store store, Cashier cashier, Collection<? extends Sellable> soldProducts) {
		
		this.store = store;
		this.cashier = cashier;
		this.soldProducts = new ArrayList<>(soldProducts);
		
		this.receiptID = UUID.randomUUID().toString();
		this.issuedOn = new Date().toString();

		generatedReceipts++;
		generatedRevenue += this.getProductsTotal();
		
	}
	
	/* Utility methods
	 * for internal use only!
	 */
	
	private String generateWindowsFriendlyDate(String date) {
		
		return date.replaceAll("(\\:)+", "-");
		
	}
	/**
	 * Calculates the total price of the item based
	 * on how many items are sold and the value of each product
	 * @return The total value of all items in the Collection of sold products;
	 * returns 0, if there are no items in the collection i.e. no sold products.
	 */
	private double getProductsTotal() {
		
		double total = 0;
		
		if(!soldProducts.isEmpty()) {
			
			for(Sellable product : soldProducts) {
				
				total += (product.getPrice() * product.getQuantitySold());
			}
			
		}
		
		return total;
	}
	
	
	private String whitespacePaddedProductPrice(double price) {
		
		String productPrice = String.format("%.2f", price);
		char[] whitespace = new char[PRODUCT_PRICE_LENGTH - productPrice.length()];
		productPrice = new String(whitespace).replace('\u0000', ' ') + productPrice;
		
		return productPrice;
		
	}
	
	private String whitespacePaddedProductQuantity(int quantity) {
		
		
		// productQuantity.length() + 2 is calculated due to the fact
		// that the quantity column will show the info in the following
		// format
		//				Quantity:
		//					1 x
		// This means that we have two extra chars for the single whitespace
		// character and the 'x' character.
		
		String productQuantity = String.valueOf(quantity);
		char[] whitespace = new char[PRODUCT_QUANTITY_LENGTH - (productQuantity.length() + 2)];
		productQuantity = new String(whitespace).replace('\u0000', ' ') + productQuantity + ' ' + 'x';
		
		return productQuantity;
	}
	
	/**
	 * @return Generates the portion of the string containing each item sold, the number of items sold
	 * and the price per item. 
	 */
	private String getProductsNamesValuesAndQuantity() {
	
		/**
		 * The length of the name column is equal to PRODUCT_NAME_LENGTH
		 * The length of the quantity column is equal to PRODUCT_QUANTITY_LENGTH
		 * The length of the price column is equal to PRODUCT_PRICE_LENGTH
		 */
	
		StringBuilder sb = new StringBuilder();

		if(!soldProducts.isEmpty()) {
			
			for(Sellable product : soldProducts) {
				
				if(product.getName().length() > PRODUCT_NAME_LENGTH) {
					
					// Retain the length of the name of the product 30 chars maximum
					// to ensure that the size of the receipt is constant
					
					String strippedProductName = product.getName().substring(0, PRODUCT_NAME_LENGTH);
					String productQuantity = whitespacePaddedProductQuantity(product.getQuantitySold());
					String productPrice = whitespacePaddedProductPrice(product.getPrice());
					
					sb.append(strippedProductName + productQuantity + productPrice + "\n");
					
				} else {
					
					char[] whitespace = new char[PRODUCT_NAME_LENGTH - product.getName().length()];
					String paddedName = product.getName() + new String(whitespace).replace('\u0000', ' ');
					String productQuantity = whitespacePaddedProductQuantity(product.getQuantitySold());
					String productPrice = whitespacePaddedProductPrice(product.getPrice());
					
					
					sb.append(paddedName + productQuantity + productPrice + "\n");
				}
				
			}
			
			return sb.toString();
			
		} else {
			
			return "No products sold";
			
		}
	}
	
	public String getReceiptID() {
		
		return this.receiptID;
	}
	
	public static double getGeneratedRevenue() {
		
		return Receipt.generatedRevenue;
	}
	
	public static int getGeneratedReceipts() {
		
		return Receipt.generatedReceipts;
	}
	
	public String generateReceiptAsString() {
		
		StringBuilder sb = new StringBuilder();
		
		String productsTotal = whitespacePaddedProductPrice(getProductsTotal());
		
		String whitespacePaddingItem = new String(new char[PRODUCT_NAME_LENGTH - 4]).replace('\u0000', ' ');
		String whitespacePaddingQuantity = new String(new char[PRODUCT_QUANTITY_LENGTH - 8]).replace('\u0000', ' ');
		String whitespacePaddingPrice = new String(new char[PRODUCT_PRICE_LENGTH - 5]).replace('\u0000', ' ');
		String whitespacePaddingTotal = new String(new char[(PRODUCT_NAME_LENGTH + PRODUCT_PRICE_LENGTH + PRODUCT_QUANTITY_LENGTH) - (7 + productsTotal.length())]).replace('\u0000', ' ');
		
		if(this.store != null){
			sb.append(this.store.getName() + "\n");
		} else {
			
			sb.append("None\n");
		}
		
		sb.append("ID: " + this.getReceiptID() + "\n");
		sb.append("Item" + whitespacePaddingItem + whitespacePaddingQuantity + "Quantity" + whitespacePaddingPrice + "Price" + "\n");
		sb.append("-----" + "\n");
		sb.append(getProductsNamesValuesAndQuantity());
		sb.append("-----" + "\n");
		
		
		sb.append("Total: " + whitespacePaddingTotal + productsTotal + "\n");
		sb.append("Your cashier: " + this.cashier.getName() + "\n" + this.issuedOn);
		
		return sb.toString();
		
	}
	
	public void writeToFile(File directory) throws IOException {
		
		if(!directory.exists()) {
			throw new IOException("Invalid directory");
		}
		
		String receiptName = generateWindowsFriendlyDate(this.issuedOn) + ".txt";
		File filePath = new File(directory + File.separator + receiptName);
		
		try(Scanner sc = new Scanner(generateReceiptAsString());
				FileWriter fileWriter = new FileWriter(filePath);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter writer = new PrintWriter(bufferedWriter)) {
			
			while(sc.hasNextLine()) {
				
				writer.println(sc.nextLine());
				
			}				
		}
	}
	
}
