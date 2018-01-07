import java.util.*;
 
class Ac
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int i,j;
		int arr[]=new int[4];
		for(i=0;i<test;i++)
		{
			for(j=0;j<4;j++)
			{
				arr[j]=sc.nextInt();
			}
			Arrays.sort(arr);
			if(arr[0] == arr[1] && arr[2] == arr[3])
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
} 