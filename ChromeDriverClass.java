package WebDriver;

public class ChromeDriverClass extends WebDriver {
	public static void main(String[] args) {
		WebDriver h = new ChromeDriverClass();
		h.b1();

	}

	
	@Override
	public void b1() {
		System.out.println("i am overrid method of class 1");
	}

	public static void m1() {
		System.out.println("first method");
	}

	public void m1(int r) {
		System.out.println("second method");
	}

	public void m3() {
		System.out.println("third method");
	}

	public ChromeDriverClass() {
		System.out.println("i am constructor of class chrome driver");
	}

	public void get(String string) {
		// TODO Auto-generated method stub

	}
}
