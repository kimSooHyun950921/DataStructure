
public class AppController {
	private AdjacencyMatrixGraph _graph;
	private Coloring             _coloring;
	
	private Coloring coloring() {
		return this._coloring;
	}
	private void setColoring(Coloring aColoring) {
		this._coloring = aColoring;
	}

	private AdjacencyMatrixGraph graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;
	}

	

	
	public AppController() {
		this.setGraph(null);
		this.setColoring(null);
	}

	public void inputAndMakeGraph() {
		AppView.outputLine(">입력할 그래프의 verex 수와 edge 수를 먼저 입력해야합니다.:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new AdjacencyMatrixGraph(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다.");

		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"(오류) 입력된 edge(" + edge.tailVertex() + "," + edge.headVertex() + ") 는그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge(" + edge.tailVertex() + "," + edge.headVertex() + ")가 그래프에 삽입되었습니다.");
				
			}
		}

	}

	private Edge inputEdge() {
		do {
			AppView.outputLine("-입력할 edge의 두 vertex를 차례로 입력해야합니다");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 vertex번호가 동일합니다.");
				} else {
					return (new Edge(tailVertex, headVertex));
				}
			} else {

				if (!this.graph().vertexDoesExist(tailVertex))
					AppView.outputLine("[오류]존재하지 않는 tailvertex입니다:" + tailVertex);

				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 headvertex입니다:" + headVertex);
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
				if (this.graph().edgeDoesExist(new Edge(tailVertex, headVertex))) {
					AppView.output(" " + headVertex);
				}
			}
			AppView.outputLine("");
		}
	}

	public void run() {
		AppView.outputLine("<<<그래프 색칠하기를 시작합니다. >>>"); //
		this.inputAndMakeGraph();
		this.showGraph();
		
		this.setColoring(new Coloring(this.graph()));
		this.coloring().runColoring();
		this.showColoring();
		
		AppView.outputLine("");
		AppView.outputLine("<<<색칠하기를 종료합니다.>>>");

	}
	private void showColoring() {
		AppView.outputLine("");
		AppView.outputLine("> 각 vertex에 칠해진 색깔은 다음과 같습니다 : ");
		for(int vertex = 0;vertex<this.graph().numberOfVertices();vertex++) {
			AppView.outputLine("["+vertex+"]"+this.coloring().vertexColor(vertex).name());
		}
		AppView.output("");
		AppView.outputLine(">양 끝 vertex의 색깔이 같은 edge들은 다음과 같습니다:");
		if(this.coloring().sameColorEdges().size()==0) {
			AppView.outputLine("!! 모든 edge 의 양 끝 vertex의 색깔이 다릅니다:");
		}
		else {
			LinkedList<Edge>.IteratorForLinkedList iterator = this.coloring().sameColorEdges().iterator();
			while(iterator.hasNext()) {
				Edge currentEdge = iterator.next();
				AppView.output("("+currentEdge.tailVertex()+","+currentEdge.headVertex()+"):");
				AppView.outputLine(" "+this.coloring().vertexColor(currentEdge.tailVertex()).name());
			}
		}
	}

}
