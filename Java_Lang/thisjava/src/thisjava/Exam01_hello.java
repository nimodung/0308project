package thisjava;

public class Exam01_hello {
	public static void main(String[] args)
	{
		Car myCar;
		Car yourCar;
		
		myCar = new Car("기아자동차", 250);
		yourCar = new Car("Bens", 240);
		myCar.color = "Red";
		
		System.out.println("제작 회사 : " + myCar.company);
		System.out.println("최고 스피드 : " + myCar.maxSpeed);
		System.out.println("차 색 : " + myCar.color);
		System.out.println("제작 회사 : " + yourCar.company);
		System.out.println("최고 스피드 : " + yourCar.maxSpeed);
		System.out.println("차 색  : " + yourCar.color);
		
		return;
	}
}
