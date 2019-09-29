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
import java.util.ArrayList;
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
        DGargariAndPermutations solver = new DGargariAndPermutations();
        solver.solve(1, in, out);
        out.close();
    }

    static class DGargariAndPermutations {
        int[] ans;
        ArrayList<Integer>[] adj;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int mat[][] = new int[k][n];
            int pos[][] = new int[k][n];
            int i, j;

            for (i = 0; i < k; i++) {
                for (j = 0; j < n; j++) {
                    mat[i][j] = in.nextInt() - 1;
                    pos[i][mat[i][j]] = j;
                }
            }


            adj = new ArrayList[n];
            for (i = 0; i < n; i++)
                adj[i] = new ArrayList<>();

            for (i = 0; i < n; i++) {
                loop:
                for (j = 0; j < n; j++) {
                    for (int l = 0; l < k; l++) {
                        if (pos[l][i] >= pos[l][j])
                            continue loop;
                    }

                    adj[i].add(j);
                }
            }

        /*for(i=0;i<n;i++)
            out.println(adj[i]);*/

            ans = new int[n];
            int max = 0;
            for (i = 0; i < n; i++)
                max = Math.max(max, rec(i));

            out.println(max);
        }

        int rec(int x) {
            if (ans[x] != 0)
                return ans[x];

            int an = 0;
            for (int y : adj[x])
                an = Math.max(rec(y), an);

            ans[x] = an + 1;
            return an + 1;
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

