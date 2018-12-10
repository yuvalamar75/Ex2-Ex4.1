package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Map extends JFrame implements MouseListener {

	JFrame framePackman;
	private final Action action = new SwingAction();
	BufferedImage iconMap = null;
	Image mapIcon;
	int x = -1;
	int y = -1;
	public Map() {
		initialize();
		this.addMouseListener(this); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		framePackman = new JFrame();
		framePackman.getContentPane().setEnabled(false);
		framePackman.setBackground(new Color(255, 255, 0));
		framePackman.setTitle("Packman");
		framePackman.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\\u05D4\u05D5\u05E8\u05D3\u05D5\u05EA\\pacman.png"));
		framePackman.setBounds(0, 0, 1459, 765);
		framePackman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePackman.setResizable(true);
		framePackman.getContentPane().setLayout(null);
		framePackman.requestFocusInWindow();
		Container contentPane = framePackman.getContentPane();
		contentPane.setLayout(new FlowLayout());





		try 
		{
			iconMap = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		//ImageIcon mapIcon = new ImageIcon(iconMap);
		JPanel panelMap = new JPanel();
		contentPane.add(panelMap);

		panelMap.addMouseListener(this);
		panelMap.setBounds(0, 67, 1437, 642);
		framePackman.getContentPane().add(panelMap);
		panelMap.setLayout(null);


		JLabel map = new JLabel("");
		map.setIcon(new ImageIcon(Map.class.getResource("/GUI/Ariel1.png")));
		map.setBounds(0, 0, 1450, 642);
		panelMap.add(map);


		JPanel panelButtens = new JPanel();
		panelButtens.setBounds(0, 0, 1437, 67);
		framePackman.getContentPane().add(panelButtens);
		contentPane.add(panelButtens);
		contentPane.setLayout(getLayout());
		contentPane.setPreferredSize(framePackman.getSize());
		panelButtens.setLayout(null);

		JButton btnBuildPath = new JButton("Build Path");
		btnBuildPath.setBounds(821, 16, 103, 29);
		panelButtens.add(btnBuildPath);
		btnBuildPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add here bulid path method
			}
		});
		btnBuildPath.setAction(action);
		
				JButton btnNewPackman = new JButton("New Packman");
				btnNewPackman.setBounds(206, 16, 131, 29);
				panelButtens.add(btnNewPackman);
				btnNewPackman.setIcon(null);
				
				
				
				
						/////////////////////////--------------Buttons--------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
						JButton btnNewGame = new JButton("New Game");
						btnNewGame.setBounds(63, 16, 109, 29);
						panelButtens.add(btnNewGame);

		/*	framePackman.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				System.out.println("componentResized");
				PanelMap.setBounds(0, 5, framePackman.getWidth(), framePackman.getHeight());

			}
		});*/
		validate();


	}

	public void paintComponent(Graphics g)
	{
		int width = this.getWidth();
		int hight = this.getHeight();
		super.paintComponents (g); 
		g.drawImage (mapIcon.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, width, hight, this); 



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

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(ACTION_COMMAND_KEY, "");
			putValue(NAME, "Build path");
			putValue(SHORT_DESCRIPTION, "Build new path to all fruits");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
