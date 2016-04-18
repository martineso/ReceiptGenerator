package cashier;

import java.util.UUID;

public class Cashier {
	
	public static final int FIELDS_COUNT = 2;
	
	private String cashierID; 
	private String name;
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cashierID == null) ? 0 : cashierID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cashier other = (Cashier) obj;
		if (cashierID == null) {
			if (other.cashierID != null)
				return false;
		} else if (!cashierID.equals(other.cashierID))
			return false;
		return true;
	}
}
