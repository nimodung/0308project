package exam11_equals;

public class MemberExam {

	public static void main(String[] args) {
		Object obj1 = new Object();
		Object obj2 = new Object();
		
		Member obj3 = new Member("종하");
		Member obj4 = new Member("은주");
		Member obj5 = obj3;
		
		if(obj1.equals(obj2)) {
			System.out.println("obj1과 obj2는 동등합니다.");
		}
		else {
			System.out.println("obj1과 obj2는 동등하지 않습니다."); //객체의 주소비교
		}
		
		if(obj3.equals(obj4)) {
			System.out.println("obj3과 obj4는 동등합니다.");
		}
		else {
			System.out.println("obj3과 obj4는 동등하지 않습니다."); //객체의 주소비교
		}
		
		if(obj3.equals(obj5)) {
			System.out.println( obj3 +"과 " + obj5 +"는 동등합니다.");
		}
		else {
			System.out.println(obj3+"과 " + obj5 + "는 동등하지 않습니다."); //객체의 주소비교
		}
	}
}
