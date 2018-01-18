import java.util.*;

class A{
	static boolean isPrime(int n) {
    for(int i=2;i<n;i++) {
        if(n%i==0)
            return false;
    }
    return true;
}
	public static void main(String arg[]){
		Scanner sc = new Scanner(System.in);
		int n  = sc.nextInt();
		int count=0;
		int i;
		int no;
		int arr[] = new int[n];
		for(i=2;i<=n;i++)
		{
			if((isPrime(i)))
			{
				no=i*i;
				arr[count] = i;
				count++;
				while(no<=n)
				{
					arr[count] = no;
					count++;
					no *= i;	
				}
			}
		}
		System.out.println(count);
		for(i=0;i<count;i++)
		{
			System.out.print(arr[i]+" ");
		}
		
	}
}