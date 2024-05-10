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
        Task531D solver = new Task531D();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task531D {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            char arr[] = in.nextString().toCharArray();
            int i;
            int c1 = 0, c0 = 0, c2 = 0;
            int cc1 = 0, cc0 = 0, cc2 = 0;

            for (i = 0; i < n; i++) {
                if (arr[i] == '0')
                    c0++;
                else if (arr[i] == '1')
                    c1++;
                else
                    c2++;
            }

            int mid = n / 3;

            for (i = 0; i < n; i++) {
                if (arr[i] == '0')
                    cc0++;
                else if (arr[i] == '1')
                    cc1++;
                else
                    cc2++;

                if (c0 < mid) {
                    if (arr[i] == '1' && c1 > mid) {
                        arr[i] = '0';
                        c0++;
                        c1--;
                        cc0++;
                        cc1--;
                    } else if (arr[i] == '2' && c2 > mid) {
                        arr[i] = '0';
                        c0++;
                        c2--;
                        cc0++;
                        cc2--;
                    }
                } else if (c1 < mid) {
                    if (arr[i] == '0' && c0 > mid && cc0 > mid) {
                        arr[i] = '1';
                        c1++;
                        c0--;
                        cc1++;
                        cc0--;
                    } else if (arr[i] == '2' && c2 > mid) {
                        arr[i] = '1';
                        c1++;
                        c2--;
                        cc1++;
                        cc2--;
                    }
                } else if (c2 < mid) {
                    if (arr[i] == '0' && c0 > mid && cc0 > mid) {
                        arr[i] = '2';
                        c2++;
                        c0--;
                        cc2++;
                        cc0--;
                    } else if (arr[i] == '1' && c1 > mid && cc1 > mid) {
                        arr[i] = '2';
                        c2++;
                        c1--;
                        cc2++;
                        cc1--;
                    }
                }

            }

            StringBuilder ans = new StringBuilder();
            for (i = 0; i < n; i++) {
                ans.append(arr[i]);
            }

            out.println(ans);
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

