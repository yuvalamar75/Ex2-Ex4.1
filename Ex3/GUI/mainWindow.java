package GUI;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JFrame;

public class mainWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Borad map;
	public mainWindow() {
	
		this.setVisible(true);
		this.setBounds(100,100,1000,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		map = new Borad();
		this.setContentPane(map);
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu loadGame = new Menu("Load Game");
		menuBar.add(loadGame);
		
		Menu add = new Menu("Add");
		menuBar.add(add);
		
		MenuItem fruit = new MenuItem("fruit");
		add.add(fruit);
		
		MenuItem packman = new MenuItem("Packman");
		add.add(packman);
		
		
		
		Menu buildPath = new Menu("Build Path");
		menuBar.add(buildPath);
		
		Menu saveGame = new Menu("Save Game");
		menuBar.add(saveGame);
		
		
		
	}
		

}
