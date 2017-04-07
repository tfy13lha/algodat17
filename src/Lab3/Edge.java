package Lab3;

public class Edge {

	public String sourceV;
	public String destinationV;
	public int cost;
	
	public Edge (String sourceV2, String destinationV2, int weight){		
		cost = weight;
		sourceV = sourceV2;
		destinationV = destinationV2;
	}
	
	public String getFrom(){
		
		return sourceV;
	}
	
	public String getTo(){
		
		return destinationV;
	}
	
	public int getCost(){
		
		return cost;
	}
}

