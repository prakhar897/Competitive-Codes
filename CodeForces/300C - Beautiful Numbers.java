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
        Task300C solver = new Task300C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task300C {
        long fact[];
        int mod = (int) 1e9 + 7;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            fact = new long[n + 1];
            fact[0] = 1;
            long ans = 0;
            for (int i = 1; i <= n; i++) {
                fact[i] = i * fact[i - 1];
                fact[i] %= mod;
            }

            for (int i = 0; i <= n; i++) {
                int sum = (i * a) + ((n - i) * b);
                if (check(sum, a, b)) {
                    //out.println(i);
                    ans += comb(n, i);
                    ans = ans % mod;
                }
            }
            out.println(ans);
        }

        long comb(int n, int i) {
            long ans = fact[n];
            long temp = IntegerUtils.power((fact[i] * fact[n - i]), mod - 2, mod);
            ans *= temp;
            ans %= mod;
            return ans;
        }

        boolean check(int sum, int a, int b) {
            while (sum > 0) {
                int now = sum % 10;
                sum /= 10;
                if (!(now == a || now == b))
                    return false;
            }
            return true;
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

    static class IntegerUtils {
        public static long power(long base, long exponent, long mod) {
            if (base >= mod) {
                base %= mod;
            }
            if (exponent == 0) {
                return 1 % mod;
            }
            long result = power(base, exponent >> 1, mod);
            result = result * result % mod;
            if ((exponent & 1) != 0) {
                result = result * base % mod;
            }
            return result;
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
}

