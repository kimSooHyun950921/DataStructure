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
				AppView.DebugPrint("경로를 사용할 수 있습니다 : "+filePath);
				break;
			}
			AppView.outputLine("[오류] 입력한 파일 경로가 존재하지 않습니다:"+filePath);
		}
		this.setfilePath(filePath);
	}



	public void inputNumberOfGenerationCount() {
		int maxCount =0 ;
		while(true) {
			maxCount = AppView.inputMaxSize();
			if(maxCount<0) {
				AppView.outputErrorLine("입력값이 0보다 작습니다 : "+maxCount);
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
			AppView.outputLine("- 입력파일의 이름을 입력합니다.");
			if(DEBUG) {
				fileName = "testset.txt";
				AppView.DebugPrint("?파일이름을 임의로 설정합니다:"+fileName);
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
			AppView.outputLine("- 출력 파일의 이름을 입력합니다.");
			if(DEBUG) {
				fileName = "testset_sorted.txt";
				AppView.DebugPrint("?파일이름을 임의로 설정합니다 : "+fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesEixst(this.filePath(), fileName)) {
				AppView.outputErrorLine("해당 파일이 존재합니다."+fileName);
			}
			else {
				break;
			}
		}
		this.setOutputFileName(fileName);
	}
	public void makeRandomFile() {
		AppView.outputLine(">무작위 파일 생성을 위해 정보를 입력해야 합니다.");
		this.inputStringOfFilePath();
		this.inputStringOfInputFileName();
		this.inputNumberOfGenerationCount();
		AppView.outputLine("");
		
		AppView.outputLine(">무작위 파일을 생성합니다.");
		
		this.setFileObject(new FileObject(this.filePath(),this.inputFileName(),IOType.Output));
		this.setRandomGen(new RandomNumberGenerator());
		this.randomGen().prepare(this.maxCount());
		
		AppView.outputLine(">파일을 오픈합니다:");
		if(this.fileObject().openFile()) {
			for(int i = 0;i<this.maxCount();i++) {
				int randomValue = this.randomGen().randomNumber();
				AppView.DebugPrint(String.format("파일에 숫자를 기록합니다:%d", randomValue));
				this.fileObject().writeInteger(randomValue);
			}
		}
		else {
			
		}
		AppView.outputLine(">파일을 생성하였습니다");
		this.fileObject().closeFile();
		
	}
	
	
	public void inputAndMakeFile() {
		this.makeRandomFile();
		AppView.outputLine("> 생성한 무작위 파일로  외부정렬을  시작합니다 ");
		AppView.outputLine("> 파일 이름 : "+this.inputFileName());

		this.inputStringOfOutputFileName();
		AppView.outputLine("");
		 
		}
	private void showSortedFile() {
		AppView.outputLine("");
		AppView.outputLine(" - 정렬된 파일의 이름은 다음과 같습니다.:");
		AppView.outputLine(" - FileName: "+this.outputFileName());
		
		AppView.outputLine("> 파일을 검증합니다.");
		AppView.outputLine("");
		this.fileSort().validation();
		
	}
	private void devideAndSort() {
		this.setFileSort(new FileSort(200,this.filePath()));
		AppView.outputLine(">2-Way 파일 정렬을 시작합니다.");
		this.fileSort().sortFile(this.inputFileName(), this.outputFileName());
	}

	
	public void run() {
		AppView.outputLine("<<<외부 정렬 프로그램을 시작합니다 >>>");
		AppView.outputLine("");
		
		this.inputAndMakeFile();
		
		this.devideAndSort();
		
		this.showSortedFile();
		
		AppView.outputLine("<<<외부정렬 프로그램을 종료합니다.>>>");
	}
	


}
