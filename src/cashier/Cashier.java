package cashier;

import java.util.UUID;

public class Cashier {
	
	private String cashierID; 
	private String name;
	
	public static final String fieldSeparator = "^";
	
	public Cashier(String name, String cashierID) {
		
		this.name = name;
		
		if(cashierID != null && cashierID.length() > 0){
			this.cashierID = cashierID;
		} else {
				this.cashierID = UUID.randomUUID().toString();
		}
		
	}
	
	public String getCashierId() {
		return cashierID;
	}
	
	public String getName() {
		return name;
	}
	
	
}
