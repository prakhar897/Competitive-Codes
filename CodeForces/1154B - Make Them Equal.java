import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        Task552B solver = new Task552B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task552B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            HashSet<Integer> hs = new HashSet<>();

            int arr[] = new int[n];
            int i;
            for (i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                hs.add(arr[i]);
            }
            Arrays.sort(arr);

            if (hs.size() > 3) {
                out.println(-1);
                return;
            }

            if (hs.size() == 3) {
                int num[] = new int[3];
                int k = 0;
                for (int s : hs) {
                    num[k] = s;
                    k++;
                }
                Arrays.sort(num);
                int mid = (num[0] + num[2]) / 2;
                if (mid != num[1] || (num[0] + num[2]) % 2 == 1) {
                    out.println(-1);
                    return;
                }

                out.println(mid - num[0]);
            } else if (hs.size() == 2) {
                int num[] = new int[2];
                int k = 0;
                for (int s : hs) {
                    num[k] = s;
                    k++;
                }
                Arrays.sort(num);
                int mid = (num[0] + num[1]) / 2;
                if ((num[0] + num[1]) % 2 == 0)
                    out.println(mid - num[0]);
                else
                    out.println(num[1] - num[0]);
            } else if (hs.size() == 1) {
                out.println(0);
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

