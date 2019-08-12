package exam18_API2;

public class WrapperExam {

	public static void main(String[] args) {
		
		//Boxing
		Integer obj1 = new Integer(100); // == Integer obj1 = 100;
		Integer obj2 = new Integer("200");
		Integer obj3 = Integer.valueOf("300");
		
		//Unboxing
		int value1 = obj1.intValue();
		int value2 = obj2.intValue();
		int value3 = obj3.intValue();
		int result = obj1 + 10; //obj1이 wrapper 클래스일 경우에는 자동으로 unboxing 되어서 연산 가능
								//연산 후 boxing되어 값 저장 
		
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
		System.out.println(result);
		
//-------------------------------------------------------------------------
		
		System.out.println("[-128~127 초과값일 경우]");
		Integer obj5 = 300;
		Integer obj6 = 300;
		System.out.println(" == 결과 : " + (obj5 == obj6)); //객체들끼리 == : 주소값 비교
		System.out.println(" == 결과 : " + (obj5 == 300)); //값과 비교할때는 자동 unboxing
		System.out.println("언박싱 후 == 결과 : " + (obj5.intValue() == obj6.intValue()));
		System.out.println("equals() 결과 : " + obj5.equals(obj6)); //객체끼리 값 비교하고싶으면
		System.out.println();
		
		System.out.println("[-128~127 범위값일 경우]");
		Integer obj7 = 10;
		Integer obj8 = 10;
		System.out.println(" == 결과 : " + (obj7 == obj8));
		System.out.println("언박싱 후 == 결과 : " + (obj7.intValue() == obj8.intValue()));
		System.out.println("equals() 결과 : " + obj7.equals(obj8));
		
		
		
		
		
		
		
		
		
		
		
	}
}
