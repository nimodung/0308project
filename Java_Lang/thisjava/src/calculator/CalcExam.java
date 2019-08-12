package calculator;

public class CalcExam {

	public static void main(String[] args)
	{
		Calculator myCal = new Calculator();
		
		int[] values1 = {1, 2, 3};
		int result1 = myCal.sum1(values1);
		System.out.println("result1 : " + result1);
		
		int result2 = myCal.sum1(new int[] {1, 2, 3, 4, 5});
		System.out.println("result2 : " + result2);
		
		int result3 = myCal.sum2(1, 2, 3);
		System.out.println("result3 : " + result3);
		
		int result4 = myCal.sum2(1, 2, 3, 4, 5);
		System.out.println("result4 : " + result4);
		
		double result5 = myCal.areaRectangle(10);
		double result6 = myCal.areaRectangle(10, 20);
		
		System.out.println("정사각형의 넓이 : " + result5);
		System.out.println("직사각형의 넓이 : " + result6);
		return;
	}
}
