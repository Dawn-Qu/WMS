package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import com.sun.tools.javac.code.Attribute.Array;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SearchFrame extends BaseFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3018385256079047929L;

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
	private JTextField timeSrcInRecordTextField,timeDestInRecordTextField;

	/**
	 * Create the application.
	 */
	public SearchFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		inputpanel2.setBounds(14, 13, 410, 71);
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
		warehouseNumberInRecordTextField.setBounds(181, 13, 61, 24);
		inputpanel2.add(warehouseNumberInRecordTextField);
		
		JLabel timeInRecordLabel = new JLabel("时间");
		timeInRecordLabel.setBounds(245, 16, 45, 18);
		inputpanel2.add(timeInRecordLabel);
		
		timeSrcInRecordTextField = new JTextField("2019.7.28");
		timeSrcInRecordTextField.setColumns(10);
		timeSrcInRecordTextField.setBounds(270, 13, 60, 24);
		inputpanel2.add(timeSrcInRecordTextField);
		
		JLabel arrowLabel = new JLabel("到");
		arrowLabel.setBounds(330, 13, 20, 10);
		inputpanel2.add(arrowLabel);
		
		timeDestInRecordTextField = new JTextField("2019.8.28");
		timeDestInRecordTextField.setColumns(10);
		timeDestInRecordTextField.setBounds(343, 13, 60, 24);
		inputpanel2.add(timeDestInRecordTextField);
		
		JLabel usageInRecordLabel = new JLabel("用途");
		usageInRecordLabel.setBounds(132, 50, 45, 18);
		inputpanel2.add(usageInRecordLabel);
		
		JComboBox usageInRecordcomboBox = new JComboBox();
		usageInRecordcomboBox.setModel(new DefaultComboBoxModel(frame.Usage.values()));
		usageInRecordcomboBox.setBounds(181, 47, 61, 24);
		usageInRecordcomboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				usageInRecordcomboBoxActionPerformed(e);
			}
		});
		inputpanel2.add(usageInRecordcomboBox);
		

		recordSearchTable = new JTable(RecordTableModel.purchaseTableModel);
		recordSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recordSearchTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(14, 97, 401, 158);
		recordSearchPanel.add(scrollPane2);
		scrollPane2.setViewportView(recordSearchTable);
	}

	protected void usageInRecordcomboBoxActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch ((Usage)((JComboBox)e.getSource()).getSelectedObjects()[0]) {
		case PURCHASE:
			recordSearchTable.setModel(RecordTableModel.purchaseTableModel);
			break;
		case TRANSFER:
			recordSearchTable.setModel(RecordTableModel.transferTableModel);
			break;
		case DELIVERY:
			recordSearchTable.setModel(RecordTableModel.deliveryTableModel);
			break;
		}
	}

	protected void cancelButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void setTabSeq(int index){
		tabbedPane.setSelectedIndex(index);
	}
}

enum Usage{
	PURCHASE("采购"),TRANSFER("转移"),DELIVERY("出售");
	
	String meaning;
	
	private Usage(String meaning) {
		// TODO Auto-generated constructor stub
		this.meaning = meaning;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return meaning;
	}
}


class RecordTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8762197853686994754L;
	
	public static final RecordTableModel purchaseTableModel = 
			new RecordTableModel(Arrays.asList("记录号","部门号","物资号","物资名","物资数量","目的仓库号","时间"));

	public static final RecordTableModel transferTableModel = 
			new RecordTableModel(Arrays.asList("记录号","物资号","物资名","物资数量","来源仓库号","目的仓库号","时间"));

	public static final RecordTableModel deliveryTableModel = 
			new RecordTableModel(Arrays.asList("记录号","客户号","物资号","物资名","物资数量","来源仓库号","时间"));

	
	public RecordTableModel(List<String> columnNameList) {
		// TODO Auto-generated constructor stub
		this.columnNames = columnNameList.toArray(new String[] {});
		this.data = new ArrayList<>();
	}

	private String[] columnNames;
	private List<List<Object>> data;

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data.get(row).get(col);
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for each
	 * cell. If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
	 */
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
//Note that the data/cell address is constant,
//no matter where the cell appears onscreen.
		return false;
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		List<Object> list = data.get(row);
		list.set(col, value);
		fireTableCellUpdated(row, col);
	}
	
	public void addRow(Object ... params) {
		List<Object> list = new ArrayList<>(Arrays.asList(params));
		data.add(list);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}
}