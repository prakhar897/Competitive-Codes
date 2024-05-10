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
        TaskHV2 solver = new TaskHV2();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskHV2 {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            if (n == 1) {
                out.println(0);
                return;
            }
            long mat[][] = new long[n][2];
            long x[] = new long[n];
            long y[] = new long[n];
            int i;

            for (i = 0; i < n; i++) {
                mat[i][0] = in.nextInt();
                mat[i][1] = in.nextInt();
                x[i] = mat[i][0];
                y[i] = mat[i][1];
            }

            Arrays.sort(x);
            Arrays.sort(y);

            long low = x[n / 2];
            long high = x[(n / 2) - 1];
            long min = Integer.MAX_VALUE;
            // out.println(low +" " + high);

            long yu = checka(low, mat);
            long u = checka(high, mat);
            min = Math.min(min, yu);
            min = Math.min(min, u);

            //out.println(min);
            low = y[n / 2];
            high = y[(n / 2) - 1];
            //out.println(low +" " + high);

            yu = checkb(low, mat);
            u = checkb(high, mat);
            //out.println(yu + " " + u);
            min = Math.min(min, yu);
            min = Math.min(min, u);

            out.println(min);
        }

        long checka(long pos, long[][] mat) {
            int n = mat.length;
            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += Math.abs(mat[i][0] - pos);
            }
            return ans;
        }

        long checkb(long pos, long[][] mat) {
            int n = mat.length;
            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += Math.abs(mat[i][1] - pos);
            }
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

        public void close() {
            writer.close();
        }

        public void println(long i) {
            writer.println(i);
        }

        public void println(int i) {
            writer.println(i);
        }

    }
}

