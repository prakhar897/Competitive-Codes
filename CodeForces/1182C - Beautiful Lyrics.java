import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Map;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.Set;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.io.Writer;
import java.util.Queue;
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
        Task566C solver = new Task566C();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task566C {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            Pair<Integer, Character> arr[] = new Pair[n];
            TreeMap<Pair, Queue<String>> hm3 = new TreeMap<>();
            TreeMap<Integer, Queue<String>> hm2 = new TreeMap<>();
            ArrayList<String[]> ans = new ArrayList<>();
            int max = n / 4;
            int i, j;

            for (i = 0; i < n; i++) {
                String s = in.nextString();
                int p = 0;
                char f = 'a';
                for (j = 0; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                        p++;
                        f = c;
                    }
                }

                arr[i] = new Pair(p, f);

                if (!hm3.containsKey(arr[i])) {
                    Queue<String> q = new LinkedList<>();
                    q.offer(s);
                    hm3.put(arr[i], q);
                } else {
                    Queue<String> q = hm3.get(arr[i]);
                    q.offer(s);
                    hm3.put(arr[i], q);
                }
            }

            Iterator it = hm3.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry p = (Map.Entry) it.next();
                Pair k = (Pair) p.getKey();
                Queue v = (Queue) p.getValue();
                if (v.size() % 2 == 1) {
                    String s = (String) v.poll();
                    Queue<String> q;

                    if (!hm2.containsKey(k.first)) {
                        q = new LinkedList<>();
                    } else {
                        q = hm2.get(k.first);
                    }
                    q.offer(s);
                    hm2.put((int) k.first, q);
                }
            }

            Queue<Pair<String, String>> ones = new LinkedList<>();
            Queue<Pair<String, String>> twos = new LinkedList<>();

            hm2.forEach((k, v) -> {
                while (v.size() >= 2) {
                    String s1 = v.poll();
                    String s2 = v.poll();
                    Pair<String, String> pa = new Pair<>(s1, s2);
                    ones.add(pa);
                }
            });

            hm3.forEach((k, v) -> {
                while (v.size() >= 2) {
                    String s1 = v.poll();
                    String s2 = v.poll();
                    Pair<String, String> pa = new Pair<>(s1, s2);
                    twos.add(pa);
                }
            });

            //out.println(ones);
            //out.println(twos);

            while (!ones.isEmpty() && !twos.isEmpty()) {
                Pair<String, String> pa1 = ones.poll();
                Pair<String, String> pa2 = twos.poll();
                String an[] = new String[4];
                an[0] = pa1.first;
                an[2] = pa1.second;
                an[1] = pa2.first;
                an[3] = pa2.second;
                ans.add(an);
            }

            while (twos.size() >= 2) {
                Pair<String, String> pa1 = twos.poll();
                Pair<String, String> pa2 = twos.poll();
                String an[] = new String[4];
                an[0] = pa1.first;
                an[2] = pa1.second;
                an[1] = pa2.first;
                an[3] = pa2.second;
                ans.add(an);
            }

            out.println(ans.size());
            for (i = 0; i < ans.size(); i++) {
                String an[] = ans.get(i);
                out.println(an[0] + " " + an[1]);
                out.println(an[2] + " " + an[3]);
            }
        }

    }

    static class Pair<U, V> implements Comparable<Pair<U, V>> {
        public final U first;
        public final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Pair pair = (Pair) o;

            return !(first != null ? !first.equals(pair.first) : pair.first != null) &&
                    !(second != null ? !second.equals(pair.second) : pair.second != null);
        }

        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        public String toString() {
            return "(" + first + "," + second + ")";
        }

        public int compareTo(Pair<U, V> o) {
            int value = ((Comparable<U>) first).compareTo(o.first);
            if (value != 0) {
                return value;
            }
            return ((Comparable<V>) second).compareTo(o.second);
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

