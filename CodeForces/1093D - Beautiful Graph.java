import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
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
        Task56D solver = new Task56D();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task56D {
        int n = (int) 3e5 + 5;
        int MOD = 998244353;
        ArrayList<Integer> a[] = new ArrayList[n];
        int count[] = new int[2];
        boolean vis[] = new boolean[n];
        int p[] = new int[n];

        void clear(int n) {
            for (int i = 0; i < n; ++i) {
                a[i].clear();
                vis[i] = false;
            }
        }

        int add(int a, int b) {
            int res = a + b;
            return res >= MOD ? res - MOD : res;
        }

        int mul(int a, int b) {
            int res = (int) ((long) a * b % MOD);
            return res < 0 ? res + MOD : res;
        }

        int pow(int a, int e) {
            if (e == 0) {
                return 1;
            }
            int r = a;
            for (int i = 30 - Integer.numberOfLeadingZeros(e); i >= 0; i--) {
                r = mul(r, r);
                if ((e & (1 << i)) != 0) {
                    r = mul(r, a);
                }
            }
            return r;
        }

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            for (int i = 0; i < a.length; ++i) {
                a[i] = new ArrayList<>();
            }

            int t = in.nextInt();
            while (t-- > 0) {

                int n = in.nextInt();
                int m = in.nextInt();
                int i;

                clear(n);

                for (i = 0; i < m; i++) {
                    int u = in.nextInt() - 1;
                    int v = in.nextInt() - 1;

                    a[u].add(v);
                    a[v].add(u);
                }

                int ans = 1;
                for (i = 0; i < n; i++) {
                    count[0] = 0;
                    count[1] = 0;
                    if (vis[i] == false) {
                        if (dfs(i, 0) == false) {
                            ans = 0;
                            break;
                        }

                        ans = mul(ans, add(pow(2, count[0]), pow(2, count[1])));
                    }
                }

                out.println(ans);

            }
        }

        boolean dfs(int vertex, int parity) {
            ++count[parity];
            vis[vertex] = true;
            p[vertex] = parity;
            for (int v : a[vertex]) {
                if (vis[v] == false) {
                    if (dfs(v, parity ^ 1) == false)
                        return false;
                } else if (p[v] == parity) {
                    return false;
                }
            }

            return true;
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

