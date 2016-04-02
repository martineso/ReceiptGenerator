package store.ui.tables.model;

import javax.swing.table.AbstractTableModel;
import receipt.product.*;
import java.util.List;

public class CustomBooksTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Book> books;
	
	@Override
	public int getColumnCount() {
		
		return Book.FIELDS_COUNT;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Book book = books.get(rowIndex);
		
		switch(columnIndex) {
		
		case 0: return book.getName();
		case 1: return book.getAuthor();
		case 2: return book.getDate();
		case 3: return book.getPublisher();
		case 4: return (book.isForeign() ? "Yes" : "No");
		case 5: return book.getQuantity() + "";
		case 6: return (book.getPrice() + "");
		case 7: return book.getId();
		
		}
		return null;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		
		switch(columnIndex) {
		
		case 0: return "Title";
		case 1: return "Author";
		case 2: return "Issued on";
		case 3: return "Publisher";
		case 4: return "Foreign Literature";
		case 5: return "Quantity";
		case 6: return "Price (BGN)";
		case 7: return "ID";
		default: return "";
		
		}
			
	}
	
	public void setBooks(List<Book> books) {
		
		this.books = books;
		fireTableDataChanged();
	}
}
