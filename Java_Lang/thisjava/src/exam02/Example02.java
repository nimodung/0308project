package exam02;

public class Example02 {

	public void method() {
		Example01 ex01 = new Example01();
		
		ex01.setField("HELLO");
		String hello = ex01.getField();
		
		System.out.println(hello);
		ex01.method();
	}
}
