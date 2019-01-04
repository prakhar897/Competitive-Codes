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
        Task446C solver = new Task446C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task446C {
        int even[];
        int odd[];
        int arr[];

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            arr = in.nextIntArray(n);
            int k = 2 * n + 4;
            even = new int[k];
            odd = new int[k];

            int q = in.nextInt();
            int i;

            build(1, 1, n);

            //out.println(even);
            //out.println(odd);

            for (i = 0; i < q; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int z = in.nextInt();

                if (x == 0) {
                    if ((z ^ arr[y - 1]) % 2 == 0)
                        continue;
                    modify(y, z, 1, 1, n);
                } else {
                    Node ans = ret(y, z, 1, 1, n);
                    if (x == 1)
                        out.println(ans.even);
                    else
                        out.println(ans.odd);
                }
            }

            //out.println(even);
            //out.println(odd);

        }

        void build(int id, int l, int r) {
            //System.out.println(l + " " +r);
            if (l == r) {
                if (arr[l - 1] % 2 == 0) {
                    even[id] = 1;
                } else {
                    odd[id] = 1;
                }
                return;
            }

            int mid = (l + r) / 2;
            build(2 * id, l, mid);
            build((2 * id) + 1, mid + 1, r);

            even[id] = even[2 * id] + even[(2 * id) + 1];
            odd[id] = odd[2 * id] + odd[(2 * id) + 1];
        }

        void modify(int x, int y, int id, int l, int r) {

            if (y % 2 == 0) {
                even[id]++;
                odd[id]--;
            } else {
                even[id]--;
                odd[id]++;
            }

            if (l == r) {
                arr[l-1] = y;
                return;
            }

            int mid = (l + r) / 2;
            if (x <= mid) {
                modify(x, y, 2 * id, l, mid);
            } else {
                modify(x, y, (2 * id) + 1, mid + 1, r);
            }
        }

        Node ret(int x, int y, int id, int l, int r) {
            if (x <= l && r <= y) {
                return new Node(even[id], odd[id]);
            }

            if (y < l || r < x) {
                return new Node(0, 0);
            }

            int mid = (l + r) / 2;
            Node a = ret(x, y, 2 * id, l, mid);
            Node b = ret(x, y, (2 * id) + 1, mid + 1, r);

            a.even += b.even;
            a.odd += b.odd;

            return a;
        }

        class Node {
            int even = 0;
            int odd = 0;

            public Node(int e, int o) {
                even = e;
                odd = o;
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

