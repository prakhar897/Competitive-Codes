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
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int a = in.nextInt();
            int b = in.nextInt();
            int x = in.nextInt();
            int n = a + b;
            int arr[] = new int[n];
            int i;
            int m = Math.max(a, b);
            if (m == a) {
                arr[0] = 0;
                a--;
            } else if (m == b) {
                arr[0] = 1;
                b--;
            }
            for (i = 1; i < n; i++) {
                if (arr[i - 1] == 1 && x > 1) {
                    arr[i] = 0;
                    a--;
                    x--;
                } else if (arr[i - 1] == 0 && x > 1) {
                    arr[i] = 1;
                    b--;
                    x--;
                } else if (x == 1 && arr[i - 1] == 0 && a > 0) {
                    arr[i] = 0;
                    a--;
                } else if (x == 1 && arr[i - 1] == 1 && b > 0) {
                    arr[i] = 1;
                    b--;
                } else if (x == 1 && arr[i - 1] == 0 && a == 0) {
                    arr[i] = 1;
                    b--;
                    x--;
                } else if (x == 1 && arr[i - 1] == 1 && b == 0) {
                    arr[i] = 0;
                    a--;
                    x--;
                } else if (x == 0 && arr[i - 1] == 0) {
                    arr[i] = 0;
                    a--;
                } else if (x == 0 && arr[i - 1] == 1) {
                    arr[i] = 1;
                    b--;
                }
            }

            for (i = 0; i < n; i++)
                out.print(arr[i] + "");
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

