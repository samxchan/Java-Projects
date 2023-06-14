import java.util.*;

/*
 * Stephen Chan
 * 4/12/13
 * pd.8
 * Bellman-Ford	
 */

public class BellmanFord_StephenChan<E> {
	
	private HashMap<String, HashMap<String, Integer>> graph;
	
	public BellmanFord_StephenChan(){
		//if(already present)
		graph = new HashMap<String, HashMap<String, Integer>>();
	}
	
	//adds a node to the map
	public void addNode(String word){
		
		graph.put(word, new HashMap<String, Integer>());
	}
	
	//adds an arc between two nodes and has it pointing from source to destination with a certain weight
	public void addArc(String source, String destination, int weight){
		
		//if nodes are not found
		graph.get(source).put(destination, weight);
	}
	
	//adds an edge between the two nodes and adds a certain weight inbetween
	public void addEdge(String source, String destination, int weight){
		
		//if nodes are not found
		if(!graph.containsKey(source) || !graph.containsKey(destination)){
			
			throw new IllegalArgumentException();
		}
		
		else{
			
			graph.get(source).put(destination, weight);
			graph.get(destination).put(source, weight);
		}
		
	}
	
	public void BellmanFord(String source){
		
		//A[i,v] = min { A[i-1,v], min {(w,v) in E {A[i-1,w] + C(w,v} }
		//Where C represents costs
		//And E represents the set of edges
		Integer[] previous = new Integer[graph.size()-1];
		Integer[] current = new Integer[graph.size()-1];
		previous[0] = 0;
		for(int i = 1; i < previous.length-1; i++){
			
			current[i] = Integer.MAX_VALUE;
		}
		
		Set<String> neighbors = graph.get(source).keySet();
		Object[] s = neighbors.toArray();
		
		for(int i=1; i<previous.length-1; i++){
			
			for(int k = 1; k<neighbors.size()-1; k++){
				
				
				current[k] = graph.get(source).get(s[k]);
				current[k]=Math.min(current[k], previous[k] + current[k]);	
			}
		}
		
		for(int p=1; p<previous.length; p++){
			
			previous[p] = current[p];
		}
		
		for(int i=0; i<current.length-1; i++){
			
			System.out.println(" | C | P |");
			System.out.println("-|---|---|");
			System.out.println(i + "| " + s[i] +" | " + s[i+1] + " |"); 
			System.out.println("-|---|---|");
		}
	}
	
	public void DjikstraAlgo(String source){
		
		Set s = new HashSet(0);
		while(s.size() < graph.size()){
			
			int k; //temp distance
			int smallest = 0; //shortest distance value
			int j =0; //total distance
			
			Object[] destination = graph.get(source).keySet().toArray();
			for(int i=0; i<s.size(); i++){
				
				k = graph.get(source).get(destination[i]);
				if(k<smallest){
			
					smallest=k;
					s.add(graph.remove(source).get(k));
				}
				
				for(int p=0; p<destination.length; p++){
					
					if(graph.get(source).get(destination[p]).compareTo(Integer.parseInt(destination[p].toString()))>0){
						
						s.remove(graph.get(source).get(k));
						s.add(graph.get(source).get(graph.get(source).get(destination[p])));
					}
				}
			}
		}
		
		for(int i=0; i<s.size(); i++ ){
			
			System.out.print(s.remove(i));
		}

	}
	
	//checks to see if the two nodes are connected 
	public boolean isConnected(String source, String destination){
		
		if(graph.get(source).containsKey(destination))
			return true;
		return false;
	}
	
	//removes any link connection between the source and destination throws illegalArgumentException if no connection
	public void removeLink(String source, String destination){ 
		
		if(isConnected(source, destination)){
			
			graph.get(source).remove(destination);
		}
		
		else
			throw new IllegalArgumentException();
	}
	
	//removes any edge connection between the source and destination throws illegalArgumentException if no connection
	public void removeEdge(String source, String destination){ 
		
		if(isConnected(source, destination)){
			graph.get(source).remove(destination);	
			graph.get(destination).remove(source);
		}
		
		else
			throw new IllegalArgumentException();
	}

	//recursively traces through the whole map and prints out all nodes but goes down first
	public void depthTraversal(String source){
	
		
	}
	
	public void get(String source, String destination){
		
		System.out.println(graph.get(source).get(destination));
	}
	
	//prints out each node in that level, goes through the whole map by levels
//	public Iterator breadthTraverse(String source){}
	
	public static void main(String args[]){
		
		BellmanFord_StephenChan test = new BellmanFord_StephenChan();
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");
		test.addNode("D");
		test.addNode("E");
		test.addEdge("A", "B", 5);
		test.addEdge("A", "C", 8);
		test.addEdge("B", "D", 10);
		test.addEdge("C", "D", 3);
		test.addEdge("D", "E", 9);
		test.BellmanFord("A");
	}

}