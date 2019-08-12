package exam12_clone;

public class MemberExam {

	public static void main(String[] args) {
		Member original = new Member("blue", "����", "12345", 18, false, 
								new Car("������"), new int[] {20, 40});

		Member cloned = original.getMember();
		//Member cloned = original;
		cloned.password = "67890";
		cloned.car.model = "������";
		cloned.scores[0] = 10;
		
		//��� ȭ���ä���
		System.out.println("[���� ��ü�� �ʵ尪]");
		System.out.println("id : " + cloned.id);
		System.out.println("name : " + cloned.name);
		System.out.println("password : " + cloned.password);
		System.out.println("age : " + cloned.age);
		System.out.println("adult : " + cloned.adult);
		System.out.println("car : " + cloned.car.model);
		for(int i = 0; i < cloned.scores.length; i++) {
			System.out.print(cloned.scores[i]);
			System.out.print((i == (cloned.scores.length-1))?"":",");
			
		}
		System.out.println();System.out.println();
		
		System.out.println("[���� ��ü�� �ʵ尪]");
		System.out.println("id : " + original.id);
		System.out.println("name : " + original.name);
		System.out.println("password : " + original.password);
		System.out.println("age : " + original.age);
		System.out.println("adult : " + original.adult);
		System.out.println("car : " + original.car.model);
		for(int i = 0; i < original.scores.length; i++) {
			System.out.print(original.scores[i]);
			System.out.print((i == (original.scores.length-1))?"":",");
			
		}
	}

}
