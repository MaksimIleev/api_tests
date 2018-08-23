package utils;

import java.lang.ref.Reference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ThreadUtils {

    public static synchronized void cleanThreadLocals() {
        int count = 0;
        try {
            // Get a reference to the thread locals table of the current thread
            Thread thread = Thread.currentThread();
            System.out.println("Cleaning ThreadLocals....");
            Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
            threadLocalsField.setAccessible(true);
            Object threadLocalTable = threadLocalsField.get(thread);

            // Get a reference to the array holding the thread local variables inside the
            // ThreadLocalMap of the current thread
            Class threadLocalMapClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
            Field tableField = threadLocalMapClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object table = tableField.get(threadLocalTable);

            // The key to the ThreadLocalMap is a WeakReference object. The referent field of this object
            // is a reference to the actual ThreadLocal variable
            Field referentField = Reference.class.getDeclaredField("referent");
            referentField.setAccessible(true);

            for (int i = 0; i < Array.getLength(table); i++) {
                // Each entry in the table array of ThreadLocalMap is an Entry object
                // representing the thread local reference and its value
                Object entry = Array.get(table, i);

                if (entry != null) {
                    // Get a reference to the thread local object and remove it from the table
                    ThreadLocal threadLocal = (ThreadLocal) referentField.get(entry);
                    //System.out.println("ThreadLocal instance of " + threadLocal.get().getClass().getSimpleName());
                    threadLocal.remove();
                    count++;
                }
            }

            System.out.println("Done cleaning ThreadLocal refrences: count = " + count);
        } catch (Exception e) {
            // We will tolerate an exception here and just log it
            System.err.println(e.getMessage());
        }
    }

    public static String getThreadInfo() {
        return Thread.currentThread().getName() + "_" + Thread.currentThread().getId();
    }
}
