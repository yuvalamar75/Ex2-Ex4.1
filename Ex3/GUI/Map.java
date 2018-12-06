package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Map {

	JFrame frmPackman;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Map() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPackman = new JFrame();
		frmPackman.setAlwaysOnTop(true);
		frmPackman.setTitle("Packman");
		frmPackman.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\\u05D4\u05D5\u05E8\u05D3\u05D5\u05EA\\pacman.png"));
		frmPackman.setBounds(0, 0, 1448, 695);
		frmPackman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPackman.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Map.class.getResource("/GUI/Ariel1.png")));
		lblNewLabel.setBounds(0, 0, 1433, 642);
		frmPackman.getContentPane().add(lblNewLabel);
	}

}
