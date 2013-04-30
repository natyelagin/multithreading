package com.jemma.multhithreading.simplelock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**A test running class
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/17/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class LockTest {
    /**Message contains*/
    private String message = null;
    private final Lock lock = new Lock();

    /**Set a message*/
    public void newMessage(String x) {
        try {
            lock.lock();
            message = x;
        } catch (InterruptedException ex) {
            Logger.getLogger(LockTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            lock.unlock();
        }
    }

    /**Clear the message*/
    public String getMessage() {
        try {
            lock.lock();
            String temp = message;
            message = null;
            return temp;
        } catch (InterruptedException ex) {
            Logger.getLogger(LockTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            lock.unlock();
        }
        return null;
    }
    
}
