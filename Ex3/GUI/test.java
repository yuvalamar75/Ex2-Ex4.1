package GUI;


import java.awt.Point;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JFrame;



import Algorithms.Convertors;
import Geom.Point3D;

public class test {
	public static void main(String[] args) {
		mainWindow mainwindow = new mainWindow();
		mainwindow.setVisible(true);
		
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		System.out.println(date);
	
		
		
		
	}
}
