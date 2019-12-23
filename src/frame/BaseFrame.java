package frame;



import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BaseFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6085006058098275024L;
	
	protected void movePositionToCenter() {
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int frm_Height = this.getHeight();
		int frm_width = this.getWidth();
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);		
		
	}
	
	protected void errInWindow(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(),"输入反馈",JOptionPane.YES_NO_OPTION);
	}

	protected void yesMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg,"",JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
