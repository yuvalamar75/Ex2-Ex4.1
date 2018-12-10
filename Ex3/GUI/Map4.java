package GUI;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class Map4 extends JFrame  {

	private JFrame frame;
	public BufferedImage myImage;
	
	public Map4() {
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel menuPanel = new JPanel();
		mainPanel.add(menuPanel, BorderLayout.NORTH);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel mapPanel = new JPanel();
		mainPanel.add(mapPanel, BorderLayout.SOUTH);
		mapPanel.setLayout(new BorderLayout(0, 0));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem item1 = new MenuItem("menu item 1");
		MenuItem item2 = new MenuItem("menu item 2");
		
		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		frame.setMenuBar(menuBar);
		
		try {
			 myImage = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	int x = -1;
	int y = -1;
	public void paint(Graphics g)
	{
		int width = this.getWidth();
		int hight = this.getHeight();
		g.drawImage(myImage, 0, 0,width,hight, this);
	
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}
	}

}
