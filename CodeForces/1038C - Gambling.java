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
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Task507C solver = new Task507C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task507C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int a[] = new int[n];
            int b[] = new int[n];

            int i, j, k;
            long sa = 0, sb = 0;

            for (i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            for (i = 0; i < n; i++) {
                b[i] = in.nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);
            i = n - 1;
            j = n - 1;

            for (k = 0; k < 2 * n; k++) {
                if (k % 2 == 0) //A's turn
                {
                    if (j < 0) {
                        sa += a[i];
                        i--;
                    } else if (i >= 0 && a[i] > b[j]) {
                        sa += a[i];
                        i--;
                    } else {
                        j--;
                    }
                } else //B's turn
                {
                    if (i < 0) {
                        sb += b[j];
                        j--;
                    } else if (j >= 0 && b[j] > a[i]) {
                        sb += b[j];
                        j--;
                    } else {
                        i--;
                    }
                }
            }
            // out.println(sa + " " +sb);
            out.println(sa - sb);

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

