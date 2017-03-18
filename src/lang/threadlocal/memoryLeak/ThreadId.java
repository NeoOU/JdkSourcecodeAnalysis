package lang.threadlocal.memoryLeak;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by omed on 2017/3/11.
 */
public class ThreadId {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());


    public static int get(boolean b) {
        Integer integer = threadId.get();
        if (b) {
            threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());
            System.gc();
        }
        return integer;
    }
}
