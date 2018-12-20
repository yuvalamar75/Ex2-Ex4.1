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

/**
 * this class holds the map of the game.
 * this object load the img from the user computer and holds 
 * all of img data such as size, pixels and holds convert object.
 * 
 * @author YuvalAmar and DvirHacohen
 *
 */
public class Map {

	private BufferedImage myMap;
	private Convertors converter;
	static double eps;
	static double TopRightX=35.21236;
	static double TopRightY=32.10569;
	static double LeftButtomX=35.20238;
	static double LeftButtomY=32.10190;

	private double mapWidth;
	private double mapHeight;

	
/*
 * load image from same path and create converter object.
 */
	public Map() {
		
		try {
			myMap = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.mapWidth = myMap.getWidth();
		this.mapHeight = myMap.getHeight();

		
		converter = new Convertors(this, LeftButtomX, TopRightX, LeftButtomY, TopRightY);
		
	}

	
	/*
	 * load image from given path and create converter object.
	 */
	public Map(String path) {
		try {
			myMap = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mapWidth = myMap.getWidth();
		mapHeight = myMap.getHeight();

		converter = new Convertors(this, LeftButtomX, TopRightX, LeftButtomY, TopRightY);
	}
/*	
	public double dis2 (int [] p0, int [] p1) {
		Point3D p = converter.pixel2Gps(p0[0], p0[1]);
		Point3D p2 = converter.pixel2Gps(p1[0], p1[1]);

		double dis = p.distance3D(p2);
		return dis;

	}*/
	
	public double getMapWidth() {
		return mapWidth;
	}

	public double getMapHeight() {
		return mapHeight;
	}
	
	public Convertors getConverter() {
		return converter;
	}
	
	public BufferedImage getMyMap() {
		return myMap;
	}

}