package leetcode;

import java.util.concurrent.Semaphore;

public class ZeroEvenOdd {
    private int n;
    private Semaphore[] locks = new Semaphore[] {
        new Semaphore(1),
        new Semaphore(1),
        new Semaphore(1),
    };

    public ZeroEvenOdd(int n) {
        this.n = n;
        try {
            locks[0].acquire();
            locks[1].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void zero(Runnable printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            locks[2].acquire();
            printNumber.run();
            locks[i % 2].release();
        }
    }

    public void even(Runnable printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            locks[0].acquire();
            printNumber.run();
            locks[2].release();
        }
    }

    public void odd(Runnable printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            locks[1].acquire();
            printNumber.run();
            locks[2].release();
        }
    }
}
