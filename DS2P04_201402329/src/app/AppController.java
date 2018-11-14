package app;
import graph.WeightedEdge;
import graph.WeightedUndirectedAdjacencyMatrixGraph;
public class AppController {
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> _graph;
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge>graph(){
		return this._graph;
	}
	
	
	

	private void setGraph(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> newGraph) {
		this._graph = newGraph;
	}

	
	
	
	public AppController() {
		this.setGraph(null);
	}

	public void inputAndMakeGraph() {
		AppView.outputLine(">입력할 그래프의 verex 수와 edge 수를 먼저 입력해야합니다.:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다.");

		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			WeightedEdge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"[오류] 입력된 edge(" + edge.tailVertex() + "," + edge.headVertex() + ",("+edge.weight()+")) 는그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge(" + edge.tailVertex() + "," + edge.headVertex() + ",("+edge.weight()+"))가 그래프에 삽입되었습니다.");
				
			}
		}

	}

	private WeightedEdge inputEdge() {
		do {
			AppView.outputLine("-입력할 edge의 두 vertex와 cost를  차례로 입력해야합니다");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = AppView.inputCost();
			
			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 vertex번호가 동일합니다.");
				} else {
					return (new WeightedEdge(tailVertex,headVertex,cost));
				}
			} else {

				if (!this.graph().vertexDoesExist(tailVertex))
					AppView.outputLine("[오류]존재하지 않는 tailvertex입니다:" + tailVertex);

				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 headvertex입니다:" + headVertex);
				}
				if(cost<0) {
					AppView.outputLine("[오류] edge의 비용은 양수 이어야합니다.:"+cost);
				}
			}
		} while (true);
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[오류] vertex 수는 0보다 크거나 같아야합니다" + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();
		}
		return numberOfEdges;
	}

	private int inputNumberOfVertices() {
		int numberOfVertices = AppView.inputNumberOfVertices();
		while (numberOfVertices <= 0) {
			AppView.outputLine("[오류] vertex 수는 0보다 커야합니다:" + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}

	public void showGraph() {
		AppView.outputLine("");
		AppView.outputLine(">입력된 그래프는 다음과 같습니다:");
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				if (this.graph().edgeDoesExist(tailVertex,headVertex)) {
					AppView.output(" " + headVertex);
					AppView.output("("+this.graph().weightOfEdge(tailVertex,headVertex)+")");
				}
				
			}
			AppView.outputLine("");
		}
		AppView.outputLine(" ");
		AppView.outputLine("> 입력된 그래프의  Adjacecny Matrix는 다음 과 같습니다:");
		AppView.outputLine("      ");
		
		for(int headVertex = 0;headVertex<this.graph().numberOfVertices();headVertex++) {
			AppView.output(String.format("  [%1s]", headVertex));
		}
		AppView.outputLine("");
		for(int tailVertex = 0; tailVertex < this.graph().numberOfVertices();tailVertex++) {
			AppView.output("["+tailVertex+"] ->");
			for(int headVertex = 0;headVertex < this.graph().numberOfVertices();headVertex++) {
				int weight = this.graph().weightOfEdge(tailVertex, headVertex);
				AppView.output(String.format("%4d",weight));
			}
			AppView.outputLine("");
		}
	}

	public void run() {
		AppView.outputLine("<<<최소비용 확장 트리 찾기 프로그램을 시작합니다. >>>"); //
		this.inputAndMakeGraph();
		this.showGraph();
		

		AppView.outputLine("");
		AppView.outputLine("<<<최소비용 확장 트리 찾기 프로그램을 종료합니다.>>>");

	}


}
