package Lab3;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge e1, Edge e2) {
		if (e1.cost > e2.cost) {
			return 1;
		} else if (e1.cost < e2.cost) {
			return -1;
		}
		return 0;
	}

}
