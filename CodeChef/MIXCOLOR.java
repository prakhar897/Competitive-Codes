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
			int arr[] = new int[n];
			int i,count=0;
			for(i=0;i<n;i++)
			{
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			for(i=1;i<n;i++)
			{
				if(arr[i]==arr[i-1])
					count++;
			}
			System.out.println(count);
		}
	}
}