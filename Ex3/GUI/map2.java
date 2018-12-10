package GUI;

import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.sun.org.apache.xpath.internal.Arg;

import jdk.internal.dynalink.linker.LinkerServices.Implementation;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;


public class map2 extends JFrame implements MouseListener,ActionListener
{
	
	public BufferedImage myImage;
	

	public map2() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\\u05D4\u05D5\u05E8\u05D3\u05D5\u05EA\\pacman.png"));
		setTitle("Packman");
		initGUI();		
		this.addMouseListener(this); 
		this.setBounds(100,100, 1458, 743);

		frameInit();
	}

	private void initGUI() 
	{
		
		try {
			myImage = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		ImageIcon mapimage = new ImageIcon(myImage);
		getContentPane().setLayout(null);
		JLabel map = new JLabel(mapimage);
		map.setBounds(0, 25, 1437, 662);
		getContentPane().add(map);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 866, 37);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JMenu mnNewMenu = new JMenu("New");
		mnNewMenu.setBounds(0, 0, 97, 31);
		panel.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Fruit");
		mnNewMenu.add(mnNewMenu_1);
		map.addMouseListener(this);
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{
		int width = this.getWidth();
		int hight = this.getHeight();
		g.drawImage(myImage, 0, 0,width,hight, this);

		if( x!=-1 && y!=-1 )

		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);


		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent e) {


	}
}
