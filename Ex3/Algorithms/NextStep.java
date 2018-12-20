package Algorithms;

import GIS.Fruit;
import GIS.Packman;
import Geom.Point3D;
/**
 * this class represents the "NextStep" of each pacman.
 * the object holds the pacman, fruit, and the time it takes the pacman to get to the fruit.
 * @author YuvalAmar and DvirHacohen
 *
 */
public class NextStep {
	private int pId;
	private int fId;
	private double time;
	private Fruit fruit;
	private Packman packman;
	
	
	public NextStep(int pId, int fId, double time, Fruit f, Packman packman ){
		this.pId =  pId;
		this.fId = fId;
		this.time =  time;
		this.fruit = f;
		this.packman = packman;
		

		
	}
	
	public NextStep(NextStep nextStep) {
		this.pId = nextStep.pId;
		this.fId= nextStep.fId;
		this.time =  nextStep.time;
		this.fruit = new Fruit(nextStep.fruit);
		this.packman = new Packman(nextStep.packman);
		
		// TODO Auto-generated constructor stub
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public double getTime() {
		return time;
	}
	public Fruit getFruit() {
		return fruit;
	}
	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}
	public Packman getPackman() {
		return packman;
	}
	public void setPackman(Packman packman) {
		this.packman = packman;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public boolean isEaten() {
		return fruit.isEaten();
	}
	public void setEaten(boolean isEaten) {
		fruit.isEaten = isEaten;
	}

	

}