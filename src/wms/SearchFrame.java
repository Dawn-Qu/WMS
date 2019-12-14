package wms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class SearchFrame extends BaseFrame{


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame window = new SearchFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTabbedPane tabbedPane;
	private JTextField warehouseNameInWHTextField;
	private JTextField warehouseNumberInWHTextField;
	private JTable warehouseSearchTable,goodsSearchTable,recordSearchTable;
	private JTextField goodsNameTextInGoodsField;
	private JTextField goodsNumberInGoodsTextField;
	private JTextField reordNumberInRecordTextField;
	private JTextField warehouseNameInGoodsTextField;
	private JTextField warehouseNumberInGoodsTextField;
	private JTextField goodsNumberInRecordTextField;
	private JTextField warehouseNumberInRecordTextField;
	private JTextField timeInRecordTextField;
	private JTextField usageInRecordTextField;

	/**
	 * Create the application.
	 */
	public SearchFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("查询");
		setBounds(100, 100, 449, 339);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//仓库查询
		JPanel warehouseSearchPanel = new JPanel();
		warehouseSearchPanel.setLayout(null);
		tabbedPane.add("仓库查询",warehouseSearchPanel);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(56, 13, 282, 71);
		warehouseSearchPanel.add(inputPanel);
		inputPanel.setLayout(null);
		JLabel warehouseNameInWHLabel = new JLabel("仓库名");
		warehouseNameInWHLabel.setBounds(14, 13, 45, 18);
		inputPanel.add(warehouseNameInWHLabel);
		
		
		warehouseNameInWHTextField = new JTextField();
		warehouseNameInWHTextField.setBounds(92, 10, 86, 24);
		inputPanel.add(warehouseNameInWHTextField);
		warehouseNameInWHTextField.setColumns(10);
		
		JLabel warehouseNumberInWHLabel = new JLabel("仓库号");
		warehouseNumberInWHLabel.setBounds(14, 44, 72, 18);
		inputPanel.add(warehouseNumberInWHLabel);
		
		warehouseNumberInWHTextField = new JTextField();
		warehouseNumberInWHTextField.setBounds(92, 41, 86, 24);
		inputPanel.add(warehouseNumberInWHTextField);
		warehouseNumberInWHTextField.setColumns(10);
		
		JButton warehouseSearchButton = new JButton("查询");
		warehouseSearchButton.setBounds(192, 23, 76, 27);
		inputPanel.add(warehouseSearchButton);
		warehouseSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed();
			}
		});
		
		Object[][] tableDate=new Object[0][4];
		String[] name= {"仓库号","仓库名","总容量","可用容量"};
		warehouseSearchTable = new JTable(tableDate,name);
		warehouseSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		warehouseSearchTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 102, 399, 158);
		scrollPane.setViewportView(warehouseSearchTable);
		warehouseSearchPanel.add(scrollPane);

		
		//物资查询
		JPanel goodsSearchPanel = new JPanel();
		tabbedPane.add("物资查询",goodsSearchPanel);

		tabbedPane.addTab("物资查询", goodsSearchPanel);
		goodsSearchPanel.setLayout(null);
		
        Object[][] tableDate1=new Object[0][5];
        String[] name1={"物资名","物资号","数量","仓库号","仓库名"};
		goodsSearchTable = new JTable(tableDate1,name1);
		goodsSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		goodsSearchTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(14, 97, 399, 158);
		goodsSearchPanel.add(scrollPane1);
		scrollPane1.setViewportView(goodsSearchTable);
		
		JPanel inputPanel1 = new JPanel();
		inputPanel1.setLayout(null);
		inputPanel1.setBounds(14, 13, 388, 71);
		goodsSearchPanel.add(inputPanel1);
		
		JLabel goodsNameInGoodsLabel = new JLabel("物资名");
		goodsNameInGoodsLabel.setBounds(14, 13, 45, 18);
		inputPanel1.add(goodsNameInGoodsLabel);
		
		goodsNameTextInGoodsField = new JTextField();
		goodsNameTextInGoodsField.setColumns(10);
		goodsNameTextInGoodsField.setBounds(73, 10, 50, 24);
		inputPanel1.add(goodsNameTextInGoodsField);
		
		JLabel goodsNumberInGoodsLabel = new JLabel("物资号");
		goodsNumberInGoodsLabel.setBounds(14, 44, 72, 18);
		inputPanel1.add(goodsNumberInGoodsLabel);
		
		goodsNumberInGoodsTextField = new JTextField();
		goodsNumberInGoodsTextField.setColumns(10);
		goodsNumberInGoodsTextField.setBounds(73, 41, 50, 24);
		inputPanel1.add(goodsNumberInGoodsTextField);
		
		JButton goodsSearchButton = new JButton("查询");
		goodsSearchButton.setBounds(298, 9, 76, 27);
		inputPanel1.add(goodsSearchButton);
		
		JLabel warehouseNameInGoodsLabel = new JLabel("仓库名");
		warehouseNameInGoodsLabel.setBounds(131, 13, 45, 18);
		inputPanel1.add(warehouseNameInGoodsLabel);
		
		JLabel warehouseNumberInGoodsLabel = new JLabel("仓库号");
		warehouseNumberInGoodsLabel.setBounds(131, 44, 45, 18);
		inputPanel1.add(warehouseNumberInGoodsLabel);
		
		warehouseNameInGoodsTextField = new JTextField();
		warehouseNameInGoodsTextField.setColumns(10);
		warehouseNameInGoodsTextField.setBounds(185, 10, 50, 24);
		inputPanel1.add(warehouseNameInGoodsTextField);
		
		warehouseNumberInGoodsTextField = new JTextField();
		warehouseNumberInGoodsTextField.setColumns(10);
		warehouseNumberInGoodsTextField.setBounds(185, 41, 50, 24);
		inputPanel1.add(warehouseNumberInGoodsTextField);
		
		//记录单查询
		JPanel recordSearchPanel = new JPanel();
		recordSearchPanel.setLayout(null);
		tabbedPane.addTab("记录单查询", null, recordSearchPanel, null);
		
		JPanel inputpanel2 = new JPanel();
		inputpanel2.setLayout(null);
		inputpanel2.setBounds(14, 13, 375, 71);
		recordSearchPanel.add(inputpanel2);
		
		JLabel reordNumberInRecordLabel = new JLabel("记录号");
		reordNumberInRecordLabel.setBounds(14, 16, 45, 18);
		inputpanel2.add(reordNumberInRecordLabel);
		
		reordNumberInRecordTextField = new JTextField();
		reordNumberInRecordTextField.setColumns(10);
		reordNumberInRecordTextField.setBounds(73, 13, 45, 24);
		inputpanel2.add(reordNumberInRecordTextField);
		
		JButton recordSearchButton = new JButton("查询");
		recordSearchButton.setBounds(268, 46, 76, 27);
		inputpanel2.add(recordSearchButton);
		
		JLabel goodsNumberInRecordlabel = new JLabel("物资号");
		goodsNumberInRecordlabel.setBounds(14, 50, 45, 18);
		inputpanel2.add(goodsNumberInRecordlabel);
		
		goodsNumberInRecordTextField = new JTextField();
		goodsNumberInRecordTextField.setColumns(10);
		goodsNumberInRecordTextField.setBounds(73, 47, 45, 24);
		inputpanel2.add(goodsNumberInRecordTextField);
		
		JLabel warehouseNumberInRecordLabel = new JLabel("仓库号");
		warehouseNumberInRecordLabel.setBounds(127, 16, 45, 18);
		inputpanel2.add(warehouseNumberInRecordLabel);
		
		warehouseNumberInRecordTextField = new JTextField();
		warehouseNumberInRecordTextField.setColumns(10);
		warehouseNumberInRecordTextField.setBounds(181, 13, 50, 24);
		inputpanel2.add(warehouseNumberInRecordTextField);
		
		JLabel timeInRecordLabel = new JLabel("时间");
		timeInRecordLabel.setBounds(245, 16, 45, 18);
		inputpanel2.add(timeInRecordLabel);
		
		timeInRecordTextField = new JTextField();
		timeInRecordTextField.setColumns(10);
		timeInRecordTextField.setBounds(278, 13, 50, 24);
		inputpanel2.add(timeInRecordTextField);
		
		JLabel usageInRecordLabel = new JLabel("用途");
		usageInRecordLabel.setBounds(132, 50, 45, 18);
		inputpanel2.add(usageInRecordLabel);
		
		usageInRecordTextField = new JTextField();
		usageInRecordTextField.setColumns(10);
		usageInRecordTextField.setBounds(181, 47, 50, 24);
		inputpanel2.add(usageInRecordTextField);
		
        Object[][] tableDate2=new Object[0][6];
        String[] name2={"记录单","物资号","来源仓库号","数量","时间","用途"};
		recordSearchTable = new JTable(tableDate2,name2);
		recordSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recordSearchTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(14, 97, 401, 158);
		recordSearchPanel.add(scrollPane2);
		scrollPane2.setViewportView(recordSearchTable);
	}

	protected void cancelButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void setTabSeq(int index){
		tabbedPane.setSelectedIndex(index);
	}
}
