package GIS;

import java.util.ArrayList;
import java.util.Set;

import Algorithms.MetaDataF;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Circle;
import Geom.Geom_element;
import Geom.Point3D;

public class Fruit implements GIS_element{
	
	Circle cf;
	Point3D p;
	double radiusf=1;
	String[] data;
	public boolean isEaten;
	int ID;
	double time;
	private ArrayList<Integer> path;
	

	
	public Fruit (String[] data) {
		this.data= data;
		double _x= Double.parseDouble(data[2]);
		double _y= Double.parseDouble(data[3]);
		double _z= Double.parseDouble(data[4]);
		p=new Point3D(_x,_y,_z);
		cf=new Circle(p, radiusf);
		ID=Integer.parseInt(data[1]);
		isEaten=false;
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
		return data[0];
	}
	public String getID() {
		return data[1];
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
	
	public ArrayList<Integer> getPath() {
		return getPath();
	}
	public void setPath(ArrayList<Integer> path) {
		this.path = path;
	}
	
	
	

}
