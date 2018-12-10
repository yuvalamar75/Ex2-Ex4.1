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
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;


public class Map5 extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	
	public Map5() 
	{
		initGUI();		
		this.addMouseListener(this); 
		
		
	}
	
	private void initGUI() 
	{
	
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBounds(0, 0, 332, 16);
		addPopup(getContentPane(), popupMenu);
		
		JButton btnFruit = new JButton("New Fruit");
		popupMenu.add(btnFruit);
		getContentPane().setLayout(null);
		
		JButton btnBuildPath = new JButton("Build Path");
		popupMenu.add(btnBuildPath);
		getContentPane().setLayout(null);
		
		JButton btnPackman = new JButton("New Packman");
		popupMenu.add(btnPackman);
		getContentPane().setLayout(null);
		
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

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
