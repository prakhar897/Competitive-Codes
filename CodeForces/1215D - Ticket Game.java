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
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        DTicketGame solver = new DTicketGame();
        solver.solve(1, in, out);
        out.close();
    }

    static class DTicketGame {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            char arr[] = in.nextString().toCharArray();
            int i, l = 0, r = 0, suml = 0, sumr = 0;

            for (i = 0; i < n / 2; i++) {
                if (arr[i] == '?')
                    l++;
                else
                    suml += arr[i] - '0';
            }

            for (i = n / 2; i < n; i++) {
                if (arr[i] == '?')
                    r++;
                else
                    sumr += arr[i] - '0';
            }

            int moves = l + r;

            for (i = 0; i < moves; i++) {
                if (i % 2 == 0) // monocarp moves
                {
                    if (suml == sumr) {
                        if (l != 0 && r != 0) {
                            if (l > r)
                                r--;
                            else
                                l--;
                        } else if (l != 0) {
                            l--;
                            suml += 9;
                        } else {
                            r--;
                            sumr += 9;
                        }
                    } else if (suml > sumr) {
                        if (l != 0) {
                            l--;
                            suml += 9;
                        } else {
                            r--;
                            if (sumr + 9 > suml)
                                sumr += 9;
                        }
                    } else {
                        if (r != 0) {
                            r--;
                            sumr += 9;
                        } else {
                            l--;
                            if (suml + 9 > sumr)
                                suml += 9;
                        }
                    }
                } else //bicarp moves
                {
                    if (suml == sumr) {
                        if (l != 0 && r != 0) {
                            if (l > r)
                                l--;
                            else
                                r--;
                        } else if (l != 0) {
                            l--;
                        } else {
                            r--;
                        }
                    } else if (suml > sumr) {
                        if (r != 0) {
                            r--;
                            sumr += Math.min(9, suml - sumr);
                        } else {
                            l--;
                        }
                    } else {
                        if (l != 0) {
                            l--;
                            suml += Math.min(9, sumr - suml);
                        } else {
                            r--;
                        }
                    }
                }
            }

            if (suml == sumr)
                out.println("Bicarp");
            else
                out.println("Monocarp");
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

