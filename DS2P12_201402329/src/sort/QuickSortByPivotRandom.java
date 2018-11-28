package sort;

import java.util.Random;

public class QuickSortByPivotRandom<E extends Comparable<E>> extends QuickSort<E> {
	public QuickSortByPivotRandom(boolean givenSortingOrder) {
		super(givenSortingOrder);
		this.setRandom(new Random());
	}
	private Random _random;
	
	public Random random() {
		return _random;
	}
	public void setRandom(Random newRandom) {
		this._random = newRandom;
	}
	@Override
	protected int pivot(E[] aList, int left, int right) {
		int randomLocationFromLeft = this.random().nextInt(right-left +1);
		int pivot = left + randomLocationFromLeft;
		return pivot;
	}
	
	

}
