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
import Geom.Point3D;

/*
 * this class crate new kml file from csv. 
 */
public class csv2kml {
	/*//http://www.clker.com
	 * This function get path of csv file,convert the file to kml and save in the destination we get.
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
	static ArrayList<NextStep> arrNextStep = new ArrayList<>();
	
	public static void writeFileKML(Game g , String output) {
		
		long pacmanTime = System.currentTimeMillis()-duration;
		String PacmanKMLtime= kmlTime(pacmanTime);
		
		GIS_layer packmans = g.getPackmanLayer();
		GIS_layer fruits = g.getFruitLayer();

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
				Queue<NextStep> tempNS = tempP.getPath();
				System.out.println(tempNS.isEmpty());
				for (NextStep next : tempNS) {
					NextStep temp = new NextStep(next);
					arrNextStep.add(temp);	
				}
				String data = "Type : "+tempP.getType()+",\n"+"ID : "+tempP.getID()+",\n"+"Speed : "+tempP.getSPEED()+"Score : "+tempP.getSum();
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
				
				String kmlElement = 
						"<name>"+"Paths"+"</name>"+
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
				for(NextStep next : arrNextStep) {
					NextStep nextT = next;
					System.out.println("time is : "+nextT.getTime());
					Point3D f = new Point3D(next.getFruit().getPoint3d());

					cordinate =cordinate +"\n"+
							f.get_x()+"&"+f.get_y()+"&"+f.get_z()+"\n";
				}
				content.add(cordinate);
				
				String kmlCordinateEnd =  "\n</coordinates>"+
						"</LineString>"+
						"</Placemark>\n";  
				content.add(kmlCordinateEnd);
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
						       " <when>"+PacmanKMLtime+"</when>"+
						      "</TimeStamp>"+
							"<Point>\n" +
							"<coordinates>"+f.get_x()+"&"+f.get_y()+"&"+f.get_z()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlFruit);
					String dataP2 = "Type : "+tempP.getType()+",\n"+"ID : "+tempP.getID()+",\n"+"Speed : "+tempP.getSPEED()+"Score : "+tempP.getSum();
					String kmlelementP2 ="<Placemark>\n" +
							"<Style id="+"\"Pacman\""+">"+
							"<IconStyle>"+
							" <Icon>"+
							"<href>http://www.clker.com/cliparts/2/7/3/a/1225215149389018376mbtwms_Pacman.svg.med.png</href>"+
							"</Icon>"+
							" </IconStyle>"+
							"</Style>"+
							"<name>"+tempP.getID()+"</name>\n" +
							"<description>\n"+dataP2+"/n"+"</description>\n" +
							   "<TimeStamp>"+
					       " <when>"+fruitTimeStamp+"</when>"+
					      "</TimeStamp>"+
							"<Point>\n" +
							"<coordinates>"+f.get_x()+"&"+f.get_y()+"&"+f.get_z()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelementP2);
					
				}
				arrNextStep.clear();
			}



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
	public static String kmlTime (long time ) {
		Date thisDate = new Date(time);
		String timeToKML = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(thisDate);
		timeToKML = timeToKML.replace(" ", "T");
		timeToKML= timeToKML + "Z";
		return timeToKML; 
		
	}


	public static void main(String[] args) {
		String csvFile = "C:\\Users\\YuvalAmar\\Desktop\\data\\game_1543684662657.csv";
		String output = "C:\\Users\\YuvalAmar\\Desktop\\tetKML.kml";
		Game g = new Game(csvFile);
		writeFileKML(g,output);
	}

}

/*	while(!tempQ.isEmpty()) {
NextStep tempNS = tempQ.poll();
String kmlCordinate = tempNS.getFruit().getPoint3d().get_y()+","+tempNS.getFruit().getPoint3d().get_x()+"\n";
System.out.println(kmlCordinate);
content.add(kmlCordinate);		
}


		/*	
			for(GIS_element tempE : packmans) {
				counter++;
				Packman tempP = (Packman) tempE;
				Queue<NextStep> tempQ = tempP.getPath();
				System.out.println(tempQ.isEmpty());
				String kmlElement = "<name>\n"+"Path "+counter+"\n"+"</name>\n"+
						"<description>\n"+
						"Path for pacman " tempP.get+\n"+"</description>\n"+
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
						"/<description>\n"+
						"path to pacman\n"+
						"</description>\n"+
						"<styleUrl>\n"+color+"\n"+"</styleUrl>\n"+
						"<LineString>\n"+
						"<extrude>\n"+
						"1"+"\n"+
						"</extrude>\n"+
						"<tessellate>\n"+"1"+"\n"+"</tessellate>\n"+
						"<altitudeMode>\n"+"absolute\n"+"</altitudeMode>\n"+
						" <coordinates>\n"+
						"</coordinates>\n"+
						"</LineString>\n"+
						"</Placemark>\n";
						content.add(kmlStart);*/


