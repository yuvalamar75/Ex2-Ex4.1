package GUI;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
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
import Algorithms.NextStep;
import Algorithms.Range;
import Algorithms.ShortestPathAlgo;
import Algorithms.loadFile;
import File_format.csv2kml;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Game;
import GIS.Packman;
import Geom.Point3D;
import jdk.nashorn.internal.ir.CatchNode;
import sun.nio.cs.HistoricallyNamedCharset;


public class Board extends JPanel implements MouseListener{

	/**
	 * 
	 */
	static BufferedImage mapImage,packman,cherry;
	static Set<GIS_element> packmanImages = new HashSet<GIS_element>();
	static Set<GIS_element> fruitsImages = new HashSet<GIS_element>();
	static Set<GIS_element> packmanOriginal = new HashSet<GIS_element>();
	static Set<GIS_element> fruitsOriginal = new HashSet<GIS_element>();
	private static ArrayList<Line> Lines = new ArrayList<>();
	static Set<GIS_element> originsP = new HashSet<>();
	static Random rand = new Random();



	static ArrayList<PacmanThread> PacmanThreads = new ArrayList<>();
	static Convertors c ;
	static ShortestPathAlgo Paths;
	Game game;
	loadFile f;

	Range xRange;
	Range yRange;


	static	int ratiox;
	static int ratioy;
	static int x;
	static int y;
	static int counterP;
	static int counterF;

