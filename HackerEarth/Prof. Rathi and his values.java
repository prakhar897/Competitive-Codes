import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
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
        Taskrathi solver = new Taskrathi();
        solver.solve(1, in, out);
        out.close();
    }

    static class Taskrathi {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            HashSet<Integer> adj[] = new HashSet[n + 1];
            int val[] = new int[n + 1];
            int i, flag = 0;

            for (i = 1; i <= n; i++)
                adj[i] = new HashSet<>();

            for (i = 0; i < m; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }

            //for(i=1;i<=n;i++)
            // out.println(adj[i]);

            loop:
            for (i = 1; i <= n; i++) {
                if (val[i] != 0) continue;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                q.add(-1);
                int cur = 1;
                while (!q.isEmpty()) {
                    int k = q.poll();

                    if (k == -1) {
                        if (cur == 1)
                            cur = 2;
                        else
                            cur = 1;
                        if (!q.isEmpty())
                            q.add(-1);
                    } else {
                        val[k] = cur;
                        for (int x : adj[k]) {
                            if (val[x] == 0)
                                q.add(x);
                            if (val[x] == val[k]) {
                                //out.println(x + " " + k);
                                flag = 1;
                                break loop;
                            }
                        }
                    }
                }
            }

            //out.println(val);
            if (flag == 0)
                out.println("possible");
            else
                out.println(-1);
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

