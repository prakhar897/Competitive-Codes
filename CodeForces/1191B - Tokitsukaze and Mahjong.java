import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        Task573B solver = new Task573B();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task573B {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            char s1[] = in.nextString().toCharArray();
            char s2[] = in.nextString().toCharArray();
            char s3[] = in.nextString().toCharArray();

            if (s1[1] == s2[1] && s2[1] == s3[1]) {
                ArrayList<Integer> al = new ArrayList<>();
                al.add((int) s1[0] - '0');
                al.add((int) s2[0] - '0');
                al.add((int) s3[0] - '0');

                Collections.sort(al);
                //out.println(al);
                if (al.get(0) == al.get(1) && al.get(1) == al.get(2)) {
                    out.println(0);
                    return;
                } else if (al.get(0) + 1 == al.get(1) && al.get(1) + 1 == al.get(2)) {
                    out.println(0);
                    return;
                }
            }

            if (follow(s1, s2) || follow(s2, s3) || follow(s1, s3)) {
                out.println(1);
                return;
            }

            out.println(2);
        }

        boolean follow(char s1[], char s2[]) {
            if (s1[1] == s2[1]) {
                ArrayList<Integer> al = new ArrayList<>();
                al.add((int) s1[0] - '0');
                al.add((int) s2[0] - '0');
                Collections.sort(al);

                if (al.get(0) == al.get(1)) {
                    return true;
                } else if (al.get(0) + 1 == al.get(1)) {
                    return true;
                } else if (al.get(0) + 2 == al.get(1)) {
                    return true;
                }
            }
            return false;
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

