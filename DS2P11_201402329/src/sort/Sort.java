package sort;

public abstract class Sort<E extends Comparable<E>> {
	private boolean _nonDecreasingOrder;
	
	public boolean nonDecreasingOrder() {
		return _nonDecreasingOrder;
	}
	public void setNonDecreasingOrder(boolean _nonDecreasingOrder) {
		this._nonDecreasingOrder = _nonDecreasingOrder;
	}
	protected void swap(E[] aList,int i, int j) {
		E tempElement  =aList[j];
		aList[j] = aList[i];
		aList[i]  = tempElement; 
	}
	protected int compare(E anElement,E theOtherElement) {
		if(this.nonDecreasingOrder()) {
			return anElement.compareTo(theOtherElement);
		}
		else {
			return -anElement.compareTo(theOtherElement);
		}
	}
	public Sort(boolean givenSortingOrder) {
		this.setNonDecreasingOrder(givenSortingOrder);
	}
	public abstract boolean sort(E[] aList);
	
	
	
}
