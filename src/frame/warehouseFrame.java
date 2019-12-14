package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class warehouseFrame extends BaseFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118468043064503908L;
	private JTextField nameTextField;
	private JTextField volumeTextField;
	private JTabbedPane tabbedPane;
	private JTable deleteTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					warehouseFrame window = new warehouseFrame();
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
	public warehouseFrame() {
		initialize();
	}
	
	public void setTabSeq(int index){
		tabbedPane.setSelectedIndex(index);
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("仓库管理");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//添加仓库
		JPanel insertPanel = new JPanel();
		insertPanel.setLayout(null);
		tabbedPane.add("添加仓库",insertPanel);
		JLabel nameLabel = new JLabel("仓库名");
		nameLabel.setBounds(80, 25, 72, 18);
		insertPanel.add(nameLabel);
		
		JLabel volumeLabel = new JLabel("容量");
		volumeLabel.setBounds(80, 56, 72, 18);
		insertPanel.add(volumeLabel);
		
		
		nameTextField = new JTextField();
		nameTextField.setBounds(161, 22, 86, 24);
		insertPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		volumeTextField = new JTextField();
		volumeTextField.setBounds(161, 53, 86, 24);
		insertPanel.add(volumeTextField);
		volumeTextField.setColumns(10);
		
		JButton insertButton = new JButton("\u6DFB\u52A0");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertButtonActionPerformed();
			}
		});
		insertButton.setBounds(56, 191, 113, 27);
		insertPanel.add(insertButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed();
			}
		});
		cancelButton.setBounds(223, 191, 113, 27);
		insertPanel.add(cancelButton);
		
		//删除仓库
		JPanel deletePanel = new JPanel();
		tabbedPane.add("删除仓库",deletePanel);

		tabbedPane.addTab("案宗删除", deletePanel);
		deletePanel.setLayout(null);
		
		JButton deleteButton = new JButton("删除");
		deleteButton.setBounds(131, 173, 65, 23);
		deletePanel.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteButtonActionPerformed(e);
			}
		});
		
        Object[][] tableDate=new Object[0][4];
        String[] name={"物资名","数量","来源仓库号","目的仓库号"};
		deleteTable = new JTable(tableDate,name);
		deleteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deleteTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane_del = new JScrollPane();
		scrollPane_del.setBounds(14, 0, 336, 160);
		deletePanel.add(scrollPane_del);
		scrollPane_del.setViewportView(deleteTable);
		
	}

	protected void deleteButtonActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	protected void cancelButtonActionPerformed() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	protected void insertButtonActionPerformed() {
		// TODO Auto-generated method stub
		this.dispose();
	}
	
	private void updateDeleteInfoTotable() {
		try {
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
