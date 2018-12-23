package GUI;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionListener;
//import java.awt.event.Componentapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.sql.rowset.Joinable;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.javafx.geom.Line2D;
import com.sun.javafx.geom.Shape;
import com.sun.org.apache.xml.internal.security.Init;
import com.sun.prism.paint.Color;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Algorithmes.GISLayer;
import Algorithmes.GISProject;
import Algorithmes.ToGisElement;
import Algorithms.Convertors;
import Algorithms.Line;
import Algorithms.Map;
import Algorithms.NextStep;
import Algorithms.Range;
import Algorithms.ShortestPathAlgo;
import Algorithms.loadFile;
import File_format.Path2KML;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Game;
import GIS.Packman;
import Geom.Point3D;
import jdk.nashorn.internal.ir.CatchNode;
import sun.nio.cs.HistoricallyNamedCharset;

/**
 * this class for the panel that shows all the the variables such pacmans and fruits. 
 * 
 */
public class Board extends JPanel implements MouseListener{


	static BufferedImage mapImage,packman,cherry;
	static Set<GIS_element> packmanImages = new HashSet<GIS_element>();
	static Set<GIS_element> fruitsImages = new HashSet<GIS_element>();
	static ArrayList<Line> Lines = new ArrayList<>();
	private static ArrayList<PacmanThread> pacmanThreads = new ArrayList<>();
	static Set<GIS_element> originsP = new HashSet<>();
	static Convertors c ;
	static Map GameMap;
	static ShortestPathAlgo algorithm;
	static Random rand = new Random();
	private Game game;
	private loadFile f;



	static	int ratiox;
	static int ratioy;
	static int x;
	static int y;
	static int counterP;
	static int counterF;

