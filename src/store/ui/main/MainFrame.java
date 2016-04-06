package store.ui.main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import net.miginfocom.swing.MigLayout;
import store.BookStore;
import store.exceptions.CashierNotFoundException;
import store.exceptions.UnsuccessfullOperationStoreException;
import receipt.product.Product;
import java.util.List;
import store.ui.tables.BooksTable;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class MainFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BooksTable booksFrame;
	private BookStore bookStore;

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
//		contentPane.setMinimumSize(new Dimension(640, 480));
		contentPane.getPreferredSize();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		bookStore = new BookStore("BookStoreOne", "Buxton, 49");
		try {
			bookStore.selectCashier("Viktor");
		} catch (CashierNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		JButton viewBooksButton = new JButton("View Books");
		viewBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { openBooksFrame(); }
		});
		
		JButton viewCashiersButton = new JButton("View Cashiers");
		
		JButton viewReceiptsButton = new JButton("View Receipts");
		
		JButton revenueAndLossesButton = new JButton("Revenue and Losses");
		
		JSeparator separator = new JSeparator();
		contentPane.setLayout(new MigLayout("", "[pref!][200px]", "[300px][2px][62px][62px]"));
		
		JPanel logoBorderPanel = new JPanel();
		logoBorderPanel.setName("");
		logoBorderPanel.setToolTipText("\n");
		logoBorderPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(logoBorderPanel, "cell 0 0 2 1,grow");
		logoBorderPanel.setLayout(new MigLayout("", "[pref!][pref!,fill][200px]", "[300px]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		logoBorderPanel.add(lblNewLabel, "cell 0 0 3 1,alignx center,aligny center");
		lblNewLabel.setIcon(new ImageIcon("res/LogoMainv2.jpg"));
		contentPane.add(separator, "cell 0 1 2 1,growx,aligny center");
		contentPane.add(viewCashiersButton, "cell 0 3,alignx left,growy");
		contentPane.add(viewReceiptsButton, "cell 1 3,grow");
		contentPane.add(viewBooksButton, "cell 0 2,grow");
		contentPane.add(revenueAndLossesButton, "cell 1 2,grow");
		
		pack();
		
	}
	
	private void openBooksFrame() {
		
		booksFrame = new BooksTable(this);
		booksFrame.setVisible(true);
		
	}
	
	protected List<? extends Product> getStoreProductsList() {
		return bookStore.getProductsList();
	}
	
	public BookStore getStore() {
		return this.bookStore;
	}

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
				
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
