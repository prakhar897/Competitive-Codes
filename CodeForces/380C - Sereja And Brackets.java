import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
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
        Task380C solver = new Task380C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task380C {
        int t[];
        int o[];
        int c[];
        char arr[];

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String s = in.nextString();

            int n = s.length();
            int m = in.nextInt();
            arr = new char[n + 1];

            for (int i = 1; i <= n; i++) {
                arr[i] = s.charAt(i - 1);
            }

            int k = (int) (Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) + 1));
            t = new int[k];
            o = new int[k];
            c = new int[k];

            build(1, 1, n);

            //out.println(t);
            //out.println(o);
            //out.println(c);

            for (int i = 0; i < m; i++) {
                int l = in.nextInt();
                int r = in.nextInt();

                Node ans = ret(l, r, 1, 1, n);
                out.println(ans.t);
            }
        }

        void build(int id, int l, int r) {
            if (r == l) {
                if (arr[l] == '(') {
                    o[id] = 1;
                } else if (arr[l] == ')') {
                    c[id] = 1;
                } else {
                    System.out.println("error");
                }
                return;
            }

            int mid = (l + r) / 2;
            build(2 * id, l, mid);
            build(2 * id + 1, mid + 1, r);

            int temp = Math.min(o[2 * id], c[(2 * id) + 1]);
            o[id] = o[2 * id] + o[(2 * id) + 1] - temp;
            c[id] = c[2 * id] + c[(2 * id) + 1] - temp;
            t[id] = t[2 * id] + t[(2 * id) + 1] + (2 * temp);
        }

        Node ret(int x, int y, int id, int l, int r) {
            //System.out.println("querying " + l + " " + r);
            if (y < l || r < x) {
                return new Node(0, 0, 0);
            }
            if (x <= l && r <= y) {
                return new Node(t[id], o[id], c[id]);
            }

            int mid = (l + r) / 2;
            Node a = ret(x, y, 2 * id, l, mid);
            Node b = ret(x, y, (2 * id) + 1, mid + 1, r);
            int temp, o, c, t;
            temp = Math.min(a.o, b.c);
            o = a.o + b.o - temp;
            c = a.c + b.c - temp;
            t = a.t + b.t + (2 * temp);

            return new Node(t, o, c);
        }

        class Node {
            int t;
            int o;
            int c;

            public Node(int t, int o, int c) {
                this.t = t;
                this.o = o;
                this.c = c;
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
}

