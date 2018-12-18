package Algorithms;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Algorithmes.GISLayer;
import Algorithmes.GISProject;
import Algorithmes.MultiCSV;
import File_format.CSVReader;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Packman;

public class loadFile {
	private File file;
	private String filePath;
	
	
	public loadFile(File file) {
		this.setFile(file) ;
		this.setFilePath(file.getAbsolutePath());
		
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	

}
