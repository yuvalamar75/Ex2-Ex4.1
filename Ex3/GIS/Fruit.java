package GIS;

import java.util.ArrayList;

import java.util.Set;

import Algorithms.MetaDataF;
import Coords.MyCoords;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Circle;
import Geom.Geom_element;
import Geom.Point3D;


/**
 * this calss represents GIS element called fruit.
 * 
 */

public class Fruit implements GIS_element{
	private Circle cf;
	private Point3D p;
	private double radiusf=1;
	private String  type = "F";
	private String[] data;
	public boolean isEaten;
	private int ID;
	private double time;
	private int weight;
	private long timeStamp;

	private int ratiox;
	private int ratioy;
	
	

	
	public Fruit (String[] data) {
		this.data= data;
		double _x= Double.parseDouble(data[3]);
		double _y= Double.parseDouble(data[2]);
		double _z= Double.parseDouble(data[4]);
		p=new Point3D(_x,_y,_z);
		cf=new Circle(p, radiusf);
		ID=Integer.parseInt(data[1]);
		isEaten=false;
		weight = (int)Double.parseDouble(data[5]);
		timeStamp = 0;
	}
	public Fruit (Fruit F) {
		this.data=F.data;
		double x = F.p.get_x();
		double y = F.p.get_y();
		double z = F.p.get_z();
		p = new Point3D(x,y,z);
		this.cf = F.cf;
		this.ID = F.ID;
		isEaten = F.isEaten;
		weight = F.weight;
		timeStamp = 0;
	}
	public  Fruit(double x, double y,int weghit,int id) {
		
		double _x= x;
		double _y= y;
		double _z= 0;
		p=new Point3D(_x,_y,_z);
		this.weight = weghit;
		ID=id;
		timeStamp = 0;
		
		
	}
	
	@Override
	
	public Geom_element getGeom() {

		
		return null;
	}

	@Override
	public Meta_data getData() {
		MetaDataF metadataf= new MetaDataF(data);
		return metadataf;
		
	}
	@Override
	public void translate(Point3D vec) {	
	}
	public String getType() {
		return type;
	}
	public String getID() {
		return data[1];
	}
	public int getId() {
		return ID;
	}
	public Point3D getPoint3d() {
		return p;
	}
	public String tostring() {
		String s="";
		s+="\n"+data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5];
		return s;
	}
	public void add(Fruit f ) {
		
		}
	public int getRatiox() {
		return ratiox;
	}
	public int getRatioy() {
		return ratioy;
	}
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public double getRadiusf() {
		return radiusf;
	}
	
	public void setEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public boolean isEaten() {
		// TODO Auto-generated method stub
		return isEaten;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	

}
