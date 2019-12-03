package wms;

import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class PurchaseFrame extends RecordFrame{

	private JLabel departmentNameLabel;
	private JTextPane departmentNameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseFrame window = new PurchaseFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PurchaseFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("采购");
	}
}
