package Lab4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		double tol=1e-10;
		
		try {
			File f=new File("closest-pair-out.txt");
			int totalFiles=0,totalCorrect=0;
			Scanner sc=new Scanner(f);
			while(sc.hasNext()){
				totalFiles++;
				String filename=sc.next();
				filename=filename.substring(filename.lastIndexOf('/')+1,filename.length()-1);
				filename=filename.replace(".tsp", "-tsp.txt");
				
				int correctN=sc.nextInt();
			
				double correctDist=Double.parseDouble(sc.next());
				
				ClosestPair cp=new ClosestPair(filename);
				int calcN=cp.getN();
				double calcDist=cp.getDistance();
				
				if (correctN!=calcN){
					System.out.println(filename+ ": NOK, calcN="+calcN+", correctN="+correctN);
					//continue;
					continue;
				}
				double diffDist=calcDist-correctDist;
				if (Math.abs(diffDist)>tol) {
					System.out.println(filename+ ": NOK, diff="+diffDist);
					continue;
				}
				totalCorrect++;
				System.out.println("     "+filename+" "+calcN+", "+calcDist);
				
			}
			System.out.println("Correct "+totalCorrect+"/"+totalFiles);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}

}
