package lang.threadlocal.myThreadV1;

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
     */
    private Map<String, Object> threadlocalMap;

    /**
     * 1. 确保获取到的threadlocalMap是当前线程的threadlocalMap
     * 2. 第一次获取threadlocalMap时对其初始化
     *
     * @return
     */
    public Map<String, Object> getThreadlocalMap() {
        //确保获取到的threadlocalMap是当前线程的threadlocalMap
        MyThread curThread = (MyThread) currentThread();
        Map<String, Object> curThreadlocalMap = curThread.threadlocalMap;

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
        if (curThreadlocalMap == null) {
            curThreadlocalMap = new HashMap<>();
        }

        return curThreadlocalMap;
    }

    public MyThread(Runnable runnable) {
        super(runnable);
    }
}
