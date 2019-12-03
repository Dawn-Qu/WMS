package wms;



import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

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

}
