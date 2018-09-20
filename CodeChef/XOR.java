import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Map;
import java.util.Map.Entry;
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
        TaskSEPC solver = new TaskSEPC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskSEPC {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            while (test-- > 0) {
                int n = in.nextInt();
                int i;
                HashMap<Long, Long> even = new HashMap<Long, Long>();
                HashMap<Long, Long> odd = new HashMap<Long, Long>();
                long ans = 0, c, eve = 0, od = 0;

                for (i = 0; i < n; i++) {
                    c = in.nextLong();
                    if (c % 2 == 0) {
                        even.put(c, even.getOrDefault(c, 0l) + 1);
                        eve++;
                    }
                    if (c % 2 == 1) {
                        odd.put(c, odd.getOrDefault(c, 0l) + 1);
                        od++;
                    }
                }

                ans = (eve * (eve - 1)) / 2 + (od * (od - 1)) / 2;

                for (Map.Entry<Long, Long> entry : even.entrySet()) {
                    long key = entry.getKey();
                    long value = entry.getValue();

                    ans -= (value * (value - 1)) / 2;
                    long o = (key + 2) ^ key;
                    //out.println(o);

                    if (even.containsKey(key + 2) && o == 2) {
                        ans -= value * even.get(key + 2);
                    }
                }

                for (Map.Entry<Long, Long> entry : odd.entrySet()) {
                    long key = entry.getKey();

                    long value = entry.getValue();
                    long o = (key + 2) ^ key;

                    ans -= (value * (value - 1)) / 2;

                    if (odd.containsKey(key + 2) && o == 2) {
                        ans -= value * odd.get(key + 2);
                    }
                }

                out.println(ans);

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

        public void close() {
            writer.close();
        }

        public void println(long i) {
            writer.println(i);
        }

    }
}

