package thisjava;

public class Exam01_hello {
	public static void main(String[] args)
	{
		Car myCar;
		Car yourCar;
		
		myCar = new Car("����ڵ���", 250);
		yourCar = new Car("Bens", 240);
		myCar.color = "Red";
		
		System.out.println("���� ȸ�� : " + myCar.company);
		System.out.println("�ְ� ���ǵ� : " + myCar.maxSpeed);
		System.out.println("�� �� : " + myCar.color);
		System.out.println("���� ȸ�� : " + yourCar.company);
		System.out.println("�ְ� ���ǵ� : " + yourCar.maxSpeed);
		System.out.println("�� ��  : " + yourCar.color);
		
		return;
	}
}
