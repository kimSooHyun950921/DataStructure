package kruskal;

import app.AppView;
import graph.WeightedEdge;
import graph.WeightedUndirectedAdjacencyListGraph;
import list.LinkedList;
import list.List;

public class MinCostSpanningTrees {
	
	private WeightedUndirectedAdjacencyListGraph<WeightedEdge> _graph;
	private MinPriorityQ<WeightedEdge>                           _minPriorityQ;
	private List<WeightedEdge>                                   _spanningTreeEdgeList;
	
	private MinPriorityQ<WeightedEdge> minPriorityQ(){
		return this._minPriorityQ;
	}
	private void setMinPriorityQ(MinPriorityQ<WeightedEdge> newMinPrioirtyQ) {
		this._minPriorityQ =newMinPrioirtyQ;

		
	}
	private WeightedUndirectedAdjacencyListGraph<WeightedEdge> graph(){
		return this._graph;
		
	}
	private void setGraph(WeightedUndirectedAdjacencyListGraph<WeightedEdge> newGraph) {
		this._graph = newGraph;
	}
	private List<WeightedEdge> spanningTreeEdgelist(){
		return this._spanningTreeEdgeList;
	}
	private void setSpanningTreeEdgeList(List<WeightedEdge> newspanningTreeEdgeList) {
		this._spanningTreeEdgeList = newspanningTreeEdgeList;
		
	}
	private void initMinPriorityQ() {
		this.setMinPriorityQ(new MinPriorityQ<WeightedEdge>(this.graph().numberOfEdges()));
		int numberofVertices = this.graph().numberOfVertices();
		for(int tailVertex = 0;tailVertex<numberofVertices;tailVertex++) {
			for (int headVertex = tailVertex+1; headVertex<numberofVertices;headVertex++) {
				if(this.graph().edgeDoesExist(tailVertex,headVertex)) {
					int weight = this.graph().weightOfEdge(tailVertex,headVertex);
					WeightedEdge edge = new WeightedEdge(tailVertex,headVertex,weight);
					this.minPriorityQ().add(edge);
				}
			}
			
		}
	}
	public List<WeightedEdge> solve(WeightedUndirectedAdjacencyListGraph<WeightedEdge> aGraph){
		this.setGraph(aGraph);
		this.initMinPriorityQ();
		this.setSpanningTreeEdgeList(new LinkedList<WeightedEdge>());
		
		PairwiseDisjointSets pairwiseDisjointSets = 
				new PairwiseDisjointSets(this.graph().numberOfVertices());
		int maxNumberOfTreeEdges = this.graph().numberOfVertices() -1;
		while((this.spanningTreeEdgelist().size()<maxNumberOfTreeEdges) &&(! this.minPriorityQ().isEmpty())) {
			WeightedEdge edge = this.minPriorityQ().removeMin();
			int setOfTailVertex = pairwiseDisjointSets.find(edge.tailVertex());
			int setOfHeadVertex = pairwiseDisjointSets.find(edge.headVertex());
			if(setOfTailVertex == setOfHeadVertex) {
				AppView.outputLine("[Debug] Edge("+edge.tailVertex()+", "+edge.headVertex()+
						", ("+edge.weight()+"))는 스패닝 트리에 사이클을 생성시키므로 버립니다.");
				
			}
			else {
				this.spanningTreeEdgelist().add(edge);
				pairwiseDisjointSets.union(setOfTailVertex, setOfHeadVertex);
				AppView.outputLine("[Debug] Edge("+edge.tailVertex()+", "+edge.headVertex()+" ,("+edge.weight()+"))는 스패닝 트리의 ege로 추가됩니다.");
			}
		}
		return(this.spanningTreeEdgelist().size()==maxNumberOfTreeEdges)?this.spanningTreeEdgelist():null;
	}
	
	
	

}
