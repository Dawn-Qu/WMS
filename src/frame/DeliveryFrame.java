package frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import exception.CapacityException;
import exception.GoodsNotFoundException;
import service.DataProcessing;

public class DeliveryFrame extends RecordFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 418226918487803326L;
	
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
		String adminNo = "";
		while(true) {
			try {
				Integer.parseInt((adminNo = JOptionPane.showInputDialog(this, "输入管理员号")));
				break;
			} catch (Exception e) {
				// TODO: handle exception
				if(adminNo == null)return;
				JOptionPane.showMessageDialog(this, "请输入整数的管理员号！!");
			}
		}
		//
		TableModel tableModel = recordTable.getModel();
		int r = tableModel.getRowCount();
		String[] GNo = new String[r];
		int[] amount = new int[r];
		String sourceWNo = (String) tableModel.getValueAt(0, 2);
		String destWNo = (String) tableModel.getValueAt(0, 3);
		for(int i=0;i<tableModel.getRowCount();i++) {
			GNo[i] = (String) tableModel.getValueAt(i, 0);
			amount[i] = Integer.parseInt((String)tableModel.getValueAt(i, 1));
		}
		try {
			DataProcessing.transfer(GNo,amount,sourceWNo,destWNo,adminNo);
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
