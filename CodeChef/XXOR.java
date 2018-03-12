import java.util.*;

class CHEGLOVE
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n= sc.nextInt();
		int q = sc.nextInt();
		int arr[][] = new int[n][32];
		int x[] = new int[32];
		int query[][]=new int[q][2];
		int i,j,zc,oc,decimal=0,k,val;
		
		for(i=0;i<n;i++)
		{
			arr[i] = convertBinary(sc.nextInt()); 
			for(j=0;j<32;j++)
			{
				if(i !=0)
				arr[i][j] += arr[i-1][j];
			}
		}
		
		
		
		for(i=0;i<q;i++)
		{
			query[i][0]=sc.nextInt();
			query[i][1]=sc.nextInt();
		} 
		
		/*for(i=0;i<n;i++)
		{
			for(j=0;j<32;j++)
			System.out.print(arr[i][j]+"");
			System.out.println();
		}*/
			
		for(k=0;k<q;k++)
		{
			decimal=0;
			val = ((query[k][1]-query[k][0])/2)+1;
			if(query[k][0] != 1)
			{
				for(j=0;j<32;j++)
				{
					if(arr[query[k][1]-1][j]-arr[query[k][0]-2][j]>=val)
						x[j]=0;
					else
						x[j]=1;	
		 		}
			}
		 	else
		 	{
		 		for(j=0;j<32;j++)
				{
		 			if(arr[query[k][1]-1][j]>=val)
						x[j]=0;
					else
						x[j]=1;
		 		}
			}
		 	/*for(i=0;i<32;i++)
		 	{
		 		System.out.print(x[i]+"");
		 	}
		 	System.out.println();*/
		 	int num = 1;
		 	for(i=0;i<31;i++)
		 	{
		 		decimal += x[i]*(num);
		 		num = num*2;
		 	}
		 	System.out.println(decimal);
		}
	}
	
	public static int[] convertBinary(int num)
	{
     int binary[] = new int[32];
     int index = 0;
     while(num > 0)
     	{
       		binary[index++] = num%2;
       		num = num/2;
     	}
     return binary;
	}
}