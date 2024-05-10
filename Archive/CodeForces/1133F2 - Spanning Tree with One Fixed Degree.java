import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
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
        TasF solver = new TasF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TasF {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            List<Integer> adj[];
            int n = in.nextInt();
            int m = in.nextInt();
            int d = in.nextInt();
            ArrayList<Pair> ans = new ArrayList<>();
            int iny[] = new int[m];
            int outy[] = new int[m];
            int i;

            for (i = 0; i < m; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                iny[i] = a;
                outy[i] = b;
            }

            adj = GraphUtils.nextU(n, m, iny, outy, true);
        /*for(i=0;i<=n;i++)
            out.println(adj[i]);*/

            DisjointSet ds = new DisjointSet();
            for (i = 1; i <= n; i++) {
                ds.makeSet(i);
            }

            for (i = 2; i <= n; i++) {
                for (int x : adj[i]) {
                    if (x != 1 && ds.findSet(i) != ds.findSet(x)) {
                        ds.union(i, x);
                        ans.add(new Pair(i, x));

                    }
                }
            }

            HashSet<Integer> parents = new HashSet<>();
            for (i = 2; i <= n; i++) {
                parents.add((int) ds.findParent(i));
            }

            int components = parents.size();
            if (d < components || d > adj[1].size()) {
                out.println("NO");
                return;
            }

            HashSet<Integer> vis = new HashSet<>();
            for (int x : adj[1]) {
                int par = (int) ds.findParent(x);
                if (parents.contains(par)) {
                    ans.add(new Pair(1, x));
                    parents.remove(par);
                    vis.add(x);
                }

            }

            d -= components;

            for (int x : adj[1]) {
                if (d == 0)
                    break;
                if (!vis.contains(x)) {
                    ans.add(new Pair(1, x));
                    d--;
                }
            }

            int yu = ans.size();
            iny = new int[yu];
            outy = new int[yu];
            i = 0;
            for (Pair t : ans) {
                iny[i] = (int) t.getFirst();
                outy[i] = (int) t.getSecond();
                i++;
            }
            // out.println(iny);
            //out.println(outy);
            adj = GraphUtils.nextU(n, yu, iny, outy, true);
            //for(i=0;i<=n;i++)
            //  out.println(adj[i]);
            ans = new ArrayList<>();

            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            int visited[] = new int[n + 1];
            visited[1] = 1;
            while (!q.isEmpty()) {
                int h = q.poll();
                for (int x : adj[h]) {
                    if (visited[x] == 0) {
                        ans.add(new Pair(x, h));
                        visited[x] = 1;
                        q.offer(x);
                    }
                }
            }

            out.println("YES");
            for (Pair t : ans) {
                int a = (int) t.getFirst();
                int b = (int) t.getSecond();
                out.println(a + " " + b);
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

    static class GraphUtils {
        public static ArrayList<Integer>[] nextU(int n, int m, int in[], int out[], boolean oneIndexed) {

            ArrayList<Integer> adj[] = new ArrayList[n + 1];
            int i;
            for (i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }

            for (i = 0; i < m; i++) {
                int a = in[i];
                int b = out[i];
                adj[a].add(b);
                adj[b].add(a);
            }

            return adj;

            //Add one indexing
            //Add longs instead of ints
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

    static class DisjointSet {
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
}

