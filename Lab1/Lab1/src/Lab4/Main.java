package Lab4;

public class Main {

	public static void main(String[]args){
		
		String fileName = "points.txt";
		
		if(args.length > 0){
			fileName = args[0];
		}
	
		ClosestPair pair = new ClosestPair(fileName);
		
		pair.writeData();
	}
}
