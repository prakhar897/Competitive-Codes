import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            outer:
            while (test-- > 0) {
                int n = in.nextInt();
                String sa = in.nextString();
                String sb = in.nextString();
                String sc = in.nextString();
                long cint = Long.parseLong(sc);
                HashSet<Long> a = new HashSet<>();
                HashSet<Long> b = new HashSet<>();

                a = permute(sa.toCharArray());
                b = permute(sb.toCharArray());

                //out.println(a);
                //out.println(b);

                for (long x : a) {
                    long rem = cint - x;
                    if (b.contains(rem)) {
                        out.println("YES");
                        continue outer;
                    }
                }

                out.println("NO");
            }
        }

        public HashSet<Long> permute(char input[]) {
            Map<Character, Integer> countMap = new TreeMap<>();
            for (char ch : input) {
                countMap.compute(ch, (key, val) -> {
                    if (val == null) {
                        return 1;
                    } else {
                        return val + 1;
                    }
                });
            }
            char str[] = new char[countMap.size()];
            int count[] = new int[countMap.size()];
            int index = 0;
            for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
                str[index] = entry.getKey();
                count[index] = entry.getValue();
                index++;
            }
            HashSet<Long> resultList = new HashSet<>();
            char result[] = new char[input.length];
            permuteUtil(str, count, result, 0, resultList);
            return resultList;
        }

        public void permuteUtil(char str[], int count[], char result[], int level, HashSet<Long> resultList) {
            if (level == result.length) {
                resultList.add(Long.parseLong(new String(result)));
                return;
            }

            for (int i = 0; i < str.length; i++) {
                if (count[i] == 0) {
                    continue;
                }
                result[level] = str[i];
                count[i]--;
                permuteUtil(str, count, result, level + 1, resultList);
                count[i]++;
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
}

