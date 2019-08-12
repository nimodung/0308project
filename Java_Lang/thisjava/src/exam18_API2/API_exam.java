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
		
		//���� ���� �� ��
		System.out.println("[���� ���� �� ��]");
		int[][] cloned1 = Arrays.copyOf(original, original.length);
		System.out.println("�迭 ���� �� : " + original.equals(cloned1));
		System.out.println("1�� �迭 �׸� �� : " + Arrays.equals(original, cloned1));
		System.out.println("��ø �迭 �׸� �� : " + Arrays.deepEquals(original, cloned1));
		
		//���� ���� �� ��
		System.out.println("\n[���� ���� �� ��]");
		int[][] cloned2 = Arrays.copyOf(original, original.length);
		cloned2[0] = Arrays.copyOf(original[0], original[0].length);
		cloned2[1] = Arrays.copyOf(original[1], original[1].length);
		System.out.println("�迭 ���� �� : " + original.equals(cloned2));
		System.out.println("1�� �迭 �׸� �� : " + Arrays.equals(original, cloned2));
		System.out.println("��ø �迭 �׸� �� : " + Arrays.deepEquals(original, cloned2));
		
//----------------------------------------------------------------	
		
		int[] scores = {99, 97, 98};
		Arrays.sort(scores);
		for(int i =0; i < scores.length; i++) {
			System.out.println("scores[" + i + "] = " + scores[i]);
		}
		System.out.println();
		
		String[] names = {"������", "�����", "������"};
		Arrays.sort(names);
		for(int i = 0; i < names.length; i++) {
			System.out.println("names[" + i + "] = " + names[i]);
		}
		System.out.println();
		
		Member m1 = new Member("������");
		Member m2 = new Member("�����");
		Member m3 = new Member("������");
		Member[] members = {m1, m2, m3};
		Arrays.sort(members);
		for(int i =0; i < members.length; i++) {
			System.out.println("members[" + i + "].name = " + members[i].name);
		}
		System.out.println();
		
//----------------------------------------------------------------		
		
		//�⺻ Ÿ�԰� �˻�
		Arrays.sort(scores);
		int index = Arrays.binarySearch(scores, 99);
		System.out.println("ã�� �ε��� : " + index);
		
		//���ڿ� �˻�
		Arrays.sort(names);
		index = Arrays.binarySearch(names, "������");
		System.out.println("ã�� �ε��� : " + index);
		
		//��ü �˻�
		Arrays.sort(members);
		index = Arrays.binarySearch(members, m3);
		System.out.println("ã�� �ε��� : " + index);
		
//----------------------------------------------------------------		
		
		
		
		
	}
}
