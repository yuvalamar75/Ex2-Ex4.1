package GUI;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.Image;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.Window.Type;

public class Maps extends JFrame implements MouseListener{
	public BufferedImage myImage;
	private JFrame frmPackman;
	int x=1;
	int y=1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maps window = new Maps();
					window.frmPackman.setVisible(true);
					window.setSize(1000,1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Maps() {
		initialize();
		this.addMouseListener(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPackman = new JFrame();
		frmPackman.setTitle("Packman");
		frmPackman.setSize(1500,2100);
		frmPackman.getContentPane().setLayout(null);
		ImageIcon image1 = new ImageIcon(getClass().getResource("/GUI/Ariel1.png"));
		JLabel panel = new JLabel(image1);
		panel.setBounds(42, -34, 1436, 798);
		frmPackman.getContentPane().add(panel);
		
	
		
		
	
		
		
		
		
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
