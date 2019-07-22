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
        TaskGlobalE solver = new TaskGlobalE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskGlobalE {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            char arr[] = in.nextString().toCharArray();
            int n = arr.length;
            int i = 0, j = n - 1, count = 0;
            Queue<Character> start = new LinkedList<>();
            Stack<Character> end = new Stack<>();

            while (j - i >= 3) {
                if (arr[i] == arr[j]) {
                    start.add(arr[i]);
                    end.push(arr[j]);
                    i++;
                    j--;
                    //out.println("loop1");
                } else if (arr[i] == arr[j - 1]) {
                    start.add(arr[i]);
                    end.push(arr[j - 1]);
                    i++;
                    j -= 2;
                    // out.println("loop2");
                } else if (arr[i + 1] == arr[j]) {
                    start.add(arr[i + 1]);
                    end.push(arr[j]);
                    i += 2;
                    j--;
                    //out.println("loop3");
                } else {
                    start.add(arr[i + 1]);
                    end.push(arr[j - 1]);
                    i += 2;
                    j -= 2;
                    //out.println("loop4");
                }
                count += 2;
            }

            if (count < n / 2)
                start.add(arr[(i + j) / 2]);
            StringBuilder ans = new StringBuilder();
            while (!start.isEmpty())
                ans.append(start.poll());

            while (!end.isEmpty())
                ans.append(end.pop());

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

