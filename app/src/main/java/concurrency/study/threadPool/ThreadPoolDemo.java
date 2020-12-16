package concurrency.study.threadPool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    final String name;

    Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                String message = i == 0 ?
                        "Initializing task " + name + " at " + formatter.format(new Date()) :
                        "Executing task "    + name + " at " + formatter.format(new Date());
                System.out.println(message);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadPoolDemo {
    public void run() {
        Runnable r1 = new Task("Task 1");
        Runnable r2 = new Task("Task 2");
        Runnable r3 = new Task("Task 3");
        Runnable r4 = new Task("Task 4");
        Runnable r5 = new Task("Task 5");

        ExecutorService pool = Executors.newFixedThreadPool(3);

        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        pool.shutdown();
    }
}
