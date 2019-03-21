import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            String ls = in.nextString();
            String rs = in.nextString();
            LinkedList<Integer> larr[] = new LinkedList[27];
            LinkedList<Integer> rarr[] = new LinkedList[27];
            LinkedList<Pair<Integer, Integer>> ans = new LinkedList<>();
            int i = 0, j;

            for (i = 0; i < 27; i++) {
                larr[i] = new LinkedList<>();
                rarr[i] = new LinkedList<>();
            }

            for (i = 0; i < n; i++) {
                char c = ls.charAt(i);
                char d = rs.charAt(i);
                if (c == '?')
                    larr[26].add(i);
                else
                    larr[c - 'a'].add(i);

                if (d == '?')
                    rarr[26].add(i);
                else
                    rarr[d - 'a'].add(i);
            }

            for (i = 0; i < 26; i++) {
                int d = Math.min(larr[i].size(), rarr[i].size());
                for (j = 0; j < d; j++) {
                    int u = larr[i].remove(0) + 1;
                    int y = rarr[i].remove(0) + 1;
                    ans.add(new Pair(u, y));
                }
            }

            for (i = 0; i < 26; i++) {
                int d = Math.min(larr[i].size(), rarr[26].size());
                for (j = 0; j < d; j++) {
                    int u = larr[i].remove(0) + 1;
                    int y = rarr[26].remove(0) + 1;
                    ans.add(new Pair(u, y));
                }

                if (rarr[26].size() == 0)
                    break;
            }

            for (i = 0; i < 26; i++) {
                int d = Math.min(larr[26].size(), rarr[i].size());
                for (j = 0; j < d; j++) {
                    int u = larr[26].remove(0) + 1;
                    int y = rarr[i].remove(0) + 1;
                    ans.add(new Pair(u, y));
                }

                if (larr[26].size() == 0)
                    break;
            }

            int d = Math.min(larr[26].size(), rarr[26].size());
            for (j = 0; j < d; j++) {
                int u = larr[26].remove(0) + 1;
                int y = rarr[26].remove(0) + 1;
                ans.add(new Pair(u, y));
            }

            out.println(ans.size());
            for (Pair t : ans) {
                out.println(t.getFirst() + " " + t.getSecond());
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

        public void println(int i) {
            writer.println(i);
        }

    }
}

