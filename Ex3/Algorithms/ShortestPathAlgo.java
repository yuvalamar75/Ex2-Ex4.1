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

public class ShortestPathAlgo {

	Game game;
	GIS_layer fruitsOrigin;
	GIS_layer packmansOrigin;
	private ArrayList<Fruit> fruits;
	private ArrayList<Packman> packmans;	
	private Queue <NextStep> currSteps;
	static ArrayList<Integer> fruitId;
	private PriorityQueue<NextStep> tempuauryPq;
	Iterator<GIS_element> packmanIterator;
	Iterator<GIS_element> fruitsIterator;
	Queue<NextStep> Path;

	public ShortestPathAlgo(Game game) {

		this.game = game;
		currSteps = new LinkedList<>();
		fruitsOrigin = game.getFruitLayer();
		packmansOrigin = game.getPackmanLayer();
		fruits = new ArrayList<>();
		packmans = new ArrayList<>();
		fruitId = new ArrayList<>();
		

	}
	
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
				

				/*else {

					Path pacPath = new Path(nextStep.getPackman(), fruits);
					nextStep = pacPath.getNext();
					
					while (nextStep.getFruit().isEaten) {
						
						nextStep = pacPath.getNext();
						
					}
					
					fruitId.add(nextStep.getfId());
					currSteps.add(nextStep);
					nextStep.getFruit().setEaten(true);
					fruits.remove(nextStep.getFruit());
				}*/
			}

			MovePackman(currSteps);
			tempuauryPq.clear();
			fruitId.clear();
			currSteps.clear();
			

		}
		for(GIS_element p : packmansOrigin) {
			Packman pt = (Packman) p;
			System.out.println("pacman Id: "+pt.getID());
			Queue<NextStep> next = pt.getPath();
			System.out.println(next);
		}
		for(GIS_element f : fruitsOrigin) {
			Fruit ft = (Fruit) f;
			System.out.println("fruit Id: "+ft.getId());
			
		}
		
	}



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
	/*	for (GIS_element ftemp : fruitsOrigin) {
			Fruit f = new Fruit(((Fruit) ftemp));
			fruits.add(f);
		}*/


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


	public static void main(String[] args) {
		Game game = new Game("D:\\data\\game_1543685769754.csv");
		ShortestPathAlgo Paths = new ShortestPathAlgo(game);
		Paths.Rotation();
		GIS_layer packmans = game.getPackmanLayer();

		Iterator<GIS_element> iterator = packmans.iterator();

		while (iterator.hasNext()) {
			Packman p = (Packman) iterator.next();

			System.out.println(p.getID()+": ");

			while (!p.getPath().isEmpty()) {

				NextStep n = p.getPath().poll();

				System.out.print("pacman id :" +n.getPackman().getID()+": "+n.getPackman().getPoint3d().get_x()+", "+ n.getPackman().getPoint3d().get_y() + " ID: "+ n.getfId()+ ", " );
			}

			System.out.println("\n");
		}
	}
}

