package com.cache.implemtation;

import java.util.concurrent.TimeUnit;

public class CacheImplClient {

    public static void main(String[] args) throws InterruptedException {

        ClientThread clientThread = new ClientThread(new TTLCacheImpl<>(10));
        for (int i = 0; i < 3 ; i++) {
            Thread thread =  new Thread(clientThread);
            thread.start();

        }
        TimeUnit.SECONDS.sleep(2);
    }

}
