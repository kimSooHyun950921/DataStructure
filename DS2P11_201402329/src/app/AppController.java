package app;

import experiment.ExperimentManager;
import experiment.ExperimentManagerForQuickSortWithInsertionSort;
import experiment.ExperimentManagerForQuickSorts;
import experiment.ExperimentManagerForThreeSorts;
import experiment.ListOrder;

public class AppController {
	private ExperimentManagerForThreeSorts _managerForThreeSorts;
	private ExperimentManagerForQuickSorts _managerForQuickSorts;
	private ExperimentManagerForQuickSortWithInsertionSort _managerForQuickSortWithInsertionSort;

	private ExperimentManagerForThreeSorts managerForThreeSorts() {
		return this._managerForThreeSorts;
	}

	private ExperimentManagerForQuickSorts managerForQuickSorts() {
		return _managerForQuickSorts;
	}

	private void setManagerForQuickSorts(ExperimentManagerForQuickSorts newManagerForQuickSorts) {
		this._managerForQuickSorts = newManagerForQuickSorts;
	}

	private ExperimentManagerForQuickSortWithInsertionSort managerForQuickSortWithInsertionSort() {
		return _managerForQuickSortWithInsertionSort;
	}

	private void setManagerForQuickSortWithInsertionSort(
			ExperimentManagerForQuickSortWithInsertionSort newManagerForQuickSortWithInsertionSort) {
		this._managerForQuickSortWithInsertionSort = newManagerForQuickSortWithInsertionSort;
	}

	private void setManagerForThreeSorts(ExperimentManagerForThreeSorts newExprimentManager) {
		this._managerForThreeSorts = newExprimentManager;
	}

	private void showTableTitle(ListOrder anOrder) {
		AppView.outputLine("> " + anOrder.orderName() + "�����Ϳ� ���� ���� ");
	}
	
	private void showTableHeadForQuickSorts() {
		AppView.outputLine(
				String.format("%7s", "������ ũ��")+
				String.format("%17s", "<Pivot Left>")+
				String.format("%17s", "<Pivot Mid>")+
				String.format("%17s", "<Pivot Median>")+
				String.format("%17s", "<Pivot Random>")+
				String.format("%17s", "<Insertion Sort>")
				);
	}
	private void showTableHeadForThreeSorts() {
		AppView.outputLine(String.format("%8S", " ") + String.format("%26s", "<Insertion Sort>")
				+ String.format("%26s", "<Quick Sort>") + String.format("%26s", "<Heap Sort>"));
		AppView.outputLine(String.format("%7s", "������ ũ��") + String.format("%26s", "Measure (Estimate)")
				+ String.format("%26s", "Measure (Estimate)") + String.format("%26s", "Measure (Estimate)"));
	}
	
	private void showtTableContentForQuickSorts() {
		for(int iteration=0;
				iteration<this.managerForQuickSorts().parameterSetForMeasurement().numberOfIteration();
				iteration++) {
			int startingSize = this.managerForQuickSorts().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForQuickSorts().parameterSetForMeasurement().incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.outputLine(
					"["+String.format("%7d", sortingSize)+"]"+
					String.format("%17d", this.managerForQuickSorts().measurementForQuickSortByPivotLeftAt(iteration))+
					String.format("%17d", this.managerForQuickSorts().measurementForQuickSortByPivotMidAt(iteration))+
					String.format("%17d", this.managerForQuickSorts().measurementForQuickSortByPivotMedainAt(iteration))+
					String.format("%17d", this.managerForQuickSorts().measurementForQuickSortByPivotRandomAt(iteration))+
					String.format("%17d", this.managerForQuickSorts().measurementForQuickSortWithInsertionSortAt(iteration))
							
			);
		}
		
		
			
	}
	private void showResultTableForQuickSorts(ListOrder anOrder) {
		this.showTableTitle(anOrder);
		this.showTableHeadForQuickSorts();
		this.showtTableContentForQuickSorts();
		AppView.outputLine("");
	}
	
