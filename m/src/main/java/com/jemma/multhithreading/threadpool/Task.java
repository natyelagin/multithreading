package com.jemma.multhithreading.threadpool;

/**A Task implementation
 * Created with IntelliJ IDEA.
 * User: tata
 * Date: 4/23/13
 * Time: 9:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Task implements Runnable
{
    int k;
    public Task (int j)
    {
        k = j;
    }
   
    @Override
    public void run ()
    {
        System.out.print(k);
    }

}
