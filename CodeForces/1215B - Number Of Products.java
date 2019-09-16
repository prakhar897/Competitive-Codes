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
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        BTheNumberOfProducts solver = new BTheNumberOfProducts();
        solver.solve(1, in, out);
        out.close();
    }

    static class BTheNumberOfProducts {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int arr[] = in.nextIntArray(n);
            int neg[] = new int[n];
            long ans = 0;

            if (arr[0] < 0)
                neg[0] = 1;
            int i, ec = 0, oc = 0;

            for (i = 1; i < n; i++) {
                neg[i] = neg[i - 1];
                if (arr[i] < 0)
                    neg[i]++;
            }

            for (i = 0; i < n; i++) {
                if (neg[i] % 2 == 0)
                    ec++;
                else
                    oc++;
            }

            for (i = 0; i < n; i++) {
                if (neg[i] % 2 == 0 && arr[i] > 0) {
                    ans += ec;
                    ec--;
                }

                if (neg[i] % 2 == 0 && arr[i] < 0) {
                    ans += oc;
                    ec--;
                }

                if (neg[i] % 2 == 1 && arr[i] > 0) {
                    ans += oc;
                    oc--;
                }

                if (neg[i] % 2 == 1 && arr[i] < 0) {
                    ans += ec;
                    oc--;
                }
            }

            long ans2 = (1l * (n) * (n + 1)) / 2;
            ans2 -= ans;
            out.println(ans2 + " " + ans);
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
}

