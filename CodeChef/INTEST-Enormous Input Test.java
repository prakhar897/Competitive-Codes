import java.util.*;

public class Ac
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int  cou = 0;
		int i,no;
		for(i=0;i<n;i++)
		{
			no = sc.nextInt();
			if(no%k == 0)
				cou++;
		}
		System.out.println(cou);
		
	}
}