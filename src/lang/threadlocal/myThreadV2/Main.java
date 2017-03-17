package lang.threadlocal.myThreadV2;

import lang.threadlocal.myThreadV2.thread.MyThread;
import lang.threadlocal.myThreadV2.thread.MyThreadlocal;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by omed on 2017/3/13.
 */
public class Main {
    private static final AtomicInteger nextId = new AtomicInteger(0);
    private static final String threadId = UUID.randomUUID().toString();

    public static void main(String[] args) throws InterruptedException {
        MyThreadlocal<String, Integer> idStorage = new MyThreadlocal<>();

        new MyThread(() -> {
            for (int i = 0; i < 10; i++) {
                new MyThread(() -> {
                    /*
                     * getThreadlocalMap()方法设置为包级私有且分包后
                     * 不能再能过((MyThread)Thread.currentThread()).getThreadlocalMap()调用
                     */
                    set(idStorage);
                    get(idStorage);
                }).start();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
             *验证set和get的是当前线程的id,且如果key相同则覆盖value
             */
            System.out.println("-------------------------------------------");
            set(idStorage);
            get(idStorage);
            set(idStorage);
            get(idStorage);

            /*
             * 验证线程的threadlocalMap可以存储多个值
             */
            System.out.println("-------------------------------------------");
            String newKey = UUID.randomUUID().toString();
            Integer newValue = 1024;
            idStorage.set(newKey, newValue);
            Integer integer = idStorage.get(newKey);
            System.out.println("threadName:" + Thread.currentThread().getName() + " newValue:" + integer);
            get(idStorage);
        }).start();
    }

    private static void set(MyThreadlocal<String, Integer> idStorage) {
        idStorage.set(threadId, nextId.getAndIncrement());
    }

    private static void get(MyThreadlocal<String, Integer> idStorage) {
        Integer id = idStorage.get(threadId);
        System.out.println("threadName:" + Thread.currentThread().getName() + " threadId:" + id);
    }
}
