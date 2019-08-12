package exam15_class;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassExam {

	public static void main(String[] args) {
		Car car = new Car();
		Class clazz1 = car.getClass();  //car�� Ŭ������ ����
		
		System.out.println(clazz1.getName()); 				//��Ű��.Ŭ�����̸�
		System.out.println(clazz1.getSimpleName()); 		//Ŭ���� �̸���
		System.out.println(clazz1.getPackage().getName()); //��Ű�� �̸�
		System.out.println();
		
		System.out.println("[������ ����]");
		Constructor[] constructors = clazz1.getDeclaredConstructors();
		for(Constructor constructor : constructors) {
			System.out.print(constructor.getName() + "(");
			Class[] parameters = constructor.getParameterTypes();
			printParameters(parameters);
			System.out.println(")");
		}
		System.out.println();
		
		System.out.println("[�ʵ� ����]");
		Field[] fields = clazz1.getDeclaredFields();
		for(Field field : fields) {
			System.out.println(field.getType().getSimpleName() + " " + field.getName());
		}
		System.out.println();
		
		System.out.println("[�޼ҵ� ����]");
		Method[] methods = clazz1.getDeclaredMethods();
		for(Method method : methods) {
			System.out.print(method.getName() + "(");
			Class[] parameters = method.getParameterTypes();
			printParameters(parameters);
			System.out.println(")");
		}
	}
	
	private static void printParameters(Class[] parameters) {
		for(int i = 0; i < parameters.length; i++) {
			System.out.print(parameters[i].getName());
			if(i < (parameters.length - 1)) {
				System.out.print(",");
			}
		}
	}
}
