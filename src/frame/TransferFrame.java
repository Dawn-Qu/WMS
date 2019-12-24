package frame;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import exception.CapacityException;
import exception.GoodsNotFoundException;
import service.DataProcessing;

public class TransferFrame extends RecordFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8633444409119831361L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferFrame window = new TransferFrame();
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
	public TransferFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("物资调转");
	}

	@Override
	protected void submissionButtonaActionPerformed() {
		// TODO Auto-generated method stub
		String ANo = JOptionPane.showInputDialog(this, "输入管理员号");
		//
		TableModel tableModel = recordTable.getModel();
		int r = tableModel.getRowCount();
		String[] GNo = new String[r];
		int[] amount = new int[r];
		String sourceWNo = (String) tableModel.getValueAt(0, 2);
		String destinyWNo = (String) tableModel.getValueAt(0, 3);
		for(int i=0;i<tableModel.getRowCount();i++) {
			GNo[i] = (String) tableModel.getValueAt(i, 0);
			amount[i] = (int)tableModel.getValueAt(i, 1);
		}
		try {
			DataProcessing.transfer(GNo, amount, sourceWNo, destinyWNo, ANo);
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
