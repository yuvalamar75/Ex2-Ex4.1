package GIS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Algorithmes.GISLayer;
import Algorithmes.GISProject;
import File_format.CSVReader;

public class Game {
	GIS_layer packmans;
	GIS_layer fruits;
	GIS_project game;


	public Game(String path) {
		
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

}
