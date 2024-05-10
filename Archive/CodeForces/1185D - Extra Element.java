import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Set;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Collections;
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
        Task568D solver = new Task568D();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task568D {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            if (n <= 3) {
                out.println(1);
                return;
            }

            ArrayList<Long> al = new ArrayList<>();
            HashMap<Long, Integer> hm = new HashMap<>();
            long arr[] = new long[n];
            int i;

            for (i = 0; i < n; i++) {
                long a = in.nextLong();
                al.add(a);
                arr[i] = a;
            }

            Collections.sort(al);

            for (i = 1; i < n; i++) {
                long diff = al.get(i) - al.get(i - 1);
                hm.merge(diff, 1, (x, y) -> (x + y));
            }
            //out.println(hm);

            int l = hm.keySet().size();
            if (l == 1) {
                int ans = search(arr, al.get(n - 1));
                out.println(ans);
                return;
            }

            long d11 = al.get(1) - al.get(0);
            int v11 = hm.get(d11);
            if (v11 == 1)
                hm.remove(d11);
            else
                hm.put(d11, v11 - 1);
            if (hm.keySet().size() == 1) {
                int ans = search(arr, al.get(0));
                out.println(ans);
                return;
            } else {
                hm.merge(d11, 1, (x, y) -> (x + y));
            }
            for (i = 1; i < n - 1; i++) {
                long d1 = al.get(i) - al.get(i - 1);
                long d2 = al.get(i + 1) - al.get(i);
                long d3 = al.get(i + 1) - al.get(i - 1);
                int v1 = hm.get(d1);
                if (v1 == 1)
                    hm.remove(d1);
                else
                    hm.put(d1, v1 - 1);

                int v2 = hm.get(d2);
                if (v2 == 1)
                    hm.remove(d2);
                else
                    hm.put(d2, v2 - 1);
                hm.merge(d3, 1, (x, y) -> (x + y));
                //out.println(hm);
                if (hm.keySet().size() == 1) {
                    int ans = search(arr, al.get(i));
                    out.println(ans);
                    return;
                } else {
                    hm.merge(d1, 1, (x, y) -> (x + y));
                    hm.merge(d2, 1, (x, y) -> (x + y));
                    int v3 = hm.get(d3);
                    if (v3 == 1)
                        hm.remove(d3);
                    else
                        hm.put(d3, v3 - 1);
                }
            }
            d11 = al.get(n - 1) - al.get(n - 2);
            v11 = hm.get(d11);
            if (v11 == 1)
                hm.remove(d11);
            else
                hm.put(d11, v11 - 1);
            if (hm.keySet().size() == 1) {
                int ans = search(arr, al.get(n - 1));
                out.println(ans);
                return;
            } else {
                hm.merge(d11, 1, (x, y) -> (x + y));
            }
            out.println(-1);
        }

        int search(long arr[], long a) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == a)
                    return i + 1;
            }
            return -1;
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

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
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
}

