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

public class AdminFrame extends BaseFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1433545671512674918L;
	private JTextField numberTextField;
	private JTextField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame window = new AdminFrame();
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
	public AdminFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle("管理员管理");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel numberLabel = new JLabel("管理员号");
		numberLabel.setBounds(80, 25, 72, 18);
		getContentPane().add(numberLabel);
		
		JLabel passwordLabel = new JLabel("密码");
		passwordLabel.setBounds(80, 56, 72, 18);
		getContentPane().add(passwordLabel);
		
		
		numberTextField = new JTextField();
		numberTextField.setBounds(161, 22, 86, 24);
		getContentPane().add(numberTextField);
		numberTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(161, 53, 86, 24);
		getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		
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
		String number = numberTextField.getText();
		String password = passwordTextField.getText();
		try {
			DataProcessing.addAdministrator(number, password);
			yesMessage("添加管理员成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(),"输入反馈",JOptionPane.YES_NO_OPTION);
		}finally {
			this.dispose();
		}
	}
}
