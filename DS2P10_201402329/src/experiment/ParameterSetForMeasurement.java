package experiment;

public class ParameterSetForMeasurement extends ParameterSetForIteration {
	private static final int DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT = 1;
	private int _numberOfRepetitionOfSingleSort;
	
	public int numberOfReptitionOfSingleSort() {
		return this._numberOfRepetitionOfSingleSort;
	}
	public void setNumberOfRepetitionOfSingleSort(int newNumberOfRepititionOfSingleSort) {
		this._numberOfRepetitionOfSingleSort = newNumberOfRepititionOfSingleSort;
	}
	
	public ParameterSetForMeasurement() {
		super();
		this.setNumberOfRepetitionOfSingleSort(DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT);
	}
	public ParameterSetForMeasurement(
			int givenStartingSize,
			int givenNumberOfIteration,
			int givenIncrementSize,
			int givenNumberOfRepititionOfSingleSort) {
		super(givenStartingSize,givenNumberOfIteration,givenIncrementSize);
		this.setNumberOfRepetitionOfSingleSort(givenNumberOfRepititionOfSingleSort);
		
	}

}
