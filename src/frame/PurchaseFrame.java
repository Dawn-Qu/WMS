package frame;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.table.TableModel;

import exception.*;
import service.DataProcessing;

public class PurchaseFrame extends RecordFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5674715713388223162L;

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

	@Override
	protected void submissionButtonaActionPerformed() {
		// TODO Auto-generated method stub
		TableModel tableModel = recordTable.getModel();
		int r = tableModel.getRowCount();
		String[] GNo = new String[r];
		int[] amount = new int[r];
		String DNo = (String) tableModel.getValueAt(0, 3);
		for(int i=0;i<tableModel.getRowCount();i++) {
			GNo[i] = (String) tableModel.getValueAt(i, 0);
			amount[i] = Integer.parseInt((String)tableModel.getValueAt(i, 1));
		}
		try {
			DataProcessing.purchase(GNo, amount, "", DNo);
		} catch (CapacityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GoodsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
