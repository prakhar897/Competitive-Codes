import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.util.Set;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
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
        TaskGBB solver = new TaskGBB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskGBB {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int cord[][] = new int[n][2];
            int pos[][] = new int[n][2];
            HashMap<Pair, Integer> hm = new HashMap<>();
            int i, j;

            for (i = 0; i < n; i++) {
                for (j = 0; j < 2; j++) {
                    cord[i][j] = in.nextInt();
                }
            }

            for (i = 0; i < n; i++) {
                for (j = 0; j < 2; j++) {
                    pos[i][j] = in.nextInt();
                }
            }

            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    int y = pos[j][0] + cord[i][0];
                    int u = pos[j][1] + cord[i][1];
                    Pair t = new Pair(y, u);

                    hm.put(t, hm.getOrDefault(t, 0) + 1);
                }
            }

            Iterator it = hm.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry p = (Map.Entry) it.next();
                Pair a = (Pair) p.getKey();
                int b = (int) p.getValue();
                //out.println(a.a + " " + a.b + " " +b);
                if (b == n) {
                    out.println(a.a + " " + a.b);
                    break;
                }
                it.remove();
            }
        }

        class Pair {
            int a;
            int b;

            public Pair(int y, int u) {
                a = y;
                b = u;
            }

            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Pair)) return false;
                Pair key = (Pair) o;
                return a == key.a && b == key.b;
            }

            public int hashCode() {
                int result = a;
                result = 31 * result + b;
                return result;
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

