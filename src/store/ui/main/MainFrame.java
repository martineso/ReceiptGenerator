package store.ui.main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import store.BookStore;
import store.exceptions.CashierNotFoundException;
import store.exceptions.UnsuccessfullOperationStoreException;
import store.ui.tables.BooksTable;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private BookStore bookStore;
	private JPanel contentPane;
	private JButton btnViewBooks;
	private JButton btnViewCashiers;
	private JButton btnService;
	private JButton btnRevenueAndLosses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		
		setType(Type.UTILITY);
		setTitle("BookStoreOne");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblMainLogo = new JLabel("");
		lblMainLogo.setIcon(new ImageIcon("C:\\Users\\Geni\\workspace\\NBU\\ReceiptGenerator\\res\\BookStoreOneLogo.png"));
		GridBagConstraints gbc_lblMainLogo = new GridBagConstraints();
		gbc_lblMainLogo.gridheight = 5;
		gbc_lblMainLogo.insets = new Insets(0, 0, 5, 0);
		gbc_lblMainLogo.gridx = 0;
		gbc_lblMainLogo.gridy = 1;
		contentPane.add(lblMainLogo, gbc_lblMainLogo);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 6;
		contentPane.add(separator, gbc_separator);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 7;
		contentPane.add(panel, gbc_panel);
		
		btnViewBooks = new JButton("View Books");
		btnViewBooks.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBooksFrame();
			}
		});
		panel.add(btnViewBooks);
		
		btnViewCashiers = new JButton("View Cashiers");
		btnViewCashiers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openCashiersFrame();
			}
		});
		panel.add(btnViewCashiers);
		
		btnService = new JButton("Service");
		btnService.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openServiceFrame();
				
			}
		});
		panel.add(btnService);
		
		btnRevenueAndLosses = new JButton("Revenue and Losses");
		btnRevenueAndLosses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openRevenueAndLossesFrame();
				
			}
		});
		panel.add(btnRevenueAndLosses);
		
		// Initialize the store
		
		bookStore = new BookStore("BookStoreOne", "Buxton blvd. 708, Sofia");
		try {
			bookStore.selectCashier("Viktor");
		} catch (CashierNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		pack();
		
	}
	
	// Button actions ----------------------------------------
	// -------------------------------------------------------
	
	private void openBooksFrame() {
		
		BooksTable booksTable = new BooksTable(this);
		booksTable.setVisible(true);
	}
	
	private void openCashiersFrame() {
		// TODO not yet implemented
	}
	
	private void openServiceFrame() {
		// TODO not yet implemented
	}
	
	private void openRevenueAndLossesFrame() {
		// TODO not yet implemented
	}
	
	public BookStore getStore() {
		return this.bookStore;
	}
	
	
	/*
	 * Window Listener Interface methods implementation
	 * For now the only useful methods are the WindowClosing(), 
	 * WindowOpened(), and WindowClosed() methods. 
	 * -------------------------------------------------------
	*/
	
	@Override
	public void windowOpened(WindowEvent e) {
		setVisible(false);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		e.getComponent().setVisible(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// Only show this window if the closed one is
		// the BooksTable frame
		if(e.getComponent() instanceof BooksTable) {
			setVisible(true);
			try {
				bookStore.saveDatabase();
			} catch (UnsuccessfullOperationStoreException e1) {
				JOptionPane.showMessageDialog(this, "Changes are not saved!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
				
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	
	}
}
