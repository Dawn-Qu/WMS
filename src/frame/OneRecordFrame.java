package frame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import model.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	private JTextPane numberTextPane;
	private JTextPane goodsTextPane;
	private JTextPane destWarehouseTextPane;
	private JTextPane srcWarehouseTextPane;

	private List<JTextPane> textPanes;
	
	RecordFrame recordFrame;

	/**
	 * Create the application.
	 */
	public OneRecordFrame(RecordFrame recordFrame) {
		this.recordFrame = recordFrame;
		initialize();
		textPanes = 
				new ArrayList<JTextPane>(
						Arrays.asList(numberTextPane,goodsTextPane,srcWarehouseTextPane,destWarehouseTextPane));
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("\u8BB0\u5F55\u5355");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel goodsLabel = new JLabel("物资名");
		goodsLabel.setBounds(14, 13, 72, 18);
		getContentPane().add(goodsLabel);
		
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
		srcWarehouseTextPane.setBounds(100, 75, 37, 18);
		getContentPane().add(srcWarehouseTextPane);
		
		destWarehouseTextPane = new JTextPane();
		destWarehouseTextPane.setBounds(100, 106, 37, 18);
		getContentPane().add(destWarehouseTextPane);
		
		numberTextPane = new JTextPane();
		numberTextPane.setBounds(100, 44, 37, 18);
		getContentPane().add(numberTextPane);
		
		goodsTextPane = new JTextPane();
		goodsTextPane.setBounds(100, 13, 37, 18);
		getContentPane().add(goodsTextPane);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.setVerticalAlignment(SwingConstants.TOP);
		cancelButton.setIcon(null);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed();
			}
		});
		cancelButton.setBounds(100, 193, 63, 27);
		getContentPane().add(cancelButton);
		
		JButton submissionButton = new JButton("确定");
		submissionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submissionButtonActionPerformed();
			}
		});
		submissionButton.setBounds(240, 193, 63, 27);
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
		String name = goodsTextPane.getText();
			int amount = Integer.parseInt(numberTextPane.getText());
			int src = Integer.parseInt(srcWarehouseTextPane.getText());
			int dest = Integer.parseInt(destWarehouseTextPane.getText());
		return new Record(name, amount, src, dest);
	}
	
	private void clear() {
		for(JTextPane textPane : textPanes) {
			textPane.setText("");
		}
	}
}
