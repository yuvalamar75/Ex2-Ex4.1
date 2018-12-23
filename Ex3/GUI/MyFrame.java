package GUI;

import java.awt.Component;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.istack.internal.logging.Logger;

import Algorithmes.GISLayer;
import Algorithms.Range;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import javafx.scene.layout.Border;

public class MyFrame extends JFrame implements MouseListener, ComponentListener{

	/**
	 * this class contain the game board panel.
	 */
	private static final long serialVersionUID = 1L;
	private Board map;

	static boolean fruitB;
	static boolean packmanB;
	static boolean addToMapB;
	static boolean clearMapB;
	static boolean loadGameB;
	static boolean buildPathB;
	static boolean runGameB;
	static boolean saveFileB;
	static String pathToSave;
	static String pathFile;
	static File file;
	static 	Image image;

	public MyFrame() {



		map = new Board();
		this.setVisible(true);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/GUI/pacmanimage.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setIconImage(image);
		this.setBounds(100,100,Board.mapImage.getWidth(),Board.mapImage.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(map);
		this.setTitle("Packman");

		this.addComponentListener(this);

		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);

		Menu loadGame = new Menu("Load Game");
		menuBar.add(loadGame);

		MenuItem load = new MenuItem("Load");
		loadGame.add(load);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				saveFileB = false;
				addToMapB = false;
				buildPathB = false;
				runGameB = false;
				loadGameB = true;
				packmanB = false;
				fruitB = false;
				
				loadGame.setEnabled(false);

				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV files (*csv)", "csv");
				jfc.setFileFilter(filter);
				int ret = jfc.showOpenDialog(MyFrame.this);
				if(ret == JFileChooser.APPROVE_OPTION)
				{
					file = jfc.getSelectedFile();
					pathFile = file.getAbsolutePath();
					map.addToGame();
				}
			}
		});

		Menu saveGame = new Menu("Save Game");
		menuBar.add(saveGame);
		saveGame.setEnabled(false);

		MenuItem save = new MenuItem("Save To CSV");
		saveGame.add(save);
		saveGame.setEnabled(true);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fruitB = false;
				packmanB = false;
				addToMapB = false;
				loadGameB = false;
				buildPathB = false;
				runGameB = false;
				saveFileB = true;

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				int userSelection = fileChooser.showSaveDialog(map);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					pathToSave = fileToSave.getAbsolutePath();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				}
				Board.game2CSV(pathToSave);

			}
		});


		Menu add = new Menu("Add");
		menuBar.add(add);

		MenuItem fruit = new MenuItem("fruit");
		add.add(fruit);
		fruit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadGameB = false;
				fruitB = true;
				packmanB = false;

			}
		});



		MenuItem packman = new MenuItem("Packman");
		add.add(packman);
		packman.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				loadGameB = false;
				fruitB = false;
				packmanB = true;


			}
		});


		Menu buildPath = new Menu("Build Game");
		menuBar.add(buildPath);
		MenuItem build = new MenuItem("Build");
		MenuItem runGame = new MenuItem("Run Game");
		runGame.setEnabled(false);
		buildPath.add(build);
		buildPath.add(runGame);


		build.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				buildPathB = true;
				runGameB = false;
				saveFileB = true;

				GISLayer fruits=new GISLayer(Board.fruitsImages);
				GISLayer packmans=new GISLayer(Board.packmanImages);

				Stack<GIS_layer> project = new Stack<>();


				project.add(fruits);
				project.add(packmans);
				Game game = new Game(project);
				map.buildPath(game);
				runGameB = true;
				runGame.setEnabled(true);
				build.setEnabled(false);




			}
		});

		buildPath.add(runGame);
		runGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveGame.setEnabled(true);
				build.setEnabled(true);
				runGame.setEnabled(false);
				map.MovePacmans();

			}
		});

		MenuItem saveKml = new MenuItem("Save To KML");
		saveGame.add(saveKml);
		saveKml.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				int userSelection = fileChooser.showSaveDialog(map);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					pathToSave = fileToSave.getAbsolutePath();
					Board.saveToKML(pathToSave);
					System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				}
			}
		});

		Menu clearGame = new Menu("Clear Game");
		menuBar.add(clearGame);
		runGame.setEnabled(false);
		saveGame.setEnabled(false);

		MenuItem clear = new MenuItem("Clear");
		add.add(clear);
		clearGame.add(clear);
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearMapB = true;
				loadGameB = false;
				loadGame.setEnabled(true);
				saveGame.setEnabled(false);
				map.clear();

			}
		});
	}
	public boolean  getFruitB() {
		return fruitB;

	}
	public boolean  getPackmanB() {
		return packmanB;

	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

		map.repaint();

	}
	@Override
	public void componentShown(ComponentEvent e) {}
	public Board getMap() {
		return map;
	}
}
