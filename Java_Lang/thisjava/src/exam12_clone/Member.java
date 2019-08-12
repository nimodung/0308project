package exam12_clone;

import java.util.Arrays;

public class Member implements Cloneable {

	public String id;
	public String name;
	public String password;
	public int age;
	public boolean adult;
	public Car car;
	public int[] scores;
	
	public Member(String id, String name, String password, int age, boolean adult, Car car, int[] scores) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.adult = adult;
		this.car = car;
		this.scores = scores;
	}
	
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		Member cloned = (Member) super.clone(); //얕은 복제
		cloned.car = new Car(this.car.model); 	//깊은 복제
		cloned.scores = Arrays.copyOf(this.scores, this.scores.length);  //배열을 깊은 복제
		return cloned;
	}



	public Member getMember() {
		Member cloned = null;
	
		try {
			cloned = (Member) clone(); //오버라이드한 clone()사용
		} catch (CloneNotSupportedException e) {}
		return cloned;
	}
}
