package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import Algorithmes.GISLayer;
import Algorithmes.ToGisElement;
import Algorithms.NextStep;
import Algorithms.ShortestPathAlgo;
import Coords.MyCoords;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Packman;

/*
 * this class crate new kml file from csv. 
 */
public class csv2kml {
		/*
		 * This function get path of csv file,convert the file to kml and save in the destination we get.
		 * @param String for input path.
		 * @param String for output path 
		 */
		public static void writeFileKML(Game g,String output) {
			ShortestPathAlgo SPA = new ShortestPathAlgo(g);
			SPA.Rotation();
			GIS_layer fruits = g.getFruitLayer();
			GIS_layer packmans = g.getPackmanLayer();
			ArrayList<String> content =new ArrayList<String>();
			int counter = 0;

			String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
					"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
			String kmlduc = "<Document>";
			String kmlduce = "</Document>";
			String kmlend = "</kml>";
			content.add(kmlstart);
			content.add(kmlduc);
			String color = "yellowLineGreenPoly";

			try{
				FileWriter fw = new FileWriter(output);
				BufferedWriter bw = new BufferedWriter(fw);
				if(!packmans.isEmpty()) {
					for(GIS_element tempE : packmans) {
						counter++;
						Packman tempP = (Packman) tempE;
						Queue<NextStep> tempQ = tempP.getPath();
						System.out.println(tempQ.isEmpty());
							String kmlStart = "<name>\n"+"Path "+counter+"\n"+"</name>\n"+
									"<description>\n"+
									"Path for pacman\n"+"</description>\n"+
									"<Style id="+""+color+">\n"+
									"<LineStyle>\n"+
									"<color>7f00ffff</color>\n"+
									"<width>\n"+"4\n"+"</width>\n"+
									" </LineStyle>\n"+
									"<PolyStyle>\n"+
									"<color>\n"+"7f00ff00"+"</color>\n"+
									"</PolyStyle>\n"+
									"</Style>\n"+
									"<Placemark>\n"+
									"<name>\n"+"Absolute Extruded\n"+"</name>\n"+
							"/<description>\n"+"Transparent green wall with yellow outlines\n"+
									"</description>\n"+
			      "<styleUrl>\n"+color+"\n"+"</styleUrl>\n"+
			      "<LineString>\n"+
			        "<extrude>\n"+
			      "1"+"\n"+
			        "</extrude>\n"+
			        "<tessellate>\n"+"1"+"\n"+"</tessellate>\n"+
			        "<altitudeMode>\n"+"absolute\n"+"</altitudeMode>\n"+
			       " <coordinates>\n";
						content.add(kmlStart);
						
						while(!tempQ.isEmpty()) {
							NextStep tempNS = tempQ.poll();
						String kmlCordinate = tempNS.getFruit().getPoint3d().get_y()+","+tempNS.getFruit().getPoint3d().get_x()+"\n";
						System.out.println(kmlCordinate);
						content.add(kmlCordinate);		
						}
						String kmlEnd = "</coordinates>\n"+
					      "</LineString>\n"+
						    "</Placemark>\n";
						content.add(kmlEnd);
						
						}
					}
				
				/*if(!fruits.isEmpty()) {
					for(GIS_element tempE : fruits) {
						Fruit tempF = (Fruit) tempE;  
						String kmlelement =
								"<Placemark>\n" +
								"<name>"+tempF.getID()+"</name>\n" +
								"<description>\n"+tempF.getData()+"/n"+"</description>\n" +
								"<Point>\n" +
								"<coordinates>"+tempF.getPoint3d().get_y()+"&"+tempF.getPoint3d().get_x()+"&"+tempF.getPoint3d().get_z()+"</coordinates>" +
								"</Point>\n" +
								"</Placemark>\n";
						content.add(kmlelement);
					}*/
				/*if(!packmans.isEmpty()) {
					for(GIS_element tempE : packmans) {
						Packman tempP = (Packman) tempE;  
						String kmlelement ="<Placemark>\n" +
								"<name>"+tempP.getID()+"</name>\n" +
								"<description>\n"+tempP.getData()+"/n"+"</description>\n" +
								"<Point>\n" +
								"<coordinates>"+tempP.getPoint3d().get_y()+"&"+tempP.getPoint3d().get_x()+"&"+tempP.getPoint3d().get_z()+"</coordinates>" +
								"</Point>\n" +
								"</Placemark>\n";
						content.add(kmlelement);
					}
				}*/
				content.add(kmlduce);
				content.add(kmlend);
				String csv = content.toString().replaceAll(",", "").replace("[", "").replace("]", "");
				csv=csv.replaceAll("&", ",");
				//System.out.println(csv);
				bw.write(csv);
				bw.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void main(String[] args) {
			String csvFile = "C:\\Users\\YuvalAmar\\Desktop\\data\\game_1543684662657.csv";
			String output = "C:\\Users\\YuvalAmar\\Desktop\\tetKML.kml";
			Game g = new Game(csvFile);
			writeFileKML(g,output);

}
}

