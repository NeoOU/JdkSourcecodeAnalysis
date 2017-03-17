package lang.threadlocal.myThreadV2.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by omed on 2017/3/12.
 */
public class MyThread extends Thread {

    /**
     * 1. 存储多个不同的键值对，第一反应便是Map
     * 2. 并不是每个线程都一定会有threadlocalMap，没有必要在声明时就初始化。
     * 3. Map的key指定为String类型，value指定为Object类型。这明显不是一个好实现
     * <p>
     * from version 2.0
     * 为了配合MyThreadlocalodl类的泛型，将Map的key改为Object类型
     */
    private Map<Object, Object> threadlocalMap;

    /**
     * 1. 确保获取到的threadlocalMap是当前线程的threadlocalMap
     * 2. 第一次获取threadlocalMap时对其初始化
     * <p>
     * from version 2.0
     * 3. 将方法设为包级私有
     *
     * @return
     */
    Map<Object, Object> getThreadlocalMap() {

        /*
         *from version 2.0
         *已经在MyThreadlocal类中确保是通过Thread.currentThread()获取threadlocalMap
         *
        //确保获取到的threadlocalMap是当前线程的threadlocalMap
        MyThread curThread = (MyThread) currentThread();
        Map<String, Object> curThreadlocalMap = curThread.threadlocalMap;*/

        /*
         * 不论在哪个线程调用getThreadlocalMap()方法，都会通过上面的代码获取到当前正在执行的线程，
         * 而线程的线性执行，使得这个方法不存在并发，不需要同步
         *
        if(curThreadlocalMap == null){
            synchronized (curThread){
                if(curThreadlocalMap == null){
                    curThreadlocalMap = new HashMap<>();
                }
            }
        }*/
        if (threadlocalMap == null) {
            threadlocalMap = new HashMap<>();
        }

        return threadlocalMap;
    }

    public MyThread(Runnable runnable) {
        super(runnable);
    }
}
