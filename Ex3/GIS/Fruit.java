package GIS;

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
	public Fruit (String[] data) {
		this.data= data;
		double _x= Double.parseDouble(data[2]);
		double _y= Double.parseDouble(data[3]);
		double _z= Double.parseDouble(data[4]);
		p=new Point3D(_x,_y,_z);
		cf=new Circle(p, radiusf);
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

}
