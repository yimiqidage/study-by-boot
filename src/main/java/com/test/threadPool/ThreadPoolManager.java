package com.test.threadPool;


import java.util.*;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
    private int maxThread=0;
    
    public Vector vector;
   
    
    public int getMaxThread() {
        return maxThread;
        
    }

    public void setMaxThread(int threadCount)
    {
        
        this.maxThread = threadCount;       
    }
   
    public ThreadPoolManager(int threadCount)
    {
        this.setMaxThread(threadCount);
       
        System.out.println("Starting thread pool...");
       
        vector = new Vector();
        for(int i=1;i<=10;i++)
        {
            SimpleThread thread = new SimpleThread(i);
            vector.addElement(thread);
            thread.start();
        }
    }
   
    public void process(String argument)
    {
        int i;
        for(i = 0;i<vector.size();i++)
        {
            SimpleThread currentThread = (SimpleThread)vector.elementAt(i);           
            if(!currentThread.isRunning())
            {
                System.out.println("Thread "+(i+1)+" is processing:"+argument);
                currentThread.setArgument(argument);
                currentThread.setRunning(true);
                return;
            }
        }       
        if(i == vector.size())
        {
            System.out.println("pool is full,try in another time.");
        }       
    }
}

