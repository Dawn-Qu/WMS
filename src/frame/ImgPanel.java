package frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImgPanel extends JPanel {

	BufferedImage img;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8088182632920520570L;

	public ImgPanel(String path) {
		// TODO Auto-generated constructor stub
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
	}

	@Override
	protected void paintComponent(Graphics g1) {
		Graphics g = (Graphics)g1;
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), this);
	}
	
	public static void main(String[] args) {
		ImgPanel imgPanel = new ImgPanel(Utils.mainFrameImgPath);
		JFrame frame = new JFrame();
		frame.setContentPane(imgPanel);
		frame.pack();
		frame.setVisible(true);
	}
}
