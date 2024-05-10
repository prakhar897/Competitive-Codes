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
        ARRA solver = new ARRA();
        solver.solve(1, in, out);
        out.close();
    }

    static class ARRA {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            while (test-- > 0) {
                int n = in.nextInt();
                int k = in.nextInt();
                int size = 100009;
                //int size = 20;
                int ar[] = new int[size];
                int arr[] = new int[size];
                int karr[] = new int[size];
                int kgarr[] = new int[size];
                int i;
                int mat[][] = new int[n][2];

                for (i = 0; i < n; i++) {
                    int a = in.nextInt();
                    mat[i][0] = a;
                    int b = in.nextInt();
                    mat[i][1] = b;
                    ar[a]++;
                    ar[b + 1]--;
                }
                //out.println(ar);

                for (i = 1; i < size; i++) {
                    arr[i] = arr[i - 1] + ar[i];
                }
                //out.println(arr);

                for (i = 1; i < size; i++) {
                    karr[i] = karr[i - 1];
                    kgarr[i] = kgarr[i - 1];
                    if (arr[i] == k)
                        karr[i]++;
                    if (arr[i] == k + 1)
                        kgarr[i]++;
                }
                //out.println(karr);
                //out.println(kgarr);
                int dig = Integer.MIN_VALUE;
                for (i = 0; i < n; i++) {
                    int cur = 0;
                    cur -= (karr[mat[i][1]] - karr[mat[i][0] - 1]);
                    cur += (kgarr[mat[i][1]] - kgarr[mat[i][0] - 1]);
                    //out.println(cur);
                    if (cur > dig) {
                        dig = cur;
                    }
                }
                out.println(Math.max(0, karr[size - 1] + dig));
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

        public void close() {
            writer.close();
        }

        public void println(int i) {
            writer.println(i);
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

