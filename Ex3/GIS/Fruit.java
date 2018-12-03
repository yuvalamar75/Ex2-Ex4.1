package GIS;

import Algorithms.MetaDataF;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;

public class Fruit implements GIS_element{

	Point3D p;
	String[] data;
	public Fruit (String[] data ,int x, int y, int z) {
		this.data= data;
		double _x= Double.parseDouble(data[x]);
		double _y= Double.parseDouble(data[y]);
		double _z= Double.parseDouble(data[z]);
		p=new Point3D(_x,_y,_z);
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
