# OOP_EX2-Ex4
This project works with GPS points.

The project gets file path then scan all the csv file that contains gps cordinate and some other data,then we convert them to kml file.
every line in csv file will be GISelement.
every csv file will be GISlayer.
folder that contains some csv files will be GISproject.

 

# Packges:

   # Algorithms:
   
    classe contains:GISLayer,GISLayerProjectMD,GISProject,MetaData,MultiCSV,ToGisElement.
    MultiCSV:the main class that convert from project to kml file.
    GISLayer,GISLayerProjectMD,GISProject,MetaData,ToGisElement:clsses that crate each element.
    
  # Coords:
   classes contains:coords_converter,MyCoords,MyCoordsTest
   coords_converter,MyCoords:This class excecute Math calculation on GPS cordinates.
   
  # FileFormat:
   classe contains:csv2kml,CSVReader,CSVReaderTest.
   CSVReader:read from csv file and return the content in arraylist.
   csv2kml:get the data from the arraylist and convet to kml file.
   
  # Geom:
   classe contains:Geom_element,Geom_element,Point3D.
   Geom_element:caluate distance 3d/2d.
   Point3D:This class excecute Math calculation on Point3D.
   
   # GIS:
    classe contains:GIS_element,GIS_layer,GIS_project,Meta_data.
    GIS_element,GIS_layer,GIS_project,Meta_data:the intefaces that we used in Algorithms.
    
    
   
  
