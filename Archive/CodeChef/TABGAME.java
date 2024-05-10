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
        TaskSEPE solver = new TaskSEPE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskSEPE {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            while (test-- > 0) {
                String str = in.nextString();
                int m = str.length();
                String str2 = in.nextString();
                int n = str2.length();
                int i;

                char row1[] = new char[m + 1];
                char row2[] = new char[m + 1];
                char col1[] = new char[n + 1];
                char col2[] = new char[n + 1];

                if (str.charAt(0) == '1' && str2.charAt(0) == '1')
                    row1[1] = 'B';
                else
                    row1[1] = 'A';

                for (i = 2; i < m + 1; i++) {
                    if (str.charAt(i - 1) == '0' || row1[i - 1] == 'B')
                        row1[i] = 'A';
                    else
                        row1[i] = 'B';
                }

                if (n > 1) {
                    if (row1[1] == 'B' || str2.charAt(1) == '0')
                        row2[1] = 'A';
                    else
                        row2[1] = 'B';

                    for (i = 2; i < m + 1; i++) {
                        if (row1[i] == 'B' || row2[i - 1] == 'B')
                            row2[i] = 'A';
                        else
                            row2[i] = 'B';
                    }
                }


                col1[1] = row1[1];
                if (m > 1 && n > 1)
                    col2[1] = row1[2];

                for (i = 2; i < n + 1; i++) {
                    if (str2.charAt(i - 1) == '0' || col1[i - 1] == 'B')
                        col1[i] = 'A';
                    else
                        col1[i] = 'B';
                }

                if (m > 1) {
                    for (i = 2; i < n + 1; i++) {
                        if (col1[i] == 'B' || col2[i - 1] == 'B')
                            col2[i] = 'A';
                        else
                            col2[i] = 'B';
                    }
                }


                int q = in.nextInt();
                StringBuilder s = new StringBuilder();
                for (i = 0; i < q; i++) {
                    int a = in.nextInt();
                    int b = in.nextInt();

                    if (a == 1) {
                        s.append(row1[b]);
                    } else if (b == 1) {
                        s.append(col1[a]);
                    } else if (a == 2) {
                        s.append(row2[b]);
                    } else if (b == 2) {
                        s.append(col2[a]);
                    } else if (b > a) {
                        int y = b - a + 2;
                        s.append(row2[y]);
                    } else {
                        int y = a - b + 2;
                        s.append(col2[y]);
                    }
                }

                StringBuilder s2 = new StringBuilder();
                for (i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (c == 'A')
                        s2.append("1");
                    else
                        s2.append("0");
                }
                //out.println(row2[6]);
                out.println(s2);
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

