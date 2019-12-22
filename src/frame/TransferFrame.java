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
		String clientNo = "";
		while(true) {
			try {
				Integer.parseInt((clientNo = JOptionPane.showInputDialog(this, "输入客户号")));
				break;
			} catch (Exception e) {
				// TODO: handle exception
				if(clientNo == null)return;
				JOptionPane.showMessageDialog(this, "请输入整数的客户号！!");
			}
		}
		//
		TableModel tableModel = recordTable.getModel();
		int r = tableModel.getRowCount();
		String[] GNo = new String[r];
		int[] amount = new int[r];
		String sourceWNo = (String) tableModel.getValueAt(0, 2);
		for(int i=0;i<tableModel.getRowCount();i++) {
			GNo[i] = (String) tableModel.getValueAt(i, 0);
			amount[i] = Integer.parseInt((String)tableModel.getValueAt(i, 1));
		}
		try {
			DataProcessing.sell(sourceWNo,clientNo,GNo,amount);
		} catch (CapacityException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(),"出错反馈",JOptionPane.YES_NO_OPTION);
		} catch (GoodsNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(),"出错反馈",JOptionPane.YES_NO_OPTION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(),"出错反馈",JOptionPane.YES_NO_OPTION);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
