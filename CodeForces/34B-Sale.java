import java.util.*;

class Sale
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int op,cou=0,sum=0,i;
		int arr[] = new int[n];
		
		for(i=0;i<n;i++)
		{
			op = sc.nextInt();
			if(op<0)
			{	
				arr[cou]=op;
				cou++;
			}
		}
		Arrays.sort(arr);
		cou=0;
		while(m!=0 && cou != arr.length)
		{
			sum += arr[cou];
			cou++;
			m--;
		}
		System.out.println(Math.abs(sum));
	}
}