import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
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
        Task568B solver = new Task568B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task568B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int q = in.nextInt();
            y:
            while (q-- > 0) {
                char a[] = in.nextString().toCharArray();
                char b[] = in.nextString().toCharArray();
                int n = a.length, m = b.length;
                Queue<Integer> q1 = new LinkedList<>();
                Queue<Character> q2 = new LinkedList<>();

                Queue<Integer> q3 = new LinkedList<>();
                Queue<Character> q4 = new LinkedList<>();
                int i = 0, j = 0;
                int ac = 1;
                char cura = a[0];
                for (i = 1; i < n; i++) {
                    if (a[i] == cura)
                        ac++;
                    else {
                        q1.offer(ac);
                        q2.offer(cura);
                        cura = a[i];
                        ac = 1;
                    }
                }

                q1.offer(ac);
                q2.offer(cura);
                ac = 1;
                cura = b[0];
                for (i = 1; i < m; i++) {
                    if (b[i] == cura)
                        ac++;
                    else {
                        q3.offer(ac);
                        q4.offer(cura);
                        cura = b[i];
                        ac = 1;
                    }
                }

                q3.offer(ac);
                q4.offer(cura);

            /*out.println(q1);
            out.println(q2);
            out.println(q3);
            out.println(q4);*/

                if (q2.size() != q4.size()) {
                    out.println("NO");
                    continue y;
                }
                int flag = 0;
                while (!q2.isEmpty()) {
                    char c = q2.poll();
                    char d = q4.poll();
                    int v = q1.poll();
                    int f = q3.poll();
                    if (c != d || v > f)
                        flag++;
                }

                if (flag == 0)
                    out.println("YES");
                else
                    out.println("NO");


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

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

