import java.util.*;

class Sale
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		int k = sc.nextInt();
		int arr[] = new int[26];
		int i,max=0,op,sum=0;
		for(i=0;i<26;i++)
		{
			arr[i] = sc.nextInt();
			if(arr[i]>max)
				max = arr[i];
		}
		
		for(i=0;i<str.length();i++)
		{
			op = (int)(str.charAt(i));
			op = op-97;
			sum += (i+1)*arr[op];
		}
		//System.out.println(i);
		sum += max*((k*(2*i+k+1))/2);
		System.out.println(sum);
	}
}