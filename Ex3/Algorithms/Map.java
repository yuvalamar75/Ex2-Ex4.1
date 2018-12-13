package Algorithms;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Geom.Point3D;
import javafx.geometry.Point2D;

public class Map {

	BufferedImage myMap;
	Convertors con;
	static double eps;
	static double TopRightX=35.21236;
	static double TopRightY=32.10569;
	static double LeftButtomX=35.20238;
	static double LeftButtomY=32.10190;

	double mapWidth;
	double mapHeight;

	

	public Map() {
		try {
			myMap = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mapWidth = myMap.getWidth();
		this.mapHeight = myMap.getHeight();

		
		con= new Convertors(this, LeftButtomX, TopRightX, LeftButtomY, TopRightY);



	}

	public Map(String path) {
		try {
			myMap = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mapWidth = myMap.getWidth();
		mapHeight = myMap.getHeight();


		con= new Convertors(this, LeftButtomX, TopRightX, LeftButtomY, TopRightY);
	}



	public double dis2 (int [] p0, int [] p1) {
		Point3D p = con.pixel2Gps(p0[0], p0[1]);
		Point3D p2 = con.pixel2Gps(p1[0], p1[1]);

		double dis = p.distance3D(p2);
		return dis;

	}

	public static void main(String[] args) {
		Map map = new Map();
		
		
		int [] a= {532,754};
		Point3D p = map.con.pixel2Gps(a[0],a[1]);
		System.out.println(p.get_x()+","+p.get_y());
		

	}
}

