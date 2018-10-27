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
        TasksnackD solver = new TasksnackD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TasksnackD {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            while (test-- > 0) {
                int n = in.nextInt();
                int a[] = in.nextIntArray(n);
                int b[] = new int[n];
                int i, flag = 0;

                for (i = 0; i < n; i++) {
                    b[i] = in.nextInt() - a[i];
                }
                if (n == 1) {
                    if (b[0] == 0) {
                        out.println("TAK");
                        continue;
                    } else {
                        out.println("NIE");
                        continue;
                    }
                }

                if (n == 2) {
                    if (b[0] == 0 && b[1] == 0) {
                        out.println("TAK");
                        continue;
                    } else {
                        out.println("NIE");
                        continue;
                    }
                }
                for (i = 0; i < n - 2; i++) {
                    if (b[i] == 0)
                        continue;
                    if (b[i] < 0) {
                        flag++;
                        break;
                    }
                    b[i + 1] -= 2 * (b[i]);
                    b[i + 2] -= 3 * (b[i]);
                    b[i] = 0;


                }

                if (b[n - 1] != 0 || b[n - 2] != 0)
                    flag++;

                if (flag == 0)
                    out.println("TAK");
                else
                    out.println("NIE");
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

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

