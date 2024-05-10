import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.HashSet;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        DGoodSubstrings solver = new DGoodSubstrings();
        solver.solve(1, in, out);
        out.close();
    }

    static class DGoodSubstrings {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String s = in.nextString();
            char arr[] = in.nextString().toCharArray();
            int k = in.nextInt();
            int i, j, n = s.length(), p = 31;
            long ans = 0, m = (long) 1e9 + 9;
            int bad[] = new int[n];
            long pow[] = new long[n];
            long prehash[] = new long[n];

            pow[0] = 1;
            for (i = 1; i < n; i++) {
                pow[i] = (pow[i - 1] * p) % m;
            }

            prehash[0] = (s.charAt(0) - 'a' + 1);
            for (i = 1; i < n; i++) {
                prehash[i] = (prehash[i - 1] + ((s.charAt(i) - 'a' + 1) * pow[i])) % m;
            }

            for (i = 0; i < n; i++) {
                if (i != 0)
                    bad[i] = bad[i - 1];

                if (arr[(int) s.charAt(i) - 'a'] == '0')
                    bad[i] += 1;
            }
            //out.println(bad);
            //out.println(prehash);

            for (i = 1; i <= n; i++) {
                HashSet<Long> hs = new HashSet<>();

                for (j = 0; j < n - i + 1; j++) {
                    String cur = s.substring(j, j + i);
                    int cbad = bad[j + i - 1];
                    if (j > 0)
                        cbad -= bad[j - 1];
                    if (cbad <= k) {
                        long h = prehash[j + i - 1];
                        if (j > 0) {
                            h = h + m - prehash[j - 1];
                        }
                        h %= m;
                        h = (h * pow[n - j - 1]) % m;
                        // out.println(cur + " " + h);
                        hs.add(h);
                    }

                }
                //out.println(hs.size());
                ans += hs.size();
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

