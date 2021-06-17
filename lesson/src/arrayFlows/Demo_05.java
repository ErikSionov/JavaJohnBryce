package arrayFlows;

import java.util.Arrays;

public class Demo_05
{
	public static void main(String[] args)
	{
		int[][] gradeMatrix = new int[20][5];
		
		for (int i = 0; i < gradeMatrix.length; i++)
		{
			System.out.print("student # " + i + ": ");
			for (int j = 0; j < gradeMatrix[i].length; j++)
			{
				gradeMatrix[i][j] = (int)(Math.random() * 101);
				System.out.print(gradeMatrix[i][j]);
				if(j==gradeMatrix[i].length-1) {
					System.out.print(".");
				}else 
					System.out.print(", ");
			}
			System.out.println();
		}
	}
}
