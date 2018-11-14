package app;
import experiment.DataGenerator;

import sort.QuickSortByPivotLeft;
import sort.QuickSortByPivotMedian;
import sort.QuickSortByPivotMid;
import sort.QuickSortByPivotRandom;
import sort.QuickSortWithInsertionSort;
import sort.Sort;
public class AppController {
	private static final int TEST_SIZE = 1000;
	
	private static final QuickSortByPivotLeft<Integer> pivitLeft = new QuickSortByPivotLeft<Integer>(true);
	private static final QuickSortByPivotMid<Integer> pivotMid = new QuickSortByPivotMid<Integer>(true);
	private static final QuickSortByPivotRandom<Integer> pivotRandom = new QuickSortByPivotRandom<Integer>(true);
	private static final QuickSortByPivotMedian<Integer> pivotMedain = new QuickSortByPivotMedian<Integer>(true);
	private static final QuickSortWithInsertionSort<Integer> pivotWithInsertion = new QuickSortWithInsertionSort<>(true);

	private Integer[] _list;
	private String _listTypeName;
	
	private Integer[] list() {
		return _list;
	}

	private void setList(Integer[] newList) {
		this._list = newList;
	}

	private String listTypeName() {
		return _listTypeName;
	}

	private void setListTypeName(String newListTypeName) {
		this._listTypeName = newListTypeName;
	}

	private String sortingOrderName(Sort<Integer> aSort) {
		if(aSort.nonDecreasingOrder()) {
			return "��������";
		}
		else {
			return "��������";
		}
	}
	private void outputValidationMessage(Sort<Integer> aSort,Integer[] aList) {
		AppView.output("- ["+this.listTypeName()+"]�� ����["+this.sortingOrderName(aSort)+"]�� ["+
	aSort.getClass().getSimpleName()+"] �����");
		if(this.sortedListIsValid(aList,aSort.nonDecreasingOrder())) {
			AppView.outputLine("�ùٸ��ϴ�.");
		}
		else {
			AppView.outputLine("�߸��Ǿ����ϴ�.");
		}
	}
	private Integer[] copyList(Integer[] aList) {
		Integer[] copiedList = new Integer[aList.length];
		for(int i = 0;i<aList.length;i++) {
			copiedList[i] = aList[i];
		}
		return copiedList;
	}
	
	private boolean sortedListIsValid(Integer[] aList, boolean nonDecreasingOrder) {
		if(nonDecreasingOrder) {
			for(int i =0;i<aList.length-1;i++) {
				if(aList[i].compareTo(aList[i+1])>0){
					return false;
				}
			}
			return true;
		}
		else {
			for(int i = 0;i<aList.length-1;i++) {
				if(aList[i].compareTo(aList[i+1])<0) {
					return false;
				}
			}
		}
		return true;
	}
	private void validateSort(Sort<Integer> aSort) {
		Integer[] list = copyList(this.list());
		aSort.sort(list);
		this.outputValidationMessage(aSort, list);
	}
	private void validSorts() {
		this.validateSort(AppController.pivitLeft);
		this.validateSort(AppController.pivotMid);
		this.validateSort(AppController.pivotRandom);
		this.validateSort(AppController.pivotMedain);
		this.validateSort(AppController.pivotWithInsertion);

		
		AppView.outputLine("");
	}
	private void validWithRandomList() {
		this.setListTypeName("������ ����Ʈ");
		this.setList(DataGenerator.randomList(AppController.TEST_SIZE));
		this.validSorts();
	}
	private void validWithAscendingList() {
		this.setListTypeName("�������� ����Ʈ");
		this.setList(DataGenerator.ascendingList(AppController.TEST_SIZE));
		this.validSorts();
	}
	private void validWithDescendintList() {
		this.setListTypeName("�������� ����Ʈ");
		this.setList(DataGenerator.descendingList(AppController.TEST_SIZE));
		this.validSorts();
	}
	private void setSortingOrder(boolean aNonDecreasing) {
		AppController.pivitLeft.setNonDecreasingOrder(aNonDecreasing);
		AppController.pivotMid.setNonDecreasingOrder(aNonDecreasing);
		AppController.pivotRandom.setNonDecreasingOrder(aNonDecreasing);
		AppController.pivotMedain.setNonDecreasingOrder(aNonDecreasing);
		AppController.pivotWithInsertion.setNonDecreasingOrder(aNonDecreasing);


		
	}


	public void run() {
		AppView.outputLine("<<<���� �˰������ �����ϴ� ���α׷��� �����մϴ�.>>>");
		AppView.outputLine("");
		AppView.outputLine(">�������� ���� ���α׷��� ����:");
		this.validWithRandomList();
		this.validWithAscendingList();
		this.validWithDescendintList();
		
		AppView.outputLine(">�������� �������α׷��� ����:");
		this.setSortingOrder(false);
		this.validWithRandomList();
		this.validWithAscendingList();
		this.validWithDescendintList();
		
		AppView.outputLine("<<<���� �˰������ �����ϴ� ���α׷��� �����մϴ�.>>>");
	}
	
}
