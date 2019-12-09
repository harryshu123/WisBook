package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class SocialNetwork implements SocialNetworkADT {
	Graph g = new Graph();
	Person CenterUser;
	@Override
	public boolean addFriends(Person f1, Person f2) throws NotAPersonException {
		// TODO Auto-generated method stub
		if(f1 == null||f2 == null) {
			return false;
		}
		if(!(f1 instanceof Person)||!(f2 instanceof Person)) {
			return false;
		}
		if(g.getAdjacentVerticesOf(f1).contains(f2)) {
			return false;
		}
		g.addEdge(f1, f2);
		return true;
	}

	@Override
	public boolean removeFriends(Person f1, Person f2) {
		// TODO Auto-generated method stub
		if(f1 == null||f2 == null) {
			return false;
		}
		if(!(f1 instanceof Person)||!(f2 instanceof Person)) {
			return false;
		}
		if(!g.getAdjacentVerticesOf(f1).contains(f2)) {
			return false;
		}
		g.removeEdge(f1, f2);
		return true;
	}

	@Override
	public boolean addUser(Person u1) {
		// TODO Auto-generated method stub
		if(!(u1 instanceof Person)) {
			return false;
		}
		g.addVertex(u1);
		return true;
	}

	@Override
	public boolean removeUser(Person u1) {
		// TODO Auto-generated method stub
		if(!(u1 instanceof Person)) {
			return false;
		}
		if(!g.getAllVertices().contains(u1)) {
			return false;
		}
		g.removeVertex(u1);
		return true;
	}

	@Override
	public Set<Person> getFriends(Person u1) throws NotAPersonException {
		// TODO Auto-generated method stub
		if(!(u1 instanceof Person)) {
			return null;
		}
		List<Person> s = g.getAdjacentVerticesOf(u1);
		Set<Person> set = new HashSet<Person>();
		for (Person p: s) {
			set.add(p);
		}
		return set;
	}

	@Override
	public Set<Person> getMutualFriends(Person f1, Person f2) {
		// TODO Auto-generated method stub
		if(f1 == null||f2 == null) {
			return null;
		}
		
		if(!(f1 instanceof Person)||!(f2 instanceof Person)) {
			System.out.println("not valid person! (get mutual)");
			return new HashSet();
		}
		HashSet<Person> result = new HashSet<Person>();
		List<Person> s1 = g.getAdjacentVerticesOf(f1);
		List<Person> s2 = g.getAdjacentVerticesOf(f2);
		for(Person p1 : s1) {
			for(Person p2 : s2) {
				if(p1.equals(p2)) {
					result.add(p1);
				}
			}
		}
		return result;
	}

	
	
	
	
	
	@Override
	public Set<String> getShortestPath(Person f1, Person f2) {
		// TODO Auto-generated method stub
		if(f1 == null||f2 == null) {
			return null;
		}
		if(!(f1 instanceof Person)||!(f2 instanceof Person)) {
			System.out.println("not valid person! (get mutual)");
			return new HashSet();
		}
		//constructing the graph to find the shortest path
		int V = g.getAllVertices().size();
		String src = f1.name;
		
		// Adjacency list representation of the  
        // connected edges 
        HashMap<String,List<Node> > adj = new HashMap<String,List<Node> >();
     
        // Initialize list for every node 
        for(Person p: g.getAllVertices()) {
        	List<Node> item = new ArrayList<Node>(); 
            adj.put(p.name, item);
        }
        
     // Inputs for the DPQ graph 
        for(List<Person> l: g.adjList) {
        	for(int i=1;i<l.size();i++) {
        		adj.get(l.get(0).name).add(new Node(l.get(i).name,1));
        	}
        }
        // Calculate the single source shortest path 
        DPQ dpq = new DPQ(V); 
        dpq.dijkstra(adj, src); 
        
        //right now we have the distance map
        //and parent hashmap
        String dest = f2.name;
        int cycle = 0;
        Set<String> result = new HashSet<String>();
        while(!dest.equals(f1.name)) {
        	//check
        	if(cycle > V) {
        		System.out.println("something wrong!!!");
        		break;
        	}
        	cycle++;
        	result.add(dest);
        	dest = dpq.parent.get(dest);
        	
        	
        	
        }
       
		
		return result;
	}

	
	
	@Override
	public Set<Graph> getConnectedComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadFromFile(File f1) throws IOException, NotAPersonException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(f1));
		String st; 
		  while ((st = br.readLine()) != null) {
			  String[] splited = st.split("\\s+");
			  String instruction = splited[0];
			  switch(instruction) {
			  case "a":
				  if(splited.length == 2) {//only add vertex
					  this.addUser(new Person(splited[1]));
				  }else if(splited.length == 3){
					  this.addFriends(new Person(splited[1]), new Person(splited[2]));
				  }else {
					  System.out.println("invalid input!");
				  }
				  break;
			  case "r":
				  Set<List<Person>> adjList = g.adjList;
				  if(splited.length == 2) {//only remove vertex
					 Set<Person> set = g.getAllVertices();
					 for(Person p: set) {
						 if(p.name.equals(splited[1])){
							 g.removeVertex(p);
						 }
					 }
				  }else if(splited.length == 3){
					  for(List<Person> l: adjList) {
						  if(l.get(0).name.equals(splited[1])) {
							  for(Person pp: l) {
								  if(pp.name.equals(splited[2])) {
									  g.removeEdge(l.get(0), pp);
									  break;
								  }
							  }
						  }
					  }
				  }else {
					  System.out.println("invalid input!");
				  }
				  break;
			  case "s":
				  Set<Person> set = g.getAllVertices();
				  for(Person p: set) {
					  if(p.name.equals(splited[1])) {
						  CenterUser = new Person(splited[1]);
						  System.out.println("Set Center Person Successfully");
						  break;
					  }
				  }
				 // System.out.println("Center user has problem!!");
				  break;
			  }
		  }
		  br.close();
		  
		
	}

	@Override
	public void saveToFile(File f1) throws IOException {
		// TODO Auto-generated method stub
//		FileWriter fw = new FileWriter(f1);
//		Set<List<Person>> adjList = g.adjList;
//		for(List<Person> l : adjList) {
//			for(Person p: l) {
//				fw.write(p.name + " ");
//			}
//			fw.write("\n");
//		}
//		System.out.println("Writing Successfully");
//		fw.close();
	}
	
	
	public static void main(String[] args) throws IOException, NotAPersonException {
//		SocialNetwork sw = new SocialNetwork();
//		File file = new File("train.txt");
//		sw.loadFromFile(file);
//		System.out.println(sw.g.order);
//		System.out.println(sw.g.size);
//		System.out.println(sw.CenterUser.name);
		int V = 5; 
        String source = "harry"; 
  
        // Adjacency list representation of the  
        // connected edges 
        HashMap<String,List<Node> > adj = new HashMap<String,List<Node> >(); 
  
        // Initialize list for every node 
       
           
            adj.put("harry",new ArrayList<Node>()); 
            adj.put("sai",new ArrayList<Node>()); 
            adj.put("joe",new ArrayList<Node>()); 
            adj.put("zed",new ArrayList<Node>()); 
            adj.put("lee",new ArrayList<Node>()); 
            adj.put("leona",new ArrayList<Node>()); 
            adj.put("xxx",new ArrayList<Node>()); 
            
         
  
        // Inputs for the DPQ graph 
        adj.get("harry").add(new Node("sai", 1)); 
        adj.get("harry").add(new Node("joe", 1)); 
        adj.get("harry").add(new Node("zed", 1)); 
        adj.get("harry").add(new Node("lee", 1)); 
  
        
        adj.get("lee").add(new Node("xxx", 1)); 
        adj.get("joe").add(new Node("leona", 1)); 
        adj.get("joe").add(new Node("sai", 1)); 
        adj.get("joe").add(new Node("zed", 1)); 
  
        // Calculate the single source shortest path 
        DPQ dpq = new DPQ(V); 
        dpq.dijkstra(adj, source); 
  
        // Print the shortest path to all the nodes 
        // from the source node 
        System.out.println(adj.get("harry").size());
        System.out.println("The shorted path from node :"); 
       System.out.println(dpq.parent);
            System.out.println(source + " to " + "xxx" + " is "
                               + dpq.dist.get("xxx")); 
	}
		
	
	
	
}





















