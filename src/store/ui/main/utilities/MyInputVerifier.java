package store.ui.main.utilities;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class MyInputVerifier extends InputVerifier {

	@Override
	public boolean verify(JComponent input) {
		
		String fieldName = input.getName();
		String value = ((JTextField) input).getText();
		String dateRegex = "(\\d){1,2}[\\/](\\d){1,2}[\\/](\\d){4}";
		String priceRegex = "[\\d]*[\\.][\\d]+";
		String quantityRegex = "[\\d]+";
		
		switch(fieldName) {
			case "titleField":
				if(value.isEmpty()) return false;
				break;
			case "authorField": 
				if(value.isEmpty()) return false;
				break;
			case "publisherField": 
				if(value.isEmpty()) return false;
				break;
			case "dateField": 
				if(!value.matches(dateRegex)) return false;
				break;
			case "priceField": 
				if(!value.matches(priceRegex)) return false;
				break;
			case "quantityField":
				if(!value.matches(quantityRegex)) return false;
				break;
		}
		
		return true;
	}
}
