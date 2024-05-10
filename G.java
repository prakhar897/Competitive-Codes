import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;


import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class G {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        CGA solver = new CGA();
        solver.solve(1, in, out);
        out.close();
    }

    class Node{

        String genre;
        String name;

        public Node(String genre, String name){
            this.genre = genre;
            this.name = name;
           
        }

    }

    static class CGA {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int t = in.readInt();
            outer:while(t-->0){
                int n = in.readInt();
                HashMap<String, ArrayList<Nodes>> genreMap = new HashMap<>();
                 HashMap<String, ArrayList<Nodes>> nameMap = new HashMap<>();

                for(int i=0;i<n;i++){
                    String genre = in.readToken();
                    String name = in.readToken();

                    Node cur = new Node(genre, name);

                    if(!genreMap.containsKey(genre)){
                        genreMap.put(genre, new ArrayList<>());
                    }
                    if(!nameMap.containsKey(name)){
                        nameMap.put(name, new ArrayList<>());
                    }
                    genreMap.get(genre).add(cur);
                    nameMap.get(name).add(cur);


                    
                    
                }
                
            }
        }

        

    }

    

    
}

class InputReader extends InputStream {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

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

    public int peek() {
        if (numChars == -1) {
            return -1;
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                return -1;
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
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

    public long readLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
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

    public String readString() {
        int length = readInt();
        if (length < 0) {
            return null;
        }
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++)
            bytes[i] = (byte) read();
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(bytes);
        }
    }

    public String readToken() {
        int c;
        while (isSpaceChar(c = read())) ;
        StringBuilder result = new StringBuilder();
        result.appendCodePoint(c);
        while (!isSpaceChar(c = read()))
            result.appendCodePoint(c);
        return result.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public char readCharacter() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return (char) c;
    }

    public double readDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E') {
                return res * Math.pow(10, readInt());
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
                    return res * Math.pow(10, readInt());
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

    public boolean isExhausted() {
        int value;
        while (isSpaceChar(value = peek()) && value != -1)
            read();
        return value == -1;
    }

    public boolean readBoolean() {
        return readInt() == 1;
    }

    public <E extends Enum<E>> E readEnum(Class<E> c) {
        String name = readString();
        if (name == null) {
            return null;
        }
        for (E e : c.getEnumConstants()) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        throw new EnumConstantNotPresentException(c, name);
    }

    public Object readTopCoder() {
        String type = readToken();
        if (type.equals("-1")) {
            return null;
        }
        if ("int".equals(type)) {
            return readInt();
        } else if ("long".equals(type)) {
            return readLong();
        } else if ("double".equals(type)) {
            return readDouble();
        } else if ("String".equals(type)) {
            return readString();
        } else if ("int[]".equals(type)) {
            int length = readInt();
            int[] result = new int[length];
            for (int i = 0; i < length; i++)
                result[i] = readInt();
            return result;
        } else if ("long[]".equals(type)) {
            int length = readInt();
            long[] result = new long[length];
            for (int i = 0; i < length; i++)
                result[i] = readLong();
            return result;
        } else if ("double[]".equals(type)) {
            int length = readInt();
            double[] result = new double[length];
            for (int i = 0; i < length; i++)
                result[i] = readDouble();
            return result;
        } else if ("String[]".equals(type)) {
            int length = readInt();
            String[] result = new String[length];
            for (int i = 0; i < length; i++)
                result[i] = readString();
            return result;
        }
        throw new InputMismatchException();
    }

}

class OutputWriter {
    public final OutputStream out;

    public OutputWriter(OutputStream outputStream) {
        out = outputStream;
    }

    public void print(Object... objects) {
        try {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    out.write(' ');
                }
                out.write(objects[i].toString().getBytes("UTF-8"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printLine(Object... objects) {
        print(objects);
        try {
            out.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printString(String s) {
        if (s == null) {
            printLine(-1);
        } else {
            try {
                printLine(s.getBytes("UTF-8").length, s);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void printBoolean(boolean b) {
        printLine(b ? 1 : 0);
    }

    public void printEnum(Enum e) {
        printString(e == null ? null : e.name());
    }

    public void printTopCoder(Object o) {
        if (o == null) {
            printString(null);
        } else if (o instanceof Integer) {
            printLine("int", o);
        } else if (o instanceof Long) {
            printLine("long", o);
        } else if (o instanceof Double) {
            printLine("double", o);
        } else if (o instanceof String) {
            printLine("String");
            printString((String) o);
        } else if (o instanceof int[]) {
            printLine("int[]", ((int[]) o).length);
            for (int i : (int[]) o)
                printLine(i);
        } else if (o instanceof long[]) {
            printLine("long[]", ((long[]) o).length);
            for (long i : (long[]) o)
                printLine(i);
        } else if (o instanceof double[]) {
            printLine("double[]", ((double[]) o).length);
            for (double i : (double[]) o)
                printLine(i);
        } else if (o instanceof String[]) {
            printLine("String[]", ((String[]) o).length);
            for (String i : (String[]) o)
                printString(i);
        }
    }
}

