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
        CPaintTheDigits solver = new CPaintTheDigits();
        solver.solve(1, in, out);
        out.close();
    }

    static class CPaintTheDigits {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            loop:
            while (test-- > 0) {
                int n = in.nextInt();
                String s = in.nextString();
                int arr[] = new int[n];
                int i;

                for (i = 0; i < n; i++) {
                    arr[i] = (int) s.charAt(i) - '0';
                }

                for (i = 0; i <= 9; i++) {
                    String ans = make(arr, i, n);
                    if (check(arr, ans, n)) {
                        out.println(ans);
                        continue loop;
                    }
                    //out.println(ans);
                }
                out.println("-");
            }
        }

        String make(int arr[], int x, int n) {
            int flag = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (arr[i] < x)
                    sb.append("1");
                else if (arr[i] > x) {
                    sb.append("2");
                    flag = 1;
                } else {
                    if (flag == 0)
                        sb.append("2");
                    else
                        sb.append("1");
                }

            }

            return sb.toString();
        }

        boolean check(int arr[], String ans, int n) {
            int arr2[] = new int[n];
            int j = 0;
            for (int i = 0; i < n; i++) {
                if (ans.charAt(i) == '1') {
                    arr2[j] = arr[i];
                    j++;
                }
            }

            for (int i = 0; i < n; i++) {
                if (ans.charAt(i) == '2') {
                    arr2[j] = arr[i];
                    j++;
                }
            }

            for (int i = 1; i < n; i++) {
                if (arr2[i] < arr2[i - 1])
                    return false;
            }
            return true;
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

