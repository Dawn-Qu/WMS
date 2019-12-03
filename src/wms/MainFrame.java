package wms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends BaseFrame{
	
	private PurchaseFrame purchaseFrame;
	private GoodsFrame goodsFrame;
	private warehouseFrame warehouseFrame;
	private DeliveryFrame deliveryFrame;
	private TransferFrame transferFrame;

	private JMenuBar menuBar;

	private JMenu transferMenu, deliveryMenu, searchMenu, purchaseMenu ,manegementMenu;

	private JMenuItem menuItem;
	private JMenuItem transferMenuItem;
	private JMenuItem deliveryMenuItem;
	private JMenuItem menuItem_3;
	private JMenu goodsManegementMenu;
	private JMenu warehouseManegementMenu;
	private JMenuItem insertWarehouseMenuItem;
	private JMenuItem deleteWarehhouseMenuItem;
	private JMenuItem InsertGoodsMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("\u4ED3\u5E93\u7BA1\u7406\u7CFB\u7EDF");
		setSize(500,300);
		movePositionToCenter();
		getContentPane().setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 210, 26);
		getContentPane().add(menuBar);
		
		transferMenu = new JMenu("\u7269\u8D44\u6D41\u901A");
		menuBar.add(transferMenu);
		
		transferMenuItem = new JMenuItem("\u7269\u8D44\u6D41\u901A");
		transferMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferMenuItemActionPerformed();
			}
		});
		transferMenu.add(transferMenuItem);
		
		deliveryMenu = new JMenu("\u51FA\u5E93");
		menuBar.add(deliveryMenu);
		
		deliveryMenuItem = new JMenuItem("\u51FA\u5E93");
		deliveryMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deliveryMenuItemActionPerformed();
			}
		});
		deliveryMenu.add(deliveryMenuItem);
		
		searchMenu = new JMenu("\u67E5\u8BE2");
		menuBar.add(searchMenu);
		
		menuItem_3 = new JMenuItem("\u67E5\u8BE2");
		searchMenu.add(menuItem_3);
		
		purchaseMenu = new JMenu("\u91C7\u8D2D");
		menuBar.add(purchaseMenu);
		
		manegementMenu = new JMenu("管理");
		menuBar.add(manegementMenu);
		
		goodsManegementMenu = new JMenu("\u7269\u8D44\u7BA1\u7406");
		manegementMenu.add(goodsManegementMenu);
		
		InsertGoodsMenuItem = new JMenuItem("\u6DFB\u52A0\u7269\u8D44");
		InsertGoodsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertGoodsMenuItemActionPerformed();
			}
		});
		goodsManegementMenu.add(InsertGoodsMenuItem);
		
		warehouseManegementMenu = new JMenu("\u4ED3\u5E93\u7BA1\u7406");
		manegementMenu.add(warehouseManegementMenu);
		
		insertWarehouseMenuItem = new JMenuItem("\u6DFB\u52A0\u4ED3\u5E93");
		insertWarehouseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertWarehouseMenuItemActionPerformed();
			}
		});
		warehouseManegementMenu.add(insertWarehouseMenuItem);
		
		deleteWarehhouseMenuItem = new JMenuItem("\u5220\u9664\u4ED3\u5E93");
		deleteWarehhouseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteWarehhouseMenuItemActionPerformed();
			}
		});
		warehouseManegementMenu.add(deleteWarehhouseMenuItem);
		
		menuItem = new JMenuItem("\u91C7\u8D2D");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mneuItem_puchaseActionperformed();
			}
		});
		purchaseMenu.add(menuItem);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	protected void transferMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(transferFrame == null) {
			transferFrame = new TransferFrame();
		}
		transferFrame.setVisible(true);
		
	}

	protected void deliveryMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(deliveryFrame == null) {
			deliveryFrame = new DeliveryFrame();
		}
		deliveryFrame.setVisible(true);
	}

	protected void deleteWarehhouseMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(warehouseFrame == null) {
			warehouseFrame = new warehouseFrame();
		}
		warehouseFrame.setTabSeq(1);
		warehouseFrame.setVisible(true);
	}

	protected void insertWarehouseMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(warehouseFrame == null) {
			warehouseFrame = new warehouseFrame();
		}
		warehouseFrame.setTabSeq(0);
		warehouseFrame.setVisible(true);
	}

	protected void insertGoodsMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(goodsFrame == null) {
			goodsFrame = new GoodsFrame();
		}
		goodsFrame.setVisible(true);
	}

	private void mneuItem_puchaseActionperformed() {
		if(purchaseFrame == null) {
			purchaseFrame = new PurchaseFrame();
		}
		purchaseFrame.setVisible(true);
	}
}
