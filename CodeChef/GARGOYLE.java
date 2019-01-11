import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
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
        TaskEnigB solver = new TaskEnigB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskEnigB {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            HashSet<Integer> hs[] = new HashSet[n + 1];
            int truth[] = new int[n + 1];
            int i, j;

            for (i = 0; i <= n; i++) {
                hs[i] = new HashSet<Integer>();
            }

            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j++) {
                    char c = in.nextCharacter();
                    if (c == 'T')
                        hs[i].add(j);
                }
            }

            outer:
            for (i = 1; i <= n; i++) {
                for (int x : hs[i]) {
                    for (int y : hs[i]) {
                        if (hs[x].contains(y) == false) {
                            truth[i] = 0;
                            continue outer;
                        }
                    }
                }
                truth[i] = 1;
            }
        /*
        for(i=1;i<=n;i++)
        {
            out.println(hs[i]);
        }*/

            //out.println(truth);
            int ans = 0;
            for (i = 1; i <= n; i++) {
                if (truth[i] == 1) {
                    int k = hs[i].size();
                    ans = Math.max(ans, k);
                }
            }

            out.println(ans);
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

        public char nextCharacter() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return (char) c;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

