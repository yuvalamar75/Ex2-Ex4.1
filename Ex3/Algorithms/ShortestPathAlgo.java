package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import sun.util.resources.cldr.ur.CurrencyNames_ur;

public class ShortestPathAlgo {

	Game game;
	GIS_layer fruitsOrigin;
	GIS_layer packmansOrigin;
	GIS_layer fruits;
	static GIS_layer packmans;	
	static Queue<NextStep> currSteps;
	static ArrayList<Integer> fruitId;
	static PriorityQueue<NextStep> tempuauryPq= new PriorityQueue<>(packmans.size(),new TimeComperator());;
	Iterator<GIS_element> packmanIterator;
	Iterator<GIS_element> fruitsIterator;

	public ShortestPathAlgo(Game game) {

		this.game = game;
		fruitsOrigin = game.getFruitLayer();
		packmansOrigin = game.getPackmanLayer();
		fruits = new GISLayer(new HashSet<>());
		packmans = new GISLayer(new HashSet<>());

		copyFruits();
		copyPackmans();


	}



	public void Rotation(){
		fruitId = new ArrayList<>();

		while (!fruits.isEmpty()) {
			packmanIterator = packmans.iterator();
			while (packmanIterator.hasNext()) {

				Packman currentP = (Packman) packmanIterator.next();
				Path pacPath = new Path(currentP, fruits);
				NextStep next = pacPath.getNext();
				tempuauryPq.add(next);


			}
			while (!tempuauryPq.isEmpty()) {
				NextStep nextStep = tempuauryPq.poll();
				if(!fruitId.contains(nextStep.getfId())) {
					currSteps.add(nextStep);
					fruitId.add(nextStep.getfId());
				}
				else {
					continue;
				}
			}

		}
	}





	public void MovePackman(Queue<NextStep> Rotate) {
		while (!Rotate.isEmpty()) {
			NextStep next = Rotate.poll();			
			Packman p = next.getPackman();
			Fruit f = next.getFruit();
			
			Point3D vector = p.getMycoords().vector3D(p.getPoint3d() , f.getPoint3d());
			p.translate(vector);
			f.isEaten = true;


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
		Packman f = new Packman((Packman)packmansIterator.next());
		fruits.add(f);
	}
}



/*	class PackmanThread extends Thread {
		NextStep next;
		public PackmanThread(NextStep next) {
			this.next = next;

		}
		@Override
		public void run() {


			super.run();
		}

	}*/

}
