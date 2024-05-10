import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        CodeEat solver = new CodeEat();
        solver.solve(1, in, out);
        out.close();
    }

    static class CodeEat {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            for (int y = 1; y <= test; y++) {
                int d = in.nextInt();
                int s = in.nextInt();
                int mat[][] = new int[s][2];

                int i, j;

                for (i = 0; i < s; i++) {
                    mat[i][0] = in.nextInt();
                    mat[i][1] = in.nextInt();
                }

                Arrays.sort(mat, (int o1[], int o2[]) -> {
                    double a = (1d * o1[1]) / o1[0];
                    double b = (1d * o2[1]) / o2[0];

                    if (b - a > 0)
                        return 1;
                    else if (a == b)
                        return 0;
                    else
                        return -1;
                });

                //out.print(mat);
                long pre[] = new long[s + 1];
                long pre2[] = new long[s + 1];

                for (i = 1; i <= s; i++) {
                    pre[i] = pre[i - 1] + mat[i - 1][0];
                    pre2[i] = pre2[i - 1] + mat[i - 1][1];
                }

                StringBuilder ans = new StringBuilder();
                outer:
                for (i = 0; i < d; i++) {
                    int a = in.nextInt();
                    int b = in.nextInt();

                    if (a > pre[s] || b > pre2[s]) {
                        ans.append('N');
                        continue outer;
                    }

                    int index = search(b, pre2, s + 1);
                    b -= pre2[index];
                    double cur_a = pre[s] - pre[index];
                    cur_a -= ((1d * b) * mat[index][0]) / (1d * mat[index][1]);

                    if (cur_a >= a)
                        ans.append('Y');
                    else
                        ans.append('N');

                }

                out.println("Case #" + y + ": " + ans.toString());
            }
        }

        int search(int b, long pre[], int n) {
            int low = 0, high = n - 1;
            while (high - low > 1) {
                int mid = (high + low) / 2;
                if (pre[mid] > b)
                    high = mid;
                else
                    low = mid;
            }
            return low;
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

