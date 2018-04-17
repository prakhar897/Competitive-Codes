import java.util.*;

class CHEFWORK
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c[] = new int[n];
		int t[] = new int[n];
		
		int i,am=999999,tm=999999,atm=999999;
		for(i=0;i<n;i++)
		{
			c[i] = sc.nextInt();
		}
		for(i=0;i<n;i++)
		{
			t[i] = sc.nextInt();
		}
		for(i=0;i<n;i++)
		{	
			if(t[i]==1)
			{
				if(c[i]<am)
					am = c[i];
			}
			else if(t[i]==2)
			{
					if(c[i]<tm)
						tm = c[i];
			}
			else if(t[i]==3)
			{
					if(c[i]<atm)
						atm = c[i];
			}
		}
		//System.out.println(am+" "+ tm+" "+ atm);
		
		if(am+tm>atm)
			System.out.println(atm);
		else
			System.out.println(am+tm);
	}
}