package exam09_nestedclass;

public class A {

	A() {
		System.out.println("A 객체가 생성되었습니다.");
	}
	
	public class B {
		B() { 
			System.out.println("B 객체가 생성되었습니다.");
		}
		int fieldB;
		void methodB() {}
	}
	
	static class C {
		C() {
			System.out.println("C 객체가 생성되었습니다.");
		}
		int fieldC;
		static int fieldC_static;
		void methodC() {System.out.println("C객체의 methodC");}
		static void methodC2_static() {System.out.println("C객체의 methodC_static");}
	}
	
	void method() {
		class D {
			D() {
				System.out.println("D 객체가 생성되었습니다.");
			}
			int fieldD;
			void methodD() {System.out.println("D객체의 methodD");}
		}
		
		D d = new D();
		d.fieldD = 3;
		d.methodD();
	}
	
	
}
