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
        Task51A solver = new Task51A();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task51A {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            while (test-- > 0) {
                String s = in.nextString();
                StringBuilder str = new StringBuilder(s);
                int i, ll = 0, ul = 0, d = 0;
                String dig = "0", ucl = "A", lcl = "a";

                for (i = 0; i < s.length(); i++) {
                    char c = str.charAt(i);
                    if (c >= 48 && c <= 57) {
                        d++;
                    }

                    if (c >= 65 && c <= 90) {
                        ul++;

                    }

                    if (c >= 97 && c <= 122) {
                        ll++;
                    }
                }

                if ((d == 0 && ul == 0) || (d == 0 && ll == 0) || (ll == 0 && ul == 0)) {
                    if (d > 0)
                        str.replace(0, 2, "Aa");
                    if (ul > 0)
                        str.replace(0, 2, "a0");
                    if (ll > 0)
                        str.replace(0, 2, "A0");

                } else if (d == 0) {
                    if (ul > ll) {
                        for (i = 0; i < str.length(); i++) {
                            if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                                str.replace(i, i + 1, "0");
                                break;
                            }
                        }
                    } else {
                        for (i = 0; i < str.length(); i++) {
                            if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                                str.replace(i, i + 1, "0");
                                break;
                            }
                        }
                    }
                } else if (ul == 0) {
                    if (d > ll) {
                        for (i = 0; i < str.length(); i++) {
                            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                                str.replace(i, i + 1, "A");
                                break;
                            }
                        }
                    } else {
                        for (i = 0; i < str.length(); i++) {
                            if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                                str.replace(i, i + 1, "A");
                                break;
                            }
                        }
                    }
                } else if (ll == 0) {
                    if (ul > d) {
                        for (i = 0; i < str.length(); i++) {
                            if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                                str.replace(i, i + 1, "a");
                                break;
                            }
                        }
                    } else {
                        for (i = 0; i < str.length(); i++) {
                            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                                str.replace(i, i + 1, "a");
                                break;
                            }
                        }
                    }
                }
                out.println(str);
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

