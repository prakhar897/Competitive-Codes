import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Stack;
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
        TaskCoin solver = new TaskCoin();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskCoin {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int mat[][] = new int[n][m];
            int mat2[][] = new int[n][m];
            int i, j;

            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    mat[i][j] = in.nextInt();
                    if (mat[i][j] == 0)
                        mat2[i][j] = 1;
                }
            }

            int ans = func(mat, n, m, out);
            int ans2 = func(mat2, n, m, out);
            out.println(Math.max(ans, ans2));
        }

        int func(int[][] mat, int n, int m, OutputWriter out) {
            int i, j;
            int dp[][] = new int[n][m];
            int d[] = new int[m];
            int d1[] = new int[m];
            int d2[] = new int[m];
            Stack<Integer> st = new Stack<>();
            Arrays.fill(d, -1);
            int ans = 0;

            for (i = 0; i < n; i++) {
                //out.println("row" + i + " start");
                for (j = 0; j < m; j++) {
                    if (mat[i][j] == 1) {
                        d[j] = i;
                    }
                }
                //out.println(d);

                for (j = 0; j < m; ++j) {
                    while (!st.empty() && d[st.peek()] <= d[j])
                        st.pop();
                    d1[j] = st.empty() ? -1 : st.peek();
                    st.push(j);
                }
                while (!st.empty())
                    st.pop();

                for (j = m - 1; j >= 0; --j) {
                    while (!st.empty() && d[st.peek()] <= d[j])
                        st.pop();
                    d2[j] = st.empty() ? m : st.peek();
                    st.push(j);
                }
                while (!st.empty())
                    st.pop();
                //out.println(d1);
                //out.println(d2);

                for (j = 0; j < m; ++j) {
                    int cur = (i - d[j]) * (d2[j] - d1[j] - 1);
                    //out.print(cur + " ");
                    ans = Math.max(ans, cur);
                }
                //out.println();

            }
            return ans;
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

