import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
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
        Task565D solver = new Task565D();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task565D {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int i, m = 2750141, j;
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            HashMap<Integer, Integer> hm = new HashMap<>();
            int ans[] = new int[n];
            int sieve[] = new int[m];

            for (i = 2; i < m; i++) {
                if (sieve[i] == 0)
                    sieve[i] = i;

                for (j = 2; j <= i; j++) {
                    if (1l * i * j >= m)
                        break;
                    sieve[i * j] = i;
                }
            }
            //out.println(sieve);
            j = 0;
            for (i = 0; i < m; i++) {
                if (sieve[i] == i) {
                    hm.put(i, j);
                    j++;
                }
            }
            //out.println(hm);

            for (i = 0; i < 2 * n; i++) {
                int a = in.nextInt();
                if (tm.containsKey(a)) {
                    tm.put(a, tm.get(a) + 1);
                } else {
                    tm.put(a, 1);
                }
            }

            for (i = 0; i < n; i++) {
                int l = tm.lastKey();
                int k = tm.get(l);
                if (k == 1) {
                    tm.remove(l);
                } else {
                    tm.put(l, k - 1);
                }

                if (sieve[l] == l) {
                    int u = hm.get(l);
                    int y = tm.get(u);
                    if (y == 1) {
                        tm.remove(u);
                    } else {
                        tm.put(u, y - 1);
                    }
                    ans[i] = u;
                } else {
                    ans[i] = l;
                    int u = sieve[l];
                    int y = tm.get(u);
                    if (y == 1) {
                        tm.remove(u);
                    } else {
                        tm.put(u, y - 1);
                    }
                }
            }

            out.println(ans);


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

        public void print(int[] array) {
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(array[i]);
            }
        }

        public void println(int[] array) {
            print(array);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