	public Board() {

		initGUI();
		this.addMouseListener(this);

	}
	private void initGUI() {
		try {
			mapImage = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		} catch (IOException e) {
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
	public void paint(Graphics g) {

		int width = this.getWidth();
		int hight = this.getHeight();

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(new BasicStroke(300, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		g.drawImage(mapImage, 0, 0,width,hight, this);
		c = new Convertors(hight, width, 35.20238, 35.21236, 32.10190, 32.10569);

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
				//repaint();
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

	@Override
	public void mouseClicked(MouseEvent e) {

		x=e.getX();
		y=e.getY();

		System.out.println(x+", " +y);
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




	public void buildPackmanThreads(ShortestPathAlgo paths) {

		for(GIS_element pacman : packmanImages) {
			Packman currpacman = ((Packman) pacman);
			PacmanThread pacmanT = new PacmanThread((currpacman));;
			PacmanThreads.add(pacmanT);
		}
	}

	public void buildPath(Game game) {

		Paths = new ShortestPathAlgo(game);
		Paths.Rotation();
		GIS_layer packmans = game.getPackmanLayer();

		if (packmans.isEmpty()) return;

		for(GIS_element pacman : packmans) {

			float r = rand.nextFloat();
			float gx = rand.nextFloat();
			float b = rand.nextFloat();

			java.awt.Color randomColor = new java.awt.Color(r, gx, b);
			GIS_element currpacman = new Packman((Packman)pacman);
			
			Queue<NextStep> path = new LinkedList<>();
			Queue<NextStep> pathOriginal = ((Packman) currpacman).getPath();

			for (NextStep next: pathOriginal ) {
				NextStep copy = new NextStep(next);
				path.add(copy);

			}





			if (!path.isEmpty()) {

				Line line = new Line(((Packman) currpacman).getPoint3d() , path.peek().getPackman().getPoint3d(), randomColor);
				Lines.add(line);

			}

			while (!path.isEmpty()) {

				NextStep nextInLine = path.poll();

				Line currLine = new Line((nextInLine.getPackman()).getPoint3d() , nextInLine.getFruit().getPoint3d(),randomColor); 

				Lines.add(currLine);
			}
		}

		repaint();

		buildPackmanThreads(Paths);

	}


	class PacmanThread implements Runnable{

		Packman p;

		public PacmanThread(Packman p) {
			this.p = p;
		} 


		@Override

		public void run() {
			Queue<NextStep> originPath = p.getPath();
			Queue<NextStep> path = new LinkedList<>();
			Packman P = new Packman (p) ;
			for (NextStep next: originPath ) {
				NextStep copy = new NextStep(next);
				path.add(copy);
			}
			P.setPath(path);
			originsP.add(P);
			while(!originPath.isEmpty()) {
				NextStep next = originPath.poll();
				p.getPoint3d().setPoint(next.getFruit().getPoint3d());
				System.out.println("Pacman "+ p.getID() + " has scored: " + next.getFruit().getWeight());
				p.setSum(p.getSum() + next.getFruit().getWeight());
				//timestamp
				repaint();
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
		}
	}

	public void MovePackmans() {
		for (PacmanThread thread : PacmanThreads) {
			Thread t = new Thread(thread);
			t.start();
		}
	}

	public static void saveToKML(String output) {
		Stack<GIS_layer> project = new Stack<>();
		GISLayer fruits=new GISLayer(fruitsImages);
		GISLayer packmans = new GISLayer(originsP);
		project.push(fruits );
		project.push( packmans);
		Game g = new Game(project);
		csv2kml.writeFileKML(g, mainWindow.pathToSave);

	}


	public void addToGame() {

		if(mainWindow.loadGameB) {

			f = new loadFile(mainWindow.file);
			Game game = new Game(f.getFilePath());

			copyFruits(game.getFruitLayer());
			copyPacmans(game.getPackmanLayer());
			
			for (GIS_element p : packmanImages) {
				System.out.println(((Packman) p).getPoint3d().get_x()+","+((Packman) p).getPoint3d().get_y());
			}
			for (GIS_element f : fruitsImages) {
				System.out.println(((Fruit) f).getPoint3d().get_x()+","+((Fruit) f).getPoint3d().get_y());
			}
		}

		if(mainWindow.fruitB) {

			counterF++;
			Point3D gps = c.pixel2Gps(x, y);
			Fruit newF = new Fruit(gps.get_x(),gps.get_y(),1,counterF);
			fruitsImages.add(newF);



		}
		if (mainWindow.packmanB) {

			counterP++;
			Point3D gps = c.pixel2Gps(x, y);
			Packman newP = new Packman(gps.get_x(), gps.get_y(), 1, 1,counterP);
			packmanImages.add(newP);
		}
		repaint();

	}

	public void clear() {

		if(mainWindow.clearMap) {
			packmanImages.clear();
			fruitsImages.clear();
			Lines.clear();
			PacmanThreads.clear();
			repaint();
		}

	}

	private void copyFruits(GIS_layer layer){
		Iterator<GIS_element> it = layer.iterator();
		while (it.hasNext()) {
			Fruit f =  (Fruit) it.next();
			Board.fruitsImages.add(f);
		}

	}

	private void copyPacmans(GIS_layer layer){
		Iterator<GIS_element> it = layer.iterator();
		while (it.hasNext()) {
			Packman pac =  (Packman) it.next();
			Board.packmanImages.add(pac);
		}

	}


	public static void game2CSV(String path) {

		Stack<GIS_layer> pro = new Stack<>();
		GIS_layer f = new GISLayer(fruitsImages);
		GIS_layer p = new GISLayer(packmanImages);
		pro.add(f);
		pro.add(p);
		Game game = new Game(pro);
		game.project2csv(game, path);




	}
	public void drawThickLine(
			Graphics g, int x1, int y1, int x2, int y2, int thickness, java.awt.Color c) {
		// The thick line is in fact a filled polygon
		g.setColor(c);
		int dX = x2 - x1;
		int dY = y2 - y1;
		// line length
		double lineLength = Math.sqrt(dX * dX + dY * dY);

		double scale = (double)(thickness) / (2 * lineLength);

		// The x,y increments from an endpoint needed to create a rectangle...
		double ddx = -scale * (double)dY;
		double ddy = scale * (double)dX;
		ddx += (ddx > 0) ? 0.5 : -0.5;
		ddy += (ddy > 0) ? 0.5 : -0.5;
		int dx = (int)ddx;
		int dy = (int)ddy;

		// Now we can compute the corner points...
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];

		xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
		xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
		xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
		xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

		g.fillPolygon(xPoints, yPoints, 4);
	}

	public static  ArrayList<Line> getLines() {
		return Lines;
	}




}





