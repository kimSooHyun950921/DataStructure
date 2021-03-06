package app;

import java.util.Scanner;

public class AppView {
	private static Scanner scanner = new Scanner(System.in);
	private static final boolean DEBUG_MODE = true;
	
	public static String inputFileName() {
		String fileName;
		String scannedToken;
		while(true) {
			AppView.output("?  사용할 파일 이름을 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			fileName = scannedToken;
			return fileName;
		}
	}
	public static String inputFilePath() {
		String filePath;
		String scannedToken;
		while(true) {
			AppView.output("? 파일을 저장할 경로를 입력하세요(. 입력시 프로젝트 경로 사용):");
			scannedToken = AppView.scanner.next();
			filePath = scannedToken;
			return filePath;
		}
	}
	public static int inputMaxSize() {
		int maxSize;
		String scannedToken;
		while(true) {
			AppView.output("? 생성할 수의 최대 값을 입력하세요 : ");
			scannedToken = AppView.scanner.next();
			try {
				maxSize = Integer.parseInt(scannedToken);
				return maxSize;
			}
			catch(NumberFormatException e) {
				AppView.outputLine("[오류] 최대값 입력에 오류  있습니다: "+scannedToken);
			}
		}
	}
	
	public static void outputDebugMessage(String aString) {
		//if(DEBUG_MODE) {
			System.out.println(aString);
	//	}
	}
	public static void outputLine(String string) {
		System.out.println(string);
	}
	public static void output(String aString) {
		System.out.print(aString);
	}
	public static void DebugPrint(String string) {
		System.out.println("[Debug] "+string);
	}
	public static void outputErrorLine(String string) {
		System.out.println("[ERROR] "+string);
	}
	

}
