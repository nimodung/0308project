package exam05_Polymorphism;

public class KIA_Car {

	Tire frontLeftTire = new HankookTire();
	Tire forntRightTire = new HankookTire();
	Tire backLeftTire = new HankookTire();
	Tire backRightTire = new HankookTire();
	
	void run() {
		frontLeftTire.roll();
		forntRightTire.roll();
		backLeftTire.roll();
		backRightTire.roll();
	}
}
