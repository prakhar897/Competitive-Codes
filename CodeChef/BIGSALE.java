import java.util.*;

class CHEGLOVE
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for(int o=0;o<test;o++)
		{
			int n = sc.nextInt();
			int i;
			double sum=0;
			for(i=0;i<n;i++)
			{
				double p = sc.nextDouble();
				double q = sc.nextDouble();
				double x = sc.nextDouble();
				sum += (x*x*p*q)/10000;
			}
			System.out.println(sum);
		}
	}
}