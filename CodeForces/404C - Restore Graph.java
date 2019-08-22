import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
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
        Task404C solver = new Task404C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task404C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            Pair d[] = new Pair[n];
            int edges[] = new int[n + 1];
            ArrayList<Pair> ans = new ArrayList<>();
            int i, cur = 0, j = 0;

            for (i = 1; i <= n; i++) {
                d[i - 1] = new Pair(i, in.nextInt());
            }

            Arrays.sort(d);

            if (d[0].s != 0) {
                out.println(-1);
                return;
            }

            i = 1;
            while (i < n) {
                if (j == i) {
                    out.println(-1);
                    return;
                }

                if (d[i].s - d[j].s == 1 && edges[d[i].f] < k && edges[d[j].f] < k) {
                    ans.add(new Pair(d[i].f, d[j].f));
                    edges[d[i].f]++;
                    edges[d[j].f]++;
                    i++;
                } else {
                    j++;
                }
            }

            out.println(ans.size());
            for (Pair p : ans)
                out.println(p);


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

