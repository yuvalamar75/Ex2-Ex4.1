package GIS;

import java.util.Iterator;

public class test {
	public static void main(String[] args) {
		
		Game g = new Game("D:\\\\test\\\\packman\\\\Ex3_data\\\\data\\\\game_1543693911932_a.csv");
		
	/*	GIS_layer aa=g.getFruitLayer();
		//System.out.println(aa.isEmpty());
		Iterator<GIS_element> fit=aa.iterator();
		while(fit.hasNext()) {
			Fruit temp=(Fruit) fit.next();
			System.out.println(temp.tostring());
		}
		
		System.out.println(aa.get_Meta_data());*/
		String s = "D:\\test\\s.csv";
		g.project2csv(g, s);
	}

}
