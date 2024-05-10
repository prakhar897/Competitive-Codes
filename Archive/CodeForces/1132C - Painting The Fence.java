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
import java.util.ArrayList;
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
        Task61C solver = new Task61C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task61C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            int mat[][] = new int[q][2];
            ArrayList<Integer> arr[] = new ArrayList[n];
            int i, j, ans = 0, total = 0, min = 999999;

            for (i = 0; i < n; i++)
                arr[i] = new ArrayList<>();

            for (i = 0; i < q; i++) {
                mat[i][0] = in.nextInt() - 1;
                mat[i][1] = in.nextInt() - 1;

                for (j = mat[i][0]; j <= mat[i][1]; j++) {
                    if (arr[j].size() < 4)
                        arr[j].add(i);
                }
            }

            int count[][] = new int[q][q];

            //out.println(arr);

            for (i = 0; i < n; i++) {
                if (arr[i].size() > 0)
                    total++;
                if (arr[i].size() == 2) {
                    int a = arr[i].get(0);
                    int b = arr[i].get(1);
                    count[a][b]++;
                    count[b][a]++;
                } else if (arr[i].size() == 1) {
                    int a = arr[i].get(0);

                    for (j = 0; j < q; j++) {
                        count[j][a]++;
                        count[a][j]++;
                    }
                }
            }

            //out.print(count);

            for (i = 0; i < q; i++)
                for (j = i + 1; j < q; j++)
                    min = Math.min(min, count[i][j]);

            ans = total - min;
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

