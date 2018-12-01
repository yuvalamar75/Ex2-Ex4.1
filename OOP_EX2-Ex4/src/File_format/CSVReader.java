package File_format;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class CSVReader{
	ArrayList<String> data;
	
	public static ArrayList<String[]> data(String s){
		String csvFile = s;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> data=new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				data.add(userInfo);
			/*	String s1=userInfo[0]+ ","+userInfo[1]+","+userInfo[2]+","+userInfo[3]+","+userInfo[4]+","+
						userInfo[5]+","+userInfo[6]+","+userInfo[7]+","+userInfo[8]+","+userInfo[9]+","+userInfo[10];
						data.add(s1);*/
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return data;
	}
	
	
}