import java.util.*;

public class Ac
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[4];
		int i;
		for(i=0;i<4;i++)
			arr[i] = sc.nextInt();
		Arrays.sort(arr);
		if(arr[3]< arr[2]+arr[1] || arr[2] < arr[1]+arr[0])
			System.out.println("TRIANGLE");
		else if(arr[3] == arr[2]+arr[1] || arr[2] == arr[1]+arr[0])
			System.out.println("SEGMENT");
		else
			System.out.println("IMPOSSIBLE");
		
	}
}