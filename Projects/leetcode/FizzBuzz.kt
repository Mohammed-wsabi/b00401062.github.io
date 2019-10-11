package leetcode

import java.util.concurrent.Semaphore
import java.util.function.IntConsumer

class FizzBuzz(private val n: Int) {
    private var i: Int = 1
    private val locks = Array(4) { Semaphore(1) }

    init {
        try {
            repeat(3) { locks[it].acquire() }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun release() = when {
        ++i > n -> repeat(4) { locks[it].release() }
        i % 15 == 0 -> locks[2].release()
        i % 3 == 0 -> locks[0].release()
        i % 5 == 0 -> locks[1].release()
        else -> locks[3].release()
    }

    @Throws(InterruptedException::class)
    fun fizz(printFizz: Runnable) {
        while (true) {
            locks[0].acquire()
            if (i > n) break
            printFizz.run()
            release()
        }
    }

    @Throws(InterruptedException::class)
    fun buzz(printBuzz: Runnable) {
        while (true) {
            locks[1].acquire()
            if (i > n) break
            printBuzz.run()
            release()
        }
    }

    @Throws(InterruptedException::class)
    fun fizzbuzz(printFizzBuzz: Runnable) {
        while (true) {
            locks[2].acquire()
            if (i > n) break
            printFizzBuzz.run()
            release()
        }
    }

    @Throws(InterruptedException::class)
    fun number(printNumber: IntConsumer) {
        while (true) {
            locks[3].acquire()
            if (i > n) break
            printNumber.accept(i)
            release()
        }
    }
}
