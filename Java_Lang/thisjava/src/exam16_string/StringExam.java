package exam16_string;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class StringExam {

	public static void main(String[] args) {
		String str = "Hello World!!!";
		System.out.println(str);
		
		byte[] bytes = {72, 101, 108, 108, 111, 32, 74, 97, 118, 97};
		
		String str1 = new String(bytes);
		System.out.println(str1);
		String str2 = new String(bytes, 6, 4);
		System.out.println(str2);
		
		char ch1 = str1.charAt(7);
		System.out.println(ch1);
		
		String ssn = "930705-3111111";
		char sex = ssn.charAt(7);
		switch(sex) {
		case '3':
			System.out.println("���� �Դϴ�.");
		case '1':
			
		
			System.out.println("�����Դϴ�.");
			break;
		case '2':
		case '4' :
			System.out.println("�����Դϴ�.");
			break;
			
		}
		
		String strVal1 = new String("ABC");
		String strVal2 = "ABC";
		
		if(strVal1 == strVal2) {
			System.out.println("���� String ��ü�� ����");
		}
		else {
			System.out.println("�ٸ� String ��ü�� ����");
		}
		
		if(strVal1.equals(strVal2)) {
			System.out.println("���� ���ڿ��� ����");
		}
		else {
			System.out.println("�ٸ� ���ڿ��� ����");
		}
		
		str = "���Ϲٺ������̹ٺ�";
		
		byte[] bytes1 = str.getBytes();
		System.out.println("bytes1.length : " + bytes1.length);
		str1 = new String(bytes1);
		System.out.println("bytes1 -> String : " + str1);
		
		try {
			byte[] bytes2 = str.getBytes("EUC-KR");
			System.out.println("bytes2.length : " + bytes2.length);
			str2 = new String(bytes2, "EUC-KR");
			System.out.println("bytes2 -> String : " + str2);
			
			byte[] bytes3 = str.getBytes("UTF-8");
			System.out.println("bytes3.length : " + bytes3.length);
			String str3 = new String(bytes3, "UTF-8");
			System.out.println("bytes3 -> String : " + str3);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String subject = "C ���α׷���";
		
		int location = subject.indexOf("���α׷���");
		System.out.println(location);
		
		if(subject.indexOf("�ڹ�") != -1) {
			System.out.println("�ڹٿ� ���þ��� å �̱���.");
		}
		else {
			System.out.println("�ڹٿ� ���þ��� å�̱���.");
		}
		
		String ssn2 = "93070521111112";
		int length = ssn2.length();
		if(length == 13) {
			System.out.println("�ֹι�ȣ �ڸ����� �½��ϴ�.");
		}
		else {
			System.out.println("�ֹι�ȣ �ڸ����� Ʋ���ϴ�.");
		}
		
		String oldStr = "�����̴� �ٺ��Դϴ�. ���ϵ� �ٺ� �¾ƿ�.";
		String newStr =  oldStr.replace("�ٺ�", "babo");
		
		System.out.println(oldStr);
		System.out.println(newStr);
		
		String firstNum = ssn2.substring(0, 6);
		System.out.println(firstNum);
		
		String secondNum = ssn2.substring(6);
		System.out.println(secondNum);


//----------------------------------------------------------------------------
		
		str1 = "Java Programming";
		str2 = "JAVA Programming";
		
		System.out.println(str1.equals(str2));
		
		String lowerStr1 = str1.toLowerCase();
		String lowerStr2 = str2.toLowerCase();
		System.out.println(lowerStr1.equals(lowerStr2));
		
		System.out.println(str1.equalsIgnoreCase(str2));

//----------------------------------------------------------------------------
		String tel1 = "   02";
		String tel2 = "123    ";
		String tel3 = "    12 34    ";
		
		//��,�� ���鹮�� ����
		String tel = tel1.trim() + tel2.trim() + tel3.trim();
		System.out.println(tel);
//------------------------------------------------------------------------ 		
		
		str1 = String.valueOf(10);
		str2 = String.valueOf(10.5);
		String str3 = String.valueOf(true);
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
//----------------------------------------------------------------------------		
	/*	
		bytes = new byte[100];
		System.out.print("�Է� : ");
		int readByteNo = 0;
		
		try {
			readByteNo = System.in.read(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str3 = new String(bytes, 0, readByteNo-2);
		System.out.println(str3);
	*/	
		
	}

}
