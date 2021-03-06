package GIS;

import java.io.BufferedWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Algorithmes.GISLayer;
import Algorithmes.GISProject;
import Algorithmes.ToGisElement;
import File_format.CSVReader;


/**
 * this class represent game.
 * it can be build either by given path or either by given fruits and pacmans.
 * @author YuvalAmar and DvirHacohen
 */

public class Game {
	private GIS_layer packmans;
	private GIS_layer fruits;
	private GIS_project game;
	private ArrayList<Packman> packmansOrigins;

	
	public Game(Stack<GIS_layer> project) {

		packmans = project.pop();
		fruits = project.pop();

	}
	/**
	 * 
	 * @param path the path in computer where the file is
	 */
	public  Game(String path) {

		packmans = new GISLayer(new HashSet<>());
		fruits = new GISLayer(new HashSet<>());
		game = new GISProject(new HashSet<>());

		ArrayList<String[]> data=CSVReader.data(path);

		for (int i = 0; i < data.size(); i++) {
			String[] temp = data.get(i);
			if(temp[0].equals("P")) {
				
				Packman tempP = new Packman(temp);
				packmans.add(tempP);

			}else {
				Fruit tempf = new Fruit(temp);
				fruits.add(tempf);
			}	
		}
		game.add(fruits);
		game.add(packmans);

	}

/**
 * 
 * @param p game p
 * @param output puts the csv file in that output path
 */
	public void project2csv(Game p,String output) {
		ArrayList<String> content =new ArrayList<String>();
		String title = "Type#Id#Lat#Lon#Alt#Speed/Weight#Radius\n";
		content.add(title);

		GIS_layer packmans=this.getPackmanLayer();
		GIS_layer fruits=this.getFruitLayer();
		Iterator<GIS_element> fit = fruits.iterator();
		Iterator<GIS_element> pit = packmans.iterator();

		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);

			while(pit.hasNext()) {

				Packman ptemp= (Packman) pit.next();
				content.add(ptemp.getType()+"#"+ptemp.getID()+"#"+ptemp.getPoint3d().get_y()+"#"+ptemp.getPoint3d().get_x()+"#"+ptemp.getPoint3d().get_z()+"#"+ptemp.getSPEED()+"#"+ptemp.getR());
				content.add("\n");
			}


			while(fit.hasNext()) {
				Fruit ftemp=(Fruit) fit.next();
				System.out.println(ftemp.getType()+"#"+ftemp.getId());
				content.add(ftemp.getType()+"#"+ftemp.getId()+"#"+ftemp.getPoint3d().get_y()+"#"+ftemp.getPoint3d().get_x()+"#"+ftemp.getPoint3d().get_z()+"#"+ftemp.getWeight()+"#"+ftemp.getRadiusf());
				content.add("\n");
			}

		
			String data = content.toString().replace("[", "").replace("]", "").replaceAll(" ", "").replace(",", "").replace("#", ",");
			bw.write(data);
			bw.close();

		}
		catch (IOException e) {
			e.printStackTrace();}
	}

	public GIS_layer getFruitLayer() {
		return fruits;
	}
	
	public GIS_layer getPackmanLayer() {
		return packmans;
	}
	
	public ArrayList<Packman> getPackmansOrigins() {
		return packmansOrigins;
	}
	
}
