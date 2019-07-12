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
        TaskHC solver = new TaskHC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskHC {
        ArrayList<Integer> adj;
        Stack<Integer> order;
        int vis;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            while (test-- > 0) {
                int n = in.nextInt();
                int m = in.nextInt();
                int arr[] = new int[n + 1];
                int i;

                for (i = 1; i <= n; i++) {
                    String str = in.nextString();
                    if (str.equals("AND"))
                        arr[i] = 1;
                    else if (str.equals("OR"))
                        arr[i] = 2;
                    else if (str.equals("XOR"))
                        arr[i] = 3;
                    else
                        arr[i] = 4;
                }

                adj = new ArrayList[n + 1];

                for (i = 1; i <= n; i++) {
                    adj[i] = new ArrayList<>();
                }

                for (i = 0; i < m; i++) {
                    int x = in.nextInt();
                    int y = in.nextInt();
                    adj[x].add(y);
                }

                //out.println(adj);

                vis = new int[n + 1];
                order = new Stack<>();

                for (i = 1; i <= n; i++) {
                    if (vis[i] == 0) {
                        dfs(i);
                    }
                }

                //out.println(order);
                ArrayList<Integer> val[] = new ArrayList[n + 1];

                for (i = 1; i <= n; i++) {
                    val[i] = new ArrayList<>();
                }
                int ans = -1;

                while (!order.isEmpty()) {
                    int k = order.pop();
                    int cur = -1;
                    if (val[k].size() == 0) {
                        if (arr[k] > 2) {
                            cur = 0;
                        } else {
                            cur = 1;
                        }
                    } else {
                        if (arr[k] == 4) {
                            if (val[k].get(0) == 0)
                                cur = 1;
                            else
                                cur = 0;
                        } else
                            cur = val[k].get(0);

                        for (int x = 1; x < val[k].size(); x++) {
                            if (arr[k] == 1)
                                cur &= val[k].get(x);
                            if (arr[k] == 2)
                                cur |= val[k].get(x);
                            if (arr[k] == 3)
                                cur ^= val[k].get(x);
                            if (arr[k] == 4)
                                cur = ~val[k].get(x);
                        }
                    }


                    for (int x : adj[k]) {
                        val[x].add(cur);
                    }

                    if (order.isEmpty()) {
                        ans = cur;
                    }

                    //out.println(val[k]);
                    //out.println(k + " " + cur);
                }
                //out.println(arr);

                out.println(ans);
            }
        }

        void dfs(int i) {
            //System.out.println(i);
            vis[i] = 1;
            for (int x : adj[i]) {
                if (vis[x] == 0)
                    dfs(x);
            }
            order.push(i);
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

        public void println(int i) {
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

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
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

