package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import Coords.MyCoords;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Packman;
import Geom.Point3D;
import javafx.scene.layout.Priority;

public class Path {

	private ArrayList<Fruit> fruits;
	private Packman p;
	private PriorityQueue<NextStep>  pQ;
	private NextStep next;
	private MyCoords myCoords = new MyCoords();
	private GIS_layer fruits2;
	
	
	public Path(Packman p, ArrayList<Fruit> fruits) {
		this.p = p;
		this.fruits = fruits;
		
		pQ=new PriorityQueue<>(fruits.size(),new TimeComperator());
		//copyFruits();

	}
	public void BuildPath() {


		for(int i=0 ; i<fruits.size(); i++) {
			Fruit fruit =  fruits.get(i);
			double time= time(p, fruit);
			NextStep next= new NextStep(p.getID(), fruit.getId(), time, fruit, p);
			
			pQ.add(next);
		}

		//return pQ.poll();
	}


	public double time(Packman p,Fruit f) {

		int pSpeed = p.getSPEED();
		double pRadious = p.getR();
		double dis = myCoords.distance3d(p.getPoint3d(), f.getPoint3d());

		if (dis==0) return 0;

		double time = (dis-pRadious)/pSpeed;

		if(time<0) return 0;

		return time;
	} 

	public PriorityQueue<NextStep> getPath(){
		return pQ;

	}
	public NextStep getNext() {
		if (pQ.peek()!=null)
			return pQ.poll();
		return null;
	}
	
	public void copyFruits() {

		Iterator<GIS_element> fruitIterator= fruits2.iterator();

		while (fruitIterator.hasNext()) {
			Fruit f = new Fruit(((Fruit)fruitIterator.next()));
			fruits.add(f);

		}

	}


	public static void main(String[] args) {
		/*Game game= new Game("C:\\Users\\1234\\Desktop\\data\\data\\game_1543684662657.csv");
		GIS_layer pac= game.getPackmanLayer();
		Iterator<GIS_element> it = pac.iterator();

		it.next();
		Packman p = (Packman) it.next(); 
		System.out.println(p.getData());
		System.out.println(p.getPoint3d());
		Path ne= new Path(p, game.getFruitLayer());
		ne.BuildPath();
		NextStep next = ne.getNext();
		System.out.println("-------------------");
		System.out.println("the out pus is: ");
		System.out.println("the pac id is "+next.getpId());
		System.out.println("the fruit id is "+next.getfId());
		System.out.println("the time is "+ next.getTime());*/

	}


}