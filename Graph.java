package targil5;

import java.util.ArrayList;
public class Graph <E> {

	private ArrayList<ArrayList<E>> data;

	// constructor
	public Graph() {
		this.data = new ArrayList<ArrayList<E>>();		
	}

	// the method add new ver to list and add the list to data
	// if the ver already exists the method throw UserExistException.
	public void addVertex(E ver) throws VertexExistException {
		for(int i=0; i<this.data.size(); i++) {
			if(this.data.get(i).get(0).equals(ver)) {
				throw new VertexExistException("Already exists");
			}
		}  
		ArrayList<E> listVer = new ArrayList<E>();
		listVer.add(ver);
		this.data.add(listVer);
	}

	// the method create connections between 2 ver, the method adds each ver to the other ver list
	// if one of the user doesn't exists the method throw UserNotFoundException.
	public void addEdge(E ver1, E ver2) throws VertexNotExistException {
		int count = 0;
		for(int i=0; i<this.data.size(); i++) {
			if(this.data.get(i).get(0).equals(ver1)) {
				count++;
			}else if(this.data.get(i).get(0).equals(ver2)) {
				count++;
			}
		}
		if(count != 2) {
			throw new VertexNotExistException("Not exists");
		}
		for(int i=0; i<this.data.size(); i++) {
			if(this.data.get(i).get(0).equals(ver1)) {
				this.data.get(i).add(ver2);
			}
			else if(this.data.get(i).get(0).equals(ver2)) {
				this.data.get(i).add(ver1);
			}
		}
	}

	// the method return all the connections that ver has in his list
	// if ver doesn't exists the method return empty list
	public ArrayList<E> getEdges(E ver) throws VertexNotExistException {
		int count = 0, index = 0;
		for(int i=0; i<this.data.size(); i++) {
			if(this.data.get(i).get(0).equals(ver)) {
				index = i;
				break;
			}
			count++;
		}
		if(count == this.data.size()-1) {
			new VertexNotExistException("Not exists");
		}
		return new ArrayList<E>(this.data.get(index).subList(1, this.data.get(index).size()));
	}

	// the method return all the ver that exists in data
	public ArrayList<E> getVertices() {
		ArrayList<E> verList = new ArrayList<E>();
		if(!this.data.isEmpty()) {
			for(int i=0; i<this.data.size(); i++) {
				verList.add(this.data.get(i).get(0));
			}
		}
		return verList;
	}

	// the method check if there is a connections between 2 ver, and return the route from ver1 to ver2
	// if there no connections, the method will return empty list
	public ArrayList<E> bfs (E from, E to) {
		ArrayList<ArrayList<E>> routeList = new ArrayList<ArrayList<E>>();
		ArrayList<E> route = new ArrayList<E>();
		route.add(from);
		routeList.add(route);
		int location = 0;
		while(!routeList.isEmpty()) {
			route = routeList.remove(0);
			for(int i=0; i<this.data.size(); i++) {
				if(this.data.get(i).get(0).equals(route.get(route.size()-1))) {
					location = i;
					break ;
				}
			}
			for(int j=0; j<this.data.get(location).size()-1; j++) {
				ArrayList<E> temp = new ArrayList<E>(route);
				if(temp.get(temp.size()-1).equals(to)) {
					return temp;
				}
				if(!temp.contains(this.data.get(location).get(j+1))) {
					temp.add(this.data.get(location).get(j+1));
					routeList.add(temp);
				}
			}
			if(!routeList.isEmpty()) {
				if(routeList.get(routeList.size()-1).get(routeList.get(routeList.size()-1).size()-1).equals(to)) {
					return routeList.get(routeList.size()-1);
				}
			}
			System.gc();
		}
		return new ArrayList<E>();
	}
	
	
	
	
	
	
	
	
	public ArrayList<E> bfs1 (E from, E to) {
		ArrayList<ArrayList<E>> datalist = new ArrayList<ArrayList<E>>();
		ArrayList<E> list = new ArrayList<E>();
		list.add(from);
		datalist.add(list);
		int indexOfLastVer = 0;
		while(datalist.isEmpty() == false) {
			list = new ArrayList<E>(datalist.get(0));
			datalist.remove(0);
			indexOfLastVer = location(list.get(list.size()-1));
			for(int j=0; j<this.data.get(indexOfLastVer).size()-1; j++) {
				ArrayList<E> templist = new ArrayList<E>(list);
				if(templist.get(templist.size()-1).equals(to)) {
					return templist;
				}
				if(templist.contains(this.data.get(indexOfLastVer).get(j+1)) == false) {
					templist.add(this.data.get(indexOfLastVer).get(j+1));
					datalist.add(templist);
				}
			}
		}
		return new ArrayList<E>();
	}
	
	private int location(E ver) {
		int index = 0;
		for(int i=0; i<this.data.size(); i++) {
			if(this.data.get(i).get(0).equals(ver)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
