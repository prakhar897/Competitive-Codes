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
        Task552E solver = new Task552E();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task552E {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int mat[][] = new int[n][2];
            int ans[] = new int[n];
            int next[] = new int[n];
            int prev[] = new int[n];
            int i, j;

            for (i = 0; i < n; i++) {
                mat[i][0] = i;
                mat[i][1] = in.nextInt();
                next[i] = i + 1;
                prev[i] = i - 1;
            }

            Arrays.sort(mat, (p1, p2) -> {
                if (p2[1] != p1[1])
                    return p2[1] - p1[1];
                return p2[0] - p1[0];
            });

            //out.print(mat);
            int cur = 1;
            for (i = 0; i < n; i++) {
                int mid = mat[i][0];
                if (ans[mid] != 0)
                    continue;

                ans[mid] = cur;
                int li = mid;
                int ri = mid;
                //out.println(mid);
                for (j = 0; j < k; j++) {
                    li = prev[li];
                    if (li < 0 || ans[li] != 0)
                        break;
                    ans[li] = cur;
                }

                for (j = 0; j < k; j++) {
                    ri = next[ri];
                    if (ri == n || ans[ri] != 0)
                        break;
                    ans[ri] = cur;
                }

                if (li >= 0 && ri < n) {
                    //out.println(li+" " + ri);
                    if (prev[li] >= 0)
                        next[prev[li]] = next[ri];
                    if (next[ri] < n)
                        prev[next[ri]] = prev[li];
                }

                if (cur == 1)
                    cur = 2;
                else
                    cur = 1;
                //out.println(ans);
            }

            StringBuilder an = new StringBuilder();
            for (i = 0; i < n; i++)
                an.append(ans[i]);
            out.println(an.toString());
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

