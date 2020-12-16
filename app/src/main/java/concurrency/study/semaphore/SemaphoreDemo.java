package concurrency.study.semaphore;

import java.util.concurrent.*;

class Shared {
    static int count = 0;
}

class SampleThread extends Thread {
    final Semaphore semaphore;
    final String name;

    SampleThread(Semaphore semaphore, String name) {
        super(name);
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {

        System.out.println("Starting thread " + name);

        try {

            System.out.println("Thread " + name + " is waiting for permit");

            semaphore.acquire();

            System.out.println("Thread " + name + " got a permit");

            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.println("Thread " + name + ": " + Shared.count);

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release();
    }
}

public class SemaphoreDemo {
    public void run() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        SampleThread a = new SampleThread(semaphore, "A");
        SampleThread b = new SampleThread(semaphore, "B");

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println("count: " + Shared.count);
    }
}
