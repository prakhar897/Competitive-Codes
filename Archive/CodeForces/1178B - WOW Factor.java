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
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskGlobalB solver = new TaskGlobalB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskGlobalB {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            char arr[] = in.nextString().toCharArray();
            int n = arr.length;
            int i, count = 1, j;
            StringBuilder cur = new StringBuilder();

            for (i = 1; i < n; i++) {
                if (arr[i] == arr[i - 1])
                    count++;
                else {
                    if (arr[i - 1] == 'v') {
                        for (j = 0; j < count - 1; j++)
                            cur.append("w");
                    } else {
                        for (j = 0; j < count; j++)
                            cur.append("o");
                    }

                    //out.println(count);
                    count = 1;
                }
            }

            if (arr[n - 1] == 'v') {
                for (j = 0; j < count - 1; j++)
                    cur.append("w");
            } else {
                for (j = 0; j < count; j++)
                    cur.append("o");
            }

            //out.println(cur.toString());
            long ans = count(cur.toString(), "wow");
            out.println(ans);

        }

        long count(String a, String b) {
            int m = a.length();
            int n = b.length();

            long lookup[][] = new long[m + 1][n + 1];

            for (int i = 0; i <= n; ++i)
                lookup[0][i] = 0;

            for (int i = 0; i <= m; ++i)
                lookup[i][0] = 1;

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (a.charAt(i - 1) == b.charAt(j - 1))
                        lookup[i][j] = lookup[i - 1][j - 1] +
                                lookup[i - 1][j];

                    else
                        lookup[i][j] = lookup[i - 1][j];
                }
            }

            return lookup[m][n];
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

