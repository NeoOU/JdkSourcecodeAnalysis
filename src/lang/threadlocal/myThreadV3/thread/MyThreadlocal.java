package lang.threadlocal.myThreadV3.thread;

import java.util.Map;

/**
 * Created by omed on 2017/3/12.
 * 封装getThreadlocalMap()访问的工具类
 * since 3.0 用MyThreadlocal对象作为Map的key
 */
public class MyThreadlocal<T> {

    public T get() {
        return getThreadlocalMap().get(this);
    }

    public void set(T value) {
        getThreadlocalMap().put(this, value);
    }

    /**
     * 确保是通过Thread.currentThread()获取threadlocalMap
     *
     * @return
     */
    private Map<MyThreadlocal, T> getThreadlocalMap() {
        return (Map<MyThreadlocal, T>) ((MyThread) Thread.currentThread()).getThreadlocalMap();
    }
}
