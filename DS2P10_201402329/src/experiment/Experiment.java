package experiment;

import app.AppView;
import sort.Sort;

public class Experiment {
	private ParameterSetForMeasurement _parameterSetForMeasurement;
	
	private ParameterSetForMeasurement parameterSetForMeasurement() {
		return this._parameterSetForMeasurement;
	}
	private void setParameterSetForMeasurement(ParameterSetForMeasurement newParameterSet) {
		this._parameterSetForMeasurement = newParameterSet;
	}
	
	public Experiment(ParameterSetForMeasurement givenParameterSet) {
		this.setParameterSetForMeasurement(givenParameterSet);
	}
	

	private static final boolean DEBUG_MODE = false;
	private static void showDebugMessage(String aMessage) {
		if(Experiment.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}
	private static Integer[] copyListOfGivenSize(Integer[] aList,int givenSize) {
		if(givenSize<= aList.length) {
			Integer[] copiedList = new Integer[givenSize];
			for(int i = 0;i<givenSize;i++) {
				copiedList[i] = aList[i];
			}
			return copiedList;
		}
		return null;
	}
	public static long durationOfSingleSort(Sort<Integer> aSort,Integer[] aList) {
		Timer.start();{
			aSort.sort(aList);
		}
		Timer.stop();
		return Timer.duration();
	}
	public long[] durationOfSort(Sort<Integer> sort, Integer[] list) {
		long[] duration = new long[this.parameterSetForMeasurement().numberOfIteration()];
		for(int i = 0, size = this.parameterSetForMeasurement().startingSize();
				i<this.parameterSetForMeasurement().numberOfIteration();
				i++,size+=this.parameterSetForMeasurement().incrementSize()) {
			long sumOfDuration = 0;
			for(int repeated = 0;
					repeated < this.parameterSetForMeasurement().numberOfReptitionOfSingleSort();
					repeated++) {
				Integer[] currentList = Experiment.copyListOfGivenSize(list, size);
				sumOfDuration += Experiment.durationOfSingleSort(sort, currentList);
			}
			duration[i] = sumOfDuration / this.parameterSetForMeasurement().numberOfReptitionOfSingleSort();
			Experiment.showDebugMessage("[Debug.Experiment]"+sort.toString()+"("+i+")\n");
			
		}
		return duration;
		
	}
}
