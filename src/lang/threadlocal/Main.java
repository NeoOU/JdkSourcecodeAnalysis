package lang.threadlocal;

public class Main {

    public static void main(String[] args) {

        int i = ThreadId.get(false);
        System.out.println(i);

        new Thread(() ->{
                int j = ThreadId.get(false);
                System.out.println(j);
        }).start();

        new Thread(()->{
            int j = ThreadId.get(true);
            System.out.println(j);
        }).start();



        //System.gc();
        Thread thread = Thread.currentThread();
        System.out.println("gc");


    }
}
