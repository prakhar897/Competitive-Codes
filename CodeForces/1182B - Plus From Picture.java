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
        Task566B solver = new Task566B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task566B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int i, j, flag = 0, k, count = 0;
            int mat[][] = new int[n][m];
            int vis[][] = new int[n][m];
            int dir[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    char c = in.nextCharacter();
                    if (c == '*') {
                        mat[i][j] = 1;
                        count++;
                    }
                }
            }

            outer:
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (vis[i][j] == 1 || mat[i][j] == 0)
                        continue;

                    boolean h = true;
                    for (k = 0; k < 4; k++) {
                        int u = i + dir[k][0];
                        int v = j + dir[k][1];
                        if (u < 0 || u >= n || v < 0 || v >= m || mat[u][v] == 0) {
                            h = false;
                            break;
                        }
                    }

                    if (h == true) {
                        flag = 1;
                        vis[i][j] = 1;

                        for (k = 0; k < 4; k++) {
                            int u = i + dir[k][0];
                            int v = j + dir[k][1];

                            while (u >= 0 && v >= 0 && u < n && v < m && mat[u][v] == 1) {
                                vis[u][v] = 1;
                                u += dir[k][0];
                                v += dir[k][1];
                            }
                        }

                        break outer;
                    }
                }
            }

            //out.print(vis);
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (mat[i][j] == 1 && vis[i][j] == 0) {
                        out.println("NO");
                        return;
                    }
                }
            }

            if (count > 0)
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

