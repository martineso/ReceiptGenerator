package cashier;

import java.util.UUID;

public class Cashier {
	
	private String cashierID; 
	private String name;
	
	public Cashier(String name) {
		
		this.name = name;
		this.cashierID = UUID.randomUUID().toString();
		
	}
	
	public String getCashierID() {
		return cashierID;
	}
	
	public String getName() {
		return name;
	}
}
