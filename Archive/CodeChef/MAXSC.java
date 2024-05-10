/* package whatever; // don't place package name! */
 
import java.util.*;
import java.lang.*;
import java.io.*;
 
/* Name of the class has to be "Main" only if the class is public. */
class MAXSC
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int o,i,j;
		for(o=0;o<test;o++)
		{
			int n = sc.nextInt();
			int arr[][] = new int[n][n];
			for(i=0;i<n;i++)
			{
				for(j=0;j<n;j++)
				{
					arr[i][j] = sc.nextInt();
				}
				Arrays.sort(arr[i]);
			}
			
			long sum = arr[n-1][n-1];
			int temp=arr[n-1][n-1];
			outer:
			for(i=n-1;i>0;i--)
			{
				j=0;
				while(temp<=arr[i-1][n-1-j])
				{
					if(j != n-1)
						j++;
					else
					{
						sum = -1;
						break outer;
					}
				}
				temp=arr[i-1][n-1-j];
				sum += arr[i-1][n-1-j];
			}
			System.out.println(sum);
		}
	}
}