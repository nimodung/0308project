package exam09_nestedclass;

public class A {

	A() {
		System.out.println("A ��ü�� �����Ǿ����ϴ�.");
	}
	
	public class B {
		B() { 
			System.out.println("B ��ü�� �����Ǿ����ϴ�.");
		}
		int fieldB;
		void methodB() {}
	}
	
	static class C {
		C() {
			System.out.println("C ��ü�� �����Ǿ����ϴ�.");
		}
		int fieldC;
		static int fieldC_static;
		void methodC() {System.out.println("C��ü�� methodC");}
		static void methodC2_static() {System.out.println("C��ü�� methodC_static");}
	}
	
	void method() {
		class D {
			D() {
				System.out.println("D ��ü�� �����Ǿ����ϴ�.");
			}
			int fieldD;
			void methodD() {System.out.println("D��ü�� methodD");}
		}
		
		D d = new D();
		d.fieldD = 3;
		d.methodD();
	}
	
	
}
