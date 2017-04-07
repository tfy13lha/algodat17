package Lab4;

import java.util.Comparator;

public class YCoordinateComparator implements Comparator<Coordinate> {

	@Override
	public int compare(Coordinate c1, Coordinate c2) {
		if (c1.getY() > c2.getY()) {
			return 1;
		} else if (c1.getY() < c2.getY()) {
			return -1;
		}
		return 0;
	}

}
