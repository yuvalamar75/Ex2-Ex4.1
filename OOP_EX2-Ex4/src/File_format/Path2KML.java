package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import Algorithmes.GISLayer;
import Algorithmes.ToGisElement;
import Algorithms.Line;
import Algorithms.Map;
import Algorithms.NextStep;
import Algorithms.ShortestPathAlgo;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Packman;
import GUI.Board;
import GUI.Board.PacmanThread;
import Geom.Point3D;

/**
 * this class crate new kml file from csv. 
 */
public class Path2KML {
	/*//http://www.clker.com
	 * This function get path of csv file,convert the file to kml and save in the destination we get.
	 * the KML also include timestamp for each fruit and pacman that shows the path of each pacman.
	 * @param String for input path.
	 * @param String for output path 
	 */
	NextStep[] nextSteps;
	static final Long duration = (long) (((120 * 60)) * 1000);
	static long pacmanTime;
	static long fruitTime;
	static  String fruitTimeStamp;
	static Point3D cuurentP;
	static long totalTime;
	static Queue<NextStep> arrNextStep ;

	public static void writeFileKML(Game g , String output, ArrayList<PacmanThread> pacmanThread) {

		long pacmanTime = System.currentTimeMillis()-duration;
		String PacmanKMLtime= kmlTime(pacmanTime);

		GIS_layer packmans = g.getPackmanLayer();
		ArrayList<String> content =new ArrayList<String>();

		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		String kmlduc = "<Document>";
		String kmlduce = "</Document>";
		String kmlend = "</kml>";
		content.add(kmlstart);
		content.add(kmlduc);


		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);

			for(GIS_element tempE : packmans) {
				Packman tempP = (Packman) tempE;
				cuurentP = new Point3D(tempP.getPoint3d());

				//adding the pacmans to the kml
				String data = "Type : "+tempP.getType()+",\n"+"ID : "+tempP.getID()+",\n"+"Speed : "+tempP.getSPEED()+",\n"+"Score : "+tempP.getSum();
				String kmlelement ="<Placemark>\n" +
						"<Style id="+"\"Pacman\""+">"+
						"<IconStyle>"+
						" <Icon>"+
						"<href>http://www.clker.com/cliparts/2/7/3/a/1225215149389018376mbtwms_Pacman.svg.med.png</href>"+
						"</Icon>"+
						" </IconStyle>"+
						"</Style>"+
						"<name>"+tempP.getID()+"</name>\n" +
						"<description>\n"+data+"/n"+"</description>\n" +
						"<TimeStamp>"+
						" <when>"+PacmanKMLtime+"</when>"+
						"</TimeStamp>"+
						"<Point>\n" +
						"<coordinates>"+tempP.getPoint3d().get_x()+"&"+tempP.getPoint3d().get_y()+"&"+tempP.getPoint3d().get_z()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";
				content.add(kmlelement);

				String cordinate = "";
				
				//adding the line for each pacman to the KML.
				
				String kmlElement = 
						"<name>"+"Path Pacman"+"</name>"+
								" <description>"+"Examples of paths. Note that the tessellate tag is by default"+
								"</description>"+
								"<Style id=\"yellowLineGreenPoly\">"+
								"<LineStyle>"+
								"<color>7f00ffff</color>"+
								"<width>10</width>"+
								" </LineStyle>"+
								"<PolyStyle>"+
								"<color>7f00ff00</color>"+
								" </PolyStyle>"+
								" </Style>"+
								"<Placemark>"+
								" <name>Absolute Extruded</name>"+
								"<description>Transparent green wall with yellow outlines</description>"+
								" <styleUrl>#yellowLineGreenPoly</styleUrl>"+
								"<LineString>"+
								"<extrude>1</extrude>"+
								" <tessellate>1</tessellate>"+
								"<altitudeMode>relativeToGround</altitudeMode>"
								+"<coordinates>\n"+
								cuurentP.get_x()+"&"+cuurentP.get_y()+"&"+cuurentP.get_z();
				content.add(kmlElement);
				for(Point3D p : tempP.getMovements()) {

					Point3D tempPt = p;

					//if(tempPt.get_x()!=0 && tempPt.get_y()!=0 && tempPt.get_z()==0) 
					cordinate =cordinate +"\n"+
							tempPt.get_x()+"&"+tempPt.get_y()+"&"+tempPt.get_z()+"\n";
				}
				content.add(cordinate);
				String kmlCordinateEnd =  "\n</coordinates>"+
						"</LineString>"+
						"</Placemark>\n";  
				content.add(kmlCordinateEnd);

				
				//adding the fruit with the time that they were eaten.
				
				arrNextStep = tempP.getPath();
				for(NextStep next : arrNextStep) {
					NextStep nextT = next;
					Fruit tempF = nextT.getFruit(); 
					long fruitTime = (long) nextT.getTime();
					totalTime = totalTime + fruitTime;
					Point3D f = new Point3D(tempF.getPoint3d());
					fruitTime = pacmanTime + TimeUnit.SECONDS.toMillis(totalTime);
					fruitTimeStamp = kmlTime(fruitTime);		
					String dataF = "Type : "+tempF.getType()+",\n"+"ID : "+tempF.getId()+",\n"+"Score : "+tempF.getWeight();
					String kmlFruit ="<Placemark>\n" +
							"<Style id="+"\"cherry\""+">"+
							"<IconStyle>"+
							" <Icon>"+
							"<href>http://www.clker.com/cliparts/6/e/8/4/1206561522317245030Rocket000_fruit-cherries.svg.med.png</href>"+
							"</Icon>"+
							" </IconStyle>"+
							"</Style>"+
							"<name>"+tempF.getId()+"</name>\n" +
							"<description>\n"+dataF+"/n"+"</description>\n" +
							"<TimeStamp>"+
							" <when>"+fruitTimeStamp+"</when>"+
							"</TimeStamp>"+
							"<Point>\n" +
							"<coordinates>"+f.get_x()+"&"+f.get_y()+"&"+f.get_z()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlFruit);

				}
			}

			content.add(kmlduce);
			content.add(kmlend);
			String csv = content.toString().replaceAll(",", "").replace("[", "").replace("]", "");
			csv=csv.replaceAll("&", ",");

			bw.write(csv);
			bw.close();

		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * this function convert current time to form that suitable for timestamp of KML 
	 * @Param long time to convert
	 */
	public static String kmlTime (long time ) {
		Date thisDate = new Date(time);
		String timeToKML = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(thisDate);
		timeToKML = timeToKML.replace(" ", "T");
		timeToKML= timeToKML + "Z";
		return timeToKML; 

	}

}
