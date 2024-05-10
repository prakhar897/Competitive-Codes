import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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
        CGargariAndBishops solver = new CGargariAndBishops();
        solver.solve(1, in, out);
        out.close();
    }

    static class CGargariAndBishops {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            long mat[][] = new long[n][n];
            long sum[][] = new long[n][n];
            HashMap<Integer, Long> hm1 = new HashMap<>();
            HashMap<Integer, Long> hm2 = new HashMap<>();
            int i, j;

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    mat[i][j] = in.nextLong();
                    hm1.put(i + j, hm1.getOrDefault(i + j, 0l) + mat[i][j]);
                    hm2.put(i - j, hm2.getOrDefault(i - j, 0l) + mat[i][j]);
                }
            }

            //out.println(hm1);
            //out.println(hm2);

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    sum[i][j] = hm1.get(i + j) + hm2.get(i - j) - mat[i][j];
                }
            }

            //out.println(sum);

            int x1 = -1, x2 = -1, y1 = -1, y2 = -1;
            long maxe = -1, maxo = -1;

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if ((i + j) % 2 == 0 && sum[i][j] > maxe) {
                        maxe = sum[i][j];
                        x1 = i + 1;
                        y1 = j + 1;
                    } else if ((i + j) % 2 == 1 && sum[i][j] > maxo) {
                        maxo = sum[i][j];
                        x2 = i + 1;
                        y2 = j + 1;
                    }
                }
            }

            out.println(maxe + maxo);
            out.println(x1 + " " + y1 + " " + x2 + " " + y2);

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

        public void println(long i) {
            writer.println(i);
        }

    }
}

