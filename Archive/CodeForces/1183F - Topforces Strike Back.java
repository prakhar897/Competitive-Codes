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
import java.util.Collections;
import java.util.TreeSet;
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
        Task1183F solver = new Task1183F();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task1183F {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int q = in.nextInt();
            while (q-- > 0) {
                int n = in.nextInt();
                TreeSet<Integer> hs = new TreeSet<>(Collections.reverseOrder());
                int i;

                for (i = 0; i < n; i++) {
                    hs.add(in.nextInt());
                }

                int max = hs.pollFirst(), smax = -1, ans1 = 0;
                if (max % 2 == 0 && max % 3 == 0 && max % 5 == 0 && hs.contains(max / 2) && hs.contains(max / 3) && hs.contains(max / 5)) {
                    ans1 = (max / 2) + (max / 3) + (max / 5);
                }

                for (int x : hs) {
                    if (max % x != 0 && smax == -1) {
                        smax = x;
                    } else if (smax != -1 && smax % x != 0 && max % x != 0) {
                        smax += x;
                        break;
                    }
                }

                if (smax != -1)
                    max += smax;
                ans1 = Math.max(ans1, max);
                out.println(ans1);
            }
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

