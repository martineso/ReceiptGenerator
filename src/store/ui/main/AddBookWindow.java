package store.ui.main;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import receipt.product.Book;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import store.ui.main.utilities.MyInputVerifier;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;

public class AddBookWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField authorTextField;
	private JTextField publisherTextField;
	private JTextField titleTextField;
	private JTextField priceTextField;
	private JTextField dateTextField;
	private JTextField quantityTextField;
	private JComboBox<String> isForeignComboBox;
	private JButton btnConfirm;
	private JButton btnCancel;
	private MyInputVerifier inputVerifier;
	private Book book;
	
	/**
	 * Create the frame.
	 */
	public AddBookWindow() {
		inputVerifier = new MyInputVerifier();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 38, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("Title");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		titleTextField = new JTextField();
		titleTextField.setName("titleField");
		titleTextField.setInputVerifier(inputVerifier);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(titleTextField, gbc_textField);
		titleTextField.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author");
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 1;
		contentPane.add(lblAuthor, gbc_lblAuthor);
		
		authorTextField = new JTextField();
		authorTextField.setName("authorField");
		authorTextField.setInputVerifier(inputVerifier);
		GridBagConstraints gbc_authorTextField = new GridBagConstraints();
		gbc_authorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_authorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_authorTextField.gridx = 1;
		gbc_authorTextField.gridy = 1;
		contentPane.add(authorTextField, gbc_authorTextField);
		authorTextField.setColumns(10);
		
		JLabel lblPublisher = new JLabel("Publisher");
		GridBagConstraints gbc_lblPublisher = new GridBagConstraints();
		gbc_lblPublisher.anchor = GridBagConstraints.WEST;
		gbc_lblPublisher.insets = new Insets(0, 0, 5, 5);
		gbc_lblPublisher.gridx = 0;
		gbc_lblPublisher.gridy = 2;
		contentPane.add(lblPublisher, gbc_lblPublisher);
		
		publisherTextField = new JTextField();
		publisherTextField.setName("publisherField");
		publisherTextField.setInputVerifier(inputVerifier);
		GridBagConstraints gbc_publisherTextField = new GridBagConstraints();
		gbc_publisherTextField.insets = new Insets(0, 0, 5, 0);
		gbc_publisherTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_publisherTextField.gridx = 1;
		gbc_publisherTextField.gridy = 2;
		contentPane.add(publisherTextField, gbc_publisherTextField);
		publisherTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 3;
		contentPane.add(lblDate, gbc_lblDate);
		
		
		dateTextField = new JFormattedTextField();
		dateTextField.setName("dateField");
		dateTextField.setInputVerifier(inputVerifier);
		GridBagConstraints gbc_dateTextField = new GridBagConstraints();
		gbc_dateTextField.insets = new Insets(0, 0, 5, 0);
		gbc_dateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateTextField.gridx = 1;
		gbc_dateTextField.gridy = 3;
		contentPane.add(dateTextField, gbc_dateTextField);
		
		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 4;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		quantityTextField = new JFormattedTextField();
		quantityTextField.setName("quantityField");
		quantityTextField.setInputVerifier(inputVerifier);
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 1;
		gbc_formattedTextField.gridy = 4;
		contentPane.add(quantityTextField, gbc_formattedTextField);
		
		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 5;
		contentPane.add(lblPrice, gbc_lblPrice);
		
		priceTextField = new JFormattedTextField();
		priceTextField.setName("priceField");
		priceTextField.setInputVerifier(inputVerifier);
		priceTextField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateConfirmButton();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateConfirmButton();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateConfirmButton();
				
			}
		});
		GridBagConstraints gbc_formattedTextField_1 = new GridBagConstraints();
		gbc_formattedTextField_1.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_1.gridx = 1;
		gbc_formattedTextField_1.gridy = 5;
		contentPane.add(priceTextField, gbc_formattedTextField_1);
		
		JLabel lblForeign = new JLabel("Foreign");
		GridBagConstraints gbc_lblForeign = new GridBagConstraints();
		gbc_lblForeign.anchor = GridBagConstraints.WEST;
		gbc_lblForeign.insets = new Insets(0, 0, 5, 5);
		gbc_lblForeign.gridx = 0;
		gbc_lblForeign.gridy = 6;
		contentPane.add(lblForeign, gbc_lblForeign);
		
		String[] options = {"Yes", "No"};
		isForeignComboBox = new JComboBox<String>(options);
		GridBagConstraints gbc_isForeignComboBox = new GridBagConstraints();
		gbc_isForeignComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_isForeignComboBox.anchor = GridBagConstraints.NORTH;
		gbc_isForeignComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_isForeignComboBox.gridx = 1;
		gbc_isForeignComboBox.gridy = 6;
		contentPane.add(isForeignComboBox, gbc_isForeignComboBox);
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 7;
		contentPane.add(buttonPanel, gbc_panel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setActionCommand("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				generateBook();
				dispose();
				
			}
		});
		btnConfirm.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonPanel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { dispose(); }
		});
		btnCancel.setAlignmentY(Component.TOP_ALIGNMENT);
		btnCancel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttonPanel.add(btnCancel);
		
		updateConfirmButton();
		pack();
		
	}
	
	public Book getBook() {
		return book;
	}
	
	private void generateBook() {
		String title = titleTextField.getText();
		String author = authorTextField.getText();
		String publisher = publisherTextField.getText();
		String issueDate = dateTextField.getText();
		double price = Double.valueOf(priceTextField.getText());
		int copies = Integer.valueOf(quantityTextField.getText());
		boolean isForeign = ((String) isForeignComboBox.getSelectedItem()).equals("Yes") ? true : false;
		
		// The field for the ID is left blank so that the Book object can generate a new unique ID
		// May implement a method to check if the book by this author and issue date already exists
		// so that there are no duplicate elements in the store
		
		this.book = new Book(title,author, publisher, issueDate, "", copies, price, isForeign);
	}
	
	private void updateConfirmButton() {
		
		btnConfirm.setEnabled(!priceTextField.getText().isEmpty());
	}
}
