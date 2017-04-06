package Lab4;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClosestPair {
	
	ArrayList<Coordinate> points; 
	
	public ClosestPair(String fileName){
		
		points = new ArrayList<Coordinate>();
		readData(fileName);
	}
	
	private void readData(String fileName) {
		try {
			Scanner input = new Scanner(new File(fileName));
			
			String line = input.nextLine();
			
			if (line.contains(":")){
				while(input.hasNextLine()){
					if(input.nextLine().contains("NODE_COORD_SECTION")){
						break;
					}	
				}
			}
			else{
				double x = Double.parseDouble(line.split(" ")[1]);
				double y = Double.parseDouble(line.split(" ")[2]);
				points.add(new Coordinate(x , y));
			}
			
			while(input.hasNextLine()){
				if(input.next().contains("EOF")){
					break;
				}
				double x = input.nextDouble();
				double y = input.nextDouble();
				points.add(new Coordinate(x , y));	
			}
			
			Collections.sort(points, new CoordinateComparator());
			
			System.out.println("Läste in all data");
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Kunde inte hitta filen");
			e.printStackTrace();
		}
		
	}
}
