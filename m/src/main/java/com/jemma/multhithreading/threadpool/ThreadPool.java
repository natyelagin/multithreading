package com.jemma.multhithreading.threadpool;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/** A pool of working threads
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/17/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPool {
    /**Number of working threads in the Pool*/
    private final int nThreads;
    
    /**Array for keeping the threads*/
    private final PoolWorkingThread[] threads;
    
    /**Task queue*/
    private final LinkedList queue;
    
    /**Was the pool stopped?*/
    private boolean isStopped;
    
    /**
     * Pool constructor
     * @param numThreads number of threads in the pool
     */
    public ThreadPool (int numThreads) {
        isStopped = false;
        nThreads = numThreads;
        queue = new LinkedList();
        threads = new PoolWorkingThread[nThreads];
        System.out.println( "Starting ThreadPool work!" );
        System.out.println( "Number of threads: "+ nThreads);
        //starting all the working threads
        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorkingThread();
            threads[i].start();
        }
    }
    
    /**Execute some task
    * @param r the task
    */
    public void execute(Runnable r) {
        if(this.isStopped) 
            throw new IllegalStateException("ThreadPool is stopped");
        synchronized(queue) {
            queue.addLast(r);
            queue.notifyAll();
        }
    }
    
    /**Close the pool*/
    public synchronized void shutDown () {
        this.isStopped = true;
        for(PoolWorkingThread thread : threads){
            thread.stopThread();
        }
    }
    
    /**Private class: the working pool thread implementation*/
    private class PoolWorkingThread extends Thread {
        /**Is the thread to stop?*/
        private boolean finished = false;
        
        /**Interrupt the thread*/
        public synchronized void stopThread() {
            finished = true;
        }
        
        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized(queue) {
                    //No tasks yet
                    if (queue.isEmpty() && !finished) {
                        try {
                            //wait for tasks
                            queue.wait();
                        }
                        catch (InterruptedException ignored) {
                            System.out.println(ignored.getMessage());
                        }
                    } 
                    //All tasks done, may close now
                    if (queue.isEmpty() && finished) {
                        this.interrupt(); 
                        return;
                    }
                    //Run the tasks left
                    r = (Runnable) queue.removeFirst();
                }
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                    Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
