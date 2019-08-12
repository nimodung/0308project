package exam15_class;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassExam {

	public static void main(String[] args) {
		Car car = new Car();
		Class clazz1 = car.getClass();  //car의 클래스를 리턴
		
		System.out.println(clazz1.getName()); 				//패키지.클래스이름
		System.out.println(clazz1.getSimpleName()); 		//클래스 이름만
		System.out.println(clazz1.getPackage().getName()); //패키지 이름
		System.out.println();
		
		System.out.println("[생성자 정보]");
		Constructor[] constructors = clazz1.getDeclaredConstructors();
		for(Constructor constructor : constructors) {
			System.out.print(constructor.getName() + "(");
			Class[] parameters = constructor.getParameterTypes();
			printParameters(parameters);
			System.out.println(")");
		}
		System.out.println();
		
		System.out.println("[필드 정보]");
		Field[] fields = clazz1.getDeclaredFields();
		for(Field field : fields) {
			System.out.println(field.getType().getSimpleName() + " " + field.getName());
		}
		System.out.println();
		
		System.out.println("[메소드 정보]");
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
