package exam14_System;

public class SystemExam {

	public static void main(String[] args) {
		long time1 = System.nanoTime();
		
		int sum = 0;
		
		for(int i = 1; i <= 1000000; i++) {
			sum += i;
		}
		
		long time2 = System.nanoTime();
		
		System.out.println("1 ~ 1000000까지의 합 : " + sum);
		System.out.println("계산에 " + (time2 - time1) + "나노초가 소요되었습니다.");
		
		String osName = System.getProperty("os.name");
		String userName = System.getProperty("user.name");
		String userHome = System.getProperty("user.home");
		
		System.out.println("운영체제 이름 : " + osName);
		System.out.println("사용자 이름 : " + userName);
		System.out.println("사용자 홈 디렉토리 : " + userHome);
		
		String javaHome = System.getenv("JAVA_HOME");
		System.out.println(" [ JAVA_HOME ] : " + javaHome);
	}
}
