import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.util.Set;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        TaskChefD solver = new TaskChefD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskChefD {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            while (test-- > 0) {
                int r = in.nextInt();
                int c = in.nextInt();
                char mat[][] = new char[r][c];
                HashMap<Integer, Integer> mat2[][] = new HashMap[r][c];
                int i, j, k;

                for (i = 0; i < r; i++) {
                    for (j = 0; j < c; j++) {
                        mat[i][j] = in.nextCharacter();
                        mat2[i][j] = new HashMap<>();
                    }
                }

                int flag = -1;
                for (i = 0; i < r; i++) {
                    for (j = 0; j < c; j++) {
                        if (mat[i][j] == '-' || mat[i][j] == '#')
                            continue;

                        if (mat[i][j] == 'U') {
                            int count = 0;
                            for (k = i; k >= 0; k--) {
                                if (mat[k][j] != '#') {
                                    mat2[k][j].merge(count, 1, (x, y) -> (x + y));
                                } else
                                    break;
                                count++;
                            }
                        }

                        if (mat[i][j] == 'D') {
                            int count = 0;
                            for (k = i; k < r; k++) {
                                if (mat[k][j] != '#') {
                                    mat2[k][j].merge(count, 1, (x, y) -> (x + y));
                                } else
                                    break;
                                count++;
                            }
                        }

                        if (mat[i][j] == 'L') {
                            int count = 0;
                            for (k = j; k >= 0; k--) {
                                if (mat[i][k] != '#') {
                                    mat2[i][k].merge(count, 1, (x, y) -> (x + y));
                                } else
                                    break;
                                count++;
                            }
                        }

                        if (mat[i][j] == 'R') {
                            int count = 0;
                            for (k = j; k < c; k++) {
                                if (mat[i][k] != '#') {
                                    mat2[i][k].merge(count, 1, (x, y) -> (x + y));
                                } else
                                    break;
                                count++;
                            }
                        }
                    }
                }

            /*for(i=0;i<r;i++)
            {
                for(j=0;j<c;j++)
                {
                    out.println(mat2[i][j]);
                }
            }*/

                long ans = 0;
                for (i = 0; i < r; i++) {
                    for (j = 0; j < c; j++) {
                        Iterator it = mat2[i][j].entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry p = (Map.Entry) it.next();
                            int key = (int) p.getKey();
                            int value = (int) p.getValue();
                            ans += (long) (value * (value - 1)) / 2;
                            it.remove();
                            //out.println(i+" " + j + " " + key  + " " + value);
                        }

                    }
                }
                out.println(ans);
            }
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
}

