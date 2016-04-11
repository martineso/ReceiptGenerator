package store.ui.main;


import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import cashier.Cashier;
import store.ui.tables.model.CustomCashierTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class CashiersFrame extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private CustomCashierTableModel tableModel;
	private JButton btnDelete, btnAdd;
	private List<Cashier> cashiers;
	

	/**
	 * Create the frame.
	 */
	public CashiersFrame(MainFrame mainFrame) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		addWindowListener(mainFrame);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setToolTipText("Search");
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		tableModel = new CustomCashierTableModel();
		
		cashiers = mainFrame.getStore().getCashierList();
		tableModel.setCashiers(cashiers);
		table.setModel(tableModel);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		
		btnAdd = new JButton("Add");
		
		btnDelete = new JButton("Delete");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAdd)
							.addGap(6)
							.addComponent(btnDelete))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd)
						.addComponent(btnDelete)))
		);
		contentPane.setLayout(gl_contentPane);
		
		pack();
	}

}
