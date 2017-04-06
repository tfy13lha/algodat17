package Lab4;

import java.util.Comparator;

public class CoordinateComparator implements Comparator<Coordinate> {

	@Override
	public int compare(Coordinate c1, Coordinate c2) {
		if (c1.getX() > c2.getX()) {
			return 1;
		} else if (c1.getX() < c2.getX()) {
			return -1;
		}
		return 0;
	}

}
