package leetcode

class Foo {
    private var checkpoint1 = false
    private var checkpoint2 = false

    @Throws(InterruptedException::class)
    fun first(printFirst: Runnable) {
        printFirst.run()
        checkpoint1 = true
    }

    @Throws(InterruptedException::class)
    fun second(printSecond: Runnable) {
        while (!checkpoint1);
        printSecond.run()
        checkpoint2 = true
    }

    @Throws(InterruptedException::class)
    fun third(printThird: Runnable) {
        while (!checkpoint2);
        printThird.run()
    }
}
