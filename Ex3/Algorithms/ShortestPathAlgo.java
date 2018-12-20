package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.sun.org.apache.xml.internal.resolver.readers.OASISXMLCatalogReader;

import Algorithmes.GISLayer;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Packman;
import Geom.Point3D;
import sun.net.www.protocol.ftp.FtpURLConnection;
import sun.util.resources.cldr.ur.CurrencyNames_ur;
/**
 * this class holds the algorithm to calculate the paths of all the pacmans with all of the fruits.
 * it uses the NextStep and Path Objects to do so.
 * 
 * 
 * @author YuvalAmar and DvirHacohen
 *
 */
public class ShortestPathAlgo {

	
	private GIS_layer fruitsOrigin;
	private GIS_layer packmansOrigin;
	private Queue <NextStep> currSteps;
	private Iterator<GIS_element> packmanIterator;
	private Iterator<GIS_element> fruitsIterator;
	private Queue<NextStep> Path;
	static ArrayList<Integer> fruitId;
	private ArrayList<Packman> packmans;
	private ArrayList<Fruit> fruits;
	private PriorityQueue<NextStep> tempuauryPq;
	private Game game;

	public ShortestPathAlgo(Game game) {

		this.game = game;
		currSteps = new LinkedList<>();
		fruitsOrigin = game.getFruitLayer();
		packmansOrigin = game.getPackmanLayer();
		fruits = new ArrayList<>();
		packmans = new ArrayList<>();
		fruitId = new ArrayList<>();
		

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
	public void Rotation(){
		
		copyFruits();
		copyPackmans();
		if (packmans.isEmpty()) return;
		tempuauryPq = new PriorityQueue<>(packmans.size() , new TimeComperator());


		while (!fruits.isEmpty()) {

			for (Packman pacman : packmans) {

				Packman currentP = pacman;
				Path pacPath = new Path(currentP, fruits);

				pacPath.BuildPath();

				NextStep next = pacPath.getNext();

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
		
	}


/*
 * get the list of every pacman next movement.
 *  get the pacman move to that same fruit in order to calculate from that point the next of his paths.
 */
	private void MovePackman(Queue <NextStep> currSteps) {
		while (!currSteps.isEmpty()) {

			NextStep nextStep = currSteps.poll();
			
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

	public void copyFruits() {

	Iterator<GIS_element> fruitIterator= fruitsOrigin.iterator();

		while (fruitIterator.hasNext()) {
			Fruit f = new Fruit(((Fruit)fruitIterator.next()));
			fruits.add(f);

		}
	}

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
}

