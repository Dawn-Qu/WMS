package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import model.DeliveryInfo;
import service.DataProcessing;
import view.OrderView;
import view.PurchaseView;
import view.StockView;
import view.TransferView;
import view.WarehouseCapacityView;

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
	private JTextField srcWarehouseNumberInRecordTextField;
	private JTextField destWarehouseNumberInRecordTextField;
	private JTextField timeSrcInRecordTextField,timeDestInRecordTextField;
	private JComboBox<?> usageInRecordcomboBox;

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
				warehouseSearchButtonActionPerformed();
			}
		});

		
		warehouseSearchTable = new JTable(showTableModel.warehouseSearchTableModel);
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
		
		goodsSearchTable = new JTable(showTableModel.goodsSearchTableModel);
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
		goodsSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				goodsSearchButtonActionPerformed();
			}
		});
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
		recordSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				recordSearchButtonActionPerformed();
			}
		});
		inputpanel2.add(recordSearchButton);
		
		JLabel goodsNumberInRecordlabel = new JLabel("物资号");
		goodsNumberInRecordlabel.setBounds(14, 50, 45, 18);
		inputpanel2.add(goodsNumberInRecordlabel);
		
		goodsNumberInRecordTextField = new JTextField();
		goodsNumberInRecordTextField.setColumns(10);
		goodsNumberInRecordTextField.setBounds(73, 47, 45, 24);
		inputpanel2.add(goodsNumberInRecordTextField);
		
		JLabel srcWarehouseNumberInRecordLabel = new JLabel("来源");
		srcWarehouseNumberInRecordLabel.setBounds(127, 0, 45, 25);
		inputpanel2.add(srcWarehouseNumberInRecordLabel);
		
		srcWarehouseNumberInRecordTextField = new JTextField();
		srcWarehouseNumberInRecordTextField.setColumns(10);
		srcWarehouseNumberInRecordTextField.setBounds(181, 0, 61, 20);
		inputpanel2.add(srcWarehouseNumberInRecordTextField);
		
		JLabel destWarehouseNumberInRecordLabel = new JLabel("目的");
		destWarehouseNumberInRecordLabel.setBounds(127, 20, 45, 25);
		inputpanel2.add(destWarehouseNumberInRecordLabel);
		
		destWarehouseNumberInRecordTextField = new JTextField();
		destWarehouseNumberInRecordTextField.setColumns(10);
		destWarehouseNumberInRecordTextField.setBounds(181, 20, 61, 20);
		inputpanel2.add(destWarehouseNumberInRecordTextField);
		
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
		
		usageInRecordcomboBox = new JComboBox();
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
		

		recordSearchTable = new JTable(showTableModel.purchaseRecordTableModel);
		recordSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recordSearchTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(14, 97, 401, 158);
		recordSearchPanel.add(scrollPane2);
		scrollPane2.setViewportView(recordSearchTable);
	}

	protected void recordSearchButtonActionPerformed() {
		// TODO Auto-generated method stub
		String inputSrcTime = timeSrcInRecordTextField.getText();
		String inputDestTime = timeSrcInRecordTextField.getText();
		
		String[] srcTime = inputSrcTime.split("\\.");
		String[] destTime = inputDestTime.split("\\.");
		
		@SuppressWarnings("deprecation")
		Timestamp srcTimestamp = new Timestamp(
				Integer.parseInt(srcTime[0]),
				Integer.parseInt(srcTime[1]), 
				Integer.parseInt(srcTime[2]), 
				0, 0, 0, 0);
		
		@SuppressWarnings("deprecation")
		Timestamp destTimestamp = new Timestamp(
				Integer.parseInt(destTime[0]),
				Integer.parseInt(destTime[1]), 
				Integer.parseInt(destTime[2]), 
				0, 0, 0, 0);
		
		Usage usage = (Usage) usageInRecordcomboBox.getSelectedItem();
		String gNo = goodsNumberInRecordTextField.getText();
		String cNo = reordNumberInRecordTextField.getText();
		String sourceWNo = srcWarehouseNumberInRecordTextField.getText();
		String destWNo = destWarehouseNumberInRecordTextField.getText();
		try {
			List<?> list = DataProcessing.getRecordDetail(
					srcTimestamp, 
					destTimestamp, 
					usage.toString().toCharArray(), 
					gNo.toCharArray(), 
					cNo.toCharArray(), 
					sourceWNo.toCharArray(), 
					destWNo.toCharArray());
			switch (usage) {
			case PURCHASE:
				for(Object purchaseView : list) {
					//"记录号","部门号","物资号","物资名","物资数量","目的仓库号","时间"
					PurchaseView pView = (PurchaseView)purchaseView;
					showTableModel.purchaseRecordTableModel
					.addRow(new Object[] {
							pView.getRNo(),
							pView.getDNo(),
							pView.getGNo(),
							pView.getGName(),
							pView.getAmount(),
							pView.getSourceWNo(),
							pView.getRTime()
					});
				}
				break;
			case TRANSFER:
				for(Object transferView : list) {
					//"记录号","物资号","物资名","物资数量","来源仓库号","目的仓库号","时间"
					TransferView tView = (TransferView)transferView;
					showTableModel.purchaseRecordTableModel
					.addRow(new Object[] {
							tView.getRNo(),
							tView.getGNo(),
							tView.getGName(),
							tView.getAmount(),
							tView.getAmount(),
							tView.getSourceWNo(),
							tView.getDestWNo(),
							tView.getRTime()
					});
				}
				break;
			case DELIVERY:
				for(Object orderView : list) {
					//"记录号","客户号","物资号","物资名","物资数量","来源仓库号","时间"
					OrderView oView = (OrderView)orderView;
					showTableModel.purchaseRecordTableModel
					.addRow(new Object[] {
							oView.getRNo(),
							oView.getClientNo(),
							oView.getGNo0(),
							oView.getGName(),
							oView.getAmount(),
							oView.getSourceWNo(),
							oView.getRtime()
					});
				}
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errInWindow(e);
		}
	}

	protected void goodsSearchButtonActionPerformed() {
		// TODO Auto-generated method stub
//		String goodsName = goodsNameTextInGoodsField.getText();
		String goodsNum = goodsNumberInGoodsTextField.getText();
//		String warehouseName = warehouseNameInGoodsTextField.getText();
		String warehouseNum = warehouseNumberInGoodsTextField.getText();
		
		try {
			List<StockView> list = DataProcessing.getStockView(goodsNum, warehouseNum);
			for(StockView stockView : list) {
				showTableModel.goodsSearchTableModel
				.addRow(new Object[] {
						stockView.getGNo(),
						stockView.getGName(),
						stockView.getAmount(),
						stockView.getWNo(),
						stockView.getWName()
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errInWindow(e);
		}
	}

	protected void warehouseSearchButtonActionPerformed() {
		// TODO Auto-generated method stub
		String name = warehouseNameInWHTextField.getText();
		String num = warehouseNameInWHTextField.getText();
		
		try {
			List<WarehouseCapacityView> list = DataProcessing.getWarehouseCapacityView(name.toCharArray(), num.toCharArray());
			for(WarehouseCapacityView warehouseCapacityView : list) {
				showTableModel.warehouseSearchTableModel
				.addRow(new Object[] {
						warehouseCapacityView.getWNo(),
						warehouseCapacityView.getWName(),
						warehouseCapacityView.getCapacity(),
						warehouseCapacityView.getExcessCapacity()
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errInWindow(e);
		}
	}

	protected void usageInRecordcomboBoxActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch ((Usage)((JComboBox<?>)e.getSource()).getSelectedObjects()[0]) {
		case PURCHASE:
			recordSearchTable.setModel(showTableModel.purchaseRecordTableModel);
			break;
		case TRANSFER:
			recordSearchTable.setModel(showTableModel.transferRecordTableModel);
			break;
		case DELIVERY:
			recordSearchTable.setModel(showTableModel.deliveryRecordTableModel);
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

/**
 * record usage
 * @author 90946
 *
 */
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

/**
 * use this class to create a local table model
 * it defines several table model used by frame package
 * this table model is used to show
 * @author 90946
 *
 */
class showTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8762197853686994754L;
	
	
	
	public static final showTableModel recordTableModel = 
	new showTableModel(Arrays.asList("物资号","数量","来源仓库号","目的仓库号"));
	
	public static final showTableModel warehouseSearchTableModel = 
			new showTableModel(Arrays.asList("仓库号","仓库名","总容量","可用容量"));
	
	public static final showTableModel goodsSearchTableModel = 
	new showTableModel(Arrays.asList("物资号","物资名","数量","仓库号","仓库名"));
	
	public static final showTableModel purchaseRecordTableModel = 
			new showTableModel(Arrays.asList("记录号","部门号","物资号","物资名","物资数量","目的仓库号","时间"));

	public static final showTableModel transferRecordTableModel = 
			new showTableModel(Arrays.asList("记录号","物资号","物资名","物资数量","来源仓库号","目的仓库号","时间"));

	public static final showTableModel deliveryRecordTableModel = 
			new showTableModel(Arrays.asList("记录号","客户号","物资号","物资名","物资数量","来源仓库号","时间"));

	/**
	 * make a empty table model by column name
	 * @param columnNameList
	 */
	public showTableModel(List<String> columnNameList) {
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

	/**
	 * get a value in (row,col)
	 */
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
	/**
	 * set a value in (row,col)
	 */
	public void setValueAt(Object value, int row, int col) {
		List<Object> list = data.get(row);
		list.set(col, value);
		fireTableCellUpdated(row, col);
	}
	
	/**
	 * insert a row to this table
	 * @param params
	 */
	public void addRow(Object ... params) {
		List<Object> list = new ArrayList<>(Arrays.asList(params));
		data.add(list);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}
	
	/**
	 * delete a row in the specified index
	 * @param index
	 */
	public void delRow(int index) {
		Objects.checkIndex(index, getRowCount());
		data.remove(index);
		fireTableRowsDeleted(index, index);
	}
}