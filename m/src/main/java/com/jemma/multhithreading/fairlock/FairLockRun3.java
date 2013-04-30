package com.jemma.multhithreading.fairlock;

/**
 * A test running class
 * User: tata
 * Date: 4/17/13
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class FairLockRun3 extends Thread {
    FairLockTest test;
    
    public FairLockRun3 (FairLockTest test1) {
        test = test1;
    }
    
    @Override
    public void run() {
        System.out.println("Thread 3 is starting the function");
        test.doSynchronized(3);
    }   
}
