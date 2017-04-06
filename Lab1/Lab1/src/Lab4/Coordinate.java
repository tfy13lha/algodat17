package Lab4;

public class Coordinate {

	private double x;
	private double y;
	private int yIndex;
	private int xIndex;

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setXIndex(int yi) {
		yIndex = yi;
	}

	public int getXIndex() {
		return yIndex;
	}
	
	public void setYIndex(int xi) {
		xIndex = xi;
	}

	public int getYIndex() {
		return xIndex;
	}
}
