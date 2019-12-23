package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import model.*;

public abstract class RecordFrame extends BaseFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5356266414968084407L;
	protected JTable recordTable;
	private OneRecordFrame oneRecordFrame = new OneRecordFrame(this);
	protected showTableModel recordTableModel;
	private String[] tableNames;

	protected JButton personButton;
	protected JPanel buttonPanel;
	/**
	 * Create the application.
	 */
	public RecordFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("\u8BB0\u5F55\u5355");
		setBounds(100, 100, 450, 300);
		movePositionToCenter();
		
        Container contentPane=getContentPane();
        
        recordTableModel = showTableModel.recordTableModel;
        recordTable=new JTable(recordTableModel);
        contentPane.add(new JScrollPane(recordTable));
        
        buttonPanel = new JPanel();
        contentPane.add(buttonPanel,BorderLayout.SOUTH);
		
        recordTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton insertButton = new JButton("插入");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertButtonActionPerformed();
			}
		});
		buttonPanel.add(insertButton);
		
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonActionPerformed();
			}
		});
		buttonPanel.add(deleteButton);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed();
			}
		});
		buttonPanel.add(cancelButton);
		
		JButton submissionButton = new JButton("提交");
		submissionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submissionButtonaActionPerformed();
			}
		});
		submissionButton.setBounds(240, 193, 63, 27);
		buttonPanel.add(submissionButton);		
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
//		showUserInfoToTable();
	}
	

	
	protected void deleteButtonActionPerformed() {
		// TODO Auto-generated method stub
		int index = recordTable.getSelectedRow();
		delete(index);
	}

	private void insertButtonActionPerformed() {
		oneRecordFrame.setVisible(true);
	}
	
	private void cancelButtonActionPerformed() {
		this.dispose();
	}
	
	protected abstract void submissionButtonaActionPerformed();
	
	public void insert(Record record) {
		recordTableModel.addRow(record.flat());
	}
	
	public void delete(int index) {
		recordTableModel.delRow(index);
	}
	
}


