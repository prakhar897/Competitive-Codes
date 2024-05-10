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
 *
 * @author prakhar897
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
            int test = in.nextInt();
            while (test-- > 0) {
                int n = in.nextInt();
                double s = in.nextDouble();
                double y = in.nextDouble();
                double v[] = new double[n];
                double p[] = new double[n];
                double d[] = new double[n];
                double c[] = new double[n];
                int i, j;
                double time = 0, te = 0;
                for (i = 0; i < n; i++)
                    v[i] = in.nextDouble();
                for (i = 0; i < n; i++)
                    d[i] = in.nextDouble();
                for (i = 0; i < n; i++)
                    p[i] = in.nextDouble();
                for (i = 0; i < n; i++)
                    c[i] = in.nextDouble();

                for (i = 0; i < n; i++) {
                    if (d[i] == 0 && p[i] <= -1 && Math.abs(p[i]) >= c[i]) {
                        time += y / s;
                        te = y / s;
                    } else if (d[i] == 1 && p[i] >= 1 && p[i] >= c[i]) {
                        time += y / s;
                        te = y / s;
                    } else if (d[i] == 0 && p[i] < 0 && Math.abs(p[i]) < c[i]) {
                        time += (y / s) + ((c[i] - Math.abs(p[i])) / v[i]);
                        te = (y / s) + ((c[i] - Math.abs(p[i])) / v[i]);
                    } else if (d[i] == 1 && p[i] > 0 && p[i] < c[i]) {
                        time += (y / s) + ((c[i] - p[i]) / v[i]);
                        te = (y / s) + ((c[i] - p[i]) / v[i]);
                    } else if (d[i] == 0 && p[i] >= 0 && y * v[i] >= (p[i] - 0.000001) * s) {
                        time += (y / s) + ((p[i] + c[i]) / v[i]);
                        te = (y / s) + ((p[i] + c[i]) / v[i]);
                    } else if (d[i] == 1 && p[i] <= 0 && y * v[i] >= (Math.abs(p[i]) - 0.000001) * s) {
                        time += (y / s) + ((Math.abs(p[i]) + c[i]) / v[i]);
                        te = (y / s) + ((Math.abs(p[i]) + c[i]) / v[i]);
                    } else {
                        time += y / s;
                        te = y / s;
                    }

                    for (j = i + 1; j < n; j++) {
                        if (d[j] == 1) {
                            p[j] += v[j] * te;
                        } else if (d[j] == 0) {
                            p[j] -= v[j] * te;
                        }
                    }
                }
                out.println(time);

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

        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, nextInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, nextInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
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

