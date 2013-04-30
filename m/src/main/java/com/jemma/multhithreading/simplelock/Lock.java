package com.jemma.multhithreading.simplelock;

/**A Lock implementation
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/17/13
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Lock {
    /**Is the resource blocked?*/
    private boolean isLocked = false;
    
    /**
     * Lock the resource
     * @throws InterruptedException 
     */
    public synchronized void lock() throws InterruptedException{
        //a gentleman wait
        while(isLocked){
            wait();
        }
        //grab it!
        isLocked = true;
    }
    
    /**
     * Free the resource
     */
    public synchronized void unlock(){
        isLocked = false;
        //tell everybody
        notify();
    }

    /**
     * Try to lock the resource
     * @return success?
     */
    public synchronized boolean trylock(){
        if (!isLocked)
        {
            //grab resource!
            isLocked = true;
            return true;
        }
        //somebody was faster!
        else return false;
    }
}
