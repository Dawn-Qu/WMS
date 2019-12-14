package frame;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GoodsFrame extends BaseFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1433545671512674918L;
	private JTextField nameTextField;
	private JTextField volumeTextField;
	private JTextField priceTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsFrame window = new GoodsFrame();
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
	public GoodsFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("\u6DFB\u52A0\u7269\u8D44");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel nameLabel = new JLabel("\u7269\u8D44\u540D");
		nameLabel.setBounds(80, 25, 72, 18);
		getContentPane().add(nameLabel);
		
		JLabel volumeLabel = new JLabel("\u5360\u91CF");
		volumeLabel.setBounds(80, 56, 72, 18);
		getContentPane().add(volumeLabel);
		
		JLabel priceLabel = new JLabel("\u51FA\u552E\u4EF7\u683C");
		priceLabel.setBounds(80, 91, 72, 18);
		getContentPane().add(priceLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(161, 22, 86, 24);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		volumeTextField = new JTextField();
		volumeTextField.setBounds(161, 53, 86, 24);
		getContentPane().add(volumeTextField);
		volumeTextField.setColumns(10);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(161, 88, 86, 24);
		getContentPane().add(priceTextField);
		priceTextField.setColumns(10);
		
		JButton insertButton = new JButton("\u6DFB\u52A0");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertButtonActionPerformed();
			}
		});
		insertButton.setBounds(56, 191, 113, 27);
		getContentPane().add(insertButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed();
			}
		});
		cancelButton.setBounds(223, 191, 113, 27);
		getContentPane().add(cancelButton);
	}

	protected void cancelButtonActionPerformed() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	protected void insertButtonActionPerformed() {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
