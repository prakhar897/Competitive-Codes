import java.util.*;

class KCON
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int o;
		for(o=0;o<test;o++)
		{	
			int n = sc.nextInt();
			int k = sc.nextInt();
			int a[]=new int[n];
			int i;
			int j =0;
			long sum =0;
			long global_max = Long.MIN_VALUE;
			int end=0;
			long current_max = 0;
			if(k<3)
			{
				for(i=0;i<n*k;i++)
				{	
					if(i<n)
					a[j] = sc.nextInt();
					current_max = current_max + a[j];
					if(global_max < current_max)
						global_max = current_max;
					if(current_max < 0)
						current_max = 0;
					if(j==n-1)
						j=0;
					else
						j++;
				}
				System.out.println(global_max);
			}
			else
			{
				for(i=0;i<3*n;i++)
				{
					if(i<n)
					{a[j] = sc.nextInt();
					sum += a[j];
					}
					current_max = current_max + a[j];
					if(global_max < current_max)
					{global_max = current_max;
					end = i;
					}
					if(current_max < 0)
						current_max = 0;
					if(j==n-1)
						j=0;
					else
						j++;
				}
				long out;
				long sum1 = ((k-3)*sum)+global_max;
				if(end<2*n)
					out = global_max;
				else
					out = sum1;
				System.out.println(out);
			}
			
		}
	}
}