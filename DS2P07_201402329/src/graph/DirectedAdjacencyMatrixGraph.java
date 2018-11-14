package graph;

import list.Iterator;

public class DirectedAdjacencyMatrixGraph<E extends Edge> extends AdjacencyGraph<E> {
	private int[][] _adjacency;

	protected int[][] adjacency() {
		return this._adjacency;
	}
	protected void setAdajcency(int[][] newAdjacency) {
		this._adjacency = newAdjacency;
	}
	
	

	protected int adjacencyOfEdge(int tailVertex, int headVertex) {
		return this.adjacency()[tailVertex][headVertex];
	}

	protected void setAdjacencyOfEdgeAs(int tailVertex, int headVertex, int anAdjacencyOfEdge) {
		this.adjacency()[tailVertex][headVertex] = anAdjacencyOfEdge;
	}
	protected void setAdjacencyOfEdgeAsExist(int tailVertex,int headVertex) {
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, AdjacencyGraph.EDGE_EXIST);
	}

	protected void setAdjacencyOfEdgeAsNone(int tailVertex, int headVertex) {
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, AdjacencyGraph.EDGE_NONE);
	}

	protected DirectedAdjacencyMatrixGraph() {

	}

	public DirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdajcency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++) {
				this.setAdjacencyOfEdgeAsNone(tailVertex, headVertex);
			}
		}
	}

	protected boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacencyOfEdge(tailVertex, headVertex) != AdjacencyGraph.EDGE_NONE);
	}

	@Override
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		if (this.edgeIsValid(aTailVertex, aHeadVertex)) {
			return (this.adjacencyOfEdgeDoesExist(aTailVertex, aHeadVertex));
		}
		return false;
	}

	@Override
	public boolean edgeDoesExist(E anEdge) {
		if (anEdge != null) {
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E edge(int aTailVertex, int aHeadVertex) {
		if (this.edgeDoesExist(aTailVertex, aHeadVertex)) {
			return (E) new Edge(aTailVertex, aHeadVertex);
		}
		return null;
	}

	@Override
	public boolean addEdge(E anEdge) {
		if (anEdge != null) {
			if (this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setAdjacencyOfEdgeAsExist(tailVertex, headVertex);
				this.setNumberOfEdges(this.numberOfEdges() + 1);
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> neighborIteratorOf(int aTailVertex) {

		return new IteratorForNeighborsOf(aTailVertex);
	}

	private class IteratorForNeighborsOf implements Iterator<E> {
		private int _tailVertex;
		private int _nextHeadVertex;

		private int tailVertex() {
			return this._tailVertex;
		}

		private void setTailVertex(int newTailVertex) {
			this._tailVertex = newTailVertex;
		}

		private int nextHeadVertex() {
			return this._nextHeadVertex;
		}

		private void setNextHeadVertex(int newNextHeadVertex) {
			this._nextHeadVertex = newNextHeadVertex;
		}

		private IteratorForNeighborsOf(int givenTailVertex) {
			this.setTailVertex(givenTailVertex);
			this.setNextHeadVertex(0);
		}

		@Override
		public boolean hasNext() {
			while (this.nextHeadVertex() < DirectedAdjacencyMatrixGraph.this.numberOfVertices()) {
				if (DirectedAdjacencyMatrixGraph.this.adjacencyOfEdgeDoesExist(this.tailVertex(), this.nextHeadVertex())) {
					return true;
				}
				this.setNextHeadVertex(this.nextHeadVertex() + 1);
			}
			return false;
		}

		@Override
		public E next() {
			@SuppressWarnings("unchecked")
			E nextElement = (E) new Edge(this.tailVertex(), this.nextHeadVertex());
			this.setNextHeadVertex(this.nextHeadVertex() + 1);
			return nextElement;
		}
	}

}
