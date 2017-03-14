package lang.threadlocal.myThreadV2.thread;

import java.util.Map;

/**
 * Created by omed on 2017/3/12.
 * 封装getThreadlocalMap()访问的工具类
 */
public class MyThreadlocal<S,T> {

    public T get(S key) {
        return getThreadlocalMap().get(key);
    }

    public void set(S key,T value) {
        getThreadlocalMap().put(key,value);
    }

    /**
     * 确保是通过Thread.currentThread()获取threadlocalMap
     * @return
     */
    private Map<S,T> getThreadlocalMap(){
       return  (Map<S,T>)((MyThread)Thread.currentThread()).getThreadlocalMap();
    }
}
