import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
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
        TaskLNMD solver = new TaskLNMD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskLNMD {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int x = in.nextInt();
            LinkedList<Integer> adj[] = new LinkedList[n + 1];
            Queue<Integer> q = new LinkedList<>();
            int visited[] = new int[n + 1];
            LinkedList<Integer> children = new LinkedList<>();
            int i, j, m;
            long ans = 0;

            for (i = 0; i <= n; i++)
                adj[i] = new LinkedList<>();

            for (i = 1; i < n; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }


            q.add(1);
            visited[1] = 1;
            while (!q.isEmpty()) {
                int h = q.poll();
                for (int u : adj[h]) {
                    if (visited[u] == 0) {
                        visited[u] = 1;
                        q.add(u);
                        if (h == x)
                            children.add(u);
                    }
                }

                if (h == x)
                    break;
            }
            //out.println(children);

            m = children.size();
            long calc[] = new long[m];
            long sum = 0;
            long summ[] = new long[m];
            i = 0;
            for (int l : children) {
                long count = 1;
                q = new LinkedList<>();
                q.add(l);
                visited[l] = 1;
                while (!q.isEmpty()) {
                    int h = q.poll();
                    for (int u : adj[h]) {
                        if (visited[u] == 0) {
                            visited[u] = 1;
                            q.add(u);
                            count++;
                        }
                    }
                }
                calc[i] = count;
                sum += count;
                summ[i] = sum;
                i++;
            }
            //out.println(calc);
            for (i = 0; i < m; i++) {
                ans += (summ[m - 1] - summ[i]) * calc[i];
            }
            ans += sum;
            out.println(ans);
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

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

