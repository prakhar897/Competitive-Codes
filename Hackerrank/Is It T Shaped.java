import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        TaskAgonA solver = new TaskAgonA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskAgonA {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int test = in.nextInt();
            outer:
            for (int o = 0; o < test; o++) {
                int mat[][] = new int[5][2];
                int i, j, flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0;

                for (i = 0; i < 5; i++) {
                    for (j = 0; j < 2; j++) {
                        mat[i][j] = in.nextInt();
                    }
                }
                int my = 10000000, mx = 10000000;
                for (i = 0; i < 5; i++) {
                    mx = Math.min(mat[i][0], mx);
                    my = Math.min(mat[i][1], my);
                }

                for (i = 0; i < 5; i++) {
                    mat[i][0] -= mx;
                    if (mat[i][0] > 2) {
                        out.println("No");
                        continue outer;
                    }
                    mat[i][1] -= my;
                    if (mat[i][1] > 2) {
                        out.println("No");
                        continue outer;
                    }
                }

            /*for(i=0;i<5;i++)
            {
                for(j=0;j<2;j++)
                {
                    out.print(mat[i][j] + " ");
                }
                out.println();
            }*/

                for (i = 0; i < 5; i++) {
                    if (mat[i][0] == 2 && mat[i][1] == 2)
                        flag1++;
                    if (mat[i][0] == 2 && mat[i][1] == 1)
                        flag2++;
                    if (mat[i][0] == 2 && mat[i][1] == 0)
                        flag3++;
                    if (mat[i][0] == 1 && mat[i][1] == 1)
                        flag4++;
                    if (mat[i][0] == 0 && mat[i][1] == 1)
                        flag5++;
                }

                if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1 && flag5 == 1) {
                    out.println("Yes");
                    continue;
                }
                flag1 = 0;
                flag2 = 0;
                flag3 = 0;
                flag4 = 0;
                flag5 = 0;

                for (i = 0; i < 5; i++) {
                    if (mat[i][0] == 1 && mat[i][1] == 2)
                        flag1++;
                    if (mat[i][0] == 1 && mat[i][1] == 1)
                        flag2++;
                    if (mat[i][0] == 0 && mat[i][1] == 0)
                        flag3++;
                    if (mat[i][0] == 1 && mat[i][1] == 0)
                        flag4++;
                    if (mat[i][0] == 2 && mat[i][1] == 0)
                        flag5++;
                }
                //out.println(flag1 + " "+ flag2 + flag3 + " " +flag4);
                if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1 && flag5 == 1) {
                    out.println("Yes");
                    continue;
                }

                flag1 = 0;
                flag2 = 0;
                flag3 = 0;
                flag4 = 0;
                flag5 = 0;

                for (i = 0; i < 5; i++) {
                    if (mat[i][0] == 0 && mat[i][1] == 2)
                        flag1++;
                    if (mat[i][0] == 0 && mat[i][1] == 1)
                        flag2++;
                    if (mat[i][0] == 0 && mat[i][1] == 0)
                        flag3++;
                    if (mat[i][0] == 1 && mat[i][1] == 1)
                        flag4++;
                    if (mat[i][0] == 2 && mat[i][1] == 1)
                        flag5++;
                }

                //out.println(flag1 + " "+ flag2 + flag3 + " " +flag4);
                if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1 && flag5 == 1) {
                    out.println("Yes");
                    continue;
                }
                flag1 = 0;
                flag2 = 0;
                flag3 = 0;
                flag4 = 0;
                flag5 = 0;

                for (i = 0; i < 5; i++) {
                    if (mat[i][0] == 0 && mat[i][1] == 2)
                        flag1++;
                    if (mat[i][0] == 1 && mat[i][1] == 2)
                        flag2++;
                    if (mat[i][0] == 2 && mat[i][1] == 2)
                        flag3++;
                    if (mat[i][0] == 1 && mat[i][1] == 1)
                        flag4++;
                    if (mat[i][0] == 1 && mat[i][1] == 0)
                        flag5++;
                }

                if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1 && flag5 == 1) {
                    out.println("Yes");
                    continue;
                }
                flag1 = 0;
                flag2 = 0;
                flag3 = 0;
                flag4 = 0;
                flag5 = 0;

                out.println("No");


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
}

