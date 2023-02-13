package WebDriver;

public class FireFoxDriver {

	public static void main(String[] args) {
//		 WebDriver m= new FireFoxDriver();
		
		FireFoxDriver obj = new FireFoxDriver();
		obj.b1();
	
	
	
	}
	public void a2() {
		
		FireFoxDriver obj = new FireFoxDriver();
	
	}
	
	
	
//	@Override
	public void b1() {
		System.out.println("overrid method of FireFoxDriver");
	}
	 public FireFoxDriver(){
	super();
	System.out.println("Non-parameterised constructer of parent class");

	}
	public FireFoxDriver(int g){
		this();
		System.out.println("parameterised constructer of parent class");

	}
}
