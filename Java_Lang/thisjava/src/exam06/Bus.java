package exam06;

public class Bus implements Vehicle {

	@Override
	public void run() {
		System.out.println("������ �޸��ϴ�.");
	}

	void checkFare() {
		System.out.println("����� �˻��մϴ�.");
	}
}
