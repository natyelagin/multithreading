package com.jemma.multhithreading.reentrantlock;

import java.util.logging.Level;
import java.util.logging.Logger;


/**A test running class
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/17/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReentrantLockTest {

  ReentrantLock lock = new ReentrantLock ();
  
  /**Times the thread grabbed the lock*/
  private int count = 0;

  /**Outer function*/
  public void outer(){
      try {
          lock.lock();
          System.out.println("Locking in the outer function");
          count++;
          System.out.println("Count= "+count);
          //neec to lock the inner as well!
          inner();
      } catch (InterruptedException ex) {
          Logger.getLogger(ReentrantLockTest.class.getName()).log(Level.SEVERE, null, ex);
      }
      finally {    
          lock.unlock();
          System.out.println("Unlocking in the outer function");
      }
  }
  
  /**Inner function*/
  public synchronized void inner(){
      try {
          lock.lock();
          System.out.println("Locking in the inner function");
          count--;
          System.out.println("Count= "+count);
      } catch (InterruptedException ex) {
          Logger.getLogger(ReentrantLockTest.class.getName()).log(Level.SEVERE, null, ex);
      }
      finally {    
          lock.unlock();
          System.out.println("Unlocking in the inner function");
      }
  }    
}