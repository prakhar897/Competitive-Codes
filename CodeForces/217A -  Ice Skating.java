import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
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
        CGA solver = new CGA();
        solver.solve(1, in, out);
        out.close();
    }

    static class CGA {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            Pair arr[] = new Pair[n];
            HashMap<Pair, Integer> hm = new HashMap<>();
            int i, count = 0;

            for (i = 0; i < n; i++) {
                arr[i] = new Pair(in.nextInt(), in.nextInt());
                hm.put(arr[i], 0);
            }

            for (i = 0; i < n; i++) {
                if (hm.get(arr[i]) == 1) continue;
                Queue<Pair> q = new LinkedList<>();
                q.add(arr[i]);
                hm.put(arr[i], 1);
                count++;
                while (!q.isEmpty()) {
                    Pair p = q.poll();
                    for (int j = 0; j < n; j++) {
                        if ((arr[j].f == p.f || arr[j].s == p.s) && hm.get(arr[j]) == 0) {
                            q.offer(arr[j]);
                            hm.put(arr[j], 1);
                        }
                    }
                }
            }

            out.println(count - 1);


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
                if (f > pr.f)
                    return 1;
                if (f == pr.f) {
                    if (s > pr.s)
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

