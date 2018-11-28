package app;


import dataGeneration.RandomNumberGenerator;
import externalSort.FileSort;
import fileHandling.FileObject;
import fileHandling.IOType;

public class AppController {
	private static boolean DEBUG =false;
	
	
	private FileObject _fileObject;
	private String _filePath;
	private String _inputFileName;
	private String _outputFileName;
	private int _maxCount;
	
	private FileSort _fileSort;
	private RandomNumberGenerator _randomGen;
	
	private FileObject fileObject() {
		return _fileObject;
	}



	private void setFileObject(FileObject aFileObject) {
		this._fileObject = aFileObject;
	}



	private String filePath() {
		return _filePath;
	}



	private void setfilePath(String aFilePath) {
		this._filePath = aFilePath;
	}



	private String inputFileName() {
		return _inputFileName;
	}



	private void setInputFileName(String aInputFileName) {
		this._inputFileName = aInputFileName;
	}



	private String outputFileName() {
		return _outputFileName;
	}



	private void setOutputFileName(String aOutputFileName) {
		this._outputFileName = aOutputFileName;
	}



	private int maxCount() {
		return _maxCount;
	}



	private void setMaxCount(int aMaxCount) {
		this._maxCount = aMaxCount;
	}



	private FileSort fileSort() {
		return _fileSort;
	}



	private void setFileSort(FileSort aFileSort) {
		this._fileSort = aFileSort;
	}



	private RandomNumberGenerator randomGen() {
		return _randomGen;
	}



	private void setRandomGen(RandomNumberGenerator aRandomGen) {
		this._randomGen = aRandomGen;
	}
	
	private void inputStringOfFilePath() {
		String filePath;
		while(true) {
			filePath = AppView.inputFilePath();
			if(filePath == null) {
				filePath = ".";
			}
			if(FileObject.filePathDoesExist(filePath)) {
				AppView.DebugPrint("��θ� ����� �� �ֽ��ϴ� : "+filePath);
				break;
			}
			AppView.outputLine("[����] �Է��� ���� ��ΰ� �������� �ʽ��ϴ�:"+filePath);
		}
		this.setfilePath(filePath);
	}



	public void inputNumberOfGenerationCount() {
		int maxCount =0 ;
		while(true) {
			maxCount = AppView.inputMaxSize();
			if(maxCount<0) {
				AppView.outputErrorLine("�Է°��� 0���� �۽��ϴ� : "+maxCount);
			}
			else {
				break;
			}
		}
		this.setMaxCount(maxCount);
	}
	
	
	

	private void inputStringOfInputFileName() {
		String fileName = null;
		while(true) {
			AppView.outputLine("- �Է������� �̸��� �Է��մϴ�.");
			if(DEBUG) {
				fileName = "testset.txt";
				AppView.DebugPrint("?�����̸��� ���Ƿ� �����մϴ�:"+fileName);
				break;
			}
			fileName = AppView.inputFileName();
			break;
		}
		this.setInputFileName(fileName);
	}
	
	private void inputStringOfOutputFileName() {
		String fileName;
		
		while(true) {
			AppView.outputLine("- ��� ������ �̸��� �Է��մϴ�.");
			if(DEBUG) {
				fileName = "testset_sorted.txt";
				AppView.DebugPrint("?�����̸��� ���Ƿ� �����մϴ� : "+fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesEixst(this.filePath(), fileName)) {
				AppView.outputErrorLine("�ش� ������ �����մϴ�."+fileName);
			}
			else {
				break;
			}
		}
		this.setOutputFileName(fileName);
	}
	public void makeRandomFile() {
		AppView.outputLine(">������ ���� ������ ���� ������ �Է��ؾ� �մϴ�.");
		this.inputStringOfFilePath();
		this.inputStringOfInputFileName();
		this.inputNumberOfGenerationCount();
		AppView.outputLine("");
		
		AppView.outputLine(">������ ������ �����մϴ�.");
		
		this.setFileObject(new FileObject(this.filePath(),this.inputFileName(),IOType.Output));
		this.setRandomGen(new RandomNumberGenerator());
		this.randomGen().prepare(this.maxCount());
		
		AppView.outputLine(">������ �����մϴ�:");
		if(this.fileObject().openFile()) {
			for(int i = 0;i<this.maxCount();i++) {
				int randomValue = this.randomGen().randomNumber();
				AppView.DebugPrint(String.format("���Ͽ� ���ڸ� ����մϴ�:%d", randomValue));
				this.fileObject().writeInteger(randomValue);
			}
		}
		else {
			
		}
		AppView.outputLine(">������ �����Ͽ����ϴ�");
		this.fileObject().closeFile();
		
	}
	
	
	public void inputAndMakeFile() {
		this.makeRandomFile();
		AppView.outputLine("> ������ ������ ���Ϸ�  �ܺ�������  �����մϴ� ");
		AppView.outputLine("> ���� �̸� : "+this.inputFileName());

		this.inputStringOfOutputFileName();
		AppView.outputLine("");
		 
		}
	private void showSortedFile() {
		AppView.outputLine("");
		AppView.outputLine(" - ���ĵ� ������ �̸��� ������ �����ϴ�.:");
		AppView.outputLine(" - FileName: "+this.outputFileName());
		
		AppView.outputLine("> ������ �����մϴ�.");
		AppView.outputLine("");
		this.fileSort().validation();
		
	}
	private void devideAndSort() {
		this.setFileSort(new FileSort(200,this.filePath()));
		AppView.outputLine(">2-Way ���� ������ �����մϴ�.");
		this.fileSort().sortFile(this.inputFileName(), this.outputFileName());
	}

	
	public void run() {
		AppView.outputLine("<<<�ܺ� ���� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		
		this.inputAndMakeFile();
		
		this.devideAndSort();
		
		this.showSortedFile();
		
		AppView.outputLine("<<<�ܺ����� ���α׷��� �����մϴ�.>>>");
	}
	


}
