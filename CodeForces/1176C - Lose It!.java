import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
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
        Task565C solver = new Task565C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task565C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int i;
            int arr[] = new int[n];
            Queue<Integer> q[] = new LinkedList[6];

            for (i = 0; i < 6; i++)
                q[i] = new LinkedList<>();

            for (i = 0; i < n; i++) {
                int a = in.nextInt();
                if (a == 4)
                    q[0].add(i);
                if (a == 8)
                    q[1].add(i);
                if (a == 15)
                    q[2].add(i);
                if (a == 16)
                    q[3].add(i);
                if (a == 23)
                    q[4].add(i);
                if (a == 42)
                    q[5].add(i);
            }

            i = 0;
            int count = 0, cur = -1;
            while (!q[i].isEmpty()) {
                int k = q[i].poll();
                while (k < cur && !q[i].isEmpty()) {
                    k = q[i].poll();
                }

                if (k < cur)
                    break;

                cur = k;
                i++;
                i %= 6;
                if (i == 0) {
                    count++;
                    cur = -1;
                }
            }

            long ans = n - (6 * count);
            out.println(ans);
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

    }
}

