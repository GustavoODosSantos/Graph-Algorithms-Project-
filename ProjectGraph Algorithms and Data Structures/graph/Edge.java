package graph;

import list.*;

public class Edge {

	protected Vertex vertex1;
	protected Vertex vertex2;
	protected int weight;
	protected DListNode node1; 
	protected DListNode node2; 

	public Edge(Vertex vertex1, Vertex vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
		this.node1 = null;
		this.node2 = null;
	}

}