	public Board() {

		GameMap = new Map();
		initGUI();

		this.addMouseListener(this);

	}
	/**
	 * this function init the GUI.
	 */
	private void initGUI() {
		try {
			mapImage = GameMap.getMyMap();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			cherry = ImageIO.read(getClass().getResourceAsStream("/GUI/cherry.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			packman = ImageIO.read(getClass().getResourceAsStream("/GUI/packman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this paint function paint all the variables of the program.
	 */

	public synchronized void paint(Graphics g) {

		int width = this.getWidth();
		int height = this.getHeight();


		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(new BasicStroke(300, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

		g.drawImage(mapImage, 0, 0,width,height, this);


		c = new Convertors(height, width, 35.20238, 35.21236, 32.10190, 32.10569);

		if (!packmanImages.isEmpty()) {

			for( GIS_element p : packmanImages) {

				int[] pixels = c.gps2Pixels(((Packman) p).getPoint3d());
				g.drawImage(packman,pixels[0],pixels[1], null);

			}
		}



		if (!fruitsImages.isEmpty()) {

			for( GIS_element f : fruitsImages) {
				int[] pixels = c.gps2Pixels(((Fruit) f).getPoint3d());
				g.drawImage(cherry,pixels[0],pixels[1], null);
			}

		}

		if (!Lines.isEmpty()) {

			for (Line line: Lines) {

				int[] pixel1 = c.gps2Pixels(line.getSource());
				int[] pixel2 = c.gps2Pixels(line.getTarget());
				drawThickLine(g2d, pixel1[0], pixel1[1], pixel2[0], pixel2[1], 4, line.getColor());

			}

		}


	}

	/**
	 * this function comes from the mouselistner and gets the specifed place in pixels of the frame .
	 * this function call for addTGame function.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		x=e.getX();
		y=e.getY();
		addToGame();

	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}



	/**
	 * 
	 * @param game get the game and stat the algorithem
	 */
	public void buildPath(Game game) {

		if (MyFrame.buildPathB) {

			algorithm = new ShortestPathAlgo(game);
			algorithm.start();
			Lines = algorithm.getLines();

			repaint();
		}

	}

	/**
	 * when press "runGame" it stars to build the pacmans
	 */
	public void MovePacmans() {
		if (MyFrame.runGameB) {
			for(GIS_element pac :packmanImages ) {


				Packman pacman =(Packman) pac;
				algorithm.buildMovemnts(pacman);

				PacmanThread pT = new PacmanThread(pacman,pacman.getPoint3d());
				PacmanThread pacThread = new PacmanThread(pT);
				pacmanThreads.add(pacThread);

				pT.start();
			}
		}
	}


	//class of Threads that will moves the pacman in his path
	public class PacmanThread extends Thread{

		private Packman p;
		private Point3D originPoint ;

		public PacmanThread(Packman p,Point3D origin) {
			this.p = p;
			originPoint = origin;
		} 

		public PacmanThread(PacmanThread PT) {
			this.p = PT.p;
			this.originPoint = PT.originPoint;
		}

		public Packman getPacman() {
			return p;

		}

		public Point3D getOriginePoint() {
			return originPoint;

		}

		@Override
		public void run() {
			Packman save = new Packman(p);
			originsP.add(save);

			for (int i = 0; i < p.getMovements().size(); i++) {

				p.getPoint3d().setPoint(p.getMovements().get(i));
				synchronized(this) { 
					repaint();}
				try {

					this.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Queue<NextStep> path = p.getPath();

			for (NextStep next :path ) {
				p.getPoint3d().setPoint(next.getFruit().getPoint3d());
				System.out.println("Pacman "+ p.getID() + " has scored: " + next.getFruit().getWeight()+" from fruit "+ next.getfId());
				p.setSum(p.getSum() + next.getFruit().getWeight());
			}
			System.out.println("pacman "+ p.getID()+" has scored total: " + p.getSum());

		}




	}

	/**
	 * this function decide how to add something new in the game board weatherer is by adding manually or from csv file
	 */
	public void addToGame() {

		if(MyFrame.loadGameB) {

			f = new loadFile(MyFrame.file);
			Game game = new Game(f.getFilePath());

			copyFruits(game.getFruitLayer());
			copyPacmans(game.getPackmanLayer());


		

		}

		if(MyFrame.fruitB) {

			counterF++;
			Point3D gps = c.pixel2Gps(x, y);
			Fruit newF = new Fruit(gps.get_x(),gps.get_y(),1,counterF);
			fruitsImages.add(newF);



		}
		if (MyFrame.packmanB) {

			counterP++;
			Point3D gps = c.pixel2Gps(x, y);
			Packman newP = new Packman(gps.get_x(), gps.get_y(), 1, 1,counterP);
			packmanImages.add(newP);
		}
		repaint();

	}
	/**
	 * this function clear all the thing that shown in tne game board .
	 */
	public void clear() {

		if(MyFrame.clearMapB) {
			packmanImages.clear();
			fruitsImages.clear();
			originsP.clear();
			Lines.clear();
			pacmanThreads.clear();
			repaint();
		}

	}
	/**
	 * this function copy the fruits to new layer
	 * @param layer of fruits
	 */
	private void copyFruits(GIS_layer layer){
		Iterator<GIS_element> it = layer.iterator();
		while (it.hasNext()) {
			Fruit f =  (Fruit) it.next();
			fruitsImages.add(f);
		}

	}
	/**
	 * this function copy the fruits to new layer
	 * @param layer of  pacmans
	 */
	private void copyPacmans(GIS_layer layer){
		Iterator<GIS_element> it = layer.iterator();
		while (it.hasNext()) {
			Packman pac =  (Packman) it.next();
			packmanImages.add(pac);
		}

	}
	/**
	 * after clicking on save to CSV button this function action.
	 * @param path of the place we want to save.
	 */


	public static void game2CSV(String path) {


		Stack<GIS_layer> pro = new Stack<>();

		GIS_layer f = new GISLayer(fruitsImages);
		GIS_layer p = new GISLayer(originsP);
		//GIS_layer p = new GISLayer(packmanImages);
		pro.add(f);
		pro.add(p);
		String path2 = path;
		Game game = new Game(pro);
		game.project2csv(game, path2);




	}
	/**
	 * after clicking on save to KML  this function action.
	 * @param output of the place we want to save.
	 */
	public static void saveToKML(String output) {
		Stack<GIS_layer> project = new Stack<>();
		GISLayer fruits=new GISLayer(fruitsImages);
		GISLayer packmans = new GISLayer(originsP);
		project.push(fruits );
		project.push( packmans);
		Game g = new Game(project);
		Path2KML.writeFileKML(g, MyFrame.pathToSave,pacmanThreads);

	}
	/**
	 * this function draw thick line for the path of the pacman.
	 * @param g for Graphics 
	 * @param x1 for relation
	 * @param y1 for relation
	 * @param x2 for relation
	 * @param y2 for relation
	 * @param thickness of the line 
	 * @param c for color
	 */

	//this function come from google
	public void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness, java.awt.Color c) {
		// The thick line is in fact a filled polygon
		g.setColor(c);
		int dX = x2 - x1;
		int dY = y2 - y1;
		// line length
		double lineLength = Math.sqrt(dX * dX + dY * dY);

		double scale = (double)(thickness) / (2 * lineLength);

		// The x,y increments from an end point needed to create a rectangle...
		double ddx = -scale * (double)dY;
		double ddy = scale * (double)dX;
		ddx += (ddx > 0) ? 0.5 : -0.5;
		ddy += (ddy > 0) ? 0.5 : -0.5;
		int dx = (int)ddx;
		int dy = (int)ddy;

		//compute the corner points.
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];

		xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
		xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
		xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
		xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

		g.fillPolygon(xPoints, yPoints, 4);
	}


}