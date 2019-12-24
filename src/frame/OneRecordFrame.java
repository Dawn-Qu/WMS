package frame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import model.*;
import service.DataProcessing;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class OneRecordFrame extends BaseFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4078221518513772520L;
	private JComboBox<?> goodsComboBox;
	private JTextPane numberTextPane;
	private JTextPane destWarehouseTextPane;
	private JTextPane srcWarehouseTextPane;
	private JLabel goodsNameLabel;
	
	private List<JTextPane> textPanes;
	
	RecordFrame recordFrame;

	private List<Goods> goods;
	/**
	 * Create the application.
	 */
	public OneRecordFrame(RecordFrame recordFrame) {
		this.recordFrame = recordFrame;
		initialize();
		textPanes = 
				new ArrayList<JTextPane>(
						Arrays.asList(numberTextPane,srcWarehouseTextPane,destWarehouseTextPane));
		
		JLabel label = new JLabel("物资名");
		label.setBounds(237, 13, 72, 18);
		getContentPane().add(label);
		
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("\u8BB0\u5F55\u5355");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel goodsLabel = new JLabel("物资号");
		goodsLabel.setBounds(14, 13, 72, 18);
		getContentPane().add(goodsLabel);
		
		goodsNameLabel = new JLabel("");
		goodsNameLabel.setBounds(293, 13, 93, 18);
		getContentPane().add(goodsNameLabel);
		
		JLabel numberLabel = new JLabel("数量");
		numberLabel.setBounds(14, 44, 72, 18);
		getContentPane().add(numberLabel);
		
		JLabel srcWarehouseLabel = new JLabel("来源仓库号");
		srcWarehouseLabel.setBounds(14, 75, 72, 18);
		getContentPane().add(srcWarehouseLabel);
		
		JLabel destWarehouseLabel = new JLabel("目的仓库号");
		destWarehouseLabel.setBounds(14, 106, 72, 18);
		getContentPane().add(destWarehouseLabel);
		
		srcWarehouseTextPane = new JTextPane();
		srcWarehouseTextPane.setBounds(100, 75, 70, 18);
		getContentPane().add(srcWarehouseTextPane);
		
		destWarehouseTextPane = new JTextPane();
		destWarehouseTextPane.setBounds(100, 106, 70, 18);
		getContentPane().add(destWarehouseTextPane);
		
		getGoods();
		goodsNameLabel.setText(new String(goods.get(0).getGName()));
		goodsComboBox = new JComboBox<>(new DefaultComboBoxModel<>(goods.toArray()));
		goodsComboBox.setBounds(100, 13, 109, 18);
		goodsComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				goodsNameLabel.setText(new String(((Goods)e.getItem()).getGName()));
			}
		});
		getContentPane().add(goodsComboBox);
		
		numberTextPane = new JTextPane();
		numberTextPane.setBounds(100, 44, 70, 18);
		getContentPane().add(numberTextPane);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.setVerticalAlignment(SwingConstants.TOP);
		cancelButton.setIcon(null);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed();
			}
		});
		cancelButton.setBounds(246, 199, 63, 27);
		getContentPane().add(cancelButton);
		
		JButton submissionButton = new JButton("确定");
		submissionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submissionButtonActionPerformed();
			}
		});
		submissionButton.setBounds(107, 199, 63, 27);
		getContentPane().add(submissionButton);
		

		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void submissionButtonActionPerformed() {
		recordFrame.insert(getRecord());
		clear();
	}
	
	private void cancelButtonActionPerformed() {
		this.dispose();
	}
	
	private Record getRecord() {
		String num = new String(((Goods)goodsComboBox.getSelectedItem()).getGNO());
		int amount = Integer.parseInt(numberTextPane.getText());
		String src = srcWarehouseTextPane.getText().trim();
		String dest = destWarehouseTextPane.getText().trim();
		return new Record(num, amount, src, dest);
	}
	
	private void clear() {
		for(JTextPane textPane : textPanes) {
			textPane.setText("");
		}
	}
	
	private void getGoods() {
		try {
			goods = DataProcessing.getAllGoods();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errInWindow(e);
		}
	}
}
