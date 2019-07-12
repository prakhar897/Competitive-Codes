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
        Task67B solver = new Task67B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task67B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            String s = in.nextString();
            int mat[][] = new int[n][26];
            int i, j;

            for (i = 0; i < n; i++) {
                if (i > 0) {
                    for (j = 0; j < 26; j++) {
                        mat[i][j] = mat[i - 1][j];
                    }
                }

                int c = (int) s.charAt(i) - 'a';
                mat[i][c]++;
            }

            //out.print(mat);

            int m = in.nextInt();
            for (i = 0; i < m; i++) {
                s = in.nextString();
                int arr[] = new int[26];
                for (j = 0; j < s.length(); j++) {
                    int c = (int) s.charAt(j) - 'a';
                    arr[c]++;
                }

                int ans = search(mat, arr, n);
                out.println(ans + 1);
            }
        }

        int search(int mat[][], int arr[], int n) {
            int low = 0;
            int high = n;
            int i, j, flag = 0;
            while (high - low > 1) {
                flag = 0;
                int mid = low + (high - low) / 2;
                for (i = 0; i < 26; i++) {
                    if (mat[mid][i] < arr[i]) {
                        flag = 1;
                        break;
                    }
                }

                if (flag == 0)
                    high = mid;
                else
                    low = mid;
            }

            flag = 0;
            for (i = 0; i < 26; i++) {
                if (mat[low][i] < arr[i]) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 0)
                return low;
            return high;
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

