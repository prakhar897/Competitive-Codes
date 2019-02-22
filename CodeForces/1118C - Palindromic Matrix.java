import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.util.Set;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
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
        Task540C solver = new Task540C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task540C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int m = (int) Math.pow(n, 2);
            int arr[] = in.nextIntArray(m);
            int i, j, flag = -1;
            int mat[][] = new int[n][n];

            HashMap<Integer, Integer> hm = new HashMap<>();
            ArrayList<Integer> al = new ArrayList<>();

            for (i = 0; i < m; i++) {
                hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1);
            }

            Iterator it = hm.entrySet().iterator();
            i = 0;
            j = 0;
            while (it.hasNext()) {
                Map.Entry p = (Map.Entry) it.next();
                int k = (int) p.getKey();
                int vv = (int) p.getValue();
                int v = vv % 4;

                int cou = vv / 4;
                for (int l = 0; l < cou; l++) {
                    if (j == n / 2) {
                        al.add(k);
                        al.add(k);
                        continue;
                    }
                    mat[i][j] = k;
                    mat[n - 1 - i][j] = k;
                    mat[i][n - 1 - j] = k;
                    mat[n - 1 - i][n - 1 - j] = k;
                    i++;
                    if (i == n / 2) {
                        i = 0;
                        j++;
                    }
                }

                if (v == 1) {
                    if (flag != -1 || n % 2 == 0) {
                        out.println("NO");
                        return;
                    } else {
                        flag = k;
                        mat[n / 2][n / 2] = k;
                    }
                } else if (v == 2) {
                    al.add(k);
                } else if (v == 0) {

                } else {
                    al.add(k);
                    if (flag != -1 || n % 2 == 0) {
                        out.println("NO");
                        return;
                    } else {
                        flag = k;
                        mat[n / 2][n / 2] = k;
                    }
                }
                it.remove();
            }

            i = 0;
            j = 0;
            if (n % 2 == 1 && al.size() == (n - 1)) {
                for (int x : al) {
                    if (i < n / 2) {
                        mat[n / 2][i] = x;
                        mat[n / 2][n - 1 - i] = x;
                        i++;
                    } else if (i == n / 2 && j < n / 2) {
                        mat[j][n / 2] = x;
                        mat[n - 1 - j][n / 2] = x;
                        j++;
                    }
                }
                al.clear();
            }

            if (al.size() != 0) {
                out.println("NO");
                return;
            }

            out.println("YES");
            out.print(mat);
            //out.println(al);
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

        public void print(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (j != 0) {
                        writer.print(' ');
                    }
                    writer.print(matrix[i][j]);
                }
                writer.println();
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

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

