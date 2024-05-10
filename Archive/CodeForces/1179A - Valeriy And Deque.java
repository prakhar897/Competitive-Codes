import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayList;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        Task569C solver = new Task569C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task569C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            int arr[] = new int[n];
            int freeze[] = new int[n];
            int i, max = -1, count = 0;
            Deque<Integer> dq = new LinkedList<>();
            ArrayList<Pair> al = new ArrayList<>();

            for (i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                if (arr[i] > max)
                    max = arr[i];
                dq.add(arr[i]);
            }
            //out.println(dq.peek());

            while (dq.peek() != max) {
                int u = dq.pollFirst();
                int v = dq.pollFirst();
                Pair p = new Pair(u, v);
                al.add(p);
                int maxi = Math.max(u, v);
                int mini = Math.min(u, v);
                dq.offerFirst(maxi);
                dq.offerLast(mini);
                count++;
                // out.println(dq);
            }
            //out.println(al);

            for (i = 0; i < n; i++) {
                freeze[i] = dq.poll();
            }
            //out.println(freeze);

            for (i = 0; i < q; i++) {
                long query = in.nextLong() - 1;
                if (query < count) {
                    Pair p = al.get((int) query);
                    out.println(p);
                } else {
                    query -= count;
                    query %= n - 1;
                    query++;
                    out.println(freeze[0] + " " + freeze[(int) query]);
                }
            }
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

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
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

    }
}

