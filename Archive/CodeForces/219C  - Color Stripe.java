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
        Task219C solver = new Task219C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task219C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            char a[] = new char[k];
            char arr[] = in.nextString().toCharArray();
            int i, j;

            for (i = 0; i < k; i++)
                a[i] = (char) ((int) ('A') + i);

            if (k > 2) {
                int dp[] = new int[n];
                dp[0] = 0;
                for (i = 1; i < n; i++) {
                    dp[i] = dp[i - 1];
                    //out.println(arr[i] + " " + arr[i - 1]);
                    if (arr[i] == arr[i - 1]) {
                        dp[i]++;
                        for (j = 0; j < k; j++) {
                            char cur = (char) ('A' + j);
                            if (cur == arr[i - 1] || (i + 1 < n && cur == arr[i + 1]))
                                continue;
                            else {
                                arr[i] = cur;
                                break;
                            }
                        }
                    }
                }

                //out.println(dp);
                out.println(dp[n - 1]);
                out.println(new String(arr));
                return;
            }

            char pos1[] = new char[n];
            char pos2[] = new char[n];
            int dif1 = 0, dif2 = 0;
            for (i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    pos1[i] = 'A';
                    pos2[i] = 'B';
                } else {
                    pos1[i] = 'B';
                    pos2[i] = 'A';
                }

                if (arr[i] == pos1[i])
                    dif2++;
                else
                    dif1++;
            }

            if (dif1 > dif2) {
                out.println(dif2);
                out.println(new String(pos2));
            } else {
                out.println(dif1);
                out.println(new String(pos1));
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

