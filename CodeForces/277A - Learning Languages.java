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
        Task277A solver = new Task277A();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task277A {
        int edges[][];
        int vis[];

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            edges = new int[n + m][n + m];
            int i, j, sum = 0;

            for (i = 0; i < n; i++) {
                int k = in.nextInt();
                sum += k;
                for (j = 0; j < k; j++) {
                    int p = in.nextInt();
                    edges[i][n + p - 1] = 1;
                    edges[n + p - 1][i] = 1;
                }
            }

            //out.print(edges);
            vis = new int[n + m];
            int ans = 0;
            for (i = 0; i < n; i++) {
                if (vis[i] == 1) continue;
                dfs(i, n, m);
                ans++;
            }

            if (sum == 0)
                ans++;
            out.println(ans - 1);
        }

        void dfs(int i, int n, int m) {
            vis[i] = 1;
            for (int j = 0; j < n + m; j++) {
                if (edges[i][j] == 1 && vis[j] == 0)
                    dfs(j, n, m);
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

        public void println(int i) {
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

