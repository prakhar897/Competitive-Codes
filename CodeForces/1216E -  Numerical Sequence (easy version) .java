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
 *
 * @author prakhar897
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        E1NumericalSequenceEasyVersion solver = new E1NumericalSequenceEasyVersion();
        solver.solve(1, in, out);
        out.close();
    }

    static class E1NumericalSequenceEasyVersion {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int q = in.nextInt();
            int k = 100000;
            long arr[] = new long[k];
            long sum[] = new long[k];
            arr[1] = 1;
            sum[1] = 1;
            int i;
            for (i = 2; i < k; i++) {
                arr[i] = arr[i - 1] + (i + "").length();
                sum[i] = sum[i - 1] + arr[i];
            }
            //out.println(arr);
            //out.println(sum);

            while (q-- > 0) {
                int val = in.nextInt();
                for (i = 0; i < k; i++) {
                    if (sum[i] >= val)
                        break;
                }
                i--;
                //out.println(sum[i]);
                val -= sum[i];
                //out.println(val);
                int val2 = val;
                for (i = 1; i <= val; i++) {
                    val2 -= (i + "").length();
                    if (val2 <= 0)
                        break;
                }

                val2 += (i + "").length();
                //out.println(val2);
                String f = i + "";
                //out.println(f);
                out.println(f.charAt(val2 - 1));
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

        public void println(char i) {
            writer.println(i);
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

