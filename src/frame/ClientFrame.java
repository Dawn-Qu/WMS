package frame;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import service.DataProcessing;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ClientFrame extends BaseFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1433545671512674918L;
	private JTextField nameTextField;
	private JTextField numberTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
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
	public ClientFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("客户管理");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel nameLabel = new JLabel("客户名");
		nameLabel.setBounds(80, 25, 72, 18);
		getContentPane().add(nameLabel);
		
		JLabel numberLabel = new JLabel("客户号");
		numberLabel.setBounds(80, 56, 72, 18);
		getContentPane().add(numberLabel);
		
		
		nameTextField = new JTextField();
		nameTextField.setBounds(161, 22, 86, 24);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		numberTextField = new JTextField();
		numberTextField.setBounds(161, 53, 86, 24);
		getContentPane().add(numberTextField);
		numberTextField.setColumns(10);
		
		
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
		String name = nameTextField.getText();
		String number = numberTextField.getText();
		try {
			DataProcessing.addClient(number, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(),"输入反馈",JOptionPane.YES_NO_OPTION);
		}finally {
			this.dispose();
		}
	}
}
