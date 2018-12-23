package File_format;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/*
 * this class read the content of CSV 
 */
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
			
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				data.add(userInfo);
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return data;
	}
	public static void main(String[] args) {
		String path="C:\\Users\\YuvalAmar\\Desktop\\WigleWifi_20171201110209.csv";
		ArrayList<String[]> data;
		data=CSVReader.data(path);
		String[] a=data.get(2);
		String actual=a[1];	
		System.out.println(actual);
		
	}
	
	
}