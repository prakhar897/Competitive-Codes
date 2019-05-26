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
        Circuit solver = new Circuit();
        solver.solve(1, in, out);
        out.close();
    }

    static class Circuit {
        int mat;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            for (int o = 1; o <= test; o++) {
                int r = in.nextInt();
                int c = in.nextInt();
                int k = in.nextInt();
                mat = new int[r][c];
                int dist[][] = new int[r][c];
                int ans[][] = new int[r][c];
                int i, j;

                for (i = 0; i < r; i++)
                    mat[i] = in.nextIntArray(c);

                for (i = 0; i < r; i++) {
                    for (j = 0; j < c; j++) {
                        dist[i][j] = check(i, j, c, k);
                    }
                }

                // out.print(dist);
                int max = 0;

                for (i = 0; i < r; i++) {
                    for (j = 0; j < c; j++) {
                        ans[i][j] = dist[i][j];
                        int min = dist[i][j];
                        for (int l = i + 1; l < r; l++) {
                            min = Math.min(min, dist[l][j]);
                            ans[i][j] = Math.max(ans[i][j], min * (l - i + 1));
                        }

                        if (ans[i][j] > max)
                            max = ans[i][j];
                    }
                }

                // out.print(ans);
                out.println("Case #" + o + ": " + max);
            }
        }

        int check(int i, int j, int c, int lim) {
            int ans = 1;
            int min = mat[i][j];
            int max = mat[i][j];
            for (int k = j + 1; k < c; k++) {
                min = Math.min(min, mat[i][k]);
                max = Math.max(max, mat[i][k]);
                if (max - min > lim)
                    break;
                ans++;
            }
            return ans;
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
}

