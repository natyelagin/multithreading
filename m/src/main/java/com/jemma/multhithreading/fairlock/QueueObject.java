package com.jemma.multhithreading.fairlock;

/**Some handmade queue object
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/17/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueueObject {
    /**Waken up*/
    private boolean isNotified = false;

    /**Wait until waken*/
    public synchronized void doWait() throws InterruptedException {
        while(!isNotified){
            this.wait();
        }
        this.isNotified = false;
    }

    /**Wake up*/
    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }
}
