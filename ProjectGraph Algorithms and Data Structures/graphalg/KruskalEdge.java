package graphalg;

public class KruskalEdge implements Comparable {

	protected Object u;
	protected Object v;
	protected int weight;

	public KruskalEdge(Object u, Object v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	public int compareTo(Object o) {
		if (weight < ((KruskalEdge)o).weight) {
			return -1;
		} else if (weight > ((KruskalEdge)o).weight) {
			return 1;
		} else {
			return 0;
		}
	}
}
