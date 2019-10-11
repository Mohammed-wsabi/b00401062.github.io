package leetcode

import java.util.concurrent.Semaphore

class ZeroEvenOdd(private val n: Int) {
    private val locks = Array(3) { Semaphore(1) }

    init {
        try {
            locks[0].acquire()
            locks[1].acquire()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Throws(InterruptedException::class)
    fun zero(printNumber: Runnable) {
        for (i in 1..n) {
            locks[2].acquire()
            printNumber.run()
            locks[i % 2].release()
        }
    }

    @Throws(InterruptedException::class)
    fun even(printNumber: Runnable) {
        for (i in 2..n step 2) {
            locks[0].acquire()
            printNumber.run()
            locks[2].release()
        }
    }

    @Throws(InterruptedException::class)
    fun odd(printNumber: Runnable) {
        for (i in 1..n step 2) {
            locks[1].acquire()
            printNumber.run()
            locks[2].release()
        }
    }
}
