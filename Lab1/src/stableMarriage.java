import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class stableMarriage {
	private Map<Integer, Stack<Integer>> proposerRanks;
	private Map<Integer, Stack<Integer>> chooserRanks;
	private Stack<Integer> freeProposers;
	private Set<Integer> freeChoosers;
	private Map<Integer, Integer> matches;
	private HashMap<Integer, HashSet<Integer>> poppedChoices;
	
	
	public stableMarriage (String fileName){
		
		proposerRanks = new HashMap<Integer, Stack<Integer>>();
		chooserRanks = new HashMap<Integer, Stack<Integer>>();
		freeProposers = new Stack<Integer>();
		freeChoosers = new HashSet<Integer>();
		poppedChoices = new HashMap<Integer, HashSet<Integer>>();
		matches = new HashMap<Integer, Integer>();
		
		readData(fileName);
		engagePeople();
		
	}
	

	
	public void readData(String fileName){	
		
		try {
			Scanner input = new Scanner(new File(fileName));
			input = skipLine(2, input);
			int n = Integer.parseInt(input.nextLine().split("n=")[1]);
			input = skipLine((2*n)+1, input);

			Stack<Integer> tempFreeProposers = new Stack<Integer>();
			
			while(input.hasNext()){
				String next = input.next();
				if(next.contains(":")){
					Stack<Integer> temp = new Stack<Integer>();
					int tempKey = (Integer.parseInt(next.split(":")[0]) -1);
					if((tempKey % 2) == 0){
						tempFreeProposers.push(tempKey);
						proposerRanks.put(tempKey, new Stack<Integer>());
						for(int i = 0; i < n; i++){
							temp.push(input.nextInt() -1);
						}
						for(int i = 0; i < n; i++){
							proposerRanks.get(tempKey).push(temp.pop());
						}
					}
					else{
						freeChoosers.add(tempKey);
						poppedChoices.put(tempKey, new HashSet<Integer>());
						chooserRanks.put(tempKey, new Stack<Integer>());
						for(int i = 0; i < n; i++){
							chooserRanks.get(tempKey).push(input.nextInt() -1);
						}
					}
				}
			}
			for(int i=0; i<n; i++){
				freeProposers.push(tempFreeProposers.pop());
			}
			
			
			System.out.println("Hittade filen och laddade in all data");
			
		} catch (FileNotFoundException e) {
			System.out.println("Finns ingen sådan fil");
		}
		
	}
	
	private Scanner skipLine(int m, Scanner sc) {
		for(int i = 0; i < m; i++){
			sc.nextLine();
		}
		return sc;
	}



	public void writeData() {
				try {
					PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
					
					List<Integer> matchesKeys = new ArrayList<>(matches.keySet());
					List<Integer> matchesValues = new ArrayList<>(matches.values());
					Collections.sort(matchesValues);
					Collections.sort(matchesKeys);
					LinkedHashMap<Integer, Integer> sortedMatches = new LinkedHashMap<>();
					Iterator<Integer> valueIt = matchesValues.iterator();
					
					while(valueIt.hasNext()){
						int val = valueIt.next();
						Iterator<Integer> keyIt = matchesKeys.iterator();
						
						while(keyIt.hasNext()){
							Integer key = keyIt.next();
							int comp1 = matches.get(key);
							int comp2 = val;
							
							if(comp1 == comp2){
								keyIt.remove();
								sortedMatches.put(key, val);
								break;
							}
						}
					}
					
					
					for(int k: sortedMatches.keySet()){
						writer.println((sortedMatches.get(k)+1) + " -- " + (k+1));
					}
					writer.flush();		
					
					System.out.println("Skrev all data till out.txt");
				} catch (IOException e) {
					System.out.println("Kunde inte skriva data till out.txt");
				}		
	}
	
	

	
	public void engagePeople(){
		
		//  while ∃ free man m who still has a woman w to propose to
		while(!freeProposers.isEmpty()){
			int proposer = freeProposers.pop();
			int chooser = proposerRanks.get(proposer).pop();
			// if w is free (m, w) become engaged
			if(freeChoosers.remove(chooser)){
				matches.put(chooser, proposer);
				while(!(chooserRanks.get(chooser).isEmpty())){
					int tempProposer = chooserRanks.get(chooser).pop();
					poppedChoices.get(chooser).add(tempProposer);
					if(proposer == tempProposer){
						break;
					}
				}
			}
			
			// else some pair (m', w) already exists
			else{
				/* if w prefers m to m'
            		m' becomes free
           			(m, w) become engaged
				 */ 
				if(!(poppedChoices.get(chooser).contains(proposer))){
					freeProposers.push(matches.get(chooser));
					matches.put(chooser, proposer);
					while(!(chooserRanks.get(chooser).isEmpty())){
						int tempProposer = chooserRanks.get(chooser).pop();
						poppedChoices.get(chooser).add(tempProposer);
						if(proposer == tempProposer){
							break;
						}
					}
				}
			    // else (m', w) remain engaged
				else{
					freeProposers.push(proposer);
				}
			}
		}
				
	}
}