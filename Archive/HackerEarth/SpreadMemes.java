import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
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
        Spread_MEMES solver = new Spread_MEMES();
        solver.solve(1, in, out);
        out.close();
    }

    static class Spread_MEMES {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList<Integer> adj[] = new ArrayList[n + 1];
            int visited[] = new int[n + 1];
            int i, j, ans = 0;

            for (i = 1; i <= n; i++)
                adj[i] = new ArrayList<>();

            for (i = 0; i < m; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }

            for (i = 1; i <= n; i++) {
                if (visited[i] == 1) continue;
                HashSet<Integer> hs = new HashSet<>();
                Queue<Integer> q = new LinkedList<>();
                int last = -1;
                q.add(i);
                while (!q.isEmpty()) {
                    int k = q.poll();
                    hs.add(k);
                    last = k;
                    visited[k] = 1;
                    for (int s : adj[k]) {
                        if (visited[s] == 0) {
                            q.add(s);
                        }
                    }
                }

                int len = 0;
                q = new LinkedList<>();
                q.add(last);
                q.add(-1);
                while (!q.isEmpty()) {
                    int k = q.poll();
                    if (k == -1) {
                        if (!q.isEmpty())
                            q.add(-1);
                        len++;
                        continue;
                    }

                    hs.remove(k);
                    for (int s : adj[k]) {
                        if (hs.contains(s)) {
                            q.add(s);
                        }
                    }
                }

                ans = Math.max(ans, len);
            }
            out.println(ans / 2);


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

