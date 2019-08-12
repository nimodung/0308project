package exam18_API2;

import java.util.Arrays;

public class API_exam {

	public static void main(String[] args) {
		char[] arr1 = {'J', 'A', 'V', 'A'};
		
		//how1
		char[] arr2 = Arrays.copyOf(arr1, arr1.length);
		System.out.println(Arrays.toString(arr2));
		
		//how2
		char[] arr3 = Arrays.copyOfRange(arr1, 1, 3);
		System.out.println(Arrays.toString(arr3));
		
		//how3
		char[] arr4 = new char[arr1.length];
		System.arraycopy(arr1, 0, arr2, 1, 3);
		for(int i = 0; i < arr4.length; i++) {
			System.out.println("arrs[" + i + "] = " + arr2[i]);
		}
		System.out.println();
		
//----------------------------------------------------------------
	
		int[][] original = { {1, 2}, {3, 4}};
		
		//얕은 복사 후 비교
		System.out.println("[얕은 복제 후 비교]");
		int[][] cloned1 = Arrays.copyOf(original, original.length);
		System.out.println("배열 번지 비교 : " + original.equals(cloned1));
		System.out.println("1차 배열 항목값 비교 : " + Arrays.equals(original, cloned1));
		System.out.println("중첩 배열 항목값 비교 : " + Arrays.deepEquals(original, cloned1));
		
		//깊은 복사 후 비교
		System.out.println("\n[깊은 복제 후 비교]");
		int[][] cloned2 = Arrays.copyOf(original, original.length);
		cloned2[0] = Arrays.copyOf(original[0], original[0].length);
		cloned2[1] = Arrays.copyOf(original[1], original[1].length);
		System.out.println("배열 번지 비교 : " + original.equals(cloned2));
		System.out.println("1차 배열 항목값 비교 : " + Arrays.equals(original, cloned2));
		System.out.println("중첩 배열 항목값 비교 : " + Arrays.deepEquals(original, cloned2));
		
//----------------------------------------------------------------	
		
		int[] scores = {99, 97, 98};
		Arrays.sort(scores);
		for(int i =0; i < scores.length; i++) {
			System.out.println("scores[" + i + "] = " + scores[i]);
		}
		System.out.println();
		
		String[] names = {"김종하", "김희람", "김지훈"};
		Arrays.sort(names);
		for(int i = 0; i < names.length; i++) {
			System.out.println("names[" + i + "] = " + names[i]);
		}
		System.out.println();
		
		Member m1 = new Member("김종하");
		Member m2 = new Member("김희람");
		Member m3 = new Member("김지훈");
		Member[] members = {m1, m2, m3};
		Arrays.sort(members);
		for(int i =0; i < members.length; i++) {
			System.out.println("members[" + i + "].name = " + members[i].name);
		}
		System.out.println();
		
//----------------------------------------------------------------		
		
		//기본 타입값 검색
		Arrays.sort(scores);
		int index = Arrays.binarySearch(scores, 99);
		System.out.println("찾은 인덱스 : " + index);
		
		//문자열 검색
		Arrays.sort(names);
		index = Arrays.binarySearch(names, "김종하");
		System.out.println("찾은 인덱스 : " + index);
		
		//객체 검색
		Arrays.sort(members);
		index = Arrays.binarySearch(members, m3);
		System.out.println("찾은 인덱스 : " + index);
		
//----------------------------------------------------------------		
		
		
		
		
	}
}
