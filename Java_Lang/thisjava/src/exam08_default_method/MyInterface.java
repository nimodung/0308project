package exam08_default_method;

public interface MyInterface {

	public void method1();
	
	
	public default void method2() {
		System.out.println("MyInterface default method2 ½ÇÇà");
	}
}
