package Lab3;

public class Main {

	public static void main(String[]args){
			
		String fileName = "USA-highway-miles.txt";
		SpanningUSA marriage = new SpanningUSA(fileName);
		marriage.writeData();
		
	}
}

