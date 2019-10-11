package leetcode

import java.util.concurrent.Semaphore
import java.util.function.IntConsumer

class FizzBuzz(private val n: Int) {
    private var i: Int = 1
    private val locks = Array(4) { Semaphore(1) }

    init {
        try {
            repeat(3) {
                locks[it].acquire()
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun releaseOne() = when {
        i % 15 == 0 -> locks[2].release()
        i % 3 == 0 -> locks[0].release()
        i % 5 == 0 -> locks[1].release()
        else -> locks[3].release()
    }

    private fun releaseAll() {
        repeat(4) {
            locks[it].release()
        }
    }

    @Throws(InterruptedException::class)
    fun fizz(printFizz: Runnable) {
        while (true) {
            locks[0].acquire()
            if (i > n) {
                releaseAll()
                break
            }
            printFizz.run()
            i++
            releaseOne()
        }
    }

    @Throws(InterruptedException::class)
    fun buzz(printBuzz: Runnable) {
        while (true) {
            locks[1].acquire()
            if (i > n) {
                releaseAll()
                break
            }
            printBuzz.run()
            i++
            releaseOne()
        }
    }

    @Throws(InterruptedException::class)
    fun fizzbuzz(printFizzBuzz: Runnable) {
        while (true) {
            locks[2].acquire()
            if (i > n) {
                releaseAll()
                break
            }
            printFizzBuzz.run()
            i++
            releaseOne()
        }
    }

    @Throws(InterruptedException::class)
    fun number(printNumber: IntConsumer) {
        while (true) {
            locks[3].acquire()
            if (i > n) {
                releaseAll()
                break
            }
            printNumber.accept(i)
            i++
            releaseOne()
        }
    }
}