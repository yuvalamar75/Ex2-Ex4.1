package GIS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Algorithmes.GISLayer;
import Algorithmes.GISProject;
import Algorithmes.ToGisElement;
import File_format.CSVReader;

public class Game {
	GIS_layer packmans;
	GIS_layer fruits;
	GIS_project game;


	
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
	public void project2csv(Game p,String output) {
		ArrayList<String> content =new ArrayList<String>();
		//Game g = new Game();
		
		GIS_layer packmans=p.getPackmanLayer();
		GIS_layer fruits=p.getFruitLayer();
		//System.out.println(fruits.isEmpty());
		Iterator<GIS_element> fit = fruits.iterator();
		Iterator<GIS_element> pit = packmans.iterator();

		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			while(pit.hasNext()) {
				Packman ptemp=(Packman) pit.next();
				content.add(ptemp.tostring());
			}
			while(fit.hasNext()) {
				Fruit ftemp=(Fruit) fit.next();
				content.add(ftemp.tostring());
			}
		
				String data = content.toString().replace("[", "").replace("]", "");
				System.out.println(data);
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
		}
