package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;

import com.sun.swing.internal.plaf.metal.resources.metal;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import javax.swing.JMenuItem;

public class Map3 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map3 window = new Map3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Map3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("packman");
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 1408, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBounds(0, 0, 663, 208);
		addPopup(frame.getContentPane(), popupMenu);
		
		JButton btnFruit = new JButton("fruit");
		popupMenu.add(btnFruit);
		
		JButton btnPackman = new JButton("packman");
		popupMenu.add(btnPackman);
		frame.getContentPane().setLayout(null);
		frame.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void mouseclick(MouseEvent e) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
