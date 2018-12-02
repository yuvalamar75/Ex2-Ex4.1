package Algorithmes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.sound.sampled.DataLine;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.glassfish.external.statistics.Statistic;

import File_format.CSVReader;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;


public class MultiCSV {
	
	/*
	 * This class scan file and make from all the csv files a project.
	 * the project have set of layers that contains Giselements.
	 * the kml file split for each layer that in the project . 
	 */
	static ArrayList<String> content;
	static ArrayList<String> files;
	public static GISProject multyCSV(String parentDirectory){
		files = new ArrayList<>();//arrayslist that contain all the path for csv files in the folder.
		File[] filesInDirectory = new File(parentDirectory).listFiles();
		for(File f : filesInDirectory){
			if(f.isDirectory()){
				multyCSV(f.getAbsolutePath());
			}
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
			if("csv".equals(fileExtenstion)){
				files.add(filePath);
				System.out.println("CSV file found -> " + filePath);
			}
		}
		Set<GIS_layer> pro = new HashSet<>();
		GISProject project=new GISProject(pro);
		//creating new project and layers that will contain GISelemnts.
		for (int i = 0; i < files.size(); i++) {
			ArrayList<String[]> data=CSVReader.data(files.get(i));
			Set<GIS_element> set = new HashSet<>();
			GISLayer datalayer=new GISLayer(set);
			for (int j = 0; j < data.size(); j++) {
				ToGisElement element= new ToGisElement(data.get(j));
				datalayer.add(element);

			}
			project.add(datalayer);
		}

		return  project;
	}

	
	/*
	 * This function get GISProject and Path of file that we want to write on it,
	 * and converts all the elements to kml file.
	 */
	public static void MultyCsvToKml(GISProject project,String output) {
		//template for kml start.
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		String kmlduc = "<Document>";
		String kmlduce = "</Document>";
		String kmlend = "</kml>";
		String kmlfolders="<Folder> \n";
		String kmlfoldere="</Folder> ";
		String kmlnamefs="<name> \n";
		String kmlnamefe="</name>";
		String kmlfoldername="";
		content= new ArrayList<>();
		content.add(kmlstart);
		content.add(kmlduc);
		int counter=0;
		Iterator<GIS_layer> pro=project.iterator();
		Set<GIS_element> set = new HashSet<>();
		GIS_layer temp=new GISLayer(set) ;
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			while(pro.hasNext()) {
				temp=pro.next();//getting the layers in the project
				Iterator<GIS_element> it=temp.iterator();
				counter++;//counter for folders in the kml.
				content.add(kmlfolders);
				content.add(kmlnamefs);
				kmlfoldername=Integer.toString(counter);
				content.add(kmlfoldername);
				content.add(kmlnamefe);
				
						
				while(it.hasNext()) {
					
					ToGisElement tempgis=(ToGisElement) it.next();//getting the elements in the layers.
					String kmlelement ="<Placemark>\n" +
							"<TimeStamp>\n"
							+ "<when>"+tempgis.gettime()+"</when>\n </TimeStamp>\n"+
							"<name>"+tempgis.getname()+"</name>\n" +
							"<description>\n"+tempgis.getData()+"/n"+"</description>\n" +
							"<Point>\n" +
							"<coordinates>"+tempgis.getPoint().get_y()+"&"+tempgis.getPoint().get_x()+"&"+tempgis.getPoint().get_z()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelement);
				
				}
				content.add(kmlfoldere);
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
	public static void main (String[] args) {
		String input ="D:\\test";
		GISProject a=multyCSV(input);
		System.out.println(a.get_Meta_data());
		String output="D:\\test\\new.kml";
		MultyCsvToKml(a, output);
		
		
	}
}