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
        CountPrefix solver = new CountPrefix();
        solver.solve(1, in, out);
        out.close();
    }

    static class CountPrefix {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            long arr[] = in.nextLongArray(n);
            int q = in.nextInt();
            int k = (int) Math.sqrt(n);
            //out.println(k);
            int l = n / k;
            if (n % k != 0)
                l++;
            long mo[] = new long[l];
            int i, j;

            for (i = 0; i < l; i++) {
                mo[i] = arr[i * k];
                for (j = 1; j < k; j++) {
                    int u = (i * k) + j;
                    if (u < n)
                        mo[i] = IntegerUtils.gcd(mo[i], arr[(i * k) + j]);
                }
            }
            //out.println(mo);

            while (q-- > 0) {
                int left = in.nextInt() - 1;
                int right = in.nextInt() - 1;
                long dev = in.nextLong();
                int ans = -1, flag = 0;

                if (right - left > n / 2) {

                    int spos = left / k;
                    int epos = right / k;

                    for (i = left; i < (spos + 1) * k; i++) {
                        if (arr[i] % dev == 0)
                            ans = i;
                        else {
                            flag = 1;
                            break;
                        }

                    }

                    if (flag == 0) {
                        for (i = spos + 1; i < epos; i++) {
                            if (mo[i] % dev == 0)
                                ans = (k * (i + 1)) - 1;
                            else
                                break;
                        }

                        for (j = i * k; j < (i * k) + k; j++) {
                            if (j < n && arr[j] % dev == 0)
                                ans = j;
                            else
                                break;
                        }
                    }

                    if (ans != -1)
                        ans++;
                } else {
                    for (i = left; i <= right; i++) {
                        if (arr[i] % dev == 0)
                            ans = (i + 1);
                        else
                            break;
                    }
                }
                out.println(ans);
            }
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

        public long[] nextLongArray(int n) {
            long[] array = new long[n];
            for (int i = 0; i < n; ++i) array[i] = nextLong();
            return array;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class IntegerUtils {
        public static long gcd(long a, long b) {
            a = Math.abs(a);
            b = Math.abs(b);
            while (b != 0) {
                long temp = a % b;
                a = b;
                b = temp;
            }
            return a;
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
}

