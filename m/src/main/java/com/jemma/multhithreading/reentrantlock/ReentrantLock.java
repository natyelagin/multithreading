package com.jemma.multhithreading.reentrantlock;

/**A reentrant lock implementation
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/17/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReentrantLock {
    /**Is the resource locked*/
    boolean isLocked = false;
    
    /**Who locked it*/
    Thread lockedBy = null;
    
    /**Times locked*/
    int lockedCount = 0;
    
    /**Lock the resource*/
    public synchronized void lock() throws InterruptedException {
        //who's locking
        Thread callingThread = Thread.currentThread();
        //wait for others to release lock
        while(isLocked && lockedBy != callingThread){
            wait();
        }
        //grab!
        isLocked = true;
        lockedCount++;
        //the Master!
        lockedBy = callingThread;
    }

    /**Try to lock*/
    public synchronized boolean trylock(){
        if (!isLocked)
        {
            //acquire resource and return true
            isLocked = true;
            Thread callingThread = Thread.currentThread();
            lockedCount++;
            lockedBy = callingThread;
            return true;
        }
        //...or fail
        else return false;
    }

    /**Unlock the resource*/
    public synchronized void unlock(){
        if(Thread.currentThread() == this.lockedBy){
            lockedCount--;
            if(lockedCount == 0){
                isLocked = false;
                notify();
            }
        }
    }
}
