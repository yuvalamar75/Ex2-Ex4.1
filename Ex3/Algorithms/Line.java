package Algorithms;

import Geom.Point3D;

public class Line {
	
	private Point3D source;
	private Point3D target;
	


	public Line(Point3D p1, Point3D p2) {
		source = p1;
		target = p2;
	}
	
	public Point3D getSource() {
		return source;
	}

	public Point3D getTarget() {
		return target;
	}

}
