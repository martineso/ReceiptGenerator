package store.ui.tables.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cashier.Cashier;

public class CustomCashierTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	List<Cashier> cashiers;
	
	@Override
	public int getColumnCount() {
		return Cashier.FIELDS_COUNT;
	}

	@Override
	public int getRowCount() {
		
		return cashiers.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Cashier cashier = cashiers.get(rowIndex);
		
		switch(columnIndex) {
		case 0: return cashier.getName();
		case 1: return cashier.getCashierId() + "";
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {

		switch(column) {
		case 0: return "Name";
		case 1: return "ID";
		default: return "";
		}
	}
	
	public void setCashiers(List<Cashier> cashiers) {
		this.cashiers = cashiers;
	}

}
