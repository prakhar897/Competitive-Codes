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
import java.util.Collections;
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
        TaskSmallestString solver = new TaskSmallestString();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskSmallestString {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            char arr[] = in.nextString().toCharArray();
            int n = arr.length;
            int m = in.nextInt();
            int vis[] = new int[n];
            char ans[] = new char[n];
            HashSet<Integer> adj[] = new HashSet[n];
            int i;

            for (i = 0; i < n; i++) {
                adj[i] = new HashSet<>();
            }

            for (i = 0; i < m; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }

            for (i = 0; i < n; i++) {
                if (vis[i] == 0) {
                    ArrayList<Character> cur = new ArrayList<>();
                    ArrayList<Integer> ts = new ArrayList<>();
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i);
                    vis[i] = 1;
                    while (!q.isEmpty()) {
                        int k = q.poll();
                        cur.add(arr[k]);
                        ts.add(k);
                        for (int x : adj[k]) {
                            if (vis[x] == 0) {
                                q.add(x);
                                vis[x] = 1;
                            }

                        }
                    }

                    Collections.sort(cur);
                    Collections.sort(ts);
                    // out.println(ts);

                    for (int j = 0; j < ts.size(); j++) {
                        ans[ts.get(j)] = cur.get(j);
                    }
                }
            }

            StringBuilder ans2 = new StringBuilder();
            for (i = 0; i < n; i++)
                ans2.append(ans[i]);
            out.println(ans2.toString());
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

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
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

    }
}

