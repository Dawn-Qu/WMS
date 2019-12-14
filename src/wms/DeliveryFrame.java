package wms;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class DeliveryFrame extends RecordFrame{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeliveryFrame window = new DeliveryFrame();
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
	public DeliveryFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("出库");
	}

	@Override
	protected void submissionButtonaActionPerformed() {
		// TODO Auto-generated method stub
		
	}

}
