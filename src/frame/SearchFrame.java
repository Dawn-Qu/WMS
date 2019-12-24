package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import service.DataProcessing;
import view.OrderView;
import view.PurchaseView;
import view.StockView;
import view.TransferView;
import view.WarehouseCapacityView;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	private JTextField goodsNumberInGoodsTextField;
	private JTextField reordNumberInRecordTextField;
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
		setBounds(100, 100, 600, 300);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		 
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//仓库查询
		JPanel warehouseSearchPanel = new JPanel();
		warehouseSearchPanel.setLayout(new BoxLayout(warehouseSearchPanel, BoxLayout.Y_AXIS));
		tabbedPane.add("仓库查询",warehouseSearchPanel);
		
		JPanel inputPanel = new JPanel();
		warehouseSearchPanel.add(inputPanel);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		
		JLabel warehouseNameInWHLabel = new JLabel("仓库名");
		JLabel warehouseNumberInWHLabel = new JLabel("仓库号");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(warehouseNameInWHLabel);
		panel.add(warehouseNumberInWHLabel);
		inputPanel.add(panel);
		
		warehouseNameInWHTextField = new JTextField();
		warehouseNameInWHTextField.setMinimumSize(new Dimension(50,20));
		warehouseNameInWHTextField.setPreferredSize(new Dimension(50,20));
		warehouseNameInWHTextField.setMaximumSize(new Dimension(50,20));
		warehouseNumberInWHTextField = new JTextField();
		warehouseNumberInWHTextField.setMinimumSize(new Dimension(50,20));
		warehouseNumberInWHTextField.setPreferredSize(new Dimension(50,20));
		warehouseNumberInWHTextField.setMaximumSize(new Dimension(50,20));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setSize(new Dimension(50,50));
		panel2.add(warehouseNameInWHTextField);
		panel2.add(warehouseNumberInWHTextField);
		inputPanel.add(panel2);
		
		JButton warehouseSearchButton = new JButton("查询");
		inputPanel.add(warehouseSearchButton);
		warehouseSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warehouseSearchButtonActionPerformed();
			}
		});

		
		warehouseSearchTable = new JTable(ShowTableModel.warehouseSearchTableModel);
		warehouseSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		warehouseSearchTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(warehouseSearchTable);
		warehouseSearchPanel.add(scrollPane);

		
		//物资查询
		JPanel goodsSearchPanel = new JPanel();
		tabbedPane.add("物资查询",goodsSearchPanel);
		tabbedPane.addTab("物资查询", goodsSearchPanel);
		goodsSearchPanel.setLayout(new BoxLayout(goodsSearchPanel, BoxLayout.Y_AXIS));
	
		JPanel inputPanel1 = new JPanel();
		inputPanel1.setLayout(new BoxLayout(inputPanel1, BoxLayout.X_AXIS));
		goodsSearchPanel.add(inputPanel1);
		
		JLabel goodsNumberInGoodsLabel = new JLabel("物资号");
		JLabel warehouseNumberInGoodsLabel = new JLabel("仓库号");
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(goodsNumberInGoodsLabel);
		panel3.add(warehouseNumberInGoodsLabel);
		inputPanel1.add(panel3);
		
		goodsNumberInGoodsTextField = new JTextField();
		goodsNumberInGoodsTextField.setMinimumSize(new Dimension(50,20));
		goodsNumberInGoodsTextField.setPreferredSize(new Dimension(50,20));
		goodsNumberInGoodsTextField.setMaximumSize(new Dimension(100,20));
		warehouseNumberInGoodsTextField = new JTextField();
		warehouseNumberInGoodsTextField.setMinimumSize(new Dimension(50,20));
		warehouseNumberInGoodsTextField.setPreferredSize(new Dimension(50,20));
		warehouseNumberInGoodsTextField.setMaximumSize(new Dimension(100,20));
		JPanel panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4.add(goodsNumberInGoodsTextField);
		panel4.add(warehouseNumberInGoodsTextField);
		inputPanel1.add(panel4);
		
		JButton goodsSearchButton = new JButton("查询");
		goodsSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				goodsSearchButtonActionPerformed();
			}
		});
		inputPanel1.add(goodsSearchButton);
		
		
		goodsSearchTable = new JTable(ShowTableModel.goodsSearchTableModel);
		goodsSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		goodsSearchTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane1 = new JScrollPane();
		goodsSearchPanel.add(scrollPane1);
		scrollPane1.setViewportView(goodsSearchTable);
		
		
		//记录单查询
		JPanel recordSearchPanel = new JPanel();
		recordSearchPanel.setLayout(new BoxLayout(recordSearchPanel, BoxLayout.Y_AXIS));
		tabbedPane.addTab("记录单查询", null, recordSearchPanel, null);
		
		JPanel inputpanel2 = new JPanel();
		inputpanel2.setLayout(new BoxLayout(inputpanel2,BoxLayout.X_AXIS));
		recordSearchPanel.add(inputpanel2);
		
		//
		JLabel goodsNumberInRecordlabel = new JLabel("物资号");
		JLabel usageInRecordLabel = new JLabel("用途");
		goodsNumberInRecordlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		usageInRecordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayout(2,0));
		panel5.add(goodsNumberInRecordlabel);
		panel5.add(usageInRecordLabel);
		inputpanel2.add(panel5);
		
		//
		
		usageInRecordcomboBox = new JComboBox();
		usageInRecordcomboBox.setModel(new DefaultComboBoxModel(frame.Usage.values()));
		usageInRecordcomboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				usageInRecordcomboBoxActionPerformed(e);
			}
		});
		goodsNumberInRecordTextField = new JTextField();
		JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayout(2,0));
		panel6.add(goodsNumberInRecordTextField);
		panel6.add(usageInRecordcomboBox);
		inputpanel2.add(panel6);
		
		//
		JLabel srcWarehouseNumberInRecordLabel = new JLabel("来源仓库号");
		JLabel destWarehouseNumberInRecordLabel = new JLabel("目的仓库号");
		JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayout(2,0));
		panel7.add(destWarehouseNumberInRecordLabel);
		panel7.add(srcWarehouseNumberInRecordLabel);
		inputpanel2.add(panel7);
		
		srcWarehouseNumberInRecordTextField = new JTextField();
		destWarehouseNumberInRecordTextField = new JTextField();
		JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayout(2,0));
		panel8.add(srcWarehouseNumberInRecordTextField);
		panel8.add(destWarehouseNumberInRecordTextField);
		inputpanel2.add(panel8);
		
		JLabel srcTimeInRecordLabel = new JLabel("起始时间");
		JLabel destTimeLabel = new JLabel("结束时间");
		JPanel panel9 = new JPanel();
		panel9.setLayout(new GridLayout(2,0));
		panel9.add(srcTimeInRecordLabel);
		panel9.add(destTimeLabel);
		inputpanel2.add(panel9);
		
		timeSrcInRecordTextField = new JTextField("2019-12-22");
		timeDestInRecordTextField = new JTextField("2019-12-28");
		JPanel panel10 = new JPanel();
		panel10.setLayout(new GridLayout(2,0));
		panel10.add(timeSrcInRecordTextField);
		panel10.add(timeDestInRecordTextField);
		inputpanel2.add(panel10);
		
		

		
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
		

		recordSearchTable = new JTable(ShowTableModel.purchaseRecordTableModel);
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
		String inputDestTime = timeDestInRecordTextField.getText();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date srcDate=null;
		Date destDate=null;
		try {
			srcDate = dateFormat.parse(inputSrcTime);
			destDate = dateFormat.parse(inputDestTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		Timestamp srcTimestamp = new Timestamp(srcDate.getTime());
		Timestamp destTimestamp = new Timestamp(destDate.getTime());
		
		Usage usage = (Usage) usageInRecordcomboBox.getSelectedItem();
		String gNo = goodsNumberInRecordTextField.getText();
		String cNo = reordNumberInRecordTextField.getText();
		String sourceWNo = srcWarehouseNumberInRecordTextField.getText();
		String destWNo = destWarehouseNumberInRecordTextField.getText();
		try {
			List<?> list = DataProcessing.getRecordDetail(
					srcTimestamp, 
					destTimestamp, 
					usage.Emeaning, 
					gNo, 
					cNo, 
					sourceWNo, 
					destWNo);
			if(list.isEmpty()) {
				noMessage("没有查到对应的信息");
				return;
			}
			switch (usage) {
			case PURCHASE:
				ShowTableModel.purchaseRecordTableModel.clear();
				for(Object purchaseView : list) {
					//"记录号","部门号","物资号","物资名","物资数量","目的仓库号","时间"
					PurchaseView pView = (PurchaseView)purchaseView;
					ShowTableModel.purchaseRecordTableModel
					.addRow(new Object[] {
							new String(pView.getRNo()),
							new String(pView.getDNo()),
							new String(pView.getGNo()),
							new String(pView.getGName()),
							pView.getAmount(),
							new String(pView.getDestWNo()),
							pView.getRTime()
					});
				}
				break;
			case TRANSFER:
				ShowTableModel.transferRecordTableModel.clear();
				for(Object transferView : list) {
					//"记录号","物资号","物资名","物资数量","来源仓库号","目的仓库号","时间"
					TransferView tView = (TransferView)transferView;
					ShowTableModel.transferRecordTableModel
					.addRow(new Object[] {
							new String(tView.getRNo()),
							new String(tView.getGNo()),
							new String(tView.getGName()),
							tView.getAmount(),
							new String(tView.getSourceWNo()),
							new String(tView.getDestWNo()),
							tView.getRTime()
					});
				}
				break;
			case DELIVERY:
				ShowTableModel.deliveryRecordTableModel.clear();
				for(Object orderView : list) {
					//"记录号","客户号","物资号","物资名","物资数量","来源仓库号","时间"
					OrderView oView = (OrderView)orderView;
					ShowTableModel.deliveryRecordTableModel
					.addRow(new Object[] {
							new String(oView.getRNo()),
							new String(oView.getClientNo()),
							new String(oView.getGNo0()),
							new String(oView.getGName()),
							oView.getAmount(),
							new String(oView.getSourceWNo()),
							oView.getRtime().toString()
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
			if(list.isEmpty()) {
				noMessage("没有查到对应的信息");
				return;
			}
			ShowTableModel.goodsSearchTableModel.clear();
			for(StockView stockView : list) {
				ShowTableModel.goodsSearchTableModel
				.addRow(new Object[] {
						new String(stockView.getGNo()),
						new String(stockView.getGName()),
						stockView.getAmount(),
						new String(stockView.getWNo()),
						new String(stockView.getWName())
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
		String num = warehouseNumberInWHTextField.getText();
		
		try {
			List<WarehouseCapacityView> list = DataProcessing.getWarehouseCapacityView(name, num);
			if(list.isEmpty()) {
				noMessage("没有查到对应的信息");
				return;
			}
			ShowTableModel.goodsSearchTableModel.clear();
			for(WarehouseCapacityView warehouseCapacityView : list) {
				ShowTableModel.warehouseSearchTableModel
				.addRow(new Object[] {
						new String(warehouseCapacityView.getWNo()),
						new String(warehouseCapacityView.getWName()),
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
			recordSearchTable.setModel(ShowTableModel.purchaseRecordTableModel);
			break;
		case TRANSFER:
			recordSearchTable.setModel(ShowTableModel.transferRecordTableModel);
			break;
		case DELIVERY:
			recordSearchTable.setModel(ShowTableModel.deliveryRecordTableModel);
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
	PURCHASE("采购",DataProcessing.USAGE_PURCHASE),
	TRANSFER("转移",DataProcessing.USAGE_TRANSFER),
	DELIVERY("出售",DataProcessing.USAGE_SELL);
	
	String meaning;
	String Emeaning;
	
	private Usage(String meaning,String eMeaning) {
		// TODO Auto-generated constructor stub
		this.meaning = meaning;
		this.Emeaning = eMeaning;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return meaning;
	}
}

