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
        AYellowCards solver = new AYellowCards();
        solver.solve(1, in, out);
        out.close();
    }

    static class AYellowCards {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int a1 = in.nextInt();
            int a2 = in.nextInt();
            int k1 = in.nextInt();
            int k2 = in.nextInt();
            int n = in.nextInt();

            int min = 0, max = 0;
            if (k1 < k2) {
                max = findmax(a1, a2, k1, k2, n);
                min = findmin(a1, a2, k1, k2, n);
            } else {
                max = findmax(a2, a1, k2, k1, n);
                min = findmin(a2, a1, k2, k1, n);
            }

            out.println(min + " " + max);


        }

        int findmax(int a1, int a2, int k1, int k2, int n) {
            int cur = n, ans = 0;
            if (a1 * k1 >= cur)
                ans += cur / k1;
            else {
                ans += a1;
                cur -= a1 * k1;
                ans += cur / k2;
            }
            return ans;
        }

        int findmin(int a1, int a2, int k1, int k2, int n) {
            int cur = n;
            cur -= (a1 * (k1 - 1)) + (a2 * (k2 - 1));
            cur = Math.max(cur, 0);
            return cur;
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

