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
		int result = obj1 + 10; //obj1�� wrapper Ŭ������ ��쿡�� �ڵ����� unboxing �Ǿ ���� ����
								//���� �� boxing�Ǿ� �� ���� 
		
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
		System.out.println(result);
		
//-------------------------------------------------------------------------
		
		System.out.println("[-128~127 �ʰ����� ���]");
		Integer obj5 = 300;
		Integer obj6 = 300;
		System.out.println(" == ��� : " + (obj5 == obj6)); //��ü�鳢�� == : �ּҰ� ��
		System.out.println(" == ��� : " + (obj5 == 300)); //���� ���Ҷ��� �ڵ� unboxing
		System.out.println("��ڽ� �� == ��� : " + (obj5.intValue() == obj6.intValue()));
		System.out.println("equals() ��� : " + obj5.equals(obj6)); //��ü���� �� ���ϰ������
		System.out.println();
		
		System.out.println("[-128~127 �������� ���]");
		Integer obj7 = 10;
		Integer obj8 = 10;
		System.out.println(" == ��� : " + (obj7 == obj8));
		System.out.println("��ڽ� �� == ��� : " + (obj7.intValue() == obj8.intValue()));
		System.out.println("equals() ��� : " + obj7.equals(obj8));
		
		
		
		
		
		
		
		
		
		
		
	}
}
