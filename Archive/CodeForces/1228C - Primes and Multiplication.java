import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        CPrimesAndMultiplication solver = new CPrimesAndMultiplication();
        solver.solve(1, in, out);
        out.close();
    }

    static class CPrimesAndMultiplication {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            long x = in.nextLong();
            long n = in.nextLong();
            HashSet<Long> primes = primeFactors(x);

            //out.println(primes);
            long mod = (long) 1e9 + 7;
            long ans = 1;
            long count = 0;

            for (long h : primes) {
                long u = h;
                count = 0;
                BigInteger hb = new BigInteger(h + "");
                BigInteger ub = new BigInteger(u + "");
                BigInteger nb = new BigInteger(n + "");

                while (ub.compareTo(nb) != 1) {
                    //out.println(u);
                    count += n / u;
                    u *= h;
                    ub = ub.multiply(hb);
                }

                ans *= IntegerUtils.power(h, count, mod);
                ans %= mod;
            }

            out.println(ans);
        }

        HashSet<Long> primeFactors(long n) {
            HashSet<Long> ans = new HashSet<>();
            while (n % 2 == 0) {
                ans.add(2l);
                n /= 2;
            }

            for (int i = 3; i <= Math.sqrt(n); i += 2) {

                while (n % i == 0) {
                    ans.add(1l * i);
                    n /= i;
                }
            }

            if (n > 2)
                ans.add(n);
            return ans;
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
}

