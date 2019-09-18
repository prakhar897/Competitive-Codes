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
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        EMarbles solver = new EMarbles();
        solver.solve(1, in, out);
        out.close();
    }

    static class EMarbles {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int arr[] = in.nextIntArray(n);
            int i, j;

            long count[][] = new long[20][20];
            ArrayList<Integer> col[] = new ArrayList[20];
            long dp[] = new long[1 << 20 + 1];

            for (i = 0; i < 20; i++) {
                col[i] = new ArrayList<>();
            }

            for (i = 0; i < n; i++) {
                col[arr[i] - 1].add(i);
            }

            for (i = 0; i < 20; i++) {
                for (j = 0; j < 20; j++) {
                    if (i == j) continue;
                    if (col[i].size() == 0 || col[j].size() == 0)
                        continue;

                    int pos2 = 0;
                    for (int pos1 = 0; pos1 < col[i].size(); pos1++) {
                        while (true) {
                            if (pos2 == col[j].size() - 1 || col[j].get(pos2 + 1) > col[i].get(pos1)) {
                                break;
                            }
                            pos2++;
                        }
                        if (col[i].get(pos1) > col[j].get(pos2)) {
                            count[i][j] += pos2 + 1;
                        }
                    }
                }
            }

            for (i = 0; i < 1 << 20; i++)
                dp[i] = Long.MAX_VALUE - 2;
            dp[0] = 0;

        /*for(i=0;i<20;i++)
            out.println(count[i]);*/

            for (int mask = 0; mask < 1 << 20; mask++) {
                ArrayList<Integer> was = new ArrayList<>();
                for (i = 0; i < 20; i++) {
                    if ((mask & (1 << i)) != 0)
                        was.add(i);
                }

                for (i = 0; i < 20; i++) {
                    if ((mask & (1 << i)) != 0)
                        continue;
                    long sum = 0;
                    for (j = 0; j < was.size(); j++) {
                        sum += count[was.get(j)][i];
                    }
                    int nmask = mask | (1 << i);
                    dp[nmask] = Math.min(dp[nmask], dp[mask] + sum);
                }
            }

            // out.println(dp);

            out.println(dp[(int) (1 << 20) - 1]);
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

        public void println(long i) {
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

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

