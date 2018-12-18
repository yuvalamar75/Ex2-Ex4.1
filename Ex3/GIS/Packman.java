package GIS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Algorithms.MetaDataF;
import Algorithms.NextStep;
import Coords.MyCoords;
import Geom.Circle;
import Geom.GeomElement;
import Geom.Geom_element;
import Geom.Point3D;

public class Packman implements GIS_element{
	private String type = "P";
	private int counter = 0;
	private Circle cp;
	private double radiusp=1;
	private int speedP=1;
	private Point3D p;
	private String[] data;
	private int ID;
	private Queue<NextStep> path=new LinkedList<>();
	private int ratiox;
	private int ratioy;
	private MyCoords mycoords;

	public Packman (String [] data) {
		this.data= data;
		double _x= Double.parseDouble(data[3]);
		double _y= Double.parseDouble(data[2]);
		double _z= Double.parseDouble(data[4]);
		p=new Point3D(_x,_y,_z);
		cp=new Circle(p, radiusp);
		ID=Integer.parseInt(data[1]);
		mycoords = new MyCoords();
		

	}
	public Packman (Packman P) {
		this.data=P.data;
		double x = P.p.get_x();
		double y = P.p.get_y();
		double z = P.p.get_z();
		p = new Point3D(x,y,z);
		this.cp = P.cp;
		this.ID = P.ID;
		this.path = P.path;
		mycoords=new MyCoords();

	}
	public  Packman(double x, double y,int speed, int radius,int id) {
		counter++;
		double _x= x;
		double _y= y;
		double _z= 0;
		p=new Point3D(_x,_y,_z);
		this.radiusp = radius;
		cp=new Circle(p, radius);
		int speedP=1;
		ID=counter;
		mycoords = new MyCoords();
		
		this.ID = id;
		
		
	}

	public int getRatiox() {
		return ratiox;
	}

	public int getRatioy() {
		return ratioy;
	}
	
	@Override
	public Geom_element getGeom() {
		GeomElement P = new GeomElement(p);
	
		return P;
	}

	@Override
	public Meta_data getData() {
		MetaDataF metaDataf=new MetaDataF(data);
		return metaDataf;
	}

	@Override
	public void translate(Point3D vec) {
		p = mycoords.add(this.p, vec);
		
	}
	public int getSpeed() {
		int speed =  Integer.parseInt(data[5]);
		return speed;
	}
	public int getSPEED() {
		
		return speedP;
	}
	public double getRadius() {
		double radiuseat=Double.parseDouble(data[6]);
		return radiuseat;
		
	}
	public double getR() {
		
		return radiusp;
		
	}
	public String getType() {
		return type ;
	}
	public Point3D getPoint3d() {
		return p;
	}
	public String tostring() {
		String s="";
		s+="\n"+data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+","+data[6];
		return s;
	}
	public int getID() {
		return ID;
	}
	public MyCoords getMycoords() {
		return mycoords;
	}
	public Queue<NextStep> getPath() {
		return path;
	}
	
	
/*public static void main(String[] args) {
	Packman p = new Packman(1, 2, 1, 1, 1);
	Point3D v = new Point3D(1323123,1322,13212);
	p.translate(v);
	System.out.println(p.getPoint3d().get_x()+", "+p.getPoint3d().get_y()+", "+ p.getPoint3d().get_z());
}*/
	

}
