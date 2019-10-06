public class Foo {
    private volatile boolean checkpoint1 = false;
    private volatile boolean checkpoint2 = false;

    public Foo() { }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        checkpoint1 = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!checkpoint1);
        printSecond.run();
        checkpoint2 = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!checkpoint2);
        printThird.run();
    }
}
