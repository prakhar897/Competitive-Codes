import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Comparator;
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
        Task547F solver = new Task547F();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task547F {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            long arr[] = in.nextLongArray(n);
            long cur = 0;
            int i, j;
            Map<Long, List<Pair>> hm = new HashMap<>();

            for (i = 0; i < n; i++) {
                cur = 0;
                for (j = i; j < n; j++) {
                    cur += arr[j];
                    if (!hm.containsKey(cur)) {
                        List<Pair> al = new LinkedList<>();
                        al.add(new Pair(i + 1, j + 1));
                        hm.put(cur, al);
                    } else {
                        List<Pair> al = hm.get(cur);
                        al.add(new Pair(i + 1, j + 1));
                        hm.put(cur, al);
                    }
                }
            }
            //out.println(hm);
            long max = arr[0];
            int msize = 0;
            for (long key : hm.keySet()) {
                List<Pair> al = hm.get(key);
                if (al.size() < hm.get(max).size())
                    continue;
                Collections.sort(al, new Comparator<Pair>() {

                    public int compare(Pair o1, Pair o2) {
                        if (o1.b != o2.b)
                            return o1.b - o2.b;
                        return o1.a - o2.a;
                    }
                });
                //out.println(al);
                List<Pair> all = new LinkedList<>();
                int prev = -1;
                for (Pair p : al) {
                    if (p.a > prev) {
                        all.add(p);
                        prev = p.b;
                    }
                }
                //out.println(all);

                hm.put(key, all);
                //out.println(all.size());
                if (all.size() > msize) {
                    //out.println("ca" + msize + " " + all.size());
                    msize = all.size();
                    max = key;
                }

            }
            //out.println(hm);

            List<Pair> al = hm.get(max);
            out.println(al.size());
            for (Pair p : al) {
                out.println(p.a + " " + p.b);
            }

        }

        class Pair {
            int a;
            int b;

            public Pair(int a, int b) {
                this.a = a;
                this.b = b;
            }

            public String toString() {
                return "(" + a + ", " + b + ")";
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

        public long[] nextLongArray(int n) {
            long[] array = new long[n];
            for (int i = 0; i < n; ++i) array[i] = nextLong();
            return array;
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

