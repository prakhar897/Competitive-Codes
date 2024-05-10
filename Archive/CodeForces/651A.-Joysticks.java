import java.util.*;

class Joysticks
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int time=0;
		while((a > 0 && b > 1)||(a>1 && b>0))
		{
			time++;
			if(a>b)
			{
				a -=2;
				b+=1;
			}
			else
			{
				b-=2;
				a+=1;
			}
		}
		System.out.println(time);
	}
}