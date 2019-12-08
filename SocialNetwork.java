package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	public Set<Person> getShortestPath(Person f1, Person f2) {
		// TODO Auto-generated method stub
		if(f1 == null||f2 == null) {
			return null;
		}
		if(!(f1 instanceof Person)||!(f2 instanceof Person)) {
			System.out.println("not valid person! (get mutual)");
			return new HashSet();
		}
		
		return null;
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
		SocialNetwork sw = new SocialNetwork();
		File file = new File("train.txt");
		sw.loadFromFile(file);
		System.out.println(sw.g.order);
		System.out.println(sw.g.size);
		System.out.println(sw.CenterUser.name);
		
	}
	
	
}





















