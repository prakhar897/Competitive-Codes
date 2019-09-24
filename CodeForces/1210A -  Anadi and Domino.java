import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.HashSet;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        CAnadiAndDomino solver = new CAnadiAndDomino();
        solver.solve(1, in, out);
        out.close();
    }

    static class CAnadiAndDomino {
        int max = 0;
        HashSet<Pair> hs;
        HashSet[] adj;

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            adj = new HashSet[n + 1];
            int i, j;
            hs = new HashSet<>();

            for (i = 1; i <= n; i++)
                adj[i] = new HashSet<Integer>();

            for (i = 0; i < m; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }

            for (i = 1; i <= 6; i++) {
                for (j = i; j <= 6; j++) {
                    hs.add(new Pair(i, j));
                }
            }

            rec(0, n, "");
            out.println(max);
        }

        void rec(int i, int n, String x) {
            if (i == n) {
                int cur = check(x);
                //System.out.println(x + " " + cur);
                if (cur > max) {
                    max = cur;
                    // System.out.println(x + " " + cur);
                }
                return;
            }

            for (int j = 1; j <= 6; j++) {
                x += j + "";
                rec(i + 1, n, x);
                x = x.substring(0, x.length() - 1);
            }
        }

        int check(String x) {
            HashSet<Pair> y = new HashSet<>(hs);
            int n = x.length();
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    int a = (int) x.charAt(i - 1) - '0';
                    int b = (int) x.charAt(j - 1) - '0';
                    if (adj[i].contains(j) && y.contains(new Pair(a, b))) {
                        y.remove(new Pair(a, b));
                        ans++;
                    }
                }
            }

            return ans;
        }

        class Pair implements Comparable {
            int f;
            int s;

            Pair(int fi, int se) {
                f = fi;
                s = se;
            }

            public int compareTo(Object o) {
                Pair pr = (Pair) o;
                if (s > pr.s)
                    return 1;
                if (s == pr.s) {
                    if (f > pr.f)
                        return 1;
                    else
                        return -1;
                } else
                    return -1;
            }

            public boolean equals(Object o) {
                Pair ob = (Pair) o;
                int ff;
                int ss;
                if (o != null) {
                    ff = ob.f;
                    ss = ob.s;
                    if ((ff == this.f) && (ss == this.s))
                        return true;
                }
                return false;
            }

            public int hashCode() {
                return (this.f + " " + this.s).hashCode();
            }

            public String toString() {
                return this.f + " " + this.s;
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

