import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
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
                int a = (int) 1e9;
                int i;
                ArrayList<Pair> white = new ArrayList<>();
                ArrayList<Pair> black = new ArrayList<>();

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

                if (b > w) {
                    for (i = 2; i <= 2 * w; i++) {
                        if (i % 2 == 0)
                            white.add(new Pair(2, i));
                        else
                            black.add(new Pair(2, i));
                    }

                    black.add(new Pair(2, 1));
                    black.add(new Pair(2, (2 * w) + 1));

                    for (Pair x : white) {
                        black.add(new Pair(1, x.b));
                        black.add(new Pair(3, x.b));
                    }

                } else {
                    for (i = 2; i <= 2 * b; i++) {
                        if (i % 2 == 1)
                            white.add(new Pair(3, i));
                        else
                            black.add(new Pair(3, i));
                    }

                    white.add(new Pair(3, 1));
                    white.add(new Pair(3, (2 * b) + 1));

                    for (Pair x : black) {
                        white.add(new Pair(2, x.b));
                        white.add(new Pair(4, x.b));
                    }
                }

                out.println("YES");
                int count = 0;
                for (Pair x : white) {
                    out.println(x.a + " " + x.b);
                    count++;
                    if (count == w)
                        break;
                }

                count = 0;
                for (Pair x : black) {
                    out.println(x.a + " " + x.b);
                    count++;
                    if (count == b)
                        break;
                }
            }
        }

        class Pair {
            int a;
            int b;

            Pair(int x, int y) {
                a = x;
                b = y;
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

