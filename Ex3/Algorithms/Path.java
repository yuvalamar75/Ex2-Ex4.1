package Algorithms;

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

	GIS_layer fruits;
	Packman p;
	PriorityQueue<NextStep>  pQ;
	NextStep next;
	MyCoords myCoords = new MyCoords();
	public Path(Packman p, GIS_layer fruits) {
		this.p = p;
		this.fruits = fruits;
		pQ=new PriorityQueue<>(fruits.size(),new TimeComperator()); 

	}
	public void BuildPath() {

		Iterator<GIS_element> it = fruits.iterator();

		while(it.hasNext()) {
			Fruit fruit = new Fruit(((Fruit)it.next()));;
			double time= time(p, fruit);
			NextStep next= new NextStep(Integer.parseInt(p.getID()), Integer.parseInt(fruit.getID()), time, fruit, p);
			
			pQ.add(next);
		}

		//return pQ.poll();
	}


	public double time(Packman p,Fruit f) {

		int pSpeed = p.getSpeed();
		double pRadious = p.getRadius();
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


	public static void main(String[] args) {
		Game game= new Game("D:\\data\\game_1543684662657.csv");
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
		System.out.println("the time is "+ next.getTime());

	}


}
