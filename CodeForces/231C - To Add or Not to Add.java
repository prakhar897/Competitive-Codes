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
        Task231C solver = new Task231C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task231C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            long arr[] = new long[n];
            long sum[] = new long[n];
            int ans[] = new int[n];
            int i;

            for (i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }

            Arrays.sort(arr);

            sum[0] = arr[0];
            for (i = 1; i < n; i++)
                sum[i] = sum[i - 1] + arr[i];

            for (i = 1; i < n; i++) {
                ans[i] = search(sum, arr[i], i, k);
            }

            //out.println(arr);
            //out.println(ans);
            int max = 0;
            for (i = 0; i < n; i++)
                max = Math.max(max, ans[i]);

            long a = 0;
            for (i = 0; i < n; i++) {
                if (ans[i] == max) {
                    a = arr[i];
                    break;
                }
            }

            out.println(max + 1 + " " + a);

        }

        int search(long sum[], long e, int x, long k) {
            int low = 0;
            int high = x - 1;
            while (high - low > 1) {
                int mid = (low + high) / 2;
                long cur = sum[x - 1];
                if (mid > 0)
                    cur -= sum[mid - 1];

                if ((1l * (x - mid) * e) - cur > k)
                    low = mid;
                else
                    high = mid;
            }

            long cur = sum[x - 1];
            if (low > 0)
                cur -= sum[low - 1];

            if ((1l * (x - low) * e) - cur <= k)
                return x - low;
            else
                return x - high;
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

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

