package GIS;

import Algorithms.MetaDataF;
import Coords.MyCoords;
import Geom.Circle;
import Geom.GeomElement;
import Geom.Geom_element;
import Geom.Point3D;

public class Packman implements GIS_element{
	Circle cp;
	double radiusp=1;
	Point3D p;
	String[] data;
	MyCoords mycoords=new MyCoords();

	public Packman (String [] data) {
		this.data= data;
		double _x= Double.parseDouble(data[2]);
		double _y= Double.parseDouble(data[3]);
		double _z= Double.parseDouble(data[4]);
		p=new Point3D(_x,_y,_z);
		cp=new Circle(p, radiusp);
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
		p=mycoords.add(this.p, vec);
		
	}
	public int getSpeed() {
		int speed =  Integer.parseInt(data[5]);
		return speed;
	}
	public double getRadius() {
		double radiuseat=Double.parseDouble(data[6]);
		return radiuseat;
		
	}
	public String getType() {
		return data[0];
	}
	public Point3D getPoint3d() {
		return p;
	}
	public String tostring() {
		String s="";
		s+="\n"+data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+","+data[6];
		return s;
	}

}
