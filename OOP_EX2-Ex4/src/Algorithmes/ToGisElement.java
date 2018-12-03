package Algorithmes;

import java.util.Arrays;

import Coords.MyCoords;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.GeomElement;
import Geom.Geom_element;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 *
 *
 */
public class ToGisElement implements GIS_element {

	Point3D p;
	String[] data;
	MyCoords mycoords=new MyCoords();
	public ToGisElement(String[] data) {
		this.data= data;
		 double x = Double.parseDouble(data[6]);
		 double y = Double.parseDouble(data[7]);
		 double z = Double.parseDouble(data[8]);
		 p= new Point3D(x,y,z);
		 
		 
		/*
		 * this function return the Geom_element 
		 */
	}
	
	public ToGisElement(String [] data ,int x, int y, int z) {
		this.data= data;
		double _x= Double.parseDouble(data[x]);
		double _y= Double.parseDouble(data[y]);
		double _z= Double.parseDouble(data[z]);
		 p= new Point3D(_x,_y,_z);
	}
	@Override
	public Geom_element getGeom() {
		Geom_element G = new GeomElement(p);
		return  G;
	}
	/*
	 * this function return the Meta_data . 
	 */

	@Override
	public Meta_data getData() {
		MetaData Meta= new MetaData(data);
		return Meta;
	}
	
	/*
	 * this function return the point3D after adding vector to the point . 
	 * @param Point3D represents the vector that we add to point.
	 */

	@Override
	public void translate(Point3D vec) {
		p=mycoords.add(this.p, vec);
		
		/*
		 * this function return the point3D of the element.
		 */
		
	}
	public Point3D getPoint() {
		return p;
	}
	/*
	 * this function return the String of the element name.
	 */
	public String getname() {
		return data[1];
		
	}
	/*
	 * this function return  the time that element was created.
	 */
	
	public String gettime() {
		String temp=data[3].replaceAll(" ", "T");
		temp=temp+"Z";
		
		
		return temp;
	}
	

}