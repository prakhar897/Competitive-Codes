import java.util.*;

class Tetris
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		int flag[] = new int[m];
		int i,a,min;
		for(i=0;i<n;i++)
		{
			a = sc.nextInt()-1;
			flag[a]++;
		}
		min=flag[0];
		for(i=1;i<m;i++)
		{
			if(flag[i]<min)
			{
				min=flag[i];
			}
		}
		System.out.println(min);
	}
}