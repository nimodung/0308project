package exam19_Math;

import java.time.LocalDateTime;

public class JavaTimeExam {

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();

		System.out.println("���� : " + now);
		
		LocalDateTime targetDateTime = null;
		
		targetDateTime = now
				.withYear(2024)
				.withMonth(10)
				.withDayOfMonth(5)
				.withHour(13)
				.withMinute(30)
				.withSecond(20);
		System.out.println("���� ���� : " + targetDateTime);
	}

}
