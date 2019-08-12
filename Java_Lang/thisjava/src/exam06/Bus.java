package exam06;

public class Bus implements Vehicle {

	@Override
	public void run() {
		System.out.println("버스가 달립니다.");
	}

	void checkFare() {
		System.out.println("요금을 검사합니다.");
	}
}
