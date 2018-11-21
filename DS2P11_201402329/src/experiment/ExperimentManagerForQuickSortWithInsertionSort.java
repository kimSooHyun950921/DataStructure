package experiment;
import sort.*;
import app.AppView;

public class ExperimentManagerForQuickSortWithInsertionSort extends ExperimentManager{
	private static final boolean DEBUG_MODE = false;
	private static void showDebugMessage(String aMessage) {
		if(ExperimentManagerForQuickSortWithInsertionSort.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}
	protected static final int DEFAULT_NUMBER_OF_ITERATION = 10;
	protected static final int DEFAULT_INCREMENT_SIZE = 10000;
	protected static final int DEFAULT_STARING_SIZE = DEFAULT_INCREMENT_SIZE;
	
	protected static final int DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT = 10;
	
	
	
	
	private static final QuickSortByPivotRandom<Integer> QuickSortByPivotRandom = 
			new QuickSortByPivotRandom<Integer>(true);
	private static final QuickSortWithInsertionSort<Integer> QuickSortWithInsertionSort = 
			new QuickSortWithInsertionSort<Integer>(true);
	
	private static final int DEFAULT_INSERTION_SORT_STARING_SIZE = 10;
	private static final int DEFAULT_INSERTION_SORT_INCREMENT_SIZE = 10;
	private static final int DEFAULT_INSERTION_SORT_NUMBER_OF_ITERATION = 9;
	
	private ParameterSetForIteration _parameterSetForMaxSizeOfInsertionSort;
	private long[] _measurementForQuickSortByPivotRandom;
	private long[][] _measurementsForQuickSortWihtInsertionSort;
	
	public ParameterSetForIteration parameterSetForMaxSizeOfInsertionSort() {
		return _parameterSetForMaxSizeOfInsertionSort;
	}
	private long[] measurementForQuickSortByPivotRandom() {
		return _measurementForQuickSortByPivotRandom;
	}
	private long[][] measurementsForQuickSortWihtInsertionSort() {
		return _measurementsForQuickSortWihtInsertionSort;
	}
	private void setParameterSetForMaxSizeOfInsertionSort(
			ParameterSetForIteration newParameterSetForMaxSizeOfInsertionSort) {
		this._parameterSetForMaxSizeOfInsertionSort = newParameterSetForMaxSizeOfInsertionSort;
	}
	private void setMeasurementForQuickSortByPivotRandom(long[] newMeasurementForQuickSortByPivotRandom) {
		this._measurementForQuickSortByPivotRandom = newMeasurementForQuickSortByPivotRandom;
	}
	private void setMeasurementsForQuickSortWihtInsertionSort(long[][] newMeasurementsForQuickSortWihtInsertionSort) {
		this._measurementsForQuickSortWihtInsertionSort = newMeasurementsForQuickSortWihtInsertionSort;
	}
	
	private void setMeasurementForQuickSortWithInsertionSort(
			long[] newMeasurement, int measurementIndex) {
		this.measurementsForQuickSortWihtInsertionSort()[measurementIndex] = newMeasurement;
	}
	@Override
	public void performExperiment(ListOrder anOrder) {
		this.performMeasuring(anOrder);
	}
	@Override
	public void performMeasuring(ListOrder anOrder) {
		Integer[] list = this.dataSet().listWithOrder(anOrder);
		
		this.setMeasurementsForQuickSortWihtInsertionSort(new long[this.parameterSetForMaxSizeOfInsertionSort().numberOfIteration()][]);
		
		this.setMeasurementForQuickSortByPivotRandom(this.experiment().durationOfSort(ExperimentManagerForQuickSortWithInsertionSort.QuickSortByPivotRandom, list));
		
		ExperimentManagerForQuickSortWithInsertionSort.showDebugMessage(	"[Debug] endof QuickSort By Pivot Random");
		for(int iteration = 0; iteration<this.parameterSetForMaxSizeOfInsertionSort().numberOfIteration();iteration++) {
			int size = this.parameterSetForMaxSizeOfInsertionSort().startingSize()+
					this.parameterSetForMaxSizeOfInsertionSort().incrementSize()*iteration;
			QuickSortWithInsertionSort.setMaxSizeForInsertionSort(size);
			this.setMeasurementForQuickSortWithInsertionSort(this.experiment().durationOfSort(ExperimentManagerForQuickSortWithInsertionSort.QuickSortWithInsertionSort, list), iteration);
			ExperimentManagerForQuickSortWithInsertionSort.showDebugMessage("[Debug] end of QuickSortWithInsertionSort"+size+"\n");
			}
	}
	public ExperimentManagerForQuickSortWithInsertionSort() {
		this.setParameterSetForMeasurement(new ParameterSetForMeasurement(
				DEFAULT_STARING_SIZE,
				DEFAULT_NUMBER_OF_ITERATION,
				DEFAULT_INCREMENT_SIZE,
				DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT));
		this.setParameterSetForMaxSizeOfInsertionSort(new ParameterSetForIteration(
				DEFAULT_INSERTION_SORT_STARING_SIZE,
				DEFAULT_INSERTION_SORT_NUMBER_OF_ITERATION,
				DEFAULT_INSERTION_SORT_INCREMENT_SIZE));
	}
	public long measurementForQuickSortByPivotRandomAt(int anIndex) {
		return this.measurementForQuickSortByPivotRandom()[anIndex];
	}
	public long measurementForQuickSortWithInsertionSortAt(int aMeasurementIndex,int anElementIndex) {
		return this.measurementsForQuickSortWihtInsertionSort()[aMeasurementIndex][anElementIndex];
	}
	
	
	
	
	

}
