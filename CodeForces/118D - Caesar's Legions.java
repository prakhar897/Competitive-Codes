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
        Task118D solver = new Task118D();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task118D {
        long mod = (long) 1e8;
        long dp[][][];

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            int k1 = in.nextInt();
            int k2 = in.nextInt();
            dp = new long[n1 + 1][n2 + 1][2];

            for (int i = 0; i <= n1; i++) {
                for (int j = 0; j <= n2; j++) {
                    dp[i][j][0] = -1;
                    dp[i][j][1] = -1;
                }
            }

            long ans = rec(n1, n2, 0, k1, k2) + rec(n1, n2, 1, k1, k2);
            ans %= mod;
            out.println(ans);
        }

        long rec(int n1, int n2, int turn, int k1, int k2) {
            if (dp[n1][n2][turn] != -1)
                return dp[n1][n2][turn];
            if (n1 == 0 && n2 == 0)
                return 1;
            if (turn == 0 && n1 == 0)
                return 0;
            if (turn == 1 && n2 == 0)
                return 0;

            long ans = 0;
            if (turn == 0) {
                for (int i = 1; i <= k1; i++) {
                    if (n1 - i >= 0)
                        ans += rec(n1 - i, n2, 1, k1, k2);
                }
            } else {
                for (int i = 1; i <= k2; i++) {
                    if (n2 - i >= 0)
                        ans += rec(n1, n2 - i, 0, k1, k2);
                }
            }
            ans %= mod;
            dp[n1][n2][turn] = ans;
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

