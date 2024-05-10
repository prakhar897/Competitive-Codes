import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.util.Set;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.HashMap;
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
        Task1213G solver = new Task1213G();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task1213G {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int mat[][] = new int[n - 1][3];
            int i;

            for (i = 0; i < n - 1; i++) {
                mat[i][0] = in.nextInt();
                mat[i][1] = in.nextInt();
                mat[i][2] = in.nextInt();
            }

            Arrays.sort(mat, (int o1[], int o2[]) -> {
                return o1[2] - o2[2];
            });

            TreeMap<Integer, Long> tm = new TreeMap<>();
            DisjointSet ds = new DisjointSet();

            for (i = 1; i <= n; i++)
                ds.makeSet(i);

            tm.put(0, 0l);
            for (i = 0; i < n - 1; i++) {
                Node a = ds.findSet(mat[i][0]);
                Node b = ds.findSet(mat[i][1]);
                long cur = 1l * a.size * b.size;
                tm.merge(mat[i][2], cur, (x, y) -> x + y);
                ds.union(mat[i][0], mat[i][1]);
            }

            //out.println(tm);

            long sum = 0;
            Iterator it = tm.keySet().iterator();

            while (it.hasNext()) {
                int key = (int) it.next();
                long val = tm.get(key);
                sum += val;
                tm.put(key, sum);
            }

            //out.println(tm);

            for (i = 0; i < m; i++) {
                int q = in.nextInt();
                long ans = tm.get(tm.floorKey(q));
                out.print(ans + " ");
            }
            out.println();
        }

        public class DisjointSet {
            private Map<Long, Node> map = new HashMap<>();

            public void makeSet(long data) {
                Node node = new Node();
                node.data = data;
                node.parent = node;
                node.rank = 1;
                node.size = 1;
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
                    parent1.size += parent2.size;
                    parent2.size = 0;
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
            long size;

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

        public void println() {
            writer.println();
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

