import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
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
        Task416C solver = new Task416C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task416C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            triplet arr[] = new triplet[n];
            int i, j;
            for (i = 0; i < n; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                arr[i] = new triplet(a, b, i + 1);
            }

            Arrays.sort(arr, Collections.reverseOrder());
            //out.println(arr);

            int k = in.nextInt();
            int res[] = new int[k + 1];
            for (i = 1; i <= k; i++) {
                res[i] = in.nextInt();
            }

            long count = 0;
            long cost = 0;
            ArrayList<Integer> ans1 = new ArrayList<>();
            ArrayList<Integer> ans2 = new ArrayList<>();
            for (i = 0; i < n; i++) {
                int pos = -1;
                int val = arr[i].f;
                for (j = 1; j <= k; j++) {
                    if (res[j] >= val) {
                        if (pos == -1 || res[pos] > res[j])
                            pos = j;
                    }
                }
                if (pos != -1) {
                    res[pos] = -1;
                    count++;
                    cost += arr[i].s;
                    ans1.add(arr[i].t);
                    ans2.add(pos);
                }
            }

            out.println(count + " " + cost);
            for (i = 0; i < ans1.size(); i++) {
                out.println(ans1.get(i) + " " + ans2.get(i));
            }
        }

        class triplet implements Comparable {
            int f;
            int t;
            int s;

            triplet(int f, int s, int t) {
                this.f = f;
                this.s = s;
                this.t = t;
            }

            public boolean equals(Object o) {
                triplet ob = (triplet) o;
                int ff;
                int ss;
                int tt;
                if (o != null) {
                    ff = ob.f;
                    ss = ob.s;
                    tt = ob.t;
                    if ((ff == this.f) && (ss == this.s) && (tt == this.t))
                        return true;
                }
                return false;
            }

            public int hashCode() {
                return (this.f + " " + this.s + " " + this.t).hashCode();
            }

            public int compareTo(Object o) {
                triplet tr = (triplet) o;
                if (s > tr.s)
                    return 1;
                else
                    return -1;
            }

            public String toString() {
                return this.f + " " + this.s + " " + this.t;
            }

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

        public void close() {
            writer.close();
        }

    }
}

