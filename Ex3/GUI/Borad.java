package GUI;

import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.org.apache.xml.internal.security.Init;

public class Borad extends JPanel implements MouseListener{
	
	static BufferedImage mapImage,packman;

	
	public Borad() {
		initGUI();
		

	}
	private void initGUI() {
		try {
			mapImage = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			packman = ImageIO.read(getClass().getResourceAsStream("/GUI/cherry.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			packman = ImageIO.read(getClass().getResourceAsStream("/GUI/packman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
		public void paint(Graphics g) {
			
			int width = this.getWidth();
			int hight = this.getHeight();
			g.drawImage(mapImage, 0, 0,width,hight, this);
			
		}
			
		
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
