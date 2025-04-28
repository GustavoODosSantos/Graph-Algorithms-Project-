/* WUGraph.java */

package graph;

import list.*;
import dict.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
	private int numOfEdges;
	private HashTableChained hashEdges;
	private HashTableChained hashVer;
	private int numOfVer;
	private DList ver;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */ 
  public WUGraph() {
    ver = new DList();
    hashVer = new HashTableChained();
    hashEdges = new HashTableChained();
    numOfVer = 0;
    numOfEdges = 0;
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount() {
    return numOfVer;
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount() {
    return numOfEdges;
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
    Object[] v = new Object[numOfVer];
    DListNode cur = ver.front();
    int index = 0;
    while (cur != null) {
      v[index++] = ((Vertex)cur.item).getItem();
      cur = ver.next(cur);
    }
    return v;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */

  public void addVertex(Object vertex) {
    if (isVertex(vertex)) {
      return;
    }
    Vertex v = new Vertex(vertex);
    ver.insertBack(v);
    v.vertexNode = ver.back();
    hashVer.insert(vertex, v);
    numOfVer++; 
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex) {
    if (!isVertex(vertex)) {
      return;
    }
    Vertex v = (Vertex)(hashVer.find(vertex)).value();
    DListNode cur = v.adjList.front();
    while (cur != null) {
      Edge edge = (Edge)cur.item;
      DListNode next = (v.adjList).next(cur);
      removeEdge((edge.vertex1).getItem(), (edge.vertex2).getItem());
      cur = next;
    }
    ver.remove(v.vertexNode);
    hashVer.remove(vertex);
    numOfVer--;
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
    return hashVer.find(vertex) != null;
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
    if (!isVertex(vertex)) {
      return 0;
    }
    Vertex v = (Vertex)hashVer.find(vertex).value();
    return v.degree;
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex) {
    if (degree(vertex) == 0) {
      return null;
    }
    Vertex v = (Vertex)hashVer.find(vertex).value();
    DListNode cur = v.adjList.front();
    Neighbors neighbors = new Neighbors();
    neighbors.neighborList = new Object[v.degree];
    neighbors.weightList = new int[v.degree];
    int index = 0;
    while (cur != null) {
      Edge edge = (Edge)cur.item;
      if (edge.vertex1.equals(v)) {
        neighbors.neighborList[index] = (edge.vertex2).getItem();
      } else {
        neighbors.neighborList[index] = (edge.vertex1).getItem();
      }
      neighbors.weightList[index] = edge.weight;
      index++;
      cur = (v.adjList).next(cur);
    }
    return neighbors;
  }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u.equals(v)) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object vertex1, Object vertex2, int weight) {
    if (isVertex(vertex1) && isVertex(vertex2)) {
      VertexPair vPair = new VertexPair(vertex1, vertex2);
      if (hashEdges.find(vPair) == null) {
        Vertex vertexU = (Vertex)hashVer.find(vertex1).value();
        Vertex vertexV = (Vertex)hashVer.find(vertex2).value();
        Edge edge = new Edge(vertexU, vertexV, weight);
        vertexU.adjList.insertBack(edge);
        vertexU.degree++;
        edge.node1 = vertexU.adjList.back();
        if (vertexU != vertexV) {
          vertexV.adjList.insertBack(edge);
          vertexV.degree++;
          edge.node2 = vertexV.adjList.back();
        }
        hashEdges.insert(vPair, edge);
        numOfEdges++;
      } else {
        Edge edge = (Edge)hashEdges.find(vPair).value();
        edge.weight = weight;
      }
    }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v) {
    if (isEdge(u, v)) {
      VertexPair vPair = new VertexPair(u, v);
      Edge edge = (Edge)hashEdges.find(vPair).value();
      (edge.vertex1).degree--;
      (edge.vertex1).adjList.remove(edge.node1);
      if (edge.vertex1 != edge.vertex2) {
        (edge.vertex2).degree--;
        (edge.vertex2).adjList.remove(edge.node2);
      }
      hashEdges.remove(vPair);
      numOfEdges--;
    }
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
    if (!isVertex(u) || !isVertex(v)) {
      return false;
    }
    VertexPair vPair = new VertexPair(u, v);
    return hashEdges.find(vPair) != null;
  }

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v) {
    if (!isEdge(u, v)) {
      return 0;
    }
    VertexPair vPair = new VertexPair(u, v);
    if (hashEdges.find(vPair) == null) {
      return 0;
    }
    Edge edge = (Edge)hashEdges.find(vPair).value();
    return edge.weight;
  }

}