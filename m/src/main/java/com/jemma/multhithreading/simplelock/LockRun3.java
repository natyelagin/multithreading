package com.jemma.multhithreading.simplelock;

/**A test running class
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/17/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class LockRun3 extends Thread{
    LockTest test; 
    public LockRun3 (LockTest test1) {
        test = test1;
    }
    
    @Override
    public void run() {
        test.newMessage("Thread3 changed the message!");
        System.out.println("Current message: "+ test.getMessage());
    }
}