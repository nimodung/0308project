package calculator;

public class Calculator {
	int sum1(int[] values)
	{
		int sum = 0;
		for(int i = 0; i < values.length; i++)
		{
			sum += values[i];
		}
		
		return sum;
	}
	
	int sum2(int ... values)
	{
		int sum = 0;
		for(int i = 0; i < values.length; i++)
		{
			sum += values[i];
		}
		return sum;
	}

	double areaRectangle(double width)
	{
		return width * width;
	}
	
	double areaRectangle(double width, double height)
	{
		return width * height;
	}
	
}
