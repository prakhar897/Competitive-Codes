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
        Task529E solver = new Task529E();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task529E {
        int o[];
        int c[];
        char arr[];

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = (int) (Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) + 1));
            o = new int[k];
            c = new int[k];
            int count = 0;
            String s = in.nextString();
            int i;
            arr = new char[n + 1];
            for (i = 1; i < n + 1; i++)
                arr[i] = s.charAt(i - 1);
            //out.println(arr);
            build(1, 1, n);

            //out.println(o);
            //out.println(c);

            for (i = 1; i <= n; i++) {
                Node a1 = ret(1, i - 1, 1, 1, n);
                Node a2 = ret(i + 1, n, 1, 1, n);
                Node a3 = ret(i, i, 1, 1, n);

                //out.println("a1 = " + a1.o + " " + a1.c);
                //out.println("a2 = " + a2.o + " " + a2.c);
                //out.println("a3 = " + a3.o + " " + a3.c);

                if (a3.o == 1) {
                    a3.o = 0;
                    a3.c = 1;
                } else {
                    a3.o = 1;
                    a3.c = 0;
                }

                int temp = Math.min(a1.o, a3.c);
                a1.o = a1.o + a3.o - temp;
                a1.c = a1.c + a3.c - temp;

                temp = Math.min(a1.o, a2.c);
                a1.o = a1.o + a2.o - temp;
                a1.c = a1.c + a2.c - temp;

                if (a1.o == 0 && a1.c == 0) {
                    //out.println("i is " + i);
                    count++;
                }
            }

            out.println(count);
        }

        void build(int id, int l, int r) {
            //System.out.println(l + " " + r);
            if (l == r) {
                if (arr[l] == '(') {
                    o[id] = 1;
                } else {
                    c[id] = 1;
                }
                return;
            }

            int mid = (l + r) / 2;
            build(2 * id, l, mid);
            build((2 * id) + 1, mid + 1, r);

            int temp = Math.min(o[2 * id], c[(2 * id) + 1]);
            o[id] = o[2 * id] + o[(2 * id) + 1] - temp;
            c[id] = c[2 * id] + c[(2 * id) + 1] - temp;

        }

        Node ret(int x, int y, int id, int l, int r) {
            if (x <= l && r <= y) {
                return new Node(o[id], c[id]);
            } else if (l > y || r < x) {
                return new Node(0, 0);
            } else {
                int mid = (l + r) / 2;
                Node a = ret(x, y, 2 * id, l, mid);
                Node b = ret(x, y, (2 * id) + 1, mid + 1, r);

                int temp = Math.min(a.o, b.c);
                a.o += b.o - temp;
                a.c += b.c - temp;

                return a;
            }
        }

        class Node {
            int o;
            int c;

            public Node(int a, int b) {
                o = a;
                c = b;
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

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
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

