package lang.threadlocal.myThreadV1;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by omed on 2017/3/12.
 */
public class Main {
    private static final AtomicInteger nextId = new AtomicInteger(0);
    private static final String threadId= UUID.randomUUID().toString();

    public static void main(String[] args) throws InterruptedException {
        new MyThread(()->{
            MyThread t = (MyThread) Thread.currentThread();
            putAndGetId(t,"group1");
            for (int i = 0; i < 10; i++) {
                new MyThread(()->{
                    MyThread t1=(MyThread)Thread.currentThread();
                    putAndGetId(t1,"group1");
                }).start();
            }
        }).start();

        Thread.sleep(2000);
        new MyThread(()->{
            MyThread t =(MyThread)Thread.currentThread();
            putAndGetId(t,"group2");

            for (int i = 0; i < 10; i++) {
                new MyThread(()->{
                    t.getThreadlocalMap();
                    putAndGetId(t,"group2");
                }).start();
            }
        }).start();

    }

    private static void putAndGetId(MyThread t,String threadGroup) {
        Map<String, Object> threadlocalMap = t.getThreadlocalMap();
        threadlocalMap.put(threadId, nextId.getAndIncrement());
        int id = (int)threadlocalMap.get(threadId);
        System.out.println("threadGroup:"+threadGroup+" threadlocalMap:"+threadlocalMap.hashCode()+" id:"+id+" threadName:"+t.getName());
    }
}
