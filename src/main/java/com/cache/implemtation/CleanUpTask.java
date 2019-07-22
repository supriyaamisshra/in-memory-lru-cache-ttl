package com.cache.implemtation;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
/**
 * A separate daemon thread “cleanUpThread” runs every "x" seconds
 * to remove cached items which have not been accessed for
 * more than TTL duration.
 * E.g. 10 seconds. This thread invokes the “cleanUp()” method.
 * */

public class CleanUpTask<K, V> implements Runnable {

    private static final Logger LOG = Logger.getLogger(CleanUpTask.class.getName());

    private final TTLCache<K,V> instance;


    public CleanUpTask(TTLCache<K, V> instance) {
        this.instance = instance;
    }



    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.SECONDS.sleep(1);
                instance.cleanUp();
            } catch (InterruptedException e) {
                LOG.severe("Error : " + e.getMessage() + ", caused by: " + e.getCause());
                Thread.currentThread().interrupt();
            }
        }

    }
}