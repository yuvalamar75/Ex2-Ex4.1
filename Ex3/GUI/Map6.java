package GUI;

import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Map6 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map6 window = new Map6();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Map6() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		//mainPanel.setPreferredSize(new Dimension(600,500));
		//mainPanel.setMinimumSize(new Dimension(4444,100));
		
		JPanel menuPanel = new JPanel();
		//menuPanel.setBorder(new EmptyBorder(10, 40, 10, 50));
		//menuPanel.setPreferredSize(new Dimension(my,200));

		mainPanel.add(menuPanel, BorderLayout.NORTH);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblNewLabel = new JLabel("New label");
		menuPanel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel mapPanel = new JPanel();
		mainPanel.add(mapPanel, BorderLayout.SOUTH);
		mapPanel.setLayout(new BorderLayout(0, 0));
		
		BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		frame.setBounds(100, 100, myPicture.getWidth(), myPicture.getHeight());

		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		mapPanel.add(picLabel, BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu"); 
		JMenuItem item1 = new JMenuItem("menu item 1");
		JMenuItem item2 = new JMenuItem("menu item 2");
		
		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		frame.setJMenuBar(menuBar);
	}

}
