import java.io.*;
import java.util.*;

class TestClass {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a[] = new int[n];
        int b[] = new int[n];
        int i;
        st = new StringTokenizer(br.readLine());
        for(i=0;i<n;i++)
        {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(i=0;i<n;i++)
        {
            b[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        int c[] = solve(n,a,b);
        for(i=0;i<n;i++)
            out.print(c[i] + " ");
        out.println();
        out.close();
    }
    
    static int[] solve(int n,int a[],int b[])
    {
        int i;
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        int c[] = new int[n];
        
        for(i=0;i<n;i++)
        {
            tm.put(b[i],tm.getOrDefault(b[i],0)+1);
        }

        
        int k;
        for(i=0;i<n;i++)
        {
            Object o = tm.ceilingKey(n-a[i]);
            if(o == null)
                k = tm.firstKey();
            else
                k = (int) o;
            c[i] = (a[i] + k)%n;
            
            int val = tm.get(k);
            if(val == 1)
                tm.remove(k);
            else
                tm.put(k,val-1);
        }
        
        return c;
    }
}
