package frame;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuBar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends BaseFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2052789499088627546L;
	private PurchaseFrame purchaseFrame;
	private GoodsFrame goodsFrame;
	private warehouseFrame warehouseFrame;
	private DeliveryFrame deliveryFrame;
	private TransferFrame transferFrame;
	private SearchFrame searchFrame;
	private AdminFrame adminFrame;
	private ClientFrame clientFrame;
	private DepartmentFrame departmentFrame;
	
	private JMenuBar menuBar;

	private JMenu transferMenu, deliveryMenu, searchMenu, purchaseMenu ,manegementMenu;

	private JMenuItem menuItem;
	private JMenuItem transferMenuItem;
	private JMenuItem deliveryMenuItem;
	private JMenuItem warehouseSearchMenuItem;
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
		setSize(800,500);
		movePositionToCenter();
		setContentPane(new ImgPanel(Utils.mainFrameImgPath));
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
		
		warehouseSearchMenuItem = new JMenuItem("仓库查询");
		warehouseSearchMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warehouseSearchMenuItemActionPerformed();
			}
		});
		searchMenu.add(warehouseSearchMenuItem);
		
		JMenuItem goodsSearchMenuItem = new JMenuItem("物资查询");
		goodsSearchMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodsSearchMenuItemActionPerformed();
			}
		});
		searchMenu.add(goodsSearchMenuItem);
		
		JMenuItem recordSearchMenuItem = new JMenuItem("记录单查询");
		recordSearchMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recordSearchMenuItemActionPerformed();
			}
		});
		searchMenu.add(recordSearchMenuItem);
		
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
		
		JMenu adminManegementMenu = new JMenu("管理员管理");
		manegementMenu.add(adminManegementMenu);
		
		JMenuItem insertAdminMenuItem = new JMenuItem("添加管理员");
		insertAdminMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertAdminMenuItemActionPerformed();
			}
		});
		adminManegementMenu.add(insertAdminMenuItem);
		
		JMenu clientManegementMenu = new JMenu("客户管理");
		manegementMenu.add(clientManegementMenu);
		
		JMenuItem insertClientMenuItem = new JMenuItem("添加客户");
		insertClientMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertClientMenuItemActionPerformed();
			}
		});
		clientManegementMenu.add(insertClientMenuItem);
		
		JMenu departmentManegementmenu = new JMenu("部门管理");
		manegementMenu.add(departmentManegementmenu);
		
		JMenuItem insertDepartmentMenuItem = new JMenuItem("添加部门");
		insertDepartmentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDepartmentMenuItemActionPerformed();
			}
		});
		departmentManegementmenu.add(insertDepartmentMenuItem);
		
		menuItem = new JMenuItem("\u91C7\u8D2D");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mneuItem_puchaseActionperformed();
			}
		});
		purchaseMenu.add(menuItem);
		//

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	
	protected void insertDepartmentMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(departmentFrame == null) {
			departmentFrame = new DepartmentFrame();
		}
		departmentFrame.setVisible(true);
	}

	protected void insertClientMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(clientFrame == null) {
			clientFrame = new ClientFrame();
		}
		clientFrame.setVisible(true);
	}

	protected void insertAdminMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(adminFrame == null) {
			adminFrame = new AdminFrame();
		}
		adminFrame.setVisible(true);
	}

	protected void recordSearchMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(searchFrame == null) {
			searchFrame = new SearchFrame();
		}
		searchFrame.setTabSeq(2);
		searchFrame.setVisible(true);
	}

	protected void goodsSearchMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(searchFrame == null) {
			searchFrame = new SearchFrame();
		}
		searchFrame.setTabSeq(1);
		searchFrame.setVisible(true);
	}

	protected void warehouseSearchMenuItemActionPerformed() {
		// TODO Auto-generated method stub
		if(searchFrame == null) {
			searchFrame = new SearchFrame();
		}
		searchFrame.setTabSeq(0);
		searchFrame.setVisible(true);
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
