package concurrency.study.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class ModifyListThread extends Thread {
    private List l;
    public ModifyListThread(List l) {
        this.l = l;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        l.add("D");
    }
}

public class CollectionDemo {
    public void run() {
        // it throws an error duo modify while reading from an unsynchronized collection
        //runWithUnsynchronizedError();

        // it works duo sync collection
        runWithSuccess();
    }

    private void runWithSuccess() {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();

        list.add("A");
        list.add("B");
        list.add("C");

        ModifyListThread modifyListThread = new ModifyListThread(list);
        modifyListThread.start();

        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(list);
    }

    private void runWithUnsynchronizedError() {
        ArrayList list = new ArrayList();

        list.add("A");
        list.add("B");
        list.add("C");

        ModifyListThread modifyListThread = new ModifyListThread(list);
        modifyListThread.start();

        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(list);
    }
}

