package Lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.imageio.IIOException;

public class SpanningUSA {

	private ArrayList<Edge> edges;
	private ArrayList<String> vertices;
	private HashMap<String, ArrayList<String>> neighbours; 
	private ArrayList<Edge> spanningTree;
	
	public SpanningUSA(String fileName){
		
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<String>();
		neighbours = new HashMap<String, ArrayList<String>>();
		spanningTree = new ArrayList<Edge>();
			
		readData(fileName);
		makeSpanningTree();
	}

	private void readData(String fileName) {
	
		try {
			Scanner input = new Scanner(new File(fileName));
			
			
			while(input.hasNextLine()){
				
				String line = input.nextLine();
				
				if(line.contains("--")){
					
					String sourceCity = line.split("--")[0];
					String destinationCity = line.split("--")[1];
	
					
					if(sourceCity.contains("\"")){
						sourceCity = sourceCity.split("\"")[1];
					}
					if(destinationCity.contains("\"")){
						destinationCity = destinationCity.split("\"")[1];
					}
					else {
						destinationCity = destinationCity.split("\\[")[0].split(" +")[0];	
					}	
					int weight = Integer.parseInt(line.split("\\[")[1].split("\\]")[0]);
					Edge e = new Edge(sourceCity, destinationCity, weight);
					edges.add(e);		
				}
				else{
					
					if(line.contains("\"")){
						vertices.add(line.split("\"")[1]);
						neighbours.put(line.split("\"")[1], new ArrayList<String>());
					}
					else {
						vertices.add(line.split(" +")[0]);	
						neighbours.put(line.split(" +")[0], new ArrayList<String>());
					}
				}				
			}	
			
			System.out.println("Läste in all data");			
			
		} catch (FileNotFoundException e) {
			System.out.println("Kunde inte läsa filen");
			e.printStackTrace();
		}	
	}
	
	public void writeData(){
		
		try {
			PrintWriter writer = new PrintWriter("Distance.txt", "UTF-8");
			
			System.out.println("Skrev ut avståndet");
			
			int sum = 0;
			
			for(Edge e : spanningTree){
				sum += e.getCost();
			}
			writer.println("Totalt avstånd: " + sum);
			writer.flush();
			
		} catch (IOException e) {
			System.out.println("Kunde inte skriva ut all data");
			e.printStackTrace();
		} 
	}
	
	private boolean connected(String v1, String v2){
			Stack<String> toDoSet = new Stack<String>();
			toDoSet.add(v1);
			ArrayList<String> doneSet = new ArrayList<String>();
			
			while(!toDoSet.isEmpty()){
				String v = toDoSet.pop();
				doneSet.add(v);
				
				for(String n : neighbours.get(v)){
					
					if(n.matches(v2)){
						return true;
					}					
					if(!doneSet.contains(n)){
						toDoSet.push(n);
					}
				}
			}
		return false;
	}
	
	private void makeSpanningTree(){
		
		Collections.sort(edges, new EdgeComparator());
	
		for(Edge e : edges){	
			
			if(!connected(e.getFrom(), e.getTo())){
				spanningTree.add(e);
				neighbours.get(e.getFrom()).add(e.getTo());
				neighbours.get(e.getTo()).add(e.getFrom());
			}
			
		}	
	}
}