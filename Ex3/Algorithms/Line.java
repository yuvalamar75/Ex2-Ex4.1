
package Algorithms;

import com.sun.prism.paint.Color;


import Geom.Point3D;

/**
 * this class represents line between 2 points
 * line has 2 points and color
 */

public class Line {
	
	private Point3D source;
	private Point3D target;
	private java.awt.Color color;


	public Line(Point3D p1, Point3D p2, java.awt.Color color) {
		source = p1;
		target = p2;
		this.color = color;
	}

	public Point3D getSource() {
		return source;
	}

	public Point3D getTarget() {
		return target;
	}
	
	public java.awt.Color getColor() {
		return color;
	}

	public void setColor(java.awt.Color color) {
		this.color = color;
	}

}