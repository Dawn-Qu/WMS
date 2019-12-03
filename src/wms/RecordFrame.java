package wms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class RecordFrame extends BaseFrame{


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordFrame window = new RecordFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTable recordTable;
	private OneRecordFrame oneRecordFrame = new OneRecordFrame();

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
        
        Object[][] tableDate=new Object[0][4];
        String[] name={"物资名","数量","来源仓库号","目的仓库号"};
        recordTable=new JTable(tableDate,name);
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
	
	private void submissionButtonaActionPerformed() {
		this.dispose();
	}
}
