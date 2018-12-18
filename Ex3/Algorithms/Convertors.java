package Algorithms;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.sun.prism.Image;

import Geom.Point3D;
import javafx.geometry.Point2D;

public class Convertors {

	Map map;
	
	private int height;
	private int width;

	

	double TopRightX;
	double TopRightY;
	double LeftButtomX;
	double LeftButtomY;

	double pixelMaxY;
	double pixelMinY;

	Range CoordsRangeX;
	Range CoordsRangeY;
	Range ImgRangeX;
	Range ImgRangeY;
	
	BufferedImage img;

	public Convertors(Map map, double LeftButtomX, double TopRightX, double LeftButtomY, double TopRightY) {
		
		this.map = map;
		
		ImgRangeX = new Range(0, map.mapWidth);
		ImgRangeY = new Range(0, map.mapHeight);
		
		CoordsRangeX= new Range(LeftButtomX, TopRightX);
		CoordsRangeY= new Range(LeftButtomY, TopRightY);
		
		
		
		pixelMaxY= map.mapHeight;
		this.LeftButtomX=LeftButtomX;
		this.TopRightX=TopRightX;
		this.LeftButtomY= LeftButtomY;
		this.TopRightY= TopRightY;
	}
	
	public Convertors(int height, int width, double LeftButtomX, double TopRightX, double LeftButtomY, double TopRightY) {
		
			ImgRangeX = new Range(0, width);
			ImgRangeY = new Range(0, height);
			
	
			CoordsRangeX = new Range(LeftButtomX, TopRightX);
			CoordsRangeY = new Range(LeftButtomY, TopRightY);
			
			pixelMaxY = height;
			
			this.LeftButtomX = LeftButtomX;
			this.TopRightX = TopRightX;
			this.LeftButtomY = LeftButtomY;
			this.TopRightY = TopRightY;
			
	}

	

	public Point3D pixel2Gps(double x, double y) {

		// getting the relations

		double relX= ImgRangeX.relation(x);
		double relY= ImgRangeY.relation(y);

		// getting the vals

		double lat= CoordsRangeX.getval(relX);
		double lon= CoordsRangeY.getval(relY);

		if (relY>=0.5) {
			double eps= TopRightY - lon;
			lon = LeftButtomY + eps;
		}

		else {
			double eps= lon - LeftButtomY;
			lon = TopRightY - eps;
		}
		Point3D p = new Point3D(lat, lon,0);
		return p;

	}

	public  int [] gps2Pixels(Point3D p) {
		// getting the relations
		double relX= CoordsRangeX.relation(p.get_x());
		double relY= CoordsRangeY.relation(p.get_y());

		//gettings the vals

		double x= ImgRangeX.getval(relX);
		double y= ImgRangeY.getval(relY);

		if (relY >= 0.5) {
			double eps= pixelMaxY - y;
			y = pixelMinY + eps;
		}
		else {
			double eps= y - pixelMinY;
			y = pixelMaxY - eps;
		}


		int [] arr = {(int)x, (int)y};
		return arr;



	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}


}
