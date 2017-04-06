package Lab4;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ClosestPair {
	
	ArrayList<Point> points; 
	
	public ClosestPair(String fileName){
		
		points = new ArrayList<Point>();
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
				points.add(new Point(x , y));
			}
			
			while(input.hasNextLine()){
				if(input.next().contains("EOF")){
					break;
				}
				double x = input.nextDouble();
				double y = input.nextDouble();
				points.add(new Coordinate(x , y));	
			}
			
			Collections.sort(points, new PointComparator());
			
			System.out.println("Läste in all data");
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Kunde inte hitta filen");
			e.printStackTrace();
		}
		
	}
}
