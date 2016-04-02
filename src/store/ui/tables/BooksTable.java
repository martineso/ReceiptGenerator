package store.ui.tables;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import receipt.product.Book;
import store.ui.tables.model.CustomBooksTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Window.Type;

public class BooksTable extends JFrame {

	private JPanel contentPane;
	private JButton btnViewCart;
	private JButton btnSave;
	private JButton btnSell;
	private JButton btnDelete;
	private JButton Cancel;
	
	private CustomBooksTableModel tableModel;
	private List<Book> books;
	private JTextField textField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BooksTable frame = new BooksTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BooksTable() {
		setTitle("BookStoreOne");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][][][grow][][][][]", "[][grow][grow][grow][][][][][][][][][]"));
	
		textField = new JTextField();
		contentPane.add(textField, "cell 0 0 9 1,grow");
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		contentPane.add(btnSearch, "cell 9 0");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 9 7,grow");
		
		table = new JTable();
		tableModel = new CustomBooksTableModel();
		/*books = new ArrayList<>();
		books.add(new Book("Slaughterhouse 5", "Vonnegut", "Aurora", "20/07/1992", "id1", 35, 25.53, true));
		tableModel.setBooks(books);
		table.setModel(tableModel);*/
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		
		
		btnSell = new JButton("Sell");
		contentPane.add(btnSell, "cell 0 8 1 4,grow");
		
		btnViewCart = new JButton("View Cart");
		//btnViewCart.addActionListener(event -> { openCart(); });
		contentPane.add(btnViewCart, "flowx,cell 1 8 1 4,grow");
		
		btnDelete = new JButton("Delete");
		contentPane.add(btnDelete, "cell 2 8 1 4,grow");
		
		btnSave = new JButton("Save");
		contentPane.add(btnSave, "cell 4 11,alignx center,aligny center");
		
		Cancel = new JButton("Cancel");
		contentPane.add(Cancel, "cell 6 11,alignx center,aligny center");
	}

}
