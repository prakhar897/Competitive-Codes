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
        Task460C solver = new Task460C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task460C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            long m = in.nextLong();
            long w = in.nextLong();

            long arr[] = new long[n];
            int i;
            long low = Integer.MAX_VALUE, high = -1;

            for (i = 0; i < n; i++) {
                arr[i] = in.nextLong();
                if (arr[i] > high)
                    high = arr[i];
                if (arr[i] < low)
                    low = arr[i];
            }

            high += m;

            while (high - low > 1) {
                long mid = low + (high - low) / 2;
                boolean a = checka(mid, arr, n, m, w);
                if (a)
                    low = mid;
                else
                    high = mid;
                //out.println(low + " " + high);
            }
            //out.println(low + " "+ high);

            if (checka(high, arr, n, m, w))
                out.println(high);
            else
                out.println(low);
        }

        boolean checka(long x, long arr[], int n, long m, long w) {
            long arr2[] = new long[n];
            long arr3[] = new long[n];
            int i;
            for (i = 0; i < n; i++) {
                arr2[i] = Math.max(0, x - arr[i]);
            }

            long count = 0, cur = 0;
            for (i = 0; i < n; i++) {
                cur += arr3[i];
                long k = arr2[i] - cur;
                if (k <= 0)
                    continue;
                count += k;
                cur += k;
                if (i + w < n)
                    arr3[(int) (i + w)] = -1 * k;
            }

            if (count <= m)
                return true;
            return false;
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

