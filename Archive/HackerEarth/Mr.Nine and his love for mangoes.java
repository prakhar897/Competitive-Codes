import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.HashMap;
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
        Mango solver = new Mango();
        solver.solve(1, in, out);
        out.close();
    }

    static class Mango {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int u = in.nextInt();
            int v = in.nextInt();
            int i;
            ArrayList<Integer> adj[] = new ArrayList[n + 1];
            HashMap<Integer, Integer> hm = new HashMap<>();

            for (i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            for (i = 0; i < n - 1; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }

            long ans = 1l * n * (n - 1);

            Stack<Integer> st = new Stack<>();
            st.add(u);
            int visited[] = new int[n + 1];
            visited[u] = 1;
            while (st.peek() != v) {
                int k = st.pop();
                visited[k] = 1;
                for (int s : adj[k]) {
                    if (visited[s] == 0) {
                        hm.put(s, k);
                        st.push(s);
                    }

                }
            }
            //out.println(hm);
            for (i = 0; i <= n; i++) {
                visited[i] = 0;
            }

            int k = hm.get(v);
            adj[v].remove(new Integer(k));
            adj[k].remove(new Integer(v));
            //out.println(k);
            int prev = -1;
            while (k != u) {
                if (prev != -1) {
                    adj[prev].remove(new Integer(k));
                    adj[k].remove(new Integer(prev));
                    //out.println(prev + " " + k);
                }
                prev = k;
                k = hm.get(k);
            }

            if (prev != -1) {
                adj[prev].remove(new Integer(u));
                adj[u].remove(new Integer(prev));
            }

            //out.println(prev);
            //out.print(adj);

            int count1 = 0;
            st = new Stack<>();
            st.push(u);
            visited[u] = 1;
            while (!st.isEmpty()) {
                k = st.pop();
                count1++;
                visited[k] = 1;
                for (int s : adj[k]) {
                    if (visited[s] == 0) {
                        st.push(s);
                    }
                }
            }

            int count2 = 0;
            st.push(v);
            visited[v] = 1;
            while (!st.isEmpty()) {
                k = st.pop();
                count2++;
                visited[k] = 1;
                for (int s : adj[k]) {
                    if (visited[s] == 0) {
                        st.push(s);
                    }
                }
            }
            //out.println(count1 + " " + count2);
            long sub = 1l * count1 * count2;
            ans -= sub;
            out.println(ans);
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

        public void close() {
            writer.close();
        }

        public void println(long i) {
            writer.println(i);
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

