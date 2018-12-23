package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import com.sun.org.apache.xml.internal.resolver.readers.OASISXMLCatalogReader;

import Algorithmes.GISLayer;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Packman;
import GUI.Board;
//import GUI.Board.PacmanThread;
import Geom.Point3D;
import sun.net.www.protocol.ftp.FtpURLConnection;
import sun.util.resources.cldr.ur.CurrencyNames_ur;
/**
 * this class holds the algorithm to calculate the paths of all the pacmans with all of the fruits.
 * it uses the NextStep and Path Objects to do so.
 * 
 * @author YuvalAmar and DvirHacohen
 *
 */
public class ShortestPathAlgo {

	private GIS_layer fruitsOrigin;
	private GIS_layer packmansOrigin;
	private Queue <NextStep> currSteps;
	private ArrayList<Line> Lines;
	private Iterator<GIS_element> packmanIterator;
	private Iterator<GIS_element> fruitsIterator;
	private Queue<NextStep> Path;
	static ArrayList<Integer> fruitId;
	private ArrayList<Packman> packmans;
	private ArrayList<Fruit> fruits;
	private PriorityQueue<NextStep> tempuauryPq;
	private Random rand = new Random();
	private ArrayList<Point3D> movements;
	public ArrayList<Point3D> getMovements() {
		return movements;
	}
	public void setMovements(ArrayList<Point3D> movements) {
		this.movements = movements;
	}

	private Game game;

	public ShortestPathAlgo(Game game) {

		this.game = game;
		currSteps = new LinkedList<>();
		fruitsOrigin = game.getFruitLayer();
		packmansOrigin = game.getPackmanLayer();
		fruits = new ArrayList<>();
		packmans = new ArrayList<>();
		fruitId = new ArrayList<>();
		Lines = new ArrayList<>();
		movements = new ArrayList<>();


	}
	/*
	 *As long there are still fruits do:
	 *calculate each pacman path.
	 *get the closet step of each pacman.
	 *add them PriorityQueue.
	 *		if we ran into Collisions(two pacmans go to the same fruit) get the one with smaller time to get to it.
	 * add the final result to another PriorityQueue.
	 * send the result the "MovePackman" function.
	 * clear all of the lists.
	 */
	public void start() {

		copyFruits();
		copyPackmans();

		if (packmans.isEmpty()) return;

		tempuauryPq = new PriorityQueue<>(packmans.size() , new TimeComperator());


		while (!fruits.isEmpty()) {

			for (Packman pacman : packmans) {

				Packman currentP = pacman;
				Path pacPath = new Path(currentP, fruits);

				pacPath.BuildPath();

				NextStep next = pacPath.getPath().peek();

				if (!next.isEaten() && next!= null) {

					tempuauryPq.add(next);
				}
			}

			while (!tempuauryPq.isEmpty()) {

				NextStep nextStep = tempuauryPq.poll();

				if(!fruitId.contains(nextStep.getfId()) && nextStep!= null) {
					currSteps.add(nextStep);
					fruitId.add(nextStep.getfId());
					nextStep.getFruit().setEaten(true);
					fruits.remove(nextStep.getFruit());
				}

			}


			MovePackman(currSteps);
			tempuauryPq.clear();
			fruitId.clear();
			currSteps.clear();


		}

		buidLines();



	}
	/*
	 * this function builds the path's lines of each pacman
	 */
	private void buidLines() {

		for(GIS_element pacman : packmansOrigin) {

			float r = rand.nextFloat();
			float gx = rand.nextFloat();
			float b = rand.nextFloat();


			java.awt.Color randomColor = new java.awt.Color(r, gx, b);
			Packman currpacman = new Packman((Packman)pacman);
			Queue<NextStep> path = new LinkedList<>();
			path = currpacman.getPath();

			if (!path.isEmpty()) {

				Line line = new Line(currpacman.getPoint3d() , path.peek().getPackman().getPoint3d(), randomColor);
				Lines.add(line);
			}


			for (NextStep next : path) {

				NextStep nextInLine = next;
				Line currLine = new Line((nextInLine.getPackman()).getPoint3d() , nextInLine.getFruit().getPoint3d(),randomColor); 
				Lines.add(currLine);

			}
		}

	}


