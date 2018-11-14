package app;

public class AppView {
	private static final boolean DEBUG_MODE = true;
	public static void outputDebugMessage(String aString) {
		if(DEBUG_MODE) {
			System.out.print(aString);
		}
	}
	public static void outputLine(String string) {
		System.out.println(string);
	}
	public static void output(String aString) {
		System.out.print(aString);
	}

}
