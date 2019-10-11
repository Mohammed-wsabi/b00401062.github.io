package leetcode

import java.util.concurrent.Semaphore

class FooBar(private val n: Int) {
    private val can_foo = Semaphore(1)
    private val can_bar = Semaphore(1)

    init {
        try {
            can_bar.acquire()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Throws(InterruptedException::class)
    fun foo(printFoo: Runnable) {
        for (i in 0 until n) {
            can_foo.acquire()
            printFoo.run()
            can_bar.release()
        }
    }

    @Throws(InterruptedException::class)
    fun bar(printBar: Runnable) {
        for (i in 0 until n) {
            can_bar.acquire()
            printBar.run()
            can_foo.release()
        }
    }
}
