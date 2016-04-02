package store.ui.main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import store.ui.tables.BooksTable;

import javax.swing.ImageIcon;

public class MainFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BooksTable booksFrame;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 397);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(640, 480));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton viewBooksButton = new JButton("View Books");
		viewBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) { openBooksFrame(); }
		});
		JButton viewReceiptsButton = new JButton("View Receipts");
		
		JButton viewCashiersButton = new JButton("View Cashiers");
		
		JButton revenueAndLossesButton = new JButton("Revenue and Losses");
		
		JSeparator separator = new JSeparator();
		contentPane.setLayout(new MigLayout("", "[pref!][pref!,fill][200px]", "[300px][2px][62px][62px]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/marti/Desktop/java/NBU/ReceiptGenerator/res/LogoMainv2.jpg"));
		lblNewLabel.setForeground(new Color(204, 0, 0));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 35));
		contentPane.add(lblNewLabel, "cell 0 0 3 1,alignx center,aligny center");
		contentPane.add(separator, "cell 0 1 3 1,growx,aligny center");
		contentPane.add(viewReceiptsButton, "cell 0 3,alignx left,growy");
		contentPane.add(viewCashiersButton, "cell 2 3,grow");
		contentPane.add(viewBooksButton, "cell 0 2,grow");
		contentPane.add(revenueAndLossesButton, "cell 2 2,grow");
		
		//addWindowListener(this);
	}
	
	private void openBooksFrame() {
		
		booksFrame = new BooksTable(this);
		booksFrame.setVisible(true);
		setVisible(false);
		
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		setVisible(true);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
