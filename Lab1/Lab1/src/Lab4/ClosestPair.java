package Lab4;

import java.util.List;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClosestPair {

	ArrayList<Coordinate> points;
	ArrayList<Coordinate> xPoints;
	ArrayList<Coordinate> yPoints;
	private double distance;
	int[] xToY;

	public ClosestPair(String fileName) {

		points = new ArrayList<Coordinate>();
		readData(fileName);

		distance = Algorithm();
	}
	
	public void writeData(){
		if(Math.round(distance) == distance){
			int idistance = (int) Math.round(distance);
			System.out.println(points.size() + " " + idistance);
		}else{
		System.out.println(points.size() + " " + distance);}
	}

	private void readData(String fileName) {
		try {
			Scanner input = new Scanner(new File(fileName));

			String line = input.nextLine();

			if (line.contains(":")) {
				while (input.hasNextLine()) {
					if (input.nextLine().contains("NODE_COORD_SECTION")) {
						break;
					}
				}
			} else {
				double x = Double.parseDouble(line.split(" ")[1]);

				double y = Double.parseDouble(line.split(" ")[2]);
				points.add(new Coordinate(x, y));
			}

			while (input.hasNextLine()) {
				line = input.nextLine();
				if (line.contains("EOF")||line.matches("^\\w*$")) {
					break;
				}
				double x = Double.parseDouble(line.split(" ")[1]);

				double y = Double.parseDouble(line.split(" ")[2]);
				points.add(new Coordinate(x, y));
			}

			System.out.println("Läste in all data");

		} catch (FileNotFoundException e) {
			System.out.println("Kunde inte hitta filen");
			e.printStackTrace();
		}

	}

	private double Algorithm() {
		xPoints = new ArrayList<Coordinate>(points);
		yPoints = new ArrayList<Coordinate>(points);
		Collections.sort(xPoints, new XCoordinateComparator());
		Collections.sort(yPoints, new XCoordinateComparator());
		for (int i = 0; i < xPoints.size(); i++) {
			xPoints.get(i).setXIndex(i);
			yPoints.get(i).setYIndex(i);
		}
		xToY = new int[xPoints.size()];
		for (Coordinate c : xPoints) {
			xToY[c.getXIndex()] = c.getYIndex();
		}
		double dmin = Recurs(xPoints, yPoints);
		return dmin;
	}

	private double Recurs(List<Coordinate> xCoords, List<Coordinate> yCoords) {

		if (xCoords.size() == 2) {
			return calcDistance(xCoords.get(0), xCoords.get(1));
		} else if (xCoords.size() == 3) {
			double d1 = (calcDistance(xCoords.get(0), xCoords.get(1)));
			double d2 = (calcDistance(xCoords.get(0), xCoords.get(2)));
			double d3 = (calcDistance(xCoords.get(1), xCoords.get(2)));

			double dmin = Math.min(d1, d2);
			dmin = Math.min(dmin, d3);

			return dmin;
		}

		// Split lists
		int len = xCoords.size();
		int len1 = len / 2;
		List<Coordinate> xCoords1 = xCoords.subList(0, len1);
		List<Coordinate> yCoords1 = new ArrayList<Coordinate>(len1);
		Coordinate xstar = xCoords1.get(xCoords1.size()-1); // last elem of
															// xcoords1

		int len2 = len - len1;
		List<Coordinate> xCoords2 = xCoords.subList(len1, len);
		List<Coordinate> yCoords2 = new ArrayList<Coordinate>(len2);

		// split y list
		//int idx1 = 0, idx2 = 0;
		for (int i = 0; i < len; i++) {
			Coordinate c = yCoords.get(i);
			if (c.getXIndex() <= xstar.getXIndex()) {
				//yCoords1.set(idx1++, c);
				yCoords1.add(c);
			} else {
				//yCoords2.set(idx2++, c);
				yCoords2.add(c);
			}
		}

		double d1 = Recurs(xCoords1, yCoords1);
		double d2 = Recurs(xCoords2, yCoords2);
		double dmin = Math.min(d1, d2);

		double dmin2 = dmin;
		for (int i = 0; i < len; i++) {
			if (Math.abs(yCoords.get(i).getX() - xstar.getX()) <= dmin) {
				int jstop = Math.min(i + 16, len);
				for (int j = i + 1; j < jstop; j++) {
					dmin2 = Math.min(dmin2, calcDistance(yCoords.get(i), yCoords.get(j)));
				}
			}
		}
		return dmin2;

	}

	private double calcDistance(Coordinate c1, Coordinate c2) {
		double xDistance = ((c1.getX() - c2.getX()) * (c1.getX() - c2.getX()));
		double yDistance = ((c1.getY() - c2.getY()) * (c1.getY() - c2.getY()));
		double distance = Math.sqrt(xDistance + yDistance);
		return distance;
	}
}
