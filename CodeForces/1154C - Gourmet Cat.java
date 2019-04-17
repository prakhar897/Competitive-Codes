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
        Task552C solver = new Task552C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task552C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            long arr[] = in.nextLongArray(3);
            long ans = 0, max = Long.MAX_VALUE;
            max = arr[0] / 3;
            max = Math.min(max, arr[1] / 2);
            max = Math.min(max, arr[2] / 2);
            ans = max * 7;
            arr[0] -= 3 * max;
            arr[1] -= 2 * max;
            arr[2] -= 2 * max;
            // out.println(ans);

            int num[] = {0, 1, 2, 0, 2, 1, 0};
            long max2 = 0;
            for (int i = 0; i < 7; i++) {
                long a = arr[0];
                long b = arr[1];
                long c = arr[2];

                int j = i;
                long loop = 0;
                while (a > -1 && b > -1 && c > -1) {
                    if (num[j] == 0)
                        a--;
                    if (num[j] == 1)
                        b--;
                    if (num[j] == 2)
                        c--;

                    j++;
                    j %= 7;
                    loop++;
                }
                loop--;
                // out.println(loop);
                max2 = Math.max(loop, max2);
            }
            ans += max2;
            out.println(ans);
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
}

