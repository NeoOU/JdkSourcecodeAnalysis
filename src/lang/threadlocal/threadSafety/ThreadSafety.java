package lang.threadlocal.threadSafety;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ThreadSafety {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    static ThreadLocal<SimpleDateFormat> threadlocal = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            /*
			 * 方式1：sdf可以被多个线程访问，不是线程安全的
			 */
            return sdf;

			/*
			 * 方式2：只能被当前这一个线程访问，不存在线程安全问题
			 */
            //return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            threadlocal.get().setTimeZone(TimeZone.getTimeZone("CTT"));//CTT - Asia/Shanghai
            printTimeZone();
            try {Thread.sleep(3000);} catch (Exception e) {}
            printTimeZone();
        }).start();

        Thread.sleep(1000);
        threadlocal.get().setTimeZone(TimeZone.getTimeZone("JST"));//JST - Asia/Tokyo
        printTimeZone();
    }

    private static void printTimeZone() {
        System.out.println(Thread.currentThread().getName() + ": timeZone=" + threadlocal.get().getTimeZone());

    }

}
