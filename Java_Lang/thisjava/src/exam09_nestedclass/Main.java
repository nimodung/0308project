package exam09_nestedclass;

public class Main {
	public static void main(String[] args) {
		A a = new A();
		
		A.B b = a.new B();
		b.fieldB = 3;
		b.methodB();
		
		A.C.fieldC_static = 3;
		A.C.methodC2_static();
		A.C c = new A.C();
		c.fieldC = 4;
		c.methodC();
		
		a.method();
	}
}
