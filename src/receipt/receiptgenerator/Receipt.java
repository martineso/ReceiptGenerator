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

public class Receipt implements Serializable {


	private static final long serialVersionUID = 3880371106220952098L;
	
	
	private Collection<? extends Product> soldProducts;
	private String receiptID; 
	private String issuedOn;
	private Cashier cashier;
	
	private static int generatedReceipts = 0;
	private static double generatedRevenue = 0;
	
	
	public static Receipt generateReceipt(Cashier cashier, Collection<? extends Product> soldProducts) {
		
		Receipt receipt = new Receipt(cashier, soldProducts);
		
		return receipt;
		
	}
	
	private Receipt(Cashier cashier, Collection<? extends Product> soldProducts) {
		
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
	
	private String getProductsNamesAndValue() {
		
		StringBuilder sb = new StringBuilder();
		
		if(!soldProducts.isEmpty()) {
			
			for(Product product : soldProducts) {
				
				sb.append(product.getName() + " " + product.getPrice() + "\n");
				
			}
			
			return sb.toString();
			
		} else {
			
			return "No products sold";
			
		}
	}
	
	public void writeToFile() {
		
		//String receiptString = this.toString();
		//Pattern p = Pattern.compile("(.+)");
		//Matcher match = p.matcher(receiptString);
		
		try(Scanner sc = new Scanner(this.toString());
				PrintWriter writer = new PrintWriter(new FileOutputStream("res/receiptScanner"))) {
			
		/*	while(match.find()) {
				
				writer.write(match.group(0) + "\n");
				
			
			}
		*/	
			// 
			
			
			while(sc.hasNextLine()) {
				
				writer.write(sc.nextLine() + "\n");
				
			}
			
			writer.write(this.receiptID + "\n");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Receipt written to a file" + "\n");
		
	}
	
	@Override
	public String toString() {
		
		return (this.cashier.getName() + " " + this.issuedOn + " " 
				+ this.getProductsTotal() + " " + "\n" + this.getProductsNamesAndValue());
		
	}
	
	
}
