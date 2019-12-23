package frame;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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
		String DNo = "";
		DNo = JOptionPane.showInputDialog(this, "输入部门号");
		
		//
		TableModel tableModel = recordTable.getModel();
		int r = tableModel.getRowCount();
		String[] GNo = new String[r];
		int[] amount = new int[r];
		for(int i=0;i<tableModel.getRowCount();i++) {
			GNo[i] = (String) tableModel.getValueAt(i, 0);
			amount[i] = (int)tableModel.getValueAt(i, 1);
		}
		String destWNo = (String)tableModel.getValueAt(0, 3);
		try {
			DataProcessing.purchase(GNo, amount, destWNo, DNo);
			yesMessage("采购成功！");
		} catch (CapacityException e) {
			// TODO Auto-generated catch block
			errInWindow(e);
		} catch (GoodsNotFoundException e) {
			// TODO Auto-generated catch block
			errInWindow(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errInWindow(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
