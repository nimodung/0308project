package exam11_equals;

public class MemberExam {

	public static void main(String[] args) {
		Object obj1 = new Object();
		Object obj2 = new Object();
		
		Member obj3 = new Member("����");
		Member obj4 = new Member("����");
		Member obj5 = obj3;
		
		if(obj1.equals(obj2)) {
			System.out.println("obj1�� obj2�� �����մϴ�.");
		}
		else {
			System.out.println("obj1�� obj2�� �������� �ʽ��ϴ�."); //��ü�� �ּҺ�
		}
		
		if(obj3.equals(obj4)) {
			System.out.println("obj3�� obj4�� �����մϴ�.");
		}
		else {
			System.out.println("obj3�� obj4�� �������� �ʽ��ϴ�."); //��ü�� �ּҺ�
		}
		
		if(obj3.equals(obj5)) {
			System.out.println( obj3 +"�� " + obj5 +"�� �����մϴ�.");
		}
		else {
			System.out.println(obj3+"�� " + obj5 + "�� �������� �ʽ��ϴ�."); //��ü�� �ּҺ�
		}
	}
}
