package receipt.receiptgenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
import java.util.Collection;
import java.util.UUID;

import cashier.Cashier;
import receipt.product.Product;
import store.Store;

public class Receipt implements Serializable {


	private static final long serialVersionUID = 3880371106220952098L;
	
	
	private Collection<? extends Product> soldProducts;
	private String receiptID; 
	private String issuedOn;
	private Cashier cashier;
	private Store store;
	
	private static int generatedReceipts = 0;
	private static double generatedRevenue = 0;
	
	
	public static Receipt generateReceipt(Store store, Cashier cashier, Collection<? extends Product> soldProducts) {
		
		Receipt receipt = new Receipt(store, cashier, soldProducts);
		
		return receipt;
		
	}
	
	private Receipt(Store store, Cashier cashier, Collection<? extends Product> soldProducts) {
		
		this.store = store;
		this.cashier = cashier;
		this.soldProducts = soldProducts;
		
		this.receiptID = UUID.randomUUID().toString();
		this.issuedOn = new Date().toString();
		
		generatedReceipts++;
		generatedRevenue += this.getProductsTotal();
		
	}
	
	private double getProductsTotal() {
		
		double total = 0;
		
		if(!soldProducts.isEmpty()) {
			
			for(Product product : soldProducts) {
				
				total += product.getPrice();
			}
			
		}
		
		return total;
		
	}
	
	private String whitespacePaddedProductPrice(double price) {
		
		String productPrice = String.valueOf(price);
		char[] whitespace = new char[10 - productPrice.length()];
		productPrice = new String(whitespace).replace('\u0000', ' ') + productPrice;
		
		return productPrice;
		
	}
	
	private String getProductsNamesAndValue() {
		
		StringBuilder sb = new StringBuilder();
		
		if(!soldProducts.isEmpty()) {
			
			for(Product product : soldProducts) {
				
				//sb.append(product.getName() + " " + product.getPrice() + "\n");
				//sb.append(product.getName());
				
				if(product.getName().length() > 30) {
					
					String stripped = product.getName().substring(0, 30);
					
					String productPrice = whitespacePaddedProductPrice(product.getPrice());
					sb.append(stripped + productPrice + "\n");
					
				} else {
					
					char[] whitespace = new char[30 - product.getName().length()];
					String paddedName = product.getName() + new String(whitespace).replace('\u0000', ' ');
					String productPrice = whitespacePaddedProductPrice(product.getPrice());;
					sb.append(paddedName + " " + productPrice + "\n");
				}
				
			}
			
			return sb.toString();
			
		} else {
			
			return "No products sold";
			
		}
	}
	
	public void writeToFile() {
		
		try(Scanner sc = new Scanner(this.toString());
				PrintWriter writer = new PrintWriter(new FileOutputStream("res/receiptTemp"))) {
			
			while(sc.hasNextLine()) {
				
				writer.write(sc.nextLine() + "\n");
				
			}
						
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Receipt written to a file" + "\n");
		
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
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		if(this.store != null){
			sb.append(this.store.getName() + "\n");
		} else {
			
			sb.append("None\n");
		}
		sb.append("ID: " + this.getReceiptID() + "\n");
		sb.append("Item" + new String(new char[40 - 4 - 5]).replace('\u0000', ' ') + " " + "Price" + "\n"); 
		sb.append("-----" + "\n");
		sb.append(getProductsNamesAndValue());
		sb.append("-----" + "\n");
		/** 
		*   char[] whitespace's size is computed given that
		*   the line in the receipt should not exceed 40 characters
		*   where 30 characters are allocated for the product name
		*   and 10 characters are allocated for the price of the time.
		*   The total length of the padding in the "Total: " line should
		*   be 30 - "Total: ".length(). This always results to 23 whitespace
		*   characters.
		**/
		String productsTotal = whitespacePaddedProductPrice(getProductsTotal());
		char[] whitespace = new char[23];
		sb.append("Total: " + new String(whitespace).replace('\u0000', ' ') + " " + productsTotal + "\n");
		sb.append("Your cashier: " + this.cashier.getName() + "\n" + this.issuedOn);
		
		return sb.toString();
	}
	
	
}
