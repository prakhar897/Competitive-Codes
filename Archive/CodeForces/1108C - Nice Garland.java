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
        Task535C solver = new Task535C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task535C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            String st = in.nextString();
            int i, flag = 0;
            int v[] = new int[6];
            v[0] = find('R', 'G', 'B', n, st);
            v[1] = find('R', 'B', 'G', n, st);
            v[2] = find('G', 'R', 'B', n, st);
            v[3] = find('G', 'B', 'R', n, st);
            v[4] = find('B', 'G', 'R', n, st);
            v[5] = find('B', 'R', 'G', n, st);
            //out.println(v);

            int max = v[0];
            for (i = 1; i < 6; i++) {
                if (v[i] > max) {
                    max = v[i];
                    flag = i;
                }
            }
            flag++;

            out.println(n - max);
            String s = "";
            if (flag == 1)
                s = gen('R', 'G', 'B', n);
            if (flag == 2)
                s = gen('R', 'B', 'G', n);
            if (flag == 3)
                s = gen('G', 'R', 'B', n);
            if (flag == 4)
                s = gen('G', 'B', 'R', n);
            if (flag == 5)
                s = gen('B', 'G', 'R', n);
            if (flag == 6)
                s = gen('B', 'R', 'G', n);

            out.println(s);


        }

        int find(char a, char b, char d, int n, String s) {
            int ans = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (i % 3 == 0 && c == a)
                    ans++;
                if (i % 3 == 1 && c == b)
                    ans++;
                if (i % 3 == 2 && c == d)
                    ans++;
            }
            return ans;
        }

        String gen(char a, char b, char c, int n) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i % 3 == 0)
                    s.append(a);
                if (i % 3 == 1)
                    s.append(b);
                if (i % 3 == 2)
                    s.append(c);
            }

            return s.toString();
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

