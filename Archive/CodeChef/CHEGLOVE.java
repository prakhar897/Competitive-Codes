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
			long l[]=new long[n];
			long g[]=new long[n];
			int fflag=0,bflag=0,i;
			for(i=0;i<n;i++)
				l[i]=sc.nextInt();
			for(i=0;i<n;i++)
				g[i]=sc.nextInt();
	
			outer: for(i=0;i<n;i++)
			{
				if(l[i]>g[i])
					fflag++;
				if(l[n-1-i]>g[i])
					bflag++;
				if(fflag !=0 && bflag !=0)
					break outer;
			}
			
			if(fflag == 0 && bflag == 0)
				System.out.println("both");
			else if(fflag ==0 && bflag !=0)
				System.out.println("front");
			else if(fflag !=0 && bflag ==0)
				System.out.println("back");
			else
				System.out.println("none");
		}
	}
}