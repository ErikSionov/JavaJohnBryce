package arraysHomework;

import java.util.Arrays;
import java.util.Iterator;

public class Ex_705
{
	public static void main(String[] args)
	{
		int[][] matrix = new int[20][10];
		
		int classAvg = 0;
		int classNumStudents = matrix.length;
		
		for (int i = 0; i < matrix.length; i++)
		{
			int sum = 0;
			int multi = 0;
			System.out.println("student number " + i);
			for (int j = 0; j < matrix[i].length; j++)
			{
				matrix[i][j] = (int)(Math.random()*101);
				multi++;
				sum += matrix[i][j];
			}
			//print out student's scores and his average score
			System.out.println("Last scores " + Arrays.toString(matrix[i]));
			System.out.println("STUDENT average " + (double)sum/multi);
			classAvg += (double)sum/multi;
		}
		//print out class average
		System.out.println();
		System.out.println("CLASS average " + (double)classAvg/classNumStudents);
		
	}
}
