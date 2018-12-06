package GUI;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;

public class Map extends JFrame implements MouseListener {

	JFrame frmePackman;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Map() {
		initialize();
		this.addMouseListener(this); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmePackman = new JFrame();
		frmePackman.setBackground(new Color(255, 255, 0));
		frmePackman.setTitle("Packman");
		frmePackman.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\\u05D4\u05D5\u05E8\u05D3\u05D5\u05EA\\pacman.png"));
		frmePackman.setBounds(0, 0, 1452, 781);
		frmePackman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmePackman.setResizable(true);
		frmePackman.getContentPane().setLayout(null);
		
		JLabel map = new JLabel("");
		map.addMouseListener(this);
		map.setBounds(0, 79, 1433, 642);
		map.setHorizontalAlignment(SwingConstants.CENTER);
		map.setIcon(new ImageIcon(Map.class.getResource("/GUI/Ariel1.png")));
		frmePackman.getContentPane().add(map);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setBounds(286, 12, 141, 51);
		frmePackman.getContentPane().add(btnNewButton);
		
		JButton btnNewFruit = new JButton("");
		btnNewFruit.setIcon(new ImageIcon("D:\\\u05D4\u05D5\u05E8\u05D3\u05D5\u05EA\\cherries.png"));
		btnNewFruit.setBounds(493, 12, 141, 51);
		frmePackman.getContentPane().add(btnNewFruit);
		
		JButton button_1 = new JButton("New button");
		button_1.setIcon(new ImageIcon("D:\\\u05D4\u05D5\u05E8\u05D3\u05D5\u05EA\\pacman.png"));
		button_1.setBounds(751, 12, 141, 51);
		frmePackman.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(1027, 12, 141, 51);
		frmePackman.getContentPane().add(button_2);
	}
	/*public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0, this);
	
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}
	}*/

	@Override
	public void mouseClicked(MouseEvent arg) {
		int x;
		int y;
		System.out.println("click");
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
