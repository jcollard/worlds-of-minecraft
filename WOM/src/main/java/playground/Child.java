package playground;

public class Child extends Parent {
	@Override
	public String method(String a, String b, int c) {
		System.out.println("Child Method: " + a + ", " + b + ", " + c);
		return "Child Method";
	}
	
}
