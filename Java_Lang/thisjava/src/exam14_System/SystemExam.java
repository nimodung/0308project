package exam14_System;

public class SystemExam {

	public static void main(String[] args) {
		long time1 = System.nanoTime();
		
		int sum = 0;
		
		for(int i = 1; i <= 1000000; i++) {
			sum += i;
		}
		
		long time2 = System.nanoTime();
		
		System.out.println("1 ~ 1000000������ �� : " + sum);
		System.out.println("��꿡 " + (time2 - time1) + "�����ʰ� �ҿ�Ǿ����ϴ�.");
		
		String osName = System.getProperty("os.name");
		String userName = System.getProperty("user.name");
		String userHome = System.getProperty("user.home");
		
		System.out.println("�ü�� �̸� : " + osName);
		System.out.println("����� �̸� : " + userName);
		System.out.println("����� Ȩ ���丮 : " + userHome);
		
		String javaHome = System.getenv("JAVA_HOME");
		System.out.println(" [ JAVA_HOME ] : " + javaHome);
	}
}
