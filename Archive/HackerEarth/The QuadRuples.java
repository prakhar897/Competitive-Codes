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
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Quadruples solver = new Quadruples();
        solver.solve(1, in, out);
        out.close();
    }

    static class Quadruples {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            long k = in.nextLong();
            int a[] = in.nextIntArray(n);
            int b[] = in.nextIntArray(n);
            int c[] = in.nextIntArray(n);
            int d[] = in.nextIntArray(n);

            HashMap<Long, Integer> hm1 = new HashMap<>();
            HashMap<Long, Integer> hm2 = new HashMap<>();

            int i, j;

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    long key = a[i] + b[j];
                    hm1.put(key, hm1.getOrDefault(key, 0) + 1);
                }
            }

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    long key = c[i] + d[j];
                    hm2.put(key, hm2.getOrDefault(key, 0) + 1);
                }
            }
            long ans = 0;
            for (long key : hm1.keySet()) {
                long cur = k - key;
                if (cur < 0)
                    continue;

                if (hm2.containsKey(cur)) {
                    int p = hm2.get(cur);
                    int q = hm1.get(key);
                    ans += p * q;
                }
            }
            out.println(ans);
       /* out.println(hm1);
        out.println(hm2);*/
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

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
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

    }
}

