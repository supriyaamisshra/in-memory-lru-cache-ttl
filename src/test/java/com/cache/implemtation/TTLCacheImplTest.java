package com.cache.implemtation;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


public class TTLCacheImplTest {

    private static final String KEY = "A";
    private static final String VALUE = "B";

    TTLCacheImpl<String,String> cache = null;

    @Before
    public void init() {
        cache = new TTLCacheImpl<>(1);
        }
    @Test
    public void testStoreAndRetrieve() {
        cache.put(KEY, VALUE);
        String value = cache.get(KEY);
        Assert.assertEquals(VALUE, value);
    }
    @Test
    public void testCleanUp() {
        cache.put(KEY, VALUE);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
        String value = cache.get(KEY);
        Assert.assertEquals(null, value);
    }



}




