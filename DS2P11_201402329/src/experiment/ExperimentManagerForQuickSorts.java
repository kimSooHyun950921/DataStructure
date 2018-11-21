package experiment;
import sort.*;
import app.AppView;

public class ExperimentManagerForQuickSorts extends ExperimentManager{
	private static final boolean DEBUG_MODE = false;
	private static void showDebugMessage(String aMessage) {
		if(ExperimentManagerForQuickSorts.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}
	private static final QuickSortByPivotLeft<Integer> QuickSortByPivotLeft =
			new QuickSortByPivotLeft<Integer>(true);
	
	private static final QuickSortByPivotMid<Integer> QuickSortByPivotMid =
			new QuickSortByPivotMid<Integer>(true);
	private static final QuickSortByPivotMedian<Integer> QuickSortByPivotMedian =
			new QuickSortByPivotMedian<Integer>(true);
	private static final QuickSortByPivotRandom<Integer> QuickSortByPivotRandom =
			new QuickSortByPivotRandom<Integer>(true);
	private static final QuickSortWithInsertionSort<Integer> QuickSortByPivotInsertionSort =
			new QuickSortWithInsertionSort<Integer>(true);
	
	private long[] _measurementForQuickSortByPivotLeft;
	private long[] _measurementForQuickSortByMid;
	private long[] _measurementForQuickSortByMedian;
	private long[] _measurementForQuickSortByRandom;
	private long[] _measurementForQuickSortByInsertionSort;

	
	

	private long[] measurementForQuickSortByPivotLeft() {
		return _measurementForQuickSortByPivotLeft;
	}

	private long[] measurementForQuickSortByMid() {
		return _measurementForQuickSortByMid;
	}

	private long[] measurementForQuickSortByMedian() {
		return _measurementForQuickSortByMedian;
	}

	private long[] measurementForQuickSortByRandom() {
		return _measurementForQuickSortByRandom;
	}

	private long[] measurementForQuickSortByInsertionSort() {
		return _measurementForQuickSortByInsertionSort;
	}

	private void setMeasurementForQuickSortByPivotLeft(long[] newMeasurementForQuickSortByPivotLeft) {
		this._measurementForQuickSortByPivotLeft = newMeasurementForQuickSortByPivotLeft;
	}

	private void setMeasurementForQuickSortByMid(long[] newMeasurementForQuickSortByMid) {
		this._measurementForQuickSortByMid = newMeasurementForQuickSortByMid;
	}

	private void setMeasurementForQuickSortByMedian(long[] newMeasurementForQuickSortByMedian) {
		this._measurementForQuickSortByMedian = newMeasurementForQuickSortByMedian;
	}

	private void setMeasurementForQuickSortByRandom(long[] newMeasurementForQuickSortByRandom) {
		this._measurementForQuickSortByRandom = newMeasurementForQuickSortByRandom;
	}

	private void setMeasurementForQuickSortByInsertionSort(long[] newMeasurementForQuickSortByInsertionSort) {
		this._measurementForQuickSortByInsertionSort = newMeasurementForQuickSortByInsertionSort;
	}

	@Override
	public void performExperiment(ListOrder anOrder) {
		this.performMeasuring(anOrder);
	}

	@Override
	public void performMeasuring(ListOrder anOrder) {
		Integer[] list = this.dataSet().listWithOrder(anOrder);
		this.setMeasurementForQuickSortByPivotLeft(
				this.experiment().durationOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotLeft, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotLeft\n");
		
		this.setMeasurementForQuickSortByMid(
				this.experiment().durationOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotMid, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotLeft\n");

		this.setMeasurementForQuickSortByMedian(
				this.experiment().durationOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotMedian, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotLeft\n");

		this.setMeasurementForQuickSortByRandom(
				this.experiment().durationOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotRandom, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotLeft\n");

		this.setMeasurementForQuickSortByInsertionSort(
				this.experiment().durationOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotInsertionSort, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotLeft\n");

		
		
 	}
	public long measurementForQuickSortByPivotLeftAt(int anIndex) {
		return this.measurementForQuickSortByPivotLeft()[anIndex];
	}
	public long measurementForQuickSortByPivotMidAt(int anIndex) {
		return this.measurementForQuickSortByMid()[anIndex];
	}
	public long measurementForQuickSortByPivotMedainAt(int anIndex) {
		return this.measurementForQuickSortByMedian()[anIndex];
	}
	public long measurementForQuickSortByPivotRandomAt(int anIndex) {
		return this.measurementForQuickSortByRandom()[anIndex];
	}
	public long measurementForQuickSortWithInsertionSortAt(int anIndex) {
		return this.measurementForQuickSortByInsertionSort()[anIndex];
	}
	

}
