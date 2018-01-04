import java.util.*;

public class Ac
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a,i;
		if(n%2 == 0)
		{
			a = n/2;
			System.out.println(a);
			for(i=0;i<a;i++)
				System.out.print("2 ");
		}
		else
		{
			a=n/2;
			System.out.println(a);
			for(i=0;i<a-1;i++)
				System.out.print("2 ");
			System.out.print("3");
		}
		
	}
}