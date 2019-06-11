import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.Map;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.HashMap;
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
        AnkL solver = new AnkL();
        solver.solve(1, in, out);
        out.close();
    }

    static class AnkL {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            long mat[][] = new long[n - 1][3];
            long mod = 1000000007;
            int i, j;
            long ans = 1;

            for (i = 0; i < n - 1; i++) {
                for (j = 0; j < 3; j++) {
                    mat[i][j] = in.nextLong();
                }
            }

            Arrays.sort(mat, (long[] o1, long[] o2) -> {
                if (o1[2] - o2[2] > 0)
                    return 1;
                else if (o1[2] - o2[2] < 0)
                    return -1;
                else
                    return 0;
            });

            // out.print(mat);

            DisjointSet dsu = new DisjointSet();

            for (i = 1; i <= n; i++)
                dsu.makeSet(i);

            for (i = 0; i < n - 1; i++) {
                Node a = dsu.findSet(mat[i][0]);
                Node b = dsu.findSet(mat[i][1]);
                long g = 1l * a.rank * b.rank;
                long cur = IntegerUtils.power(mat[i][2], g, mod);
                ans *= cur;
                ans %= mod;
                // out.println(mat[i][2] + " " + g + " " + cur);

                dsu.union(mat[i][0], mat[i][1]);
            }
            out.println(ans);
        }

        class DisjointSet {
            private Map<Long, Node> map = new HashMap<>();

            public void makeSet(long data) {
                Node node = new Node();
                node.data = data;
                node.parent = node;
                node.rank = 1;
                map.put(data, node);
            }

            public boolean union(long data1, long data2) {
                Node node1 = map.get(data1);
                Node node2 = map.get(data2);

                Node parent1 = findSet(node1);
                Node parent2 = findSet(node2);

                if (parent1.data == parent2.data) {
                    return false;
                } else {
                    parent2.parent = parent1;
                    parent1.rank += parent2.rank;
                    parent2.rank = 0;
                }
                return true;
            }

            public Node findSet(long data) {
                return findSet(map.get(data));
            }

            private Node findSet(Node node) {
                Node parent = node.parent;
                if (parent == node) {
                    return parent;
                }
                node.parent = findSet(node.parent);
                return node.parent;
            }

        }

        class Node {
            long data;
            Node parent;
            int rank;

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

    static class IntegerUtils {
        public static long power(long base, long exponent, long mod) {
            if (base >= mod) {
                base %= mod;
            }
            if (exponent == 0) {
                return 1 % mod;
            }
            long result = power(base, exponent >> 1, mod);
            result = result * result % mod;
            if ((exponent & 1) != 0) {
                result = result * base % mod;
            }
            return result;
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
}

