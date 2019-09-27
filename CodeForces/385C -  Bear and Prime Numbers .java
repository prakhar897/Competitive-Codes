import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        CBearAndPrimeNumbers solver = new CBearAndPrimeNumbers();
        solver.solve(1, in, out);
        out.close();
    }

    static class CBearAndPrimeNumbers {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int arr[] = in.nextIntArray(n);
            int k = (int) 1e7;
            HashSet<Integer> primes = generatePrimes(k);
            int count[] = new int[k + 1];
            long pre[] = new long[k + 1];
            int i, j;

            for (i = 0; i < n; i++) {
                count[arr[i]]++;
            }

            for (i = 2; i <= k; i++) {
                pre[i] = pre[i - 1];
                if (primes.contains(i)) {
                    long cur = 0;
                    for (int l = 1; l * i <= k; l++) {
                        cur += count[l * i];
                    }
                    pre[i] += cur;
                }
            }
            // out.println(count);
            //out.println(pre);

            int m = in.nextInt();
            while (m-- > 0) {
                int l = Math.min(k, in.nextInt());
                int r = Math.min(in.nextInt(), k);
                out.println(pre[r] - pre[l - 1]);
            }
        }

        HashSet<Integer> generatePrimes(int upTo) {
            int[] isPrime = IntegerUtils.generateBitPrimalityTable(upTo);
            HashSet<Integer> primes = new HashSet<>();
            for (int i = 0; i < upTo; i++) {
                if ((isPrime[i >> 5] >>> (i & 31) & 1) == 1) {
                    primes.add((int) i);
                }
            }

            return primes;
        }

    }

    static class IntegerUtils {
        public static int[] generateBitPrimalityTable(int upTo) {
            int[] isPrime = new int[(upTo + 31) >> 5];
            if (upTo < 2) {
                return isPrime;
            }
            Arrays.fill(isPrime, -1);
            isPrime[0] &= -4;
            for (int i = 2; i * i < upTo; i++) {
                if ((isPrime[i >> 5] >>> (i & 31) & 1) == 1) {
                    for (int j = i * i; j < upTo; j += i) {
                        isPrime[j >> 5] &= -1 - (1 << (j & 31));
                    }
                }
            }
            return isPrime;
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

