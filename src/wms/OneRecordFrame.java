package wms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OneRecordFrame extends BaseFrame{


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneRecordFrame window = new OneRecordFrame();
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
	public OneRecordFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("\u8BB0\u5F55\u5355");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel goodsLabel = new JLabel("\u7269\u54C1\u540D");
		goodsLabel.setBounds(14, 13, 72, 18);
		getContentPane().add(goodsLabel);
		
		JLabel numberLabel = new JLabel("\u6570\u91CF");
		numberLabel.setBounds(14, 44, 72, 18);
		getContentPane().add(numberLabel);
		
		JLabel srcWarehouseLabel = new JLabel("��Դ�ֿ��");
		srcWarehouseLabel.setBounds(14, 75, 72, 18);
		getContentPane().add(srcWarehouseLabel);
		
		JLabel destWarehouseLabel = new JLabel("Ŀ�Ĳֿ��");
		destWarehouseLabel.setBounds(14, 106, 72, 18);
		getContentPane().add(destWarehouseLabel);
		
		JTextPane srcWarehouseTextPane = new JTextPane();
		srcWarehouseTextPane.setBounds(100, 75, 37, 18);
		getContentPane().add(srcWarehouseTextPane);
		
		JTextPane destWarehouseTextPane = new JTextPane();
		destWarehouseTextPane.setBounds(100, 106, 37, 18);
		getContentPane().add(destWarehouseTextPane);
		
		JTextPane numberTextPane = new JTextPane();
		numberTextPane.setBounds(100, 44, 37, 18);
		getContentPane().add(numberTextPane);
		
		JTextPane goodsTextPane = new JTextPane();
		goodsTextPane.setBounds(100, 13, 37, 18);
		getContentPane().add(goodsTextPane);
		
		JButton cancelButton = new JButton("ȡ��");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed();
			}
		});
		cancelButton.setBounds(100, 193, 63, 27);
		getContentPane().add(cancelButton);
		
		JButton submissionButton = new JButton("ȷ��");
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
		this.dispose();
	}
	
	private void cancelButtonActionPerformed() {
		this.dispose();
	}
}
