package exam12_clone;

import java.util.Date;

public class ToStringExam {

	public static void main(String[] args) {
		Object obj1 = new Object();
		Date obj2 = new Date();
		Car obj3 = new Car("종하 자전거");
		
		System.out.println(obj1);
		System.out.println(obj1.toString());
		
		System.out.println(obj2);
		
		System.out.println(obj3);
	}

	
}
