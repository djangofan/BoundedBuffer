package qa.test;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBuffer {
	
   Lock lock;
   Condition notFull; 
   Condition notEmpty; 
   Object[] items;
   int putptr, takeptr, count;
   
   BoundedBuffer() {
	   lock = new ReentrantLock();
	   notFull  = lock.newCondition(); 
	   notEmpty = lock.newCondition(); 
	   items = new Object[100];
   }

   public void put(Object x) throws InterruptedException {
     lock.lock();
     try {
       while (count == items.length)
         notFull.await();
       items[putptr] = x;
       if (++putptr == items.length) putptr = 0;
       ++count;
       notEmpty.signal();
     } finally {
       lock.unlock();
     }
   }

   public Object take() throws InterruptedException {
     lock.lock();
     try {
       while (count == 0)
         notEmpty.await();
       Object x = items[takeptr];
       if (++takeptr == items.length) takeptr = 0;
       --count;
       notFull.signal();
       return x;
     } finally {
       lock.unlock();
     }
   }
   
 }
 
 