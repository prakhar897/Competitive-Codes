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
        JokerDig solver = new JokerDig();
        solver.solve(1, in, out);
        out.close();
    }

    static class JokerDig {
        long dp = new long[19][180];
        boolean check = new boolean[19][180];

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            long a = in.nextLong();
            long b = in.nextLong();
            int k = in.nextInt();
            int i;

            long arr1[] = digitsum(b + 1);
            long arr2[] = digitsum(a);
            //out.println(arr1);
            //out.println(arr2);

            long arr[] = new long[180];
            long ans = 0;
            for (i = 1; i < 180; i++) {
                arr[i] = arr1[i] - arr2[i];
                int gcd = IntegerUtils.gcd(i, k);
                if (IntegerUtils.isPrime(gcd)) {
                    ans += arr[i];
                }
            }
            //out.println(arr);
            out.println(ans);

        }

        long[] digitsum(long a) {
            char arr[] = (a + "").toCharArray();
            long ans[] = new long[180];
            int i, j, k;

            for (i = 1; i < 180; i++) {
                int sum = i;
                int len = arr.length;
                int lent = len;
                for (j = 0; j < len; j++) {
                    lent--;
                    int digit = arr[j] - '0';
                    for (k = 0; k < digit; k++) {
                        if (sum - k >= 0)
                            ans[i] += F(lent, sum - k);
                    }
                    sum -= digit;
                }
            }
            return ans;
        }

        long F(int digit, int sum) {
            if (digit == 0) {
                if (sum == 0)
                    return 1;
                return 0;
            }

            if (check[digit][sum])
                return dp[digit][sum];

            check[digit][sum] = true;
            long ret = 0;
            for (int i = 0; i < 10; i++) {
                if (sum - i >= 0)
                    ret += F(digit - 1, sum - i);
            }
            dp[digit][sum] = ret;
            return ret;
        }

    }

    static class IntegerUtils {
        public static int gcd(int a, int b) {
            a = Math.abs(a);
            b = Math.abs(b);
            while (b != 0) {
                int temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }

        public static boolean isPrime(long number) {
            if (number < 2) {
                return false;
            }
            for (long i = 2; i * i <= number; i++) {
                if (number % i == 0) {
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

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
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

