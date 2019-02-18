import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        Task539B solver = new Task539B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task539B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int arr[] = new int[n];
            int i, sum = 0;

            for (i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                sum += arr[i];
            }
            Arrays.sort(arr);
            int fina = 0, diff = 0;
            for (i = 1; i < n; i++) {
                ArrayList<Integer> a = getfactor(arr[i]);
                int cs = arr[i] + arr[0];
                int ns = 0;
                for (int x : a) {
                    ns = (arr[i] / x) + (arr[0] * x);
                    diff = cs - ns;
                    fina = Math.max(fina, diff);
                }
            }

            sum -= fina;
            out.println(sum);
        }

        ArrayList<Integer> getfactor(int num) {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(1);
            int i;
            if (num != 1)
                ans.add(num);

            for (i = 2; i * i < num; i++) {
                if (num % i == 0) {
                    ans.add(i);
                    ans.add(num / i);
                }
            }

            if (num % i == 0)
                ans.add(i);
            return ans;
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

