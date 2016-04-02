package store.ui.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import receipt.product.Book;
import receipt.product.Product;
import store.BookStore;
import store.ui.main.MainFrame;
import store.ui.tables.model.CustomBooksTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JSeparator;


public class BooksTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnViewCart;
	private JButton btnSave;
	private JButton btnAddToCart;
	private JButton btnDelete;
	private JButton btnCancel;
	private CustomBooksTableModel tableModel;
	private List<Product> cart;
	private BookStore bookStore;
	private JTextField textField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	private JSeparator separator;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public BooksTable(MainFrame mainFrame) {
		
		setTitle("BookStoreOne");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 887, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][][][grow][][][][]", "[][][grow][grow][][][][][][][][][]"));
	
		// Initialize the bookstore and add the parent window listener
		// in order to manage the focus of each window
		bookStore = new BookStore("BookStoreOne", "Buxton, 46");
		addWindowListener(mainFrame);
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 0 9 1,grow");
		textField.setColumns(10);
				
		separator = new JSeparator();
		contentPane.add(separator, "cell 0 1 9 1,growx");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2 9 6,grow");
		
		table = new JTable();
		tableModel = new CustomBooksTableModel();
		tableModel.setBooks((List<Book>) bookStore.getProductsList());
		table.setModel(tableModel);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { search(); }
		});
		contentPane.add(btnSearch, "cell 9 0");
		
		btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { addToCart(); }
		});
		contentPane.add(btnAddToCart, "cell 0 8 1 4,grow");
		
		btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { openCart(); }
		});
		contentPane.add(btnViewCart, "flowx,cell 1 8 1 4,grow");
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { deleteBook(); }
		});
		contentPane.add(btnDelete, "cell 2 8 1 4,grow");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { save(); }
		});
		contentPane.add(btnSave, "cell 4 8 1 4,alignx center,growy");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { discardChanges(); }
		});
		contentPane.add(btnCancel, "cell 6 8 2 4,alignx center,growy");
		
		
	}

	private void discardChanges() {
		// TODO Auto-generated method stub
		
	}

	private void save() {
		// TODO Auto-generated method stub
		
	}

	private void deleteBook() {
		// TODO Auto-generated method stub
		
	}

	private void openCart() {
		// TODO Auto-generated method stub
		
	}

	private void addToCart() {
		
		int selection = table.getSelectionModel().getMinSelectionIndex();
		
		if(selection != -1) {
			
		}
		
	}

	private void search() {
		// TODO Auto-generated method stub
		
	}

}
