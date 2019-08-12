package exam17_API;

import java.util.StringTokenizer;

public class API_Exam {

	public static void main(String[] args) {
		
		String text;
		
//----------------------------------------------------------------------------
		text = "홍길동&이수홍,박연수,김자바-최명호";
		String[] names = text.split("&|,|-");
		
		for(String name : names) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
//----------------------------------------------------------------------------		
		text = "홍길동/이수홍/박연수";
		
		//how1 : 전체 토큰 수를 얻어 for문으로 루핑
		StringTokenizer st = new StringTokenizer(text, "/");
		int countTokens = st.countTokens();
		for(int i = 0; i < countTokens; i++) {
			String token = st.nextToken();
			System.out.println(token);
		}
		
		System.out.println();
		//how2 : 남아 있는 토큰을 확인하고 while문으로 루핑
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
		System.out.println("총 문자수 : " + length);
		
		String result = sb.toString();
		System.out.println(result);
		
	}
}
