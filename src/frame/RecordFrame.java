package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.*;

public abstract class RecordFrame extends BaseFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5356266414968084407L;
	protected JTable recordTable;
	private OneRecordFrame oneRecordFrame = new OneRecordFrame(this);
	protected TableDataManegment tableDataManegment;
	private String[] tableNames;

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
        
        tableDataManegment = new TableDataManegment(5, 4);
        tableNames = new String[] {"物资名","数量","来源仓库号","目的仓库号"};
        recordTable=new JTable(tableDataManegment.tableData,tableNames);
        contentPane.add(new JScrollPane(recordTable));
        
        JPanel buttonPanel=new JPanel();
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
	

	
	private void insertButtonActionPerformed() {
		oneRecordFrame.setVisible(true);
	}
	
	private void cancelButtonActionPerformed() {
		this.dispose();
	}
	
	protected abstract void submissionButtonaActionPerformed();
	
	public void insert(Record record) {
		tableDataManegment.addRow(record.toStringArray());
		updateTable();
	}
	
	private void updateTable() {
		recordTable.setModel(new DefaultTableModel(tableDataManegment.tableData,tableNames));
	}

}

class TableDataManegment{
	String[][] tableData;
	int i=0,j=0;
	
	public TableDataManegment(int r,int c) {
		// TODO Auto-generated constructor stub
		tableData = new String[r][c];
	}
	
	public void addRow(String ... params) {
		for(String param : params) {
			tableData[i][j++] = param;
		}
		i++;
		j=0;
	}
}