	private void measureAndShow(ExperimentManager anExperimentManager, ListOrder anOrder) {
		anExperimentManager.performExperiment(anOrder);
		if(anExperimentManager.getClass().equals(ExperimentManagerForThreeSorts.class)) {
			this.showResultTableForThreeSorts(anOrder);
		}
		else if(anExperimentManager.getClass().equals(ExperimentManagerForQuickSorts.class)) {
			this.showResultTableForQuickSorts(anOrder);
		}
		else {
			this.showResultTableForQuickSortWithInsertionSort(anOrder);
		}
	}
	

	private void showTableHeadForQuickSortWithInsertionSort() {
		AppView.output(
				String.format("%7s ", "������ ũ��")+
				String.format("%12s","<Pivot Random>" )
				);
		int numberOfIteration = 
				this._managerForQuickSortWithInsertionSort.
				parameterSetForMaxSizeOfInsertionSort().
				numberOfIteration();
		for(int iterationOfMaxSize =0 ;
				iterationOfMaxSize < numberOfIteration;
				iterationOfMaxSize++) {
			int startingMaxSize = 
					this.managerForQuickSortWithInsertionSort().
					parameterSetForMaxSizeOfInsertionSort().
					startingSize();
			int IncrementSizeOfMaxSize = 
					this.managerForQuickSortWithInsertionSort().
					parameterSetForMaxSizeOfInsertionSort().
					incrementSize();
			int maxSortingSize = startingMaxSize + IncrementSizeOfMaxSize * iterationOfMaxSize;
			AppView.output(String.format("  <Size%3d>", maxSortingSize));
		}
		AppView.outputLine("");
	}

	
	
	
	private void showTableContentForQuickSortWithInsertionSort() {
		int numberOfIteration = 
				this.managerForQuickSortWithInsertionSort().parameterSetForMeasurement().numberOfIteration();
		for(int iteration=0;iteration<numberOfIteration;iteration++) {
			int startingSize = this.managerForQuickSortWithInsertionSort().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForQuickSortWithInsertionSort().parameterSetForMeasurement().incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.output("["+String.format("%7d", sortingSize)+"]");
			AppView.output(
					String.format("%14d", this.managerForQuickSortWithInsertionSort().
							measurementForQuickSortByPivotRandomAt(iteration)));
			int numberOfIterationOfMaxSize = 
					this._managerForQuickSortWithInsertionSort.
					parameterSetForMaxSizeOfInsertionSort().numberOfIteration();
			for(int iterationOfMaxSize = 0;iterationOfMaxSize < numberOfIterationOfMaxSize ; iterationOfMaxSize++) {
				AppView.output(
						String.format("%11d", this.managerForQuickSortWithInsertionSort().measurementForQuickSortWithInsertionSortAt(iterationOfMaxSize, iteration)));
				
			}
			AppView.outputLine("");
			
		}
	}
	private void showTableContentForQuickSorts() {
		for (int iteration = 0; iteration < this.managerForThreeSorts().parameterSetForMeasurement()
				.numberOfIteration(); iteration++) {
			int startingSize = this.managerForThreeSorts().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForThreeSorts().parameterSetForMeasurement().incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.outputLine("[" + String.format("%7d", sortingSize) + "] "
					+ String.format("%15d", this.managerForThreeSorts().measurementForInsertionSortAt(iteration)) 
					+ "(" + String.format("%8d", this.managerForThreeSorts().estimationForInsertionSortAt(iteration))+")"
					+ String.format("%15d", this.managerForThreeSorts().measurementForQuickSortAt(iteration))
					+ "(" + String.format("%8s", this.managerForThreeSorts().estimationForQuickSortAt(iteration)) + ")"
					+ String.format("%15d", this.managerForThreeSorts().measurementForHeapSortAt(iteration)) 
					+ "("+String.format("%8s", this.managerForThreeSorts().estimationForHeapSortAt(iteration)) + ")");
		}
		
	}

