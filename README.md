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
    
    
    
    
  # OOP_EX3
  
  information about how we insert pacmens and fruits in the right place in the pixels from gps cordinate and the oppsite way.
  we create class range we get 
  
  
  
   # The Pacman Game

Wellcom to Dvir&Yuval Pacman game!

This game gets Pacmans and fruits and moves each Pacman to his shortest path to his fruits. 

some information about the convert that we made for inserting the right points of pacman or fruit.

class range - this class gets a range and return the realation of the point in this range.
              this class helps us to  know the exact location of the pixel that the gps cordinate represnt.
              
class convert - this class use the calculation of that comes from the range class and convert from gps cordinate to pixels 
                the opsite way.we use the the resolution of the the ariel1.png file to make the right relation.
                
              



# Game variables

  #Game board

Game Board - a real map of Ariel University.

  #Pacman

contains data such: 
Location based on GPS cordinate 
Speed M/S.
Radius eating.
have the ability to move on the board.

  #Fruit

Contains data such : 
Location based on GPS cordinate.
Points.
The fruits doesnt have the ability to move on the board.


  #Start new game

#How to create new Game

there is 2 ways to create a new game:

1)adding fruits by clicking on the ADD button (Pacman or Fruit) and then clickig on the specfied location on the map.
2)addind exsiting game by clickig on the Load File button.
  by clicking Load File button you add fruits and pacmans by reading the CSV.
  
  #Run the Game
  
  after adding some fruits and Pacmans you should click on BUILD GAME button and then on RUN button.
  
  BUILD GAME - Build Game button shows you the path for each pacman.
               each path have diffrent color.
               
  RUN - makes the Pacmans move on their paths.
        the pacman actualy move from fruit to fruit.
  
  
   #Export the Game
   
   there is 2 diffrent ways to export the game.
   the SAVE FILE bouttn in game borad including tow optins.
    
   1)New CSV file
     create CSV file with the location and the data of the fruits and pacmans that add to the game. 
   2)KML file
     create KML file that shows the pacmnas and the fruit in google Erth.
     you can also see the movement of the pacmans in his path,based on the eating time for each fruit.
      
      
   Enjoy the game!! 

<p><img src="https://github.com/yuvalamar75/Ex2-Ex4.1/blob/master/classDiagramEx3.jpg" alt="" /><br /> <br /> Enjoy the game!!</p>
    
    
               
