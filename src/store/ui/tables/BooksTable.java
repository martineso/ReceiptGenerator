package store.ui.tables;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.JButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import receipt.product.Book;
import receipt.product.Product;
import receipt.product.exceptions.OutOfStockProductException;
import store.BookStore;
import store.exceptions.UnsuccessfullOperationStoreException;
import store.ui.main.AddBookWindow;
import store.ui.main.MainFrame;
import store.ui.main.ReceiptWindow;
import store.ui.tables.model.CustomBooksTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;


public class BooksTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnViewReceipt;
	private JButton btnNew;
	private JButton btnSell;
	private JButton btnDelete;
	private CustomBooksTableModel tableModel;
	private JTextField searchField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	private JSeparator separator;
	private BookStore bookStore;
	private List<Book> tempBookList;
	
	/**
	 * Create the frame.
	 */
	public BooksTable(MainFrame mainFrame) {
		
		setTitle("BookStoreOne");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 887, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][fill][][][][][][][][][]", "[][][grow][grow][][][][][][][][][]"));
	
		// Initialize the bookstore and add the parent window listener
		// in order to manage the focus of each window
		bookStore = mainFrame.getStore();
		tempBookList = bookStore.getProductsList();
		addWindowListener(mainFrame);
		
		searchField = new JTextField();
		contentPane.add(searchField, "cell 0 0 8 1,grow");
		searchField.setColumns(10);
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSearchButton();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSearchButton();
				search();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSearchButton();
				search();
			}
		});
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { search(); }
		});
		contentPane.add(btnSearch, "cell 8 0,growx");
				
		separator = new JSeparator();
		contentPane.add(separator, "cell 0 1 9 1,growx");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2 9 6,grow");
		
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateSellButton();
				updateDeleteButton();
			}
		});
		
		tableModel = new CustomBooksTableModel();
		tableModel.setBooks(bookStore.getProductsList());
		table.setModel(tableModel);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { sell(); }
		});
		contentPane.add(btnSell, "cell 0 8 1 4,grow");
		
		btnViewReceipt = new JButton("View Receipt");
		btnViewReceipt.setEnabled(false);
		btnViewReceipt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { viewReceipt(); }
		});
		contentPane.add(btnViewReceipt, "flowx,cell 1 8 1 4,grow");
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { deleteBook(); }
		});
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { newBook(); }
		});
		contentPane.add(btnNew, "cell 3 8 1 2,alignx center,growy");
		contentPane.add(btnDelete, "cell 4 8 1 2,grow");
		
		updateSearchButton();
		updateSellButton();
		updateDeleteButton();
		
	}

	private void newBook() {
		AddBookWindow addBook = new AddBookWindow();
		addBook.setModalityType(ModalityType.APPLICATION_MODAL);
		addBook.setVisible(true);
		
		Book book = addBook.getBook();
		
		if(book != null) {
			bookStore.addNewStock(book);
		} else {
			JOptionPane.showMessageDialog(this, "Unable to add book, try again!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void deleteBook() {
		
		int selectedRow = table.getSelectionModel().getMinSelectionIndex();
		
		if(selectedRow != -1) {
			String bookName = tempBookList.get(selectedRow).getName();
			try {
				bookStore.deleteProduct(bookName);
			} catch (UnsuccessfullOperationStoreException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
	}

	private void viewReceipt() {
		
		ReceiptWindow rWindow = new ReceiptWindow(this.bookStore);
		rWindow.setVisible(true);
		btnViewReceipt.setEnabled(false);
	}

	private void sell() {
		
		int selectedRow = table.getSelectionModel().getMinSelectionIndex();
		
		if(selectedRow != -1) {
			int quantity = getQuantityFromInput();
			Product product = tempBookList.get(selectedRow);
			try {
			
				bookStore.sell(product, quantity);
				tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
				btnViewReceipt.setEnabled(true);
			
			} catch (OutOfStockProductException e) {
				JOptionPane.showMessageDialog(this, "Invalid quantity, try again", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
			
	}
		

	private void search() {
		
		String searchResult = searchField.getText();
		tempBookList = bookStore.find(searchResult);
		tableModel.setBooks(tempBookList);
		
	}
	
	private int getQuantityFromInput() {
		
		String valueString = JOptionPane.showInputDialog("How many?", 1);
		return Integer.valueOf(valueString);
	}
	
	// Button listeners ---------------
	
	private void updateSearchButton() {
		
		Document doc = searchField.getDocument();
		int len = doc.getLength();
		if(len < 3) {
			
			tableModel.setBooks(bookStore.getProductsList());
			btnSearch.setEnabled(false);
			
		} else {
			btnSearch.setEnabled(true);
		}	
	}
	
	private void updateSellButton() {
		int selectedRow = table.getSelectedRow();
		btnSell.setEnabled(selectedRow != -1);
	}
	
	private void updateDeleteButton() {
		int selectedRow = table.getSelectedRow();
		btnDelete.setEnabled(selectedRow != -1);
	}
	
}
