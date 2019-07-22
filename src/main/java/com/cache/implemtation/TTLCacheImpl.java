package com.cache.implemtation;

/**
 * Implementation class of cache, where methods put, get, cleanUp are declared.
 *
 */
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TTLCacheImpl<K,V> implements TTLCache<K, V> {


    private final long ttlInSec;
    private final Map<K, CacheValueHolder<V>> cacheMap;


    /**
     * Constructor of class, which will initialise TTL time and
     * for map it will create an object of concurrent HashMap with an initial
     * capacity.
     * [ The ConcurrentHashMap is used as the backing data structure
     * with key “K” and value CacheValueHolder as the value.]
     *
     */
    public TTLCacheImpl(long ttlInSec) {

        this.ttlInSec = ttlInSec;
        this.cacheMap = new ConcurrentHashMap<>(100);
        initCache();

    }

    private void initCache() {
        Thread cleanUpThread = new Thread(new CleanUpTask<>(this));
        cleanUpThread.setDaemon(true);
        cleanUpThread.start();
    }

    private void remove(K key) {
        cacheMap.remove(key);
    }

    /**
     * put an entry
     */

    @Override
    public void put(K key, V value) {

        cacheMap.put(key, new CacheValueHolder<>(value));

    }
    /**
     * Access an entry
     */

    @Override
    public V get(K key) {
        CacheValueHolder<V> cacheValue = cacheMap.get(key);

        if (cacheValue != null) {
            cacheValue.setLastAccessedTimeStamp(LocalDateTime.now());
            return cacheValue.getValue();
        } else {
            return null;
        }

    }

    /**
     * Remove the elapsed keys from the map
     */

    @Override
    public void cleanUp() {


        Set<K> keySet = cacheMap.keySet();
        LocalDateTime now = LocalDateTime.now();

        for (K key : keySet) {
            CacheValueHolder<V> cacheValueHolder = cacheMap.get(key);

            synchronized (cacheMap) {
                if (cacheValueHolder != null) {
                    LocalDateTime lastAccessTs = cacheValueHolder.getLastAccessedTimeStamp();
                    if (lastAccessTs != null) {
                        long elapsedTime = ChronoUnit.SECONDS.between(lastAccessTs,now);
                        if (elapsedTime > this.ttlInSec) {
                            this.remove(key);
                            Thread.yield();
                        }
                    }

                }
            }

        }

    }


}
