import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        Task530C solver = new Task530C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task530C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String str = in.nextString();
            char arr[] = str.toCharArray();
            int i;
            int k = in.nextInt();
            int n = str.length();
            int lc = 0, sc = 0, cc = 0;

            for (i = 0; i < n; i++) {
                if (arr[i] == '*')
                    sc++;
                else if (arr[i] == '?')
                    cc++;
                else
                    lc++;
            }

            if (k < lc - (sc + cc)) {
                out.println("Impossible");
                return;
            }

            if (sc == 0 && k > lc) {
                out.println("Impossible");
                return;
            }

            StringBuilder ans = new StringBuilder();

            if (lc > k) {
                int diff = lc - k;
                for (i = 0; i < n; i++) {
                    if ((arr[i] == '*' || arr[i] == '?') && diff > 0) {
                        ans.setLength(ans.length() - 1);
                        diff--;
                    } else if (arr[i] == '?' || arr[i] == '*') {
                        continue;
                    } else {
                        ans.append(arr[i]);
                    }
                }
            }

            if (lc <= k) {
                int diff = k - lc;
                for (i = 0; i < n; i++) {
                    if (arr[i] == '*' && diff > 0) {
                        while (diff > 0) {
                            ans.append(arr[i - 1]);
                            diff--;
                        }
                    } else if (arr[i] == '?' || arr[i] == '*') {
                        continue;
                    } else {
                        ans.append(arr[i]);
                    }
                }
            }

            out.println(ans);
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

