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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int q = (2 * n) - 2;

            if (n == 2) {
                out.println("PS");
                return;
            }

            String arr[] = new String[q];
            int i, j, flag = 0, cou = 0;
            String pref = "", suff = "", str = "";

            for (i = 0; i < q; i++) {
                arr[i] = in.nextString();
                if (arr[i].length() == n - 1 && flag == 0) {
                    pref = arr[i];
                    flag++;
                } else if (arr[i].length() == n - 1 && flag == 1)
                    suff = arr[i];
            }

            if (pref.charAt(n - 2) == suff.charAt(n - 3)) {

                str = pref + suff.charAt(n - 2);
            } else {
                str = suff + pref.charAt(n - 2);
            }

            //out.println(str);

            char ans[] = new char[q];

            all:
            for (i = 0; i < q; i++) {
                for (j = i + 1; j < q; j++) {
                    if (arr[i].length() == arr[j].length()) {
                        //boolean a = ispref(str,arr[i]);
                        boolean b = issuff(str, arr[i]);
                        //boolean c = ispref(str,arr[j]);
                        boolean d = issuff(str, arr[j]);

                        if (b == false && d == false) {
                            break all;
                        } else if (b == false) {
                            ans[i] = 'P';
                            ans[j] = 'S';
                            cou++;
                            break;
                        } else {
                            ans[i] = 'S';
                            ans[j] = 'P';
                            cou++;
                            break;
                        }
                    }
                }
            }

            if (cou != n - 1) {
                str = suff + pref.charAt(n - 2);
                all:
                for (i = 0; i < q; i++) {
                    for (j = i + 1; j < q; j++) {
                        if (arr[i].length() == arr[j].length()) {
                            //boolean a = ispref(str,arr[i]);
                            boolean b = issuff(str, arr[i]);
                            //boolean c = ispref(str,arr[j]);
                            boolean d = issuff(str, arr[j]);

                            if (b == false && d == false) {
                                break all;
                            } else if (b == false) {
                                ans[i] = 'P';
                                ans[j] = 'S';
                                cou++;
                                break;
                            } else {
                                ans[i] = 'S';
                                ans[j] = 'P';
                                cou++;
                                break;
                            }
                        }
                    }
                }
            }

            out.println(ans);
        }

        boolean issuff(String str, String pre) {
            int n = str.length();
            int m = pre.length();
            int j, i = 0;

            for (j = m - 1; j >= 0; j--) {
                if (str.charAt(n - 1 - i) != pre.charAt(j))
                    break;
                i++;
            }

            if (j == -1)
                return true;
            else return false;
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

        public void println(char[] array) {
            writer.println(array);
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

