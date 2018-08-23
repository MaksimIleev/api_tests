package utils;

import manage.ThreadId;

public class Utils {

    public static synchronized String generateId() {
        return System.nanoTime() + "_" + ThreadId.get();
    }


}
