import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class compareResults {

	public static void main(String[]args) throws FileNotFoundException{
		Scanner out = new Scanner(new File("out.txt"));
		Scanner result = new Scanner(new File("outcompare.txt"));
		boolean decide = true;
		
		while(out.hasNextLine() && result.hasNextLine()){
			String outNext = out.nextLine();
			String resultNext = result.nextLine();
			if(!(outNext.equals(resultNext))){
				
				decide = false;
			}
		}
		if(decide){
			System.out.println("Filerna stämmer!");
		}
		else{
			System.out.println("Filerna stämmer INTE.");
		}
	}
}
