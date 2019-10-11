package leetcode

import java.util.concurrent.BrokenBarrierException
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Semaphore

class H2O {
    private val locks = arrayOf(
        Semaphore(2),
        Semaphore(1)
    )
    private val barrier = CyclicBarrier(3) {
        locks[0].release(2)
        locks[1].release(1)
    }

    @Throws(InterruptedException::class)
    fun hydrogen(releaseHydrogen: Runnable) {
        locks[0].acquire()
        releaseHydrogen.run()
        try {
            barrier.await()
        } catch (e: BrokenBarrierException) {
            e.printStackTrace()
        }
    }

    @Throws(InterruptedException::class)
    fun oxygen(releaseOxygen: Runnable) {
        locks[1].acquire()
        releaseOxygen.run()
        try {
            barrier.await()
        } catch (e: BrokenBarrierException) {
            e.printStackTrace()
        }
    }
}
