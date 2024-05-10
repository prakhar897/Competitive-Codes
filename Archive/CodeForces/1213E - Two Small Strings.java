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
        Task582E solver = new Task582E();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task582E {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String arr[] = {"abc", "acb", "bac", "bca", "cab", "cba"};
            int n = in.nextInt();
            String s = in.nextString();
            String t = in.nextString();
            int i, j;

            outer:
            for (i = 0; i < 6; i++) {
                for (j = 0; j < 3; j++) {
                    String cur = arr[i].charAt(j) + "" + arr[i].charAt((j + 1) % 3);
                    if (cur.equals(s) || cur.equals(t)) {
                        continue outer;
                    }
                }

                break;
            }

            if (i == 6) {
                if (n == 1) {
                    if (s.charAt(0) == t.charAt(0)) {
                        for (i = 0; i < 6; i++) {
                            if (arr[i].charAt(2) == s.charAt(0)) {
                                out.println("YES");
                                out.println(arr[i]);
                                return;
                            }
                        }
                    }

                    if (s.charAt(1) == t.charAt(1)) {
                        for (i = 0; i < 6; i++) {
                            if (arr[i].charAt(0) == s.charAt(1)) {
                                out.println("YES");
                                out.println(arr[i]);
                                return;
                            }
                        }
                    }

                    if (s.charAt(0) == t.charAt(1) && s.charAt(1) == t.charAt(0)) {
                        for (i = 0; i < 6; i++) {
                            if (arr[i].charAt(1) != s.charAt(1) && arr[i].charAt(1) != s.charAt(0)) {
                                out.println("YES");
                                out.println(arr[i]);
                                return;
                            }
                        }
                    }
                }

                if (s.charAt(0) == t.charAt(0)) {
                    StringBuilder ans = new StringBuilder();
                    for (i = 0; i < n; i++)
                        ans.append(s.charAt(1));
                    for (i = 0; i < n; i++)
                        ans.append(t.charAt(1));
                    for (i = 0; i < n; i++)
                        ans.append(s.charAt(0));

                    out.println("YES");
                    out.println(ans.toString());
                    return;
                }

                if (s.charAt(1) == t.charAt(1)) {
                    StringBuilder ans = new StringBuilder();
                    for (i = 0; i < n; i++)
                        ans.append(s.charAt(1));
                    for (i = 0; i < n; i++)
                        ans.append(t.charAt(0));
                    for (i = 0; i < n; i++)
                        ans.append(s.charAt(0));

                    out.println("YES");
                    out.println(ans.toString());
                    return;
                }

                if (s.charAt(0) == t.charAt(1) && s.charAt(1) == t.charAt(0)) {
                    StringBuilder ans = new StringBuilder();
                    for (i = 0; i < n; i++)
                        ans.append(s.charAt(0));
                    if (s.charAt(0) != 'a' && s.charAt(1) != 'a')
                        for (i = 0; i < n; i++)
                            ans.append('a');
                    if (s.charAt(0) != 'b' && s.charAt(1) != 'b')
                        for (i = 0; i < n; i++)
                            ans.append('b');
                    if (s.charAt(0) != 'c' && s.charAt(1) != 'c')
                        for (i = 0; i < n; i++)
                            ans.append('c');
                    for (i = 0; i < n; i++)
                        ans.append(s.charAt(1));

                    out.println("YES");
                    out.println(ans.toString());
                    return;
                }

            }

            StringBuilder ans = new StringBuilder();
            for (j = 0; j < 3 * n; j++) {
                ans.append(arr[i].charAt((j % 3)));
            }

            out.println("YES");
            out.println(ans.toString());
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

