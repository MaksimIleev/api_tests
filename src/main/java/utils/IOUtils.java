package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IOUtils {

    public static String readFile(String filePath) {
        ThreadLocal<List<String>> list = new ThreadLocal<>();
        ThreadLocal<StringBuilder> sb = ThreadLocal.withInitial(() -> { return new StringBuilder();});

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            //br returns as stream and convert it into a List
            list.set(br.lines().collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line: list.get()) {
            sb.get().append(line).append("<br/>");
        }
        return new String(sb.get().toString().getBytes(), StandardCharsets.UTF_8);
    }

    public static <T> T[][] multiListToArray(final List<List<T>> listOfList, final Class<T> classz) {
        final T[][] array = (T[][]) Array.newInstance(classz, listOfList.size(), 0);

        for (int i = 0; i < listOfList.size(); i++) {
            array[i] = listOfList.get(i).toArray((T[]) Array.newInstance(classz, listOfList.get(i).size()));
        }

        return array;
    }

    public static List<String> split(String data, char myQuote, char myDelimiter) {
        data = unescape(data, '\"');
        int size = data.length();
        List<String> list = new ArrayList<String>();
        if (size == 0) {
            return list;
        }
        StringBuilder buf = new StringBuilder(1024);
        boolean quoted = false;
        boolean start = true;
        try {
            for (int i = 0; i < size; i++) {
                char c = data.charAt(i);
                if (c == myQuote) {
                    quoted = !quoted;
                    if (quoted && !start) {
                        buf.append(c);
                    }
                    start = false;
                } else if (c == myDelimiter && !quoted) {
                    list.add(buf.length() > 0 ? buf.toString() : "");
                    buf = new StringBuilder(1024);
                    start = true;
                } else {
                    buf.append(c);
                    start = false;
                }
            }
        } catch(Exception e) {
            System.out.println("Failed after " + buf);
            e.printStackTrace();
        }

        list.add(buf.length() > 0 ? buf.toString() : "");

        return list;
    }

    public static String unescape(String str, char charToUnescape) {
        StringBuilder buf = new StringBuilder(str.toCharArray().length);

            boolean quoted = false;
            for (char c: str.toCharArray()) {
                if (c == '\n' && !quoted) {
                    break;
                }
                if (c == charToUnescape) {
                    quoted = !quoted;
                }
                buf.append(c);
            }
       return buf.toString();
    }
}
