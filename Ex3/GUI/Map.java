package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map extends JFrame implements MouseListener{


	public BufferedImage myImage;
	
	int x = 1;
	int y = 1;

	public Map()
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI()  
	{
		JPanel main = new JPanel();
		this.add(main);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menu.setLabel("new");
		MenuItem packman = new MenuItem("Packman");
		MenuItem fruit = new MenuItem("Fruit");
		MenuItem buildPath = new MenuItem("Build path");
		/*ImageIcon image1 = new ImageIcon(getClass().getResource("/GUI/Ariel1.png"));
		try {
			File pic = new File(getClass().getResource("/GUI/Ariel1.png").toURI());
			myImage = ImageIO.read(pic);
		} catch (URISyntaxException | IOException e2) {
			e2.printStackTrace();
		}  
		
		JLabel layeredPane = new JLabel(image1);
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);*/
	
		
		


		menu.add(packman);
		menu.add(fruit);
		menu.add(buildPath);
		menuBar.add(menu);
		

		this.setMenuBar(menuBar);

		//Default Map
		try {
			File pic = new File(getClass().getResource("/GUI/Ariel1.png").toURI());
			myImage = ImageIO.read(pic);
		} catch (URISyntaxException | IOException e2) {
			e2.printStackTrace();
		}


	}



	public void paint(Graphics g)
	{
		int height = this.getHeight();
		int width = this.getWidth();
		
		g.drawImage(myImage, 0, 0, width,height, this);

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
	




}
