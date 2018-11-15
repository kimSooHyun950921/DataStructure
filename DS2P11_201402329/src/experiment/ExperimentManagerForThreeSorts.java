 package experiment;

import app.AppView;
import sort.InsertionSort;
import sort.QuickSort;
import sort.HeapSort;
public class ExperimentManagerForThreeSorts extends ExperimentManager {
	
	private static final boolean DEBUG_MODE = false;
	private static void showDebugMessage(String aMessage) {
		if(ExperimentManagerForThreeSorts.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}
	private static final InsertionSort<Integer> InsertionSort=
			new InsertionSort<Integer>(true);
	private static final QuickSort<Integer>QuickSort=
			new QuickSort<Integer> (true);
	private static final HeapSort<Integer>HeapSort =
			new HeapSort<Integer>(true);
	private long[] _measurementForInsertionSort;
	private long[] _measurementForQuickSort;
	private long[] _measurementForHeapSort;
	
	private long[] _estimationForInsertionSort;
	private long[] _estimationForQuickSort;
	private long[] _estimationForHeapSort;
	
	
	private long[] measurementForInsertionSort() {
		return _measurementForInsertionSort;
	}
	private void setMeasurementForInsertionSort(long[] newMeasurement) {
		this._measurementForInsertionSort = newMeasurement;
	}
	private long[] measurementForQuickSort() {
		return _measurementForQuickSort;
	}
	private void setMeasurementForQuickSort(long[] newEstimation) {
		this._measurementForQuickSort = newEstimation;
	}
	private long[] measurementForHeapSort() {
		return _measurementForHeapSort;
	}
	private void setMeasurementForHeapSort(long[] newEstimation) {
		this._measurementForHeapSort = newEstimation;
	}
	private long[] estimationForInsertionSort() {
		return _estimationForInsertionSort;
	}
	private void setEstimationForInsertionSort(long[] newEstimation) {
		this._estimationForInsertionSort = newEstimation;
	}
	private long[] estimationForQuickSort() {
		return _estimationForQuickSort;
	}
	private void setEstimationForQuickSort(long[] newEstimation) {
		this._estimationForQuickSort = newEstimation;
	}
	private long[] estimationForHeapSort() {
		return _estimationForHeapSort;
	}
	private void setEstimationForHeapSort(long[] newEstimation) {
		this._estimationForHeapSort = newEstimation;
	}
	@SuppressWarnings("unused")
	private static boolean isDebugMode() {
		return DEBUG_MODE;
	}
	@SuppressWarnings("unused")
	private static InsertionSort<Integer> getInsertionsort() {
		return InsertionSort;
	}
	@SuppressWarnings("unused")
	private static QuickSort<Integer> getQuicksort() {
		return QuickSort;
	}
	@SuppressWarnings("unused")
	private static HeapSort<Integer> getHeapsort() {
		return HeapSort;
	}
	
	@Override
	public void performExperiment(ListOrder anOrder) {
		this.performMeasuring(anOrder);
		if(anOrder.equals(ListOrder.Random)) {
			this.estimateForRandomList();
		}
		else if(anOrder.equals(ListOrder.Ascending)) {
			this.estimateForAscendingList();
		}
		else {
			this.estimateForDescendingList();
		}
	}
	@Override
	public void performMeasuring(ListOrder anOrder) {
		Integer[] experimentList = this.dataSet().listWithOrder(anOrder);
		this.setMeasurementForInsertionSort(
				this.experiment().durationOfSort(ExperimentManagerForThreeSorts.InsertionSort, experimentList));
		ExperimentManagerForThreeSorts.showDebugMessage("[Debug] end of Insertion Sort\n");
		this.setMeasurementForQuickSort(
				this.experiment().durationOfSort(ExperimentManagerForThreeSorts.QuickSort, experimentList));
		ExperimentManagerForThreeSorts.showDebugMessage("[Debug] end of Quick Sort\n");
		this.setMeasurementForHeapSort(
				this.experiment().durationOfSort(ExperimentManagerForThreeSorts.HeapSort, experimentList));
		ExperimentManagerForThreeSorts.showDebugMessage("[Debug]end of HeapSort\n");
		
	}
	private void estimateForRandomList() {
		this.setEstimationForInsertionSort(
						Estimation.estimatByQuadratic(
								this.measurementForInsertionSort(),
								this.parameterSetForMeasurement())
				);
		this.setEstimationForQuickSort(
				Estimation.estimateByNLogN(
						this.measurementForQuickSort(),
						this.parameterSetForMeasurement()));
		this.setEstimationForHeapSort(
				Estimation.estimateByNLogN(
						this.measurementForHeapSort(),
						this.parameterSetForMeasurement()));
		
	}
	private void estimateForAscendingList() {
		this.setEstimationForInsertionSort(
						Estimation.estimateByLinear(
								this.measurementForInsertionSort(),
								this.parameterSetForMeasurement())
				);
		this.setEstimationForQuickSort(
				Estimation.estimatByQuadratic(
						this.measurementForQuickSort(),
						this.parameterSetForMeasurement()));
		this.setEstimationForHeapSort(
				Estimation.estimateByNLogN(
						this.measurementForHeapSort(),
						this.parameterSetForMeasurement()));
		
	}
	
	private void estimateForDescendingList() {
		this.setEstimationForInsertionSort(
				Estimation.estimatByQuadratic(
						this.measurementForInsertionSort(),
						this.parameterSetForMeasurement()));
		this.setEstimationForQuickSort(
				Estimation.estimatByQuadratic(
						this.measurementForQuickSort()
						, this.parameterSetForMeasurement()));
		this.setEstimationForHeapSort(
				Estimation.estimateByNLogN(
						this.measurementForHeapSort(),
						this.parameterSetForMeasurement()));
	}
	public long measurementForInsertionSortAt(int anIndex) {
		return this.measurementForInsertionSort()[anIndex];
	}
	public long measurementForQuickSortAt(int anIndex) {
		return this.measurementForQuickSort()[anIndex];
	}
	public long measurementForHeapSortAt(int anIndex){return this.measurementForHeapSort()[anIndex];}
	public long estimationForInsertionSortAt(int anIndex){
		return this.estimationForInsertionSort()[anIndex];}
	public long estimationForQuickSortAt(int anIndex){
		return this.estimationForQuickSort()[anIndex];
	}
	public long estimationForHeapSortAt(int anIndex){
		return this.estimationForHeapSort()[anIndex];
	}

}
