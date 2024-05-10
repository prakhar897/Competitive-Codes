import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        Task371C solver = new Task371C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task371C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String s = in.nextString();
            int n[] = in.nextIntArray(3);
            int p[] = in.nextIntArray(3);
            int m[] = new int[3];
            long r = in.nextLong();
            long ans = 0, sum = 0, tc = 0;
            int i;

            for (i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'B')
                    m[0]++;
                else if (c == 'S')
                    m[1]++;
                else
                    m[2]++;
            }

            int min = Integer.MAX_VALUE;

            for (i = 0; i < 3; i++) {
                if (m[i] != 0)
                    min = Math.min(min, n[i] / m[i]);
                else
                    n[i] = 0;
                tc += m[i] * p[i];
            }

            ans += min;
            //out.println(min);

            for (i = 0; i < 3; i++) {
                n[i] -= min * m[i];
                sum += n[i];
            }
            //out.println(sum);
            //out.println(n);
            //out.println(ans);

            while (sum != 0 && r > 0) {
                for (i = 0; i < 3; i++) {
                    if (n[i] <= m[i]) {
                        r -= p[i] * (m[i] - n[i]);
                        sum -= n[i];
                        n[i] = 0;
                    } else {
                        sum -= m[i];
                        n[i] -= m[i];
                    }
                }

                if (r >= 0) {
                    ans++;
                }
            }

            //out.println(ans);
            //out.println(r);

            if (r > 0) {
                ans += r / tc;
            }

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

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
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

