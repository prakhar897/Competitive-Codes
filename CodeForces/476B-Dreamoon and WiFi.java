import java.util.*;
import java.math.BigDecimal;

class PRTITION
{
	public static int fact(int num)
    {
        int fact=1, i;
        for(i=1; i<=num; i++)
        {
            fact = fact*i;
        }
        return fact;
    }
    
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		int sum1 = 0;
		int sum2 =0;
		int i;
		int plus;
		double val;
		char c;
		int count = 0;
		for(i=0;i<s1.length();i++)
		{
			c = s1.charAt(i);
			if(c=='+')
				sum1++;
			if(c=='-')
				sum1--;
		}
		
		for(i=0;i<s2.length();i++)
		{
			c = s2.charAt(i);
			if(c=='+')
				sum2++;
			if(c=='-')
				sum2--;
			if(c=='?')
				count++;
		}
		int trav = Math.abs(sum1-sum2);
		if(count==0 &&  trav != 0 || trav>count)
			System.out.println("0.000000000000");
		else if(count==0 &&  trav == 0)
			System.out.println("1.000000000000");
		else
		{
			plus = (trav+count)/2;
			val = (fact(count))/(fact(plus)*fact(count-plus)*Math.pow(2,count));
			BigDecimal bd = new BigDecimal(val);
			bd = bd.setScale(12, BigDecimal.ROUND_HALF_UP);
			System.out.println(bd);
		} 
	}
}