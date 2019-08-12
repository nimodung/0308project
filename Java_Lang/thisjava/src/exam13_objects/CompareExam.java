package exam13_objects;

import java.util.Comparator;
import java.util.Objects;

public class CompareExam {

	public static void main(String[] args) {
		Student s1 = new Student(1);
		Student s2 = new Student(1);
		Student s3 = new Student(2);
		
		int result = Objects.compare(s1, s2, new StudentComparator());
		System.out.println(result);
		result = Objects.compare(s1, s3, new StudentComparator());
		System.out.println(result);
	}
	
	static class Student {
		int sno;
		Student(int sno) {
			this.sno = sno;
		}
	}
	
	static class StudentComparator implements Comparator<Student>{

		@Override
		public int compare(Student s1, Student s2) {
			/*
			 * if(s1.sno<b.sno) return -1;
			 * else if(s1.sno == s2.sno) return 0;
			 * else return 1;
			 */
			return Integer.compare(s1.sno, s2.sno);
		}
		
		
	}
}
