import java.util.*;

class Game
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int u=0,i,count,op;
		for(u=0;u<test;u++)
		{
			count = 0;
			int n = sc.nextInt();
			for(i=0;i<n;i++)
			{
				op = sc.nextInt();
				if(op%2==1)
				{	count++;
				//System.out.println(op);
				}
			}
		//	System.out.println(count);
			if(count%2 == 1)
				System.out.println("2");
			else
				System.out.println("1");
		}
	}
}