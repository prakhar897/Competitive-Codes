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
import java.util.Comparator;
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
        Task536B solver = new Task536B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task536B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int mat[][] = new int[n + 1][3];
            int mat2[][] = new int[n + 1][3];
            int i, j;

            for (i = 1; i <= n; i++) {
                mat[i][0] = i;
                mat[i][1] = in.nextInt();
                mat2[i][0] = mat[i][0];
                mat2[i][1] = mat[i][1];
            }

            for (i = 1; i <= n; i++) {
                mat[i][2] = in.nextInt();
                mat2[i][2] = mat[i][2];
            }

            Arrays.sort(mat2, new Sorta());
            //out.print(mat);
            int cheap = 1;
            long ans = 0;

            for (i = 0; i < m; i++) {
                int kind = in.nextInt();
                int no = in.nextInt();
                ans = 0;

                if (mat[kind][1] >= no) {
                    ans += 1l * no * mat[kind][2];
                    mat[kind][1] -= no;
                    no = 0;
                } else {
                    ans += 1l * mat[kind][1] * mat[kind][2];
                    no -= mat[kind][1];
                    mat[kind][1] = 0;

                    while (no > 0) {
                        if (cheap > n) {
                            break;
                        }
                        int get = mat2[cheap][0];
                        if (mat[get][1] >= no) {
                            ans += 1l * no * mat[get][2];
                            mat[get][1] -= no;
                            no = 0;
                        } else {
                            ans += 1l * mat[get][1] * mat[get][2];
                            no -= mat[get][1];
                            mat[get][1] = 0;
                            cheap++;
                        }
                    }
                }

                if (no == 0)
                    out.println(ans);
                else
                    out.println(0);
            }
        }

        class Sorta implements Comparator<int[]> {
            public int compare(int[] o1, int[] o2) {

                if (o1[2] == o2[2])
                    return o1[0] - o2[0];
                else
                    return o1[2] - o2[2];

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

        public void println(long i) {
            writer.println(i);
        }

        public void println(int i) {
            writer.println(i);
        }

    }
}

