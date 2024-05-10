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
import java.util.TreeMap;
import java.util.Map;
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
        Task1165F solver = new Task1165F();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task1165F {
        ArrayList<Pair<Integer, Integer>> al = new ArrayList<>();

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int arr[] = new int[n];
            int i, j, sum = 0;

            for (i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                sum += arr[i];
            }

            for (i = 0; i < m; i++) {
                int d = in.nextInt();
                int t = in.nextInt() - 1;
                al.add(new Pair(d, t));
            }

            int low = sum;
            int high = 2 * sum;

            while (high - low > 1) {
                int mid = (high + low) / 2;
                int cur = check(arr, n, mid, sum);
                if (cur == 0)
                    high = mid;
                else
                    low = mid;
            }

            if (check(arr, n, low, sum) == 0)
                out.println(low);
            else
                out.println(high);
        }

        int check(int arra[], int n, int total_money, int suma) {
            int arr[] = Arrays.copyOf(arra, n);
            int sum = suma;
            int day[] = new int[n];

            int i, j;
            for (Pair t : al) {
                int first = (int) t.getFirst();
                int second = (int) t.getSecond();
                if (first > total_money)
                    continue;
                day[second] = Math.max(day[second], first);
            }

            TreeMap<Integer, Integer> tm = new TreeMap<>();
            for (i = 0; i < n; i++) {
                if (day[i] != 0) {
                    tm.put(day[i], tm.getOrDefault(day[i], 0) + arr[i]);
                }
            }
            //System.out.println(total_money +" " +tm);
            int cur_money = 0;
            for (i = 1; i <= total_money; i++) {
                cur_money++;
                int val = Math.min(tm.getOrDefault(i, 0), cur_money);
                cur_money -= val;
                sum -= val;
            }

            cur_money /= 2;
            sum -= cur_money;
            sum = Math.max(0, sum);
            return sum;
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

    static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            super();
            this.first = first;
            this.second = second;
        }

        public int hashCode() {
            int hashFirst = first != null ? first.hashCode() : 0;
            int hashSecond = second != null ? second.hashCode() : 0;

            return (hashFirst + hashSecond) * hashSecond + hashFirst;
        }

        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair otherPair = (Pair) other;
                return
                        ((this.first == otherPair.first ||
                                (this.first != null && otherPair.first != null &&
                                        this.first.equals(otherPair.first))) &&
                                (this.second == otherPair.second ||
                                        (this.second != null && otherPair.second != null &&
                                                this.second.equals(otherPair.second))));
            }

            return false;
        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

    }
}

