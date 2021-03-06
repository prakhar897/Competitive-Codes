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
        TaskY solver = new TaskY();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskY {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            while (q-- > 0) {
                int x = in.nextInt();
                int y = in.nextInt();
                long ans, a, c, b, d;
                if (n % 2 == 0) {
                    c = x + y;
                    b = n / 2;
                    d = (y + 1) / 2;
                    ans = ((x - 1) * b) + d;
                    if (c % 2 == 0) {
                        out.println(ans);
                    } else {
                        a = ((long) n * n) / 2;
                        ans += a;
                        out.println(ans);
                    }
                } else {
                    c = x + y;
                    b = (x - 1) / 2;
                    d = (y + 1) / 2;
                    ans = (b * n) + d;
                    if (x % 2 == 0)
                        ans += (n / 2) + 1;
                    if (c % 2 == 0) {
                        out.println(ans);
                    } else if (c % 2 == 1) {
                        a = ((long) n * n) / 2 + 1;
                        ans += a;
                        if (y % 2 == 1)
                            ans--;
                        out.println(ans);
                    }
                }
            }
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

