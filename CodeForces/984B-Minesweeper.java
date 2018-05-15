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
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int i, j, ns = 0, flag = 0;
            int mat[][] = new int[n][m];
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    mat[i][j] = in.nextCharacter();
                    if (mat[i][j] == 46) {
                        mat[i][j] = 0;
                    } else if (mat[i][j] > 47)
                        mat[i][j] -= 48;
                }
            }

       /* for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
                out.print(mat[i][j]);
            out.println();
        }*/

            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (mat[i][j] == 42) {
                        if (i >= 1 && j >= 1 && i < n && j < m && mat[i - 1][j - 1] != 42) {
                            mat[i - 1][j - 1]--;
                        }

                        if (i >= 1 && j >= 0 && i < n && j < m && mat[i - 1][j] != 42) {
                            mat[i - 1][j]--;

                        }

                        if (i >= 1 && j >= 0 && i < n && j < m - 1 && mat[i - 1][j + 1] != 42) {
                            mat[i - 1][j + 1]--;
                        }

                        if (i >= 0 && j >= 1 && i < n && j < m && mat[i][j - 1] != 42) {
                            mat[i][j - 1]--;
                        }

                        if (i >= 0 && j >= 0 && i < n && j < m - 1 && mat[i][j + 1] != 42) {
                            mat[i][j + 1]--;
                        }

                        if (i >= 0 && j >= 1 && i < n - 1 && j < m && mat[i + 1][j - 1] != 42) {
                            mat[i + 1][j - 1]--;
                        }

                        if (i >= 0 && j >= 0 && i < n - 1 && j < m && mat[i + 1][j] != 42) {
                            mat[i + 1][j]--;
                        }

                        if (i >= 0 && j >= 0 && i < n - 1 && j < m - 1 && mat[i + 1][j + 1] != 42) {
                            mat[i + 1][j + 1]--;
                        }
                    }

                }
            }

            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (mat[i][j] == 0 || mat[i][j] == 42) {
                    } else {
                        flag++;
                    }
                }
            }

            if (flag == 0)
                out.println("YES");
            else
                out.println("NO");
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

        public char nextCharacter() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return (char) c;
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

