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
        Task339D solver = new Task339D();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task339D {
        int arr[];
        int tree[];

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int size = (int) Math.pow(2, n);
            int size2 = (size * 2);
            arr = in.nextIntArray(size);
            tree = new int[size2];
            int op = 0, i;
            if (n % 2 == 0)
                op = 1;
            build(1, size, op);
            //out.println(tree);

            for (i = 0; i < m; i++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt();
                arr[a] = b;
                op = 0;
                int pos = size + a;
                tree[pos] = b;
                pos /= 2;
                while (pos != 0) {
                    if (op == 0)
                        tree[pos] = tree[2 * pos] | tree[(2 * pos) + 1];
                    else
                        tree[pos] = tree[2 * pos] ^ tree[(2 * pos) + 1];
                    pos /= 2;

                    if (op == 0)
                        op = 1;
                    else
                        op = 0;
                }
                //out.println(tree);
                out.println(tree[1]);
            }
        }

        void build(int i, int size, int op) {
            if (i >= size) {
                int k = i % size;
                tree[i] = arr[k];
                return;
            }

            int op2 = 0;
            if (op == 0)
                op2 = 1;
            build(2 * i, size, op2);
            build((2 * i) + 1, size, op2);

            if (op == 0)
                tree[i] = tree[2 * i] | tree[(2 * i) + 1];
            else
                tree[i] = tree[2 * i] ^ tree[(2 * i) + 1];
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

