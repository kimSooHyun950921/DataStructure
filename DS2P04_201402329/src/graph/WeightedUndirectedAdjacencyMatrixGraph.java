package graph;

public class WeightedUndirectedAdjacencyMatrixGraph <WE extends WeightedEdge> extends UndirectedAdjacencyMatrixGraph<WE> implements SupplementForWeightedGraph<WE> {

	private static final int WEIGHTED_EDGE_NONE = -1;
	
	private void setWeightedOfEdge(int aTailVertex , int aHeadVertex, int newWeight){
		this.adjacency()[aTailVertex][aHeadVertex] = newWeight;
	}
	private void setWeightOfEdgeAsNone(int aTailVertex,int aHeadVertex) {
		this.setWeightedOfEdge(aTailVertex, aHeadVertex, WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}
	
	public WeightedUndirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		super(givenNumberOfVertices);

		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex = 0;tailVertex<this.numberOfVertices();tailVertex++) {
			for(int headVertex=0;headVertex < this.numberOfVertices();headVertex++) {
			this.setWeightOfEdgeAsNone(tailVertex,headVertex);}
		}
	}
	@Override
	public int weightOfEdge(WE anEdge) {
		if(anEdge !=null){
			return this.weightOfEdge(anEdge.tailVertex(),anEdge.headVertex());
		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}
	@Override
	public int weightOfEdge(int aTailVertex, int aHeadVertex) {
		if(this.edgeDoesExist(aTailVertex,aHeadVertex)) {
			return this.adjacencyOfEdge(aTailVertex, aHeadVertex);
		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}
	@Override
	protected boolean adjacencyOfEdgeDoesExist(int aTailVertex,int aHeadVertex) {
		return(this.adjacencyOfEdge(aTailVertex, aHeadVertex)!=WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}
	@Override
	public boolean addEdge(WE anEdge) {
		if(anEdge !=null) {
				if(this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
					int tailVertex = anEdge.tailVertex();
					int headVertex = anEdge.headVertex();
					this.setWeightedOfEdge(tailVertex, headVertex, anEdge.weight());
					this.setWeightedOfEdge(headVertex, tailVertex, anEdge.weight());
					this.setNumberOfEdges(this.numberOfEdges()+1);
					return true;
				}
			}
			return false;
		}
	



}
