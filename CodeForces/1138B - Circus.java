import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Vector;
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
        Task545B solver = new Task545B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task545B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            String cc = in.nextString();
            String aa = in.nextString();

            Stack<Integer> p1 = new Stack<>();
            Stack<Integer> p2 = new Stack<>();
            Stack<Integer> p3 = new Stack<>();
            Stack<Integer> p4 = new Stack<>();
            ArrayList<Integer> ans = new ArrayList<>();
            int i, flag = 0;

            for (i = 0; i < n; i++) {
                if (cc.charAt(i) == '0' && aa.charAt(i) == '0')
                    p1.push(i + 1);
                else if (cc.charAt(i) == '0' && aa.charAt(i) == '1')
                    p2.push(i + 1);
                else if (cc.charAt(i) == '1' && aa.charAt(i) == '0')
                    p3.push(i + 1);
                else if (cc.charAt(i) == '1' && aa.charAt(i) == '1')
                    p4.push(i + 1);
            }

            int na = p1.size();
            int nb = p2.size();
            int nc = p3.size();
            int nd = p4.size();

            int a = 0, b = 0, c = 0, d = 0;

            out:
            for (b = 0; b <= nb; b++) {
                for (c = 0; c <= nc; c++) {
                    int v1 = b + c;
                    int v2 = nb + nd - v1;
                    int v3 = n - nb - nd - v1;

                    if (v2 % 2 == 0 && v3 % 2 == 0) {
                        d = v2 / 2;
                        a = v3 / 2;
                        if (a >= 0 && a <= na && d >= 0 && d <= nd) {
                            flag = 1;
                            break out;
                        }
                    }
                }
            }

            if (flag == 0) {
                out.println(-1);
                return;
            }

            while (a-- > 0) {
                ans.add(p1.pop());
            }

            while (b-- > 0) {
                ans.add(p2.pop());
            }
            while (c-- > 0) {
                ans.add(p3.pop());
            }
            while (d-- > 0) {
                ans.add(p4.pop());
            }

            for (int x : ans) {
                out.print(x + " ");
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

