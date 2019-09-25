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
        CWhiteSheet solver = new CWhiteSheet();
        solver.solve(1, in, out);
        out.close();
    }

    static class CWhiteSheet {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int w[] = in.nextIntArray(4);
            int b1[] = in.nextIntArray(4);
            int b2[] = in.nextIntArray(4);

            for (int i = w[0]; i <= w[2]; i++) {
                int s = w[1], e = w[3];
                if (b1[0] <= i && b1[2] >= i) {
                    if (b1[3] >= e)
                        e = Math.min(e, b1[1]);
                    if (b1[1] <= s)
                        s = Math.max(s, b1[3]);
                }
                if (b2[0] <= i && b2[2] >= i) {
                    if (b2[3] >= e)
                        e = Math.min(e, b2[1]);
                    if (b2[1] <= s)
                        s = Math.max(s, b2[3]);
                }
                //out.println(s + " " + e);

                if (e - s > 0) {
                    out.println("YES");
                    return;
                }
            }

            for (int i = w[1]; i <= w[3]; i++) {
                int s = w[0], e = w[2];
                if (b1[1] <= i && b1[3] >= i) {
                    if (b1[2] >= e)
                        e = Math.min(e, b1[0]);
                    if (b1[0] <= s)
                        s = Math.max(s, b1[2]);
                }
                if (b2[1] <= i && b2[3] >= i) {
                    if (b2[2] >= e)
                        e = Math.min(e, b2[0]);
                    if (b2[0] <= s)
                        s = Math.max(s, b2[2]);
                }
                //out.println(s + " " + e);

                if (e - s > 0) {
                    out.println("YES");
                    return;
                }
            }


            out.println("NO");
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