	private void showTableCenterForThreeSorts() {
		for (int iteration = 0; iteration < this.managerForThreeSorts().parameterSetForMeasurement()
				.numberOfIteration(); iteration++) {
			int startingSize = this.managerForThreeSorts().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForThreeSorts().parameterSetForMeasurement().incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.outputLine("[" + String.format("%7d", sortingSize) + "] "
					+ String.format("%15d", this.managerForThreeSorts().measurementForInsertionSortAt(iteration)) 
					+ "(" + String.format("%8d", this.managerForThreeSorts().estimationForInsertionSortAt(iteration))+")"
					+ String.format("%15d", this.managerForThreeSorts().measurementForQuickSortAt(iteration))
					+ "(" + String.format("%8s", this.managerForThreeSorts().estimationForQuickSortAt(iteration)) + ")"
					+ String.format("%15d", this.managerForThreeSorts().measurementForHeapSortAt(iteration)) 
					+ "("+String.format("%8s", this.managerForThreeSorts().estimationForHeapSortAt(iteration)) + ")");
		}

	}
	private void showResultTableForQuickSortWithInsertionSort(ListOrder anOrder) {
		this.showTableTitle(anOrder);
		this.showTableHeadForQuickSortWithInsertionSort();
		this.showTableContentForQuickSortWithInsertionSort();
		AppView.outputLine("");
	}

	private void showResultTableForThreeSorts(ListOrder anOrder) {
		this.showTableTitle(anOrder);
		this.showTableHeadForThreeSorts();
		this.showTableCenterForThreeSorts();
		AppView.outputLine("");
	}
	
	

	

	
	private void measurementAndShow(ExperimentManager anExperimentManager,ListOrder anOrder) {
		anExperimentManager.performExperiment(anOrder);
		if(anExperimentManager.getClass().equals(ExperimentManagerForThreeSorts.class)) {
			this.showResultTableForThreeSorts(anOrder);
		}
		else if(anExperimentManager.getClass().equals(ExperimentManagerForQuickSorts.class)) {
			this.showResultTableForQuickSorts(anOrder);
		}
		else {
			this.showResultTableForQuickSortWithInsertionSort(anOrder);
		}
		
	}

	public void run() {
		AppView.outputLine("<< ���� ���� �� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		{
			AppView.outputLine(">>3 ���� ������ ���� ��: ����, ��, ��<<");
			this.setManagerForThreeSorts(new ExperimentManagerForThreeSorts());
			this.managerForThreeSorts().prepareExperiment(null);

			this.measurementAndShow(this.managerForThreeSorts(), ListOrder.Random);
			this.measurementAndShow(this.managerForThreeSorts(), ListOrder.Ascending);
			this.measurementAndShow(this.managerForThreeSorts(), ListOrder.Descending);
		}
		{
			AppView.outputLine(">>5���� �� ���� ������ ���� �� <<");
			this.setManagerForQuickSorts(new ExperimentManagerForQuickSorts());
			this.managerForQuickSorts().prepareExperiment(null);
			
			this.measureAndShow(this.managerForQuickSorts(),ListOrder.Random);
			this.measureAndShow(this.managerForQuickSorts(),ListOrder.Ascending);
			this.measureAndShow(this.managerForQuickSorts(),ListOrder.Descending);
		}
		{
			AppView.outputLine(">>���� ������ ����ϴ� �� ������ ���� : ���� ������ �����ϴ� ũ�⺰ ���� ��<<");
			this.setManagerForQuickSortWithInsertionSort(new ExperimentManagerForQuickSortWithInsertionSort());
			this.managerForQuickSortWithInsertionSort().prepareExperiment(null);
			
			this.measureAndShow(this.managerForQuickSortWithInsertionSort(), ListOrder.Random);
			
			this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort().setStartingSize(15);
			this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort().setIncrementSize(1);
			this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort().setNumberOfIteration(11);
			
			this.measureAndShow(this.managerForQuickSortWithInsertionSort(), ListOrder.Random);

			
			
		}
		AppView.outputLine("<<< ���� ���� �� ���α׷��� �����մϴ�.>>>");
	}

}
