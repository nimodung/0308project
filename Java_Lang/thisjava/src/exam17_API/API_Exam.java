package exam17_API;

import java.util.StringTokenizer;

public class API_Exam {

	public static void main(String[] args) {
		
		String text;
		
//----------------------------------------------------------------------------
		text = "ȫ�浿&�̼�ȫ,�ڿ���,���ڹ�-�ָ�ȣ";
		String[] names = text.split("&|,|-");
		
		for(String name : names) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
//----------------------------------------------------------------------------		
		text = "ȫ�浿/�̼�ȫ/�ڿ���";
		
		//how1 : ��ü ��ū ���� ��� for������ ����
		StringTokenizer st = new StringTokenizer(text, "/");
		int countTokens = st.countTokens();
		for(int i = 0; i < countTokens; i++) {
			String token = st.nextToken();
			System.out.println(token);
		}
		
		System.out.println();
		//how2 : ���� �ִ� ��ū�� Ȯ���ϰ� while������ ����
		st = new StringTokenizer(text, "/");
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println(token);
		}
		System.out.println();
//----------------------------------------------------------------------------	
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Java ");
		sb.append("Program Study");
		System.out.println(sb.toString());
		
		sb.insert(4, "2");
		System.out.println(sb.toString());
		
		sb.setCharAt(4, '6');
		System.out.println(sb.toString());
		
		sb.replace(6, 13, "Book");
		System.out.println(sb.toString());
		
		sb.delete(4, 5);
		System.out.println(sb.toString());
		
		int length = sb.length();
		System.out.println("�� ���ڼ� : " + length);
		
		String result = sb.toString();
		System.out.println(result);
		
	}
}