	/**
	 * 
	 * get the list of every pacman next movement.
	 * get the pacman move to that same fruit in order to calculate from that point the next of his paths.

	 * @param currSteps the next step of each pacman
	 */
	private void MovePackman(Queue <NextStep> currSteps) {

		for (NextStep nextStep : currSteps) {

			Fruit fruit = nextStep.getFruit();
			Packman pacman = nextStep.getPackman();

			for (GIS_element P : packmansOrigin) {
				
				if (pacman.getID() == ((Packman) P).getID()){
					NextStep newStep = new NextStep(nextStep);
					Path = ((Packman) P).getPath();
					Path.add(newStep);
					break;
				}
			}

			for(Packman pac : packmans) {

				if (pac.getID() == pacman.getID()) {
					pac.getPoint3d().set_x(fruit.getPoint3d().get_x());
					pac.getPoint3d().set_y(fruit.getPoint3d().get_y());
					pac.getPoint3d().set_z(fruit.getPoint3d().get_z());
					break;
				}
			}
		}
	}
	/*
	 * this function create small steps for each pacman.
	 */
	public void buildMovemnts (Packman pacman) {
		//ArrayList<Point3D> eachMovment = new ArrayList<>();
		Point3D originP = new Point3D(pacman.getPoint3d());
		Point3D pointP = new Point3D(pacman.getPoint3d());

		Queue<NextStep> pathP = pacman.getPath();

		double t2=4;//jumps in 2 sec
		double z,w;//new x y
		
		for (NextStep next : pathP) {
			
			NextStep currnext = next;
			Point3D fruitP = new Point3D (currnext.getFruit().getPoint3d());
			double totalTime = currnext.getTime();
			while (totalTime - t2 > 4) {			
				//fruitP = new Point3D (currnext.getFruit().getPoint3d());
				z = (t2/totalTime) * (fruitP.get_x() - pointP.get_x()) + pointP.get_x();
				w = (t2/totalTime) * (fruitP.get_y() - pointP.get_y()) + pointP.get_y();

				Point3D newmove = new Point3D(z,w); 
				pacman.getMovements().add(newmove);

				t2= t2 + 4;
			}
			//pointP.setPoint(fruitP);
			pointP.set_x(fruitP.get_x());
			pointP.set_y(fruitP.get_y());
			pointP.set_z(fruitP.get_z());
			Point3D newmove = new Point3D(fruitP.get_x(),fruitP.get_y());
			pacman.getMovements().add(newmove);

			pacman.getPoint3d().set_x(fruitP.get_x());
			pacman.getPoint3d().set_y(fruitP.get_y());
			pacman.getPoint3d().set_z(fruitP.get_z());
			
			t2=1;


		}
		pacman.getPoint3d().set_x(originP.get_x());
		pacman.getPoint3d().set_y(originP.get_y());
		pacman.getPoint3d().set_z(originP.get_z());
		//return eachMovment;
	}
	
	/*
	 * this function copy the fruits
	 */
	public void copyFruits() {

		Iterator<GIS_element> fruitIterator= fruitsOrigin.iterator();

		while (fruitIterator.hasNext()) {
			Fruit f = new Fruit(((Fruit)fruitIterator.next()));
			fruits.add(f);

		}
	}
	/*
	 * this function copy the pacmans
	 */
	public void copyPackmans() {

		Iterator<GIS_element> packmansIterator = packmansOrigin.iterator();

		while (packmansIterator.hasNext()) {
			Packman p = new Packman((Packman)packmansIterator.next());
			packmans.add(p);
		}
	}

	public ArrayList<Packman> getPackmans() {
		return packmans;
	}

	public ArrayList<Line> getLines() {
		return Lines;
	}
	public void setLines(ArrayList<Line> lines) {
		this.Lines = lines;
	}
}

