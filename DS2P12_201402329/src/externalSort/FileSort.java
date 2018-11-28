package externalSort;

import app.AppView;
import fileHandling.FileObject;
import fileHandling.IOType;
import fileHandling.Run;
import sort.QuickSort;
import sort.QuickSortWithInsertionSort;

/** �־��� �ٽ�ũ ������ �����Ͽ� ���ο� ���Ϸ� �����Ѵ�.*/
public class FileSort {
	private static boolean DEBUG =true;
	private Integer[] _runMemory;
	private int _maxInternalSorting;
	private String _defaultFilePath;
	
	private String _inputFileName;
	private String _outputFileName;
	
	private FileObject _inputFileObject;
	
	private RunManager _runManager;
	
	private QuickSort<Integer> _quickSort = new QuickSortWithInsertionSort<Integer>(true);
	
	
	private FileObject inputFileObject() {
		return this._inputFileObject;
	}
	private void setInputFileObject(FileObject aInputFileObject) {
		this._inputFileObject = aInputFileObject;
	}
	
	private int maxInternalSorting() {
		return this._maxInternalSorting;
	}
	
	
	private String defaultFilePath() {
		return this._defaultFilePath;
	}
	private void setDefaultFilePath(String aDefaultFilePath) {
		this._defaultFilePath = aDefaultFilePath;
	}
	
	private void setRunManager(RunManager aRunManager) {
		
		this._runManager = aRunManager;
	}
	private RunManager runManager() {
		return this._runManager;
	}
	
	private String inputFileName() {
		return this._inputFileName;
	}
	private void setInputFileName(String aFileName) {
		this._inputFileName = aFileName;
	}
	
	private String outputFileName() {
		return this._outputFileName;
	}
	private void setOutputFileName(String aOutputFileName) {
		this._outputFileName = aOutputFileName;
	}
	
	private Integer[] runMemory() {
		return this._runMemory;
	}
	private void setRunMemory(Integer[] aRunMemory) {
		this._runMemory = aRunMemory;
	}
	public FileSort() {
		
	}
	public FileSort(int givenMaxInternalSortingSize, String givenDefaultFilePath) {
		this.setMaxInternalSortingSize(givenMaxInternalSortingSize);
		this.setDefaultFilePath(givenDefaultFilePath);
		this.setRunMemory(new Integer[givenMaxInternalSortingSize]);
		
	}
	
	public void setFilePath(String newFilePath) {
		this._defaultFilePath = newFilePath;
		
	}
	public void setMaxInternalSortingSize(int aMaxInternalSortingSize) {
		this._maxInternalSorting = aMaxInternalSortingSize;
	}
	public void sortFile(String anInputFileName, String anOutputFileName) {
		this.setInputFileName(anInputFileName);
		this.setOutputFileName(anOutputFileName);
		
		this.generateInitRuns();
		this.mergeAllRuns();
	}
	private boolean readElementsForInternalSort() {
		int count = 0;
		for(int i = 0;i<this.maxInternalSorting();i++) {
			Integer value = this.inputFileObject().readInteger();
			if(value ==null||value ==-1) {
				break;
			}
			this.runMemory()[i] = value.intValue();
			count++;
		}
		if(count==0) return false;
		return true;
	}
	private void mergeAllRuns() {
		while(true) {
			this.mergeTwoRuns();
			if(this.runManager().runListLength() == 1) break;
		}
		this.renameFinalRun();
	}
	private void renameFinalRun() {
		Run lastRun = this.runManager().getRun();
		lastRun.renameFile(this.outputFileName());
	}
	private void mergeTwoRuns() {
		Run firstRun = this.runManager().getRun();
		Run secondRun = this.runManager().getRun();
		
		firstRun.setIOType(IOType.Input);
		secondRun.setIOType(IOType.Input);
		
		if(firstRun.openFile()== false|| secondRun.openFile()==false) {
			AppView.outputErrorLine("�� ������ �� �� �����ϴ�.");
			return;
		}
		Run outputRun = new Run();
		outputRun.openFile();
		 
		
		Integer firstRunValue = firstRun.readInteger();
		Integer secondRunValue = secondRun.readInteger();
		
		while(true) {
			if(firstRunValue == null && secondRunValue == null) break;
			if(firstRunValue == null && secondRunValue !=null) {
				outputRun.writeInteger(secondRunValue);
				secondRunValue = secondRun.readInteger();
				continue;
			}
			else if (firstRunValue !=null && secondRunValue ==null) {
				outputRun.writeInteger(firstRunValue);
				firstRunValue = firstRun.readInteger();
				continue;
			}
			else if (firstRunValue.compareTo(secondRunValue)<0) {
				outputRun.writeInteger(firstRunValue);
				firstRunValue = firstRun.readInteger();
			}
			else {
				outputRun.writeInteger(secondRunValue);
				secondRunValue = secondRun.readInteger();
			}
		}
			firstRun.closeFile();
			secondRun.closeFile();
			outputRun.closeFile();
			this.runManager().insertRunList(outputRun);
			
			if(firstRun.removeFile() == false) {
				AppView.outputErrorLine("first Run�� �����ϴµ� �����߽��ϴ�.");
			}
			if(secondRun.removeFile() == false) {
				AppView.outputErrorLine("Run�� �����ϴµ� �����߽��ϴ� .");
			}
		
	}

	private void generateInitRuns() {
		this.setRunManager(new RunManager());
		this.runManager().setRunInformation(this.defaultFilePath(), "runlist");

		this.setInputFileObject(new FileObject(this.defaultFilePath(),this.inputFileName(),IOType.Input));

		if(this.inputFileObject().openFile() == false) {
			AppView.outputErrorLine("�ʱ� run ������ ���� ���� ���⿡ �����߽��ϴ�:"+this.inputFileName());
			return;
		}

		while(this.readElementsForInternalSort()) {
			if((this._quickSort.sort(this.runMemory()))==false) {
				AppView.outputErrorLine("Run ���Ŀ� �����Ͽ����ϴ�");
				break;
			}
			this.runManager().insertRunList(this.runMemory());

		}
		if(this.inputFileObject().closeFile()==false) {
			AppView.outputErrorLine("�ʱ� run ������ ���� ���� �ݱ⿡ �����Ͽ����ϴ� : "+this.inputFileName());
			return;
		}

	}
	public void validation() {
		FileObject validFile = new FileObject(this.defaultFilePath(),this.outputFileName(),IOType.Input);
		if(validFile.openFile() == false) {
			AppView.outputErrorLine("- Validation: ������ �� �� �����ϴ�"+this.outputFileName());
			return;
		}
		Integer value = validFile.readInteger();
		boolean state = true;
		if(value == null){
			AppView.outputErrorLine("- Validation: ������ ���� �� �����ϴ�"+this.outputFileName());
			return;
		}
		while(true) {
			Integer newValue = validFile.readInteger();
			if((newValue) == null) {
				break;
			}
			if(value.compareTo(newValue)>0) {
				AppView.outputErrorLine("- ������ ���ĵǾ� ���� �ʽ��ϴ�");
				state = false;
			}
		}
		if(validFile.closeFile()==false) {
			AppView.outputErrorLine("- Validation:������ ���� �� �����ϴ�"+this.outputFileName());
		}
		if(state) {
			AppView.outputLine(">������ ���������� ���ĵǾ����ϴ�:"+this.outputFileName());
		}
	}

}
