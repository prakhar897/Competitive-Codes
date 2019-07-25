import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        Task575E solver = new Task575E();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task575E {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int q = in.nextInt();
            int mat[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            loop:
            while (q-- > 0) {
                int b = in.nextInt();
                int w = in.nextInt();
                int a = (int) 100;
                HashSet<Pair> white = new HashSet<>();
                HashSet<Pair> black = new HashSet<>();

                if (w > b) {
                    long max = 4 + (b - 1) * 3;
                    if (w > max) {
                        out.println("NO");
                        continue loop;
                    }
                } else {
                    long max = 4 + (w - 1) * 3;
                    if (b > max) {
                        out.println("NO");
                        continue loop;
                    }
                }

                black.add(new Pair(a / 2, (a / 2) + 1));
                while (white.size() < w || black.size() < b) {
                    for (Pair x : black) {
                        int f = x.f;
                        int s = x.s;

                        for (int j = 0; j < 4; j++) {
                            int curf = f + mat[j][0];
                            int curs = s + mat[j][1];
                            white.add(new Pair(curf, curs));
                        }
                    }

                    for (Pair x : white) {
                        int f = x.f;
                        int s = x.s;

                        for (int j = 0; j < 4; j++) {
                            int curf = f + mat[j][0];
                            int curs = s + mat[j][1];
                            black.add(new Pair(curf, curs));
                        }
                    }
                }

                out.println("YES");
                int count = 0;
                for (Pair x : white) {
                    out.println(x.f + " " + x.s);
                    count++;
                    if (count == w)
                        break;
                }

                count = 0;
                for (Pair x : black) {
                    out.println(x.f + " " + x.s);
                    count++;
                    if (count == b)
                        break;
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

