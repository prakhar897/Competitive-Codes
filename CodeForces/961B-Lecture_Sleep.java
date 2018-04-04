import java.util.*;

class Lecture
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int arr[] = new int[n];
		int flag[] = new int[n];
		int sum[] = new int[n-k+1];
		int i,j,ans=0,add=0,big=0;
		for(i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		for(i=0;i<n;i++)
		{
			flag[i]=sc.nextInt();
		}
		for(i=0;i<n;i++)
		{
			if(flag[i]==1)
			{ans += arr[i];
			arr[i]=0;
			}
		}
		for(i=0;i<n;i++)
		{
			add += arr[i];
			if(i==k-1)
				big=add;
			else if(i>k-1)
			{
				add-= arr[i-k];
				big = Math.max(big,add);
			}
		}
		ans += big;
		System.out.println(ans);
	}
}