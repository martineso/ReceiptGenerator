package store.ui.main;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import store.BookStore;
import store.exceptions.UnsuccessfullOperationStoreException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ReceiptWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JEditorPane editorPane;
	private JPanel contentPane;
	private JButton printButton;
	private BookStore bookStore;
	
	/**
	 * Create the frame
	 */
	public ReceiptWindow(BookStore bookStore) {
	
		this.bookStore = bookStore;
		this.bookStore.generateReceipt();
		
		setMinimumSize(new Dimension(600, 420));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(600, 450));
		setContentPane(contentPane);
		
		editorPane = new JTextPane();
		editorPane.setMinimumSize(new Dimension(600, 350));
		editorPane.setPreferredSize(new Dimension(600, 350));
		editorPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		editorPane.setEditable(false);
		
		editorPane.setText(bookStore.getLastReceipt().generateReceiptAsString());
		contentPane.add(editorPane);
		
		printButton = new JButton("Print");
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				writeReceiptToFile();	
			}
		});
		contentPane.add(printButton);
		
	}
	
	private void writeReceiptToFile() {
		
		try {
		
			bookStore.writeLastReceiptToFile();
			JOptionPane.showMessageDialog(this, "Receipt successfully printed!", "", JOptionPane.INFORMATION_MESSAGE);
		
		} catch (UnsuccessfullOperationStoreException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			dispose();
		}
	}
}
