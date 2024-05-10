import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.HashMap;
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
        Task510A solver = new Task510A();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task510A {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int i, a, ans;
            boolean arr[] = new boolean[3];
            HashMap<String, Integer> hm = new HashMap<String, Integer>();
            for (i = 0; i < n; i++) {
                int cost = in.nextInt();
                String str = in.nextString();
                for (int j = 0; j < str.length(); j++) {
                    int y = str.charAt(j) - 65;
                    if (arr[y] == false)
                        arr[y] = true;
                }
                if (str.length() == 3)
                    str = "ABC";
                else if (str.equals("BA"))
                    str = "AB";
                else if (str.equals("CB"))
                    str = "BC";
                else if (str.equals("CA"))
                    str = "AC";

                if (hm.containsKey(str)) {
                    a = hm.get(str);
                    cost = Math.min(a, cost);
                }
                hm.put(str, cost);
            }

            for (i = 0; i < 3; i++) {
                if (arr[i] == false) {
                    out.println(-1);
                    return;
                }
            }

            int sum1 = hm.getOrDefault("A", 1000000) + hm.getOrDefault("B", 1000000) + hm.getOrDefault("C", 1000000);
            int sum3 = hm.getOrDefault("ABC", 1000000);
            ans = Math.min(sum1, sum3);

            int sum2 = hm.getOrDefault("AB", 1000000) + hm.getOrDefault("BC", 1000000);
            ans = Math.min(ans, sum2);
            int sum4 = hm.getOrDefault("AC", 1000000) + hm.getOrDefault("BC", 1000000);
            ans = Math.min(ans, sum4);
            int sum5 = hm.getOrDefault("AB", 1000000) + hm.getOrDefault("AC", 1000000);
            ans = Math.min(ans, sum5);

            int sum6 = hm.getOrDefault("AB", 1000000) + hm.getOrDefault("C", 1000000);
            ans = Math.min(ans, sum6);
            int sum7 = hm.getOrDefault("AC", 1000000) + hm.getOrDefault("B", 1000000);
            ans = Math.min(ans, sum7);
            int sum8 = hm.getOrDefault("BC", 1000000) + hm.getOrDefault("A", 1000000);
            ans = Math.min(ans, sum8);


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

