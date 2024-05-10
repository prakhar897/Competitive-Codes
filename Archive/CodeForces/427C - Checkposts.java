import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Vector;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Task427C solver = new Task427C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task427C {
        ArrayList<Integer> adj[];
        ArrayList<Integer> rev[];
        int vis[];
        ArrayList<Integer> divisions[];
        Stack<Integer> st = new Stack<>();

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int arr[] = in.nextIntArray(n);
            int m = in.nextInt();
            adj = new ArrayList[n];
            rev = new ArrayList[n];
            divisions = new ArrayList[n];
            vis = new int[n];
            int i, j;

            for (i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
                rev[i] = new ArrayList<>();
                divisions[i] = new ArrayList<>();
            }

            for (i = 0; i < m; i++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                adj[a].add(b);
                rev[b].add(a);
            }

            for (i = 0; i < n; i++) {
                if (vis[i] == 0)
                    dfs(i);
            }

            //out.println(st);

            vis = new int[n];

            while (!st.isEmpty()) {
                int k = st.pop();
                if (vis[k] == 0)
                    dfsutil(k, k);
            }

            //for(i=0;i<n;i++)
            //  out.println(divisions[i]);
            int min[] = new int[n];
            int count[] = new int[n];
            i = 0;
            for (j = 0; j < n; j++) {
                if (divisions[j].size() == 0)
                    continue;
                int curmin = Integer.MAX_VALUE;
                int cc = 1;
                for (int y : divisions[j]) {
                    if (arr[y] < curmin) {
                        curmin = arr[y];
                        cc = 1;
                    } else if (arr[y] == curmin) {
                        cc++;
                    }
                }
                min[i] = curmin;
                count[i] = cc;
                i++;
            }

            long ans1 = 0;
            long ans2 = 1;
            long mod = (long) 1e9 + 7;

            for (j = 0; j < i; j++) {
                ans1 += min[j];
                ans2 *= count[j];
                ans2 %= mod;
            }

            //out.println(min);
            //out.println(count);
            out.println(ans1 + " " + ans2);

        }

        void dfs(int x) {
            vis[x] = 1;
            for (int y : adj[x]) {
                if (vis[y] == 0)
                    dfs(y);
            }
            st.push(x);
        }

        void dfsutil(int x, int z) {
            vis[z] = 1;
            divisions[x].add(z);
            for (int y : rev[z]) {
                if (vis[y] == 0)
                    dfsutil(x, y);
            }
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

