package com.cache.implemtation;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class ClientThread implements Runnable {

    private Logger LOG = Logger.getLogger(ClientThread.class.getName());
    private TTLCache<String, String> cache;

    public ClientThread(TTLCacheImpl<String, String> cache) {
        this.cache = cache;


    }

    @Override
    public void run() {

        try {

            for (int i = 0; i <  10; i++) {
                cache.put("key "+ i, "Value " + i);
            }
            TimeUnit.SECONDS.sleep(1);

            for (int i = 0; i < 5 ; i++) {
                print(i);
            }
            TimeUnit.SECONDS.sleep(5);

            for (int i = 5; i < 8; i++) {
                print(i);
            }
            TimeUnit.SECONDS.sleep(7);

            for (int i = 8; i < 10 ; i++) {
                print(i);

            }

        } catch (InterruptedException e) {
            LOG.severe("Error : " + e);
        }

    }
    private void print(int i) {
        LOG.info(Thread.currentThread().getName() + ": key: " + i + " = "
                + cache.get("key: " + i));
    }
}
