import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HelperClass {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();

    public void increment() {
        while (atomicInteger.get() < 101) {
            reentrantLock.lock();
            try {
                condition.signal();
                int res = atomicInteger.getAndIncrement(); // 1
                String threadName = "ИМЯ ПОТОКА " + Thread.currentThread().getName();
                System.out.println(threadName);
                System.out.println(res);
                if (res != 100) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (reentrantLock.isHeldByCurrentThread()) {
                    reentrantLock.unlock();
                }
            }
        }
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
}
