import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
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
        Task560C solver = new Task560C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task560C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            String s = in.nextString();
            Queue<Character> q = new LinkedList<>();
            Stack<Character> st = new Stack<>();
            int i, count = 0;
            for (i = 0; i < n; i++) {
                if (i > 0 && s.charAt(i) == s.charAt(i - 1))
                    count++;
                else {
                    count = 0;
                }

                if (count < 2)
                    q.add(s.charAt(i));
            }
            //out.println(al);
            while (!q.isEmpty()) {
                char c = q.poll();
                if (st.isEmpty()) {
                    st.push(c);
                    continue;
                } else if (c == st.peek() && st.size() % 2 == 1) {
                    if (st.size() == 1) {
                    } else {
                        char d = st.pop();
                        char e = st.pop();
                        if (st.peek() == c)
                            st.push(e);
                        else
                            st.push(d);
                        st.push(c);
                    }
                } else {
                    st.push(c);
                }
            }
            //out.println(st.size());
            if (st.size() % 2 == 1)
                st.pop();
            out.println(n - st.size());
            Stack<Character> st2 = new Stack<>();
            while (!st.isEmpty()) {
                char c = st.pop();
                st2.push(c);
            }
            StringBuilder ans = new StringBuilder();
            while (!st2.isEmpty()) {
                char c = st2.pop();
                ans.append(c);
            }
            out.println(ans.toString());
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

