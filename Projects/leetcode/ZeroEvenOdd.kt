package leetcode

import java.util.concurrent.Semaphore
import java.util.function.IntConsumer

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
    fun zero(printNumber: IntConsumer) {
        for (i in 1..n) {
            locks[2].acquire()
            printNumber.accept(0)
            locks[i % 2].release()
        }
    }

    @Throws(InterruptedException::class)
    fun even(printNumber: IntConsumer) {
        for (i in 2..n step 2) {
            locks[0].acquire()
            printNumber.accept(i)
            locks[2].release()
        }
    }

    @Throws(InterruptedException::class)
    fun odd(printNumber: IntConsumer) {
        for (i in 1..n step 2) {
            locks[1].acquire()
            printNumber.accept(i)
            locks[2].release()
        }
    }
}
