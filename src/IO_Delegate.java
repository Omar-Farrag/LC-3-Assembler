import java.util.Scanner;

class IO_Delegate {

	private final static Scanner scanner = new Scanner(System.in);
	
	public static void println(String text) {
		System.out.println(text);
	}
	public static void print(String text) {
		System.out.print(text);
	}
	public static String readln() {
		return scanner.nextLine();
	}
}
