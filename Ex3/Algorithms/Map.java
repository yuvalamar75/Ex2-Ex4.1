package Algorithms;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import javafx.geometry.Point2D;

public class Map {

	BufferedImage myMap;
	// This map would show Germany:
	static double north = Math.toRadians(35.20236);
	static double east = Math.toRadians(32.10569);
	static double south = Math.toRadians(35.20238);
	static double west = Math.toRadians(32.10190);
	static double pixelWidth;
	static double pixelHeight;
	public Map() {
		try {
			myMap = ImageIO.read(getClass().getResourceAsStream("/GUI/Ariel1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pixelWidth = myMap.getWidth();
		pixelHeight = myMap.getHeight();
	}

	public static Point2D convertLatLongToCoord (Point2D coordinate) {
		long  x = (long) coordinate.getY();
		long  px = (long) (pixelWidth * (x-east) / (west-east));
		long y = (long) coordinate.getX();
		long py = (long) (pixelHeight * (y-north)/(south-north));
		Point2D temp = new Point2D(px,py);

		return temp;
	}

	public static void main (String[] args) {
		Point2D test = new Point2D(35.21235, 32.10194);
		Point2D after = convertLatLongToCoord(test);
		System.out.println(after.getX()+","+after.getY());




	}



	/*	}
	// Formula for mercator projection y coordinate:
		public double  mercY(double lat) {
			return Math.log(Math.tan((lat/2) + (Math.PI/4)));
			}
		double ymin = mercY(south);
		double ymax = mercY(north);
		double xFactor = pixelWidth/(east - west);
		double yFactor = pixelHeight/(ymax - ymin);



		// Some constants to relate chosen area to screen coordinates

		public double[]  mapProject(double lat, double lon) { // both in radians, use deg2rad if neccessary
		  double[] array = new double[2];
		  double x = lon;
		  double y = mercY(lat);
		  x = (x - west)*xFactor;
		  y = (ymax - y)*yFactor; // y points south
		  array[0]=x;
		  array[1]=y;
		  return array;

		}	
		public static void main(String[] args) {
			Map test = new Map();
			System.out.println(Arrays.toString(test.mapProject(35.21235, 32.10194)));
		}*/
}





/*



	}





 */