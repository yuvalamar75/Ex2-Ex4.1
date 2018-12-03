package GIS;

import Coords.MyCoords;
import Geom.GeomElement;
import Geom.Geom_element;
import Geom.Point3D;

public class Packman implements GIS_element{
	Point3D p;
	String[] data;
	MyCoords mycoords=new MyCoords();

	public Packman (String [] data ,int x, int y, int z) {
		this.data= data;
		double _x= Double.parseDouble(data[x]);
		double _y= Double.parseDouble(data[y]);
		double _z= Double.parseDouble(data[z]);
		p=new Point3D(_x,_y,_z);
	}
	@Override
	public Geom_element getGeom() {
		GeomElement p = new GeomElement(data);
	
		return p;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		p=mycoords.add(this.p, vec);
		
	}

}
