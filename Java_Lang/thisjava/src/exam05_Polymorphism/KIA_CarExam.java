package exam05_Polymorphism;

public class KIA_CarExam {
	public static void main(String[] args) {
		
		BMW_Car myCar = new BMW_Car();
		
		myCar.run();
		
		
		myCar.tires[0] = new KumhoTire();
		myCar.tires[1] = new KumhoTire();
		
		System.out.println();
		myCar.run();
	}
}
