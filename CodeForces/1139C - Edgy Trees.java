import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Map;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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
        Task548C solver = new Task548C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task548C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int mod = (int) 1e9 + 7;
            int i = 0;
            DisjointSet ds = new DisjointSet(1, n);
            HashMap<Integer, Integer> hm = new HashMap<>();

            for (i = 0; i < n - 1; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int x = in.nextInt();
                if (x == 0)
                    ds.union(u, v);
            }

            for (i = 1; i <= n; i++) {
                int u = (int) ds.findParent(i);
                hm.put(u, hm.getOrDefault(u, 0) + 1);
            }

            long ans = IntegerUtils.power(n, k, mod);
            for (int x : hm.keySet()) {
                int val = hm.get(x);
                ans -= IntegerUtils.power(val, k, mod);
                while (ans < 0)
                    ans += mod;
            }
            while (ans < 0)
                ans += mod;
            out.println(ans);

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

    static class DisjointSet {
        private Map<Long, Node> map = new HashMap<>();

        public DisjointSet(int p, int n) {
            for (int i = p; i <= n; i++) {
                makeSet(i);
            }
        }

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

        public long findParent(long data) {
            return findSet(data).data;
        }

        private Node findSet(Node node) {
            Node parent = node.parent;
            if (parent == node) {
                return parent;
            }
            node.parent = findSet(node.parent);
            return node.parent;
        }

        class Node {
            long data;
            Node parent;
            int rank;

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
}

